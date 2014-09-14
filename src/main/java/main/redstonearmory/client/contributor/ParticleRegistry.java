package main.redstonearmory.client.contributor;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import net.minecraftforge.client.event.TextureStitchEvent;

import java.util.HashMap;

@SideOnly(Side.CLIENT)
public class ParticleRegistry {

	// Oh look, BoP code!

	private ExternalTextureManager externalTextureManager;

	public static HashMap<String, ExternalTexture> trailMap = new HashMap<String, ExternalTexture>();

	public ParticleRegistry() {
		this.externalTextureManager = ExternalTextureManager.getInstance();

		ParticleVersionChecker trailsVersionChecker = ParticleVersionChecker.getInstance();

		trailMap.put("dev_trail", this.externalTextureManager.retrieveExternalTexture(ModInformation.WEBASSETSDIR + "dev_trail.png", "trails", trailsVersionChecker));
		trailMap.put("contributor_trail", this.externalTextureManager.retrieveExternalTexture(ModInformation.WEBASSETSDIR + "contributor_trail.png", "trails", trailsVersionChecker));
	}

	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent event) {
		if (event.map.getTextureType() == 1) {
			event.map.setTextureEntry("rarm:dev_trail", trailMap.get("dev_trail"));
			event.map.setTextureEntry("rarm:contributor_trail", trailMap.get("contributor_trail"));
		}
	}
}
