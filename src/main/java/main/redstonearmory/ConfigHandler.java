package main.redstonearmory;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	//sections to add to the config
	public static String general = "general";
	public static String balances = "balances";
	public static String features = "features";

	//options in the config
	public static boolean enableAxeWeatherClear;
	public static boolean enableAxeMultiBreak;
	public static boolean enableAxeLightning;
	public static boolean enablePickaxeEnderDislocation;
	public static boolean enableSwordSuckage;

	public static void init(Configuration config) {
		config.load();

		config.addCustomCategoryComment(general, "General category for other stuff. These should be synced between the server and client, but it is not required. Clients will be confused if it isn't.");
		config.addCustomCategoryComment(balances, "Balancing tweaks to fine tune the mod to how you want. These should be synced between the server and client, but it is not required. Clients will be confused if it isn't.");
		config.addCustomCategoryComment(features, "Enabling and Disabling of mod features. These should be synced between the server and client, but it is not required. Clients will be confused if it isn't.");

		enableAxeMultiBreak = config.get(features, "enableAxeMultiBreak", true, "Determines whether to add a multi-break effect to empowered axes. [DEFAULT - TRUE]").getBoolean(enableAxeMultiBreak);
		enableAxeLightning = config.get(features, "enableAxeLightning", true, "Determines whether to allow the axe to spawn lightning. [DEFAULT - TRUE]").getBoolean(enableAxeLightning);
		enableAxeWeatherClear = config.get(features, "enableAxeWeatherClear", true, "Determines whether to allow the axe to clear weather. [DEFAULT - TRUE]").getBoolean(enableAxeWeatherClear);
		enablePickaxeEnderDislocation = config.get(features, "enablePickaxeEnderDislocation", true, "Determines whether to allow the pickaxe to [DEFAULT - TRUE]").getBoolean(enablePickaxeEnderDislocation);
		enableSwordSuckage = config.get(features, "enableSwordSuckage", true, "Determines whether to add magnet mode while blocking with the sword. [DEFAULT - TRUE]").getBoolean(enableSwordSuckage);

		if(config.hasChanged()) {
			config.save();
		}
	}
}
