package tehnut.redstonearmory.client.gui;

import com.enderio.core.api.common.config.IConfigHandler;
import com.enderio.core.client.config.BaseConfigGui;
import net.minecraft.client.gui.GuiScreen;
import tehnut.redstonearmory.ConfigHandler;
import tehnut.redstonearmory.util.Utils;

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