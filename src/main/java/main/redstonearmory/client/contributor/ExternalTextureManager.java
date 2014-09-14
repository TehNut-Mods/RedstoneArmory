package main.redstonearmory.client.contributor;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import main.redstonearmory.util.interfaces.IVersionChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class ExternalTextureManager {

	// Oh look, BoP code!

	public static final String[] fileAssets = new String[] { "fileAssets", "field_110446_Y" };

	private static ExternalTextureManager instance;

	private final TextureManager textureManager;
	private final File rarmExternalFiles;

	private ExternalTextureManager() {
		this.textureManager = Minecraft.getMinecraft().renderEngine;
		this.rarmExternalFiles = new File((File) ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), fileAssets), "rarmexternal");
	}

	public ExternalTexture retrieveExternalTexture(String url, String type, IVersionChecker versionChecker) {
		File file1 = new File(this.rarmExternalFiles, type);
		File file2 = new File(file1, getBaseName(url));

		return new ExternalTexture(file2, url, versionChecker);
	}

	public ExternalTexture retrieveExternalTexture(String url, String type) {
		return this.retrieveExternalTexture(url, type, null);
	}

	private String getBaseName(String url) {
		return FilenameUtils.getBaseName(url);
	}

	public static ExternalTextureManager getInstance() {
		return instance == null ? instance = new ExternalTextureManager() : instance;
	}
}
