package main.redstonearmory.util;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.RedstoneArmory;

import java.util.ArrayList;
import java.util.List;

public class EnviroChecks {

	public static boolean hasOptifine = false;

	public static void verifyEnviro() {
		List<String> modIds = new ArrayList<String>();

		if (FMLCommonHandler.instance().getSide() == Side.CLIENT && FMLClientHandler.instance().hasOptifine() || Loader.isModLoaded("optifine")) {
			if (ConfigHandler.enableEnviroCheckMessages)
				RedstoneArmory.logger.warn(TextHelper.localize("info.RArm.console.optifine"));
			hasOptifine = true;
			modIds.add("optifine");
		}
	}
}