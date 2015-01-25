package main.redstonearmory;

import tterrag.core.common.config.AbstractConfigHandler;

public class ConfigHandler extends AbstractConfigHandler {

	public static final ConfigHandler INSTANCE = new ConfigHandler();

	private ConfigHandler() {
		super(ModInformation.ID);
	}

	//sections to add to the config
	public static final String balances = "balances";
	public static final String crafting = "crafting";
	public static final String features = "features";
	public static final String general = "general";
//	public static final String compat = "compatability";

	//options in the config
	public static boolean enableGelidAxeCrafting = true;
	public static boolean enableGelidBattleWrenchCrafting = true;
	public static boolean enableGelidPickaxeCrafting = true;
	public static boolean enableGelidShovelCrafting = true;
	public static boolean enableGelidSickleCrafting = true;
	public static boolean enableGelidSwordCrafting = true;

	public static boolean enableEnderiumFluxArmorCrafting = true;
	public static boolean enablePowersuitCrafting = true;
	public static boolean enableLumiumArmorCrafting = true;
	public static boolean enableMithrilArmorCrafting = true;
	public static boolean enableTinkersAlloyArmorCrafting = true;
	public static boolean enableTuberousArmorCrafting = true;
	public static boolean overrideVanillaArmorRecipes = false;
	public static boolean addCustomChainmailRecipe = true;

	public static boolean enableTestingEnviro = false;

	public static boolean enableAxeWeatherClear = true;
	public static boolean enableAxeMultiBreak = true;
	public static boolean enableAxeLightning = true;
	public static boolean enablePickaxeEnderDislocation = true;
	public static boolean enableSwordSuckage = true;

	public static boolean enableEnviroCheckMessages = true;
	public static boolean addNutsToys = true;
	public static boolean enableDebugThingsAndStuff = false;

//	public static boolean enableSimplyJetpacksCompat = true;

	@Override
	protected void init() {
		addSection(balances, balances, "Balancing tweaks to fine tune the mod to how you want.");
		addSection(crafting, crafting, "Toggling of ability to craft items.");
		addSection(features, features, "Enabling and disabling of mod features.");
		addSection(general, general, "General category for other stuff.");
//		addSection(compat, compat, "Mod integration settings.");
	}

	@Override
	protected void reloadNonIngameConfigs() {

		activateSection(crafting);
		enableGelidAxeCrafting = getValue("enableGelidAxeCrafting", enableGelidAxeCrafting, RestartReqs.REQUIRES_MC_RESTART);
		enableGelidBattleWrenchCrafting = getValue("enableGelidBattleWrenchCrafting", enableGelidBattleWrenchCrafting, RestartReqs.REQUIRES_MC_RESTART);
		enableGelidPickaxeCrafting = getValue("enableGelidPickaxeCrafting", enableGelidPickaxeCrafting, RestartReqs.REQUIRES_MC_RESTART);
		enableGelidShovelCrafting = getValue("enableGelidShovelCrafting", enableGelidShovelCrafting, RestartReqs.REQUIRES_MC_RESTART);
		enableGelidSickleCrafting = getValue("enableGelidSickleCrafting", enableGelidSickleCrafting, RestartReqs.REQUIRES_MC_RESTART);
		enableGelidSwordCrafting = getValue("enableGelidSwordCrafting", enableGelidSwordCrafting, RestartReqs.REQUIRES_MC_RESTART);

		enableEnderiumFluxArmorCrafting = getValue("enableEnderiumFluxArmorCrafting", enableEnderiumFluxArmorCrafting, RestartReqs.REQUIRES_MC_RESTART);
		enablePowersuitCrafting = getValue("enablePowersuitCrafting", enablePowersuitCrafting, RestartReqs.REQUIRES_MC_RESTART);
		enableLumiumArmorCrafting = getValue("enableLumiumArmorCrafting", enableLumiumArmorCrafting, RestartReqs.REQUIRES_MC_RESTART);
		enableMithrilArmorCrafting = getValue("enableMithrilArmorCrafting", enableMithrilArmorCrafting, RestartReqs.REQUIRES_MC_RESTART);
		enableTinkersAlloyArmorCrafting = getValue("enableTinkersAlloyArmorCrafting", enableTinkersAlloyArmorCrafting, RestartReqs.REQUIRES_MC_RESTART);
		enableTuberousArmorCrafting = getValue("enableTuberousArmorCrafting", enableTuberousArmorCrafting, RestartReqs.REQUIRES_MC_RESTART);
		overrideVanillaArmorRecipes = getValue("overrideVanillaArmorRecipes", overrideVanillaArmorRecipes, RestartReqs.REQUIRES_MC_RESTART);
		addCustomChainmailRecipe = getValue("addCustomChainmailRecipe", "Adds a custom chainmail recipe using chainlink. Will nly be active if overrideVanillaArmorRecipes is false.", addCustomChainmailRecipe, RestartReqs.REQUIRES_MC_RESTART);

		activateSection(features);
		enableTestingEnviro = getValue("enableTestingEnviro", "This enables unfinished/broken features. For stable play, leave this disabled. Most of the things don't work, anyways.", enableTestingEnviro, RestartReqs.REQUIRES_MC_RESTART);

		enableAxeMultiBreak = getValue("enableAxeMultiBreak", "Determines whether to add an AOE effect to empowered axes. [DEFAULT - TRUE]", enableAxeMultiBreak, RestartReqs.NONE);
		enableAxeLightning = getValue("enableAxeLightning", "Determines whether to allow the axe to spawn lightning. [DEFAULT - TRUE]", enableAxeLightning, RestartReqs.NONE);
		enableAxeWeatherClear = getValue("enableAxeWeatherClear", "Determines whether to allow the axe to clear weather. [DEFAULT - TRUE]", enableAxeWeatherClear, RestartReqs.NONE);
		enablePickaxeEnderDislocation = getValue("enablePickaxeEnderDislocation", "Determines whether to allow the pickaxe to [DEFAULT - TRUE]", enablePickaxeEnderDislocation, RestartReqs.NONE);
		enableSwordSuckage = getValue("enableSwordSuckage", "Determines whether to add magnet mode while blocking with the sword. [DEFAULT - TRUE]", enableSwordSuckage, RestartReqs.NONE);

		activateSection(general);
		enableEnviroCheckMessages = getValue("enableEnviroCheckMessages", "Enable environment check console logging. [DEFAULT - TRUE]", enableEnviroCheckMessages, RestartReqs.REQUIRES_MC_RESTART);
		addNutsToys = getValue("addNutsToys", "Nut likes random things so Nut added random things. These won't change gameplay. [DEFAULT - TRUE]", addNutsToys, RestartReqs.REQUIRES_MC_RESTART);
		enableDebugThingsAndStuff = getValue("enableDebugThingsAndStuff", "You probably don't want to enable this...", enableDebugThingsAndStuff, RestartReqs.REQUIRES_MC_RESTART);

//		activateSection(compat);
//		enableSimplyJetpacksCompat = getValue("enableSimplyJetpacksCompat", "Adds SimplyJetpacks integration", enableSimplyJetpacksCompat, RestartReqs.REQUIRES_MC_RESTART);
	}

	@Override
	protected void reloadIngameConfigs() {
		// Do stuff
	}
}
