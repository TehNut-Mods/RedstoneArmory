package main.redstonearmory.gui;

//Creates a config GUI for your mod. This requires a working mcmod.info file to work. These are dummy sections that don't do anything.

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import main.redstonearmory.ModInformation;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;

import java.util.ArrayList;
import java.util.List;

import static main.redstonearmory.ConfigHandler.*;

public class ConfigGui extends GuiConfig {

	public ConfigGui(GuiScreen parentScreen) {
		super(parentScreen, getConfigElements(parentScreen), ModInformation.ID, false, false, StatCollector.translateToLocal("gui." + ModInformation.ID + ".config.title"));
	}

	@SuppressWarnings("rawtypes")
	private static List<IConfigElement> getConfigElements(GuiScreen parent) {
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		// adds sections declared in ConfigHandler. toLowerCase() is used because the configuration class automatically does this, so must we.
		list.add(new ConfigElement<ConfigCategory>(config.getCategory(balances.toLowerCase())));
		list.add(new ConfigElement<ConfigCategory>(config.getCategory(crafting.toLowerCase())));
		list.add(new ConfigElement<ConfigCategory>(config.getCategory(features.toLowerCase())));
		list.add(new ConfigElement<ConfigCategory>(config.getCategory(general.toLowerCase())));

		return list;
	}
}