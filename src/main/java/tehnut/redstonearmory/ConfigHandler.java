package tehnut.redstonearmory;

import com.enderio.core.common.config.AbstractConfigHandler;
import com.enderio.core.common.config.ConfigProcessor;
import com.enderio.core.common.config.annot.Comment;
import com.enderio.core.common.config.annot.Config;
import com.enderio.core.common.config.annot.NoSync;
import com.enderio.core.common.config.annot.RestartReq;

public class ConfigHandler extends AbstractConfigHandler {

    public static final ConfigHandler INSTANCE = new ConfigHandler();

    // Categories
    public static final String balances = "balances";
    public static final String crafting = "crafting";
    public static final String features = "features";
    public static final String general = "general";

    public static ConfigProcessor processor;

    // Settings
    @Config(crafting)
    @RestartReq(AbstractConfigHandler.RestartReqs.REQUIRES_MC_RESTART)
    public static boolean enableGelidAxeCrafting = true;
    @Config(crafting)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    public static boolean enableGelidBattleWrenchCrafting = true;
    @Config(crafting)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    public static boolean enableGelidPickaxeCrafting = true;
    @Config(crafting)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    public static boolean enableGelidShovelCrafting = true;
    @Config(crafting)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    public static boolean enableGelidSickleCrafting = true;
    @Config(crafting)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    public static boolean enableGelidSwordCrafting = true;
    @Config(crafting)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    public static boolean enableCapacitorBaubleCrafting = true;
    @Config(crafting)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    public static boolean enablePotahoeCrafting = true;
    @Config(crafting)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    public static boolean enableEnderiumFluxArmorCrafting = true;
    @Config(crafting)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    public static boolean enableLumiumArmorCrafting = true;
    @Config(crafting)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    public static boolean enableTuberousArmorCrafting = true;
    @Config(crafting)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    @Comment("Adds a custom chainmail recipe using chainlink.")
    public static boolean addCustomChainmailRecipe = false;
    @Config(crafting)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    public static boolean enableRedstoneBootsCrafting = true;

    @Config(features)
    @RestartReq(RestartReqs.NONE)
    @Comment("Determines whether to allow the axe to clear weather.")
    public static boolean enableAxeWeatherClear = true;
    @Config(features)
    @RestartReq(RestartReqs.NONE)
    @Comment("Determines whether to add an AOE effect to empowered axes.")
    public static boolean enableAxeMultiBreak = true;
    @Config(features)
    @RestartReq(RestartReqs.NONE)
    @Comment("Determines whether to allow the axe to spawn lightning.")
    public static boolean enableAxeLightning = true;
    @Config(features)
    @RestartReq(RestartReqs.NONE)
    @Comment("Determines whether to add magnet mode while blocking with the sword.")
    public static boolean enableSwordSuckage = true;

    @Config(general)
    @RestartReq(RestartReqs.NONE)
    @Comment("Enable environment check console logging.")
    @NoSync
    public static boolean enableConsoleLogging = true;
    @Config(general)
    @RestartReq(RestartReqs.REQUIRES_MC_RESTART)
    @Comment("Nut likes random things so Nut added random things. These won't change gameplay.")
    public static boolean addNutsToys = true;

    private ConfigHandler() {
        super(ModInformation.ID);
    }

    @Override
    protected void init() {
//		addSection(balances, balances, "Balancing tweaks to fine tune the mod to how you want.");
        addSection(crafting, crafting, "Toggling of ability to craft items.");
        addSection(features, features, "Enabling and disabling of mod features.");
        addSection(general, general, "General category for other stuff.");

        processor = new ConfigProcessor(getClass(), this);
        processor.process(true);
    }

    @Override
    protected void reloadNonIngameConfigs() {
        // Do stuff
    }

    @Override
    protected void reloadIngameConfigs() {
        // Do stuff
    }
}
