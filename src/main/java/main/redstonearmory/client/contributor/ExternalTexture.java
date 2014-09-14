package main.redstonearmory.client.contributor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.interfaces.IVersionChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.Level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

@SideOnly(Side.CLIENT)
public class ExternalTexture extends TextureAtlasSprite {

	// Oh look, BoP code!

	private static final AtomicInteger threadDownloadCounter = new AtomicInteger(0);
	private final File imageFile;
	private final String imageUrl;
	private final IVersionChecker versionChecker;
	private BufferedImage bufferedImage;
	private Thread imageThread;

	protected ExternalTexture(File imageFile, String imageUrl, IVersionChecker versionChecker) {
		super("RArm:" + FilenameUtils.getBaseName(imageUrl));

		this.imageFile = imageFile;
		this.imageUrl = imageUrl;
		this.versionChecker = versionChecker;

		if (!imageFile.exists() || versionChecker != null ? !versionChecker.isUpToDate(getBaseName()) : false) {
			this.downloadImage();
			RedstoneArmory.logger.log(Level.WARN, "Downloading image from: " + imageUrl + " to " + imageFile.getAbsolutePath() + ". Loading.");
		} else {
			try {
				this.bufferedImage = ImageIO.read(this.imageFile);
				RedstoneArmory.logger.log(Level.INFO, "Loaded existing image from: " + imageFile.getAbsolutePath() + ". Loading.");
				this.imageFile.delete();
			}
			catch (Exception exception) {
				RedstoneArmory.logger.log(Level.ERROR, "Could not read existing image from: " + imageFile.getAbsolutePath() + ". Attempting redownload.", exception);

				this.imageFile.delete();
				this.downloadImage();
				RedstoneArmory.logger.log(Level.WARN, "Downloading image from: " + imageUrl + " to " + imageFile.getAbsolutePath() + ". Loading.");
			}
		}
	}

	protected ExternalTexture(File imageFile, String imageUrl)
	{
		this(imageFile, imageUrl, null);
	}

	public void setBufferedImage(BufferedImage bufferedImage)
	{
		this.bufferedImage = bufferedImage;
	}

	public String getBaseName()
	{
		return FilenameUtils.getBaseName(this.imageUrl);
	}

	protected void downloadImage()
	{
		this.imageThread = new Thread("Texture Downloader #" + threadDownloadCounter.incrementAndGet())
		{
			public void run()
			{
				HttpURLConnection httpurlconnection = null;
				RedstoneArmory.logger.log(Level.DEBUG, "Downloading http texture from {} to {}", new Object[]{ExternalTexture.this.imageUrl, ExternalTexture.this.imageFile});

				try
				{
					httpurlconnection = (HttpURLConnection)(new URL(ExternalTexture.this.imageUrl)).openConnection(Minecraft.getMinecraft().getProxy());
					httpurlconnection.setDoInput(true);
					httpurlconnection.setDoOutput(false);
					httpurlconnection.connect();

					if (httpurlconnection.getResponseCode() / 100 == 2)
					{
						BufferedImage bufferedimage;

						if (ExternalTexture.this.imageFile != null)
						{
							FileUtils.copyInputStreamToFile(httpurlconnection.getInputStream(), ExternalTexture.this.imageFile);
							bufferedimage = ImageIO.read(ExternalTexture.this.imageFile);
						}
						else
						{
							bufferedimage = ImageIO.read(httpurlconnection.getInputStream());
						}

						ExternalTexture.this.setBufferedImage(bufferedimage);
						if (versionChecker != null) versionChecker.markUpToDate(ExternalTexture.this.getBaseName());
						return;
					}
				}
				catch (Exception exception)
				{
					RedstoneArmory.logger.log(Level.ERROR, "Couldn\'t download http texture", exception);
					return;
				}
				finally
				{
					if (httpurlconnection != null)
					{
						httpurlconnection.disconnect();
					}
				}
			}
		};
		this.imageThread.setDaemon(true);
		this.imageThread.start();
	}

	@Override
	public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location)
	{
		return this.bufferedImage != null;
	}

	@Override
	public boolean load(IResourceManager manager, ResourceLocation location)
	{
		int mipmapLevels = Minecraft.getMinecraft().gameSettings.mipmapLevels;

		BufferedImage[] image = new BufferedImage[1 + mipmapLevels];

		image[0] = this.bufferedImage;

		this.loadSprite(image, null, (float) Minecraft.getMinecraft().gameSettings.anisotropicFiltering > 1.0F);

		//For whatever reason, the call to this is inverted
		return true;
	}
}