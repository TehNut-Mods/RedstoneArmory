package main.redstonearmory.compat;

import main.redstonearmory.ConfigHandler;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.TextHelper;
import tterrag.core.common.compat.ICompatability;

public class SimplyJetpacksCompat implements ICompatability {

	public static void load() {
		if (ConfigHandler.enableSimplyJetpacksCompat && ConfigHandler.enableTestingEnviro) {
			RedstoneArmory.logger.info(TextHelper.localize("info.RArm.console.compat.simplyjetpacks"));
			registerItems();
			registerRecipes();
		}
	}

	private static void registerItems() {

	}

	private static void registerRecipes() {

	}
}
