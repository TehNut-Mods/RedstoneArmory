package tehnut.redstonearmory.client.gui;

import com.enderio.core.common.config.BaseConfigFactory;
import net.minecraft.client.gui.GuiScreen;

public class ConfigGuiFactory extends BaseConfigFactory {

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return ConfigGui.class;
    }
}
