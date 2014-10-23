package main.redstonearmory;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

	public static Configuration config;

	//sections to add to the config
	public static String balances = "balances";
	public static String crafting = "crafting";
	public static String features = "features";
	public static String general = "general";

	//options in the config
	public static boolean enableGelidAxeCrafting;
	public static boolean enableGelidBattleWrenchCrafting;
	public static boolean enableGelidPickaxeCrafting;
	public static boolean enableGelidShovelCrafting;
	public static boolean enableGelidSickleCrafting;
	public static boolean enableGelidSwordCrafting;

	public static boolean enableEnderiumFluxArmorCrafting;
	public static boolean enablePowersuitCrafting;
	public static boolean enableLumiumArmorCrafting;
	public static boolean enableMithrilArmorCrafting;
	public static boolean enableTinkersAlloyArmorCrafting;
	public static boolean enableTuberousArmorCrafting;
	public static boolean overrideVanillaArmorRecipes;

	public static boolean enablePowersuit;

	public static boolean enableAxeWeatherClear;
	public static boolean enableAxeMultiBreak;
	public static boolean enableAxeLightning;
	public static boolean enablePickaxeEnderDislocation;
	public static boolean enableSwordSuckage;

	public static boolean enableEnviroCheckMessages;
	public static boolean addNutsToys;

	public static void init(File file) {
		config = new Configuration(file);

		loadConfig();
		syncConfig();
	}

	public static void loadConfig() {
		config.load();
	}

	public static void syncConfig() {

		config.addCustomCategoryComment(balances, "Balancing tweaks to fine tune the mod to how you want. These should be synced between the server and client, but it is not required. Clients will be confused if it isn't.");
		config.addCustomCategoryComment(crafting, "Toggling of ability to craft items. All default to true");
		config.addCustomCategoryComment(features, "Enabling and Disabling of mod features. These should be synced between the server and client, but it is not required. Clients will be confused if it isn't.");
		config.addCustomCategoryComment(general, "General category for other stuff. These should be synced between the server and client, but it is not required. Clients will be confused if it isn't.");

		enableGelidAxeCrafting = config.get(crafting, "enableGelidAxeCrafting", true).getBoolean(enableGelidAxeCrafting);
		enableGelidBattleWrenchCrafting = config.get(crafting, "enableGelidBattleWrenchCrafting", true).getBoolean(enableGelidBattleWrenchCrafting);
		enableGelidPickaxeCrafting = config.get(crafting, "enableGelidPickaxeCrafting", true).getBoolean(enableGelidPickaxeCrafting);
		enableGelidShovelCrafting = config.get(crafting, "enableGelidShovelCrafting", true).getBoolean(enableGelidShovelCrafting);
		enableGelidSickleCrafting = config.get(crafting, "enableGelidSickleCrafting", true).getBoolean(enableGelidSickleCrafting);
		enableGelidSwordCrafting = config.get(crafting, "enableGelidSwordCrafting", true).getBoolean(enableGelidSwordCrafting);

		enableEnderiumFluxArmorCrafting = config.get(crafting, "enableEnderiumFluxArmorCrafting", true).getBoolean(enableEnderiumFluxArmorCrafting);
		enablePowersuitCrafting = config.get(crafting, "enablePowersuitCrafting", true).getBoolean(enablePowersuitCrafting);
		enableLumiumArmorCrafting = config.get(crafting, "enableLumiumArmorCrafting", true).getBoolean(enableLumiumArmorCrafting);
		enableMithrilArmorCrafting = config.get(crafting, "enableMithrilArmorCrafting", true).getBoolean(enableMithrilArmorCrafting);
		enableTinkersAlloyArmorCrafting = config.get(crafting, "enableTinkersAlloyArmorCrafting", true).getBoolean(enableTinkersAlloyArmorCrafting);
		enableTuberousArmorCrafting = config.get(crafting, "enableTuberousArmorCrafting", true).getBoolean(enableTuberousArmorCrafting);
		overrideVanillaArmorRecipes = config.get(crafting, "overrideVanillaArmorRecipes", false, "Replaces ingots in Vanilla armor recipes with armor plating.").getBoolean(overrideVanillaArmorRecipes);

		enablePowersuit = config.get(features, "enablePowersuit", false, "Currently has a pretty major memory leak. I suggest not enabling this unless you know what you're doing.").getBoolean(enablePowersuit);

		enableAxeMultiBreak = config.get(features, "enableAxeMultiBreak", true, "Determines whether to add a multi-break effect to empowered axes. [DEFAULT - TRUE]").getBoolean(enableAxeMultiBreak);
		enableAxeLightning = config.get(features, "enableAxeLightning", true, "Determines whether to allow the axe to spawn lightning. [DEFAULT - TRUE]").getBoolean(enableAxeLightning);
		enableAxeWeatherClear = config.get(features, "enableAxeWeatherClear", true, "Determines whether to allow the axe to clear weather. [DEFAULT - TRUE]").getBoolean(enableAxeWeatherClear);
		enablePickaxeEnderDislocation = config.get(features, "enablePickaxeEnderDislocation", true, "Determines whether to allow the pickaxe to [DEFAULT - TRUE]").getBoolean(enablePickaxeEnderDislocation);
		enableSwordSuckage = config.get(features, "enableSwordSuckage", true, "Determines whether to add magnet mode while blocking with the sword. [DEFAULT - TRUE]").getBoolean(enableSwordSuckage);

		enableEnviroCheckMessages = config.get(general, "enableEnviroCheckMessages", true, "Enable environment check console logging. [DEFAULT - TRUE]").getBoolean(enableEnviroCheckMessages);
		addNutsToys = config.get(general, "addNutsToys", true, "Nut likes random things so Nut added random things. These won't change gameplay. [DEFAULT - TRUE]").getBoolean(addNutsToys);

		if(config.hasChanged()) {
			config.save();
		}
	}
}
