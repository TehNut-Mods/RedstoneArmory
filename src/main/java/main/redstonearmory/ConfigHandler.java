package main.redstonearmory;

import main.redstonearmory.blocks.BlockInfo;
import main.redstonearmory.items.ItemInfo;
import net.minecraftforge.common.Configuration;

import java.io.File;

/**
 * Created by Nick on 6/6/14.
 */

public class ConfigHandler {

	//Categories
	public static String itemId = "1- Item ID's";
	public static String blockId = "2- Block ID's";
	public static String general = "3 - General";

	//Options
	public static boolean enableEnderiumAxe = true;
	public static boolean enableEnderiumBattleWrench = true;
	public static boolean enableEnderiumPickaxe = true;
	public static boolean enableEnderiumShovel = true;
	public static boolean enableEnderiumSickle = true;
	public static boolean enableEnderiumSword = true;

	public static void registerConfig(File file) {
		Configuration config = new Configuration(file);

		config.load();

		//materials
		ItemInfo.INGOT_GELID_ENDERIUM_ID = config.getItem(itemId, ItemInfo.INGOT_GELID_ENDERIUM_KEY, ItemInfo.INGOT_GELID_ENDERIUM_DEFAULT).getInt() - 256;
		ItemInfo.NUGGET_GELID_ENDERIUM_ID = config.getItem(itemId, ItemInfo.NUGGET_GELID_ENDERIUM_KEY, ItemInfo.NUGGET_GELID_ENDERIUM_DEFAULT).getInt() - 256;
		ItemInfo.MATERIAL_GELID_ENDERIUM_ID = config.getItem(itemId, ItemInfo.MATERIAL_GELID_ENDERIUM_KEY, ItemInfo.MATERIAL_GELID_ENDERIUM_DEFAULT).getInt() - 256;

		//tools


		//blocks
		BlockInfo.INGOT_STORAGE_ID = config.getBlock(blockId, BlockInfo.INGOT_STORAGE_KEY, BlockInfo.INGOT_STORAGE_DEFAULT).getInt();

		//general
		enableEnderiumAxe = config.get(general, "enableEnderiumAxe", true, "Enable crafting of the Gelid Enderium Axe").getBoolean(true);
		enableEnderiumBattleWrench = config.get(general, "enableEnderiumBattleWrench", true, "Enable crafting of the Gelid Enderium BattleWrench").getBoolean(true);
		enableEnderiumPickaxe = config.get(general, "enableEnderiumPickaxe", true, "Enable crafting of the Gelid Enderium Pickaxe").getBoolean(true);
		enableEnderiumShovel = config.get(general, "enableEnderiumShovel", true, "Enable crafting of the Gelid Enderium Shovel").getBoolean(true);
		enableEnderiumSickle = config.get(general, "enableEnderiumSickle", true, "Enable crafting of the Gelid Enderium Sickle").getBoolean(true);
		enableEnderiumSword = config.get(general, "enableEnderiumSword", true, "Enable crafting of the Gelid Enderium Sword").getBoolean(true);

		if(config.hasChanged()) {
			config.save();
		}
	}

}
