package main.redstonearmory;

import main.redstonearmory.blocks.BlockInfo;
import main.redstonearmory.items.ItemInfo;
import net.minecraftforge.common.Configuration;

import java.io.File;

public class ConfigHandler {

	//Categories
	public static String itemId = "1- Item ID's";
	public static String blockId = "2- Block ID's";
	public static String general = "3 - General";

	//Options
	public static boolean enableEnderiumAxe;
	public static boolean enableEnderiumBattleWrench;
	public static boolean enableEnderiumPickaxe;
	public static boolean enableEnderiumShovel;
	public static boolean enableEnderiumSickle;
	public static boolean enableEnderiumSword;

	public static void registerConfig(File file) {
		Configuration config = new Configuration(file);

		config.load();

		//materials
		ItemInfo.MATERIAL_GELID_ENDERIUM_ID = config.getItem(itemId, ItemInfo.MATERIAL_GELID_ENDERIUM_KEY, ItemInfo.MATERIAL_GELID_ENDERIUM_DEFAULT).getInt() - 256;

		//tools
		ItemInfo.AXE_GELID_ENDERIUM_ID = config.getItem(itemId, ItemInfo.AXE_GELID_ENDERIUM_KEY, ItemInfo.AXE_GELID_ENDERIUM_DEFAULT).getInt() - 256;
		ItemInfo.BATTLEWRENCH_GELID_ENDERIUM_ID = config.getItem(itemId, ItemInfo.BATTLEWRENCH_GELID_ENDERIUM_KEY, ItemInfo.BATTLEWRENCH_GELID_ENDERIUM_DEFAULT).getInt() - 256;
		ItemInfo.PICKAXE_GELID_ENDERIUM_ID = config.getItem(itemId, ItemInfo.PICKAXE_GELID_ENDERIUM_KEY, ItemInfo.PICKAXE_GELID_ENDERIUM_DEFAULT).getInt() - 256;
		ItemInfo.SHOVEL_GELID_ENDERIUM_ID = config.getItem(itemId, ItemInfo.SHOVEL_GELID_ENDERIUM_KEY, ItemInfo.SHOVEL_GELID_ENDERIUM_DEFAULT).getInt() - 256;
		ItemInfo.SICKLE_GELID_ENDERIUM_ID = config.getItem(itemId, ItemInfo.SICKLE_GELID_ENDERIUM_KEY, ItemInfo.SICKLE_GELID_ENDERIUM_DEFAULT).getInt() - 256;
		ItemInfo.SWORD_GELID_ENDERIUM_ID = config.getItem(itemId, ItemInfo.SWORD_GELID_ENDERIUM_KEY, ItemInfo.SWORD_GELID_ENDERIUM_DEFAULT).getInt() - 256;

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
