package main.redstonearmory.gui;

import main.redstonearmory.ConfigHandler;
import main.redstonearmory.util.TextHelper;
import net.minecraft.client.gui.GuiScreen;
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
		return TextHelper.localize("config.RArm.title");
	}
}