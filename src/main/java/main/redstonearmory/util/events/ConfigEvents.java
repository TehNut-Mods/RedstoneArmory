package main.redstonearmory.util.events;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.TextHelper;
import tterrag.core.common.Handlers;
import tterrag.core.common.event.ConfigFileChangedEvent;

@Handlers.Handler
public class ConfigEvents {

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.modID.equals(ModInformation.ID)) {
			ConfigHandler.syncConfig();
			RedstoneArmory.logger.info(TextHelper.localize("info.RArm.console.config.refresh"));
		}
	}

	@SubscribeEvent
	public void onConfigFileChanged(ConfigFileChangedEvent event) {
		if (event.modID.equals(ModInformation.ID)) {
			ConfigHandler.loadConfig();
			ConfigHandler.syncConfig();
			RedstoneArmory.logger.info(TextHelper.localize("info.RArm.console.config.refresh"));
			event.setSuccessful();
		}
	}
}
