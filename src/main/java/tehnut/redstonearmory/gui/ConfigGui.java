package tehnut.redstonearmory.gui;

import net.minecraft.client.gui.GuiScreen;
import tehnut.redstonearmory.ConfigHandler;
import tehnut.redstonearmory.util.Utils;
import tterrag.core.api.common.config.IConfigHandler;
import tterrag.core.client.config.BaseConfigGui;

public class ConfigGui extends BaseConfigGui {
    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen);
    }

    @Override
    protected IConfigHandler getConfigHandler() {
        return ConfigHandler.INSTANCE;
    }

    @Override
    protected String getTitle() {
        return Utils.localize("config.RArm.title");
    }
}