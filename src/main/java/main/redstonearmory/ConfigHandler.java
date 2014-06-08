package main.redstonearmory;

import main.redstonearmory.blocks.BlockInfo;
import main.redstonearmory.items.ItemInfo;
import net.minecraftforge.common.Configuration;

import java.io.File;

public class ConfigHandler {

	//Categories
	public static String id = "1- ID's";
	public static String general = "2 - General";

	//Options
	public static boolean enableEnderiumAxeCrafting;
	public static boolean enableEnderiumBattleWrenchCrafting;
	public static boolean enableEnderiumPickaxeCrafting;
	public static boolean enableEnderiumShovelCrafting;
	public static boolean enableEnderiumSickleCrafting;
	public static boolean enableEnderiumSwordCrafting;

	public static boolean enableEnderiumAxe;
	public static boolean enableEnderiumBattleWrench;
	public static boolean enableEnderiumPickaxe;
	public static boolean enableEnderiumShovel;
	public static boolean enableEnderiumSickle;
	public static boolean enableEnderiumSword;

	public static String empowerKey;
	public static boolean addItemLoreToItems;

	public static void registerConfig(File file) {
		Configuration config = new Configuration(file);

		config.load();

		//materials
		ItemInfo.MATERIAL_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.MATERIAL_GELID_ENDERIUM_KEY, ItemInfo.MATERIAL_GELID_ENDERIUM_DEFAULT).getInt() - 256;

		//tools
		ItemInfo.AXE_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.AXE_GELID_ENDERIUM_KEY, ItemInfo.AXE_GELID_ENDERIUM_DEFAULT).getInt() - 256;
		ItemInfo.BATTLEWRENCH_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.BATTLEWRENCH_GELID_ENDERIUM_KEY, ItemInfo.BATTLEWRENCH_GELID_ENDERIUM_DEFAULT).getInt() - 256;
		ItemInfo.PICKAXE_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.PICKAXE_GELID_ENDERIUM_KEY, ItemInfo.PICKAXE_GELID_ENDERIUM_DEFAULT).getInt() - 256;
		ItemInfo.SHOVEL_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.SHOVEL_GELID_ENDERIUM_KEY, ItemInfo.SHOVEL_GELID_ENDERIUM_DEFAULT).getInt() - 256;
		ItemInfo.SICKLE_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.SICKLE_GELID_ENDERIUM_KEY, ItemInfo.SICKLE_GELID_ENDERIUM_DEFAULT).getInt() - 256;
		ItemInfo.SWORD_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.SWORD_GELID_ENDERIUM_KEY, ItemInfo.SWORD_GELID_ENDERIUM_DEFAULT).getInt() - 256;

		//blocks
		BlockInfo.INGOT_STORAGE_ID = config.getBlock(id, BlockInfo.INGOT_STORAGE_KEY, BlockInfo.INGOT_STORAGE_DEFAULT).getInt();

		//general
		enableEnderiumAxe = config.get(general, "enableEnderiumAxe", true, "Enable registry of the Gelid Enderium Axe. False removes it from the game entirely.").getBoolean(true);
		enableEnderiumBattleWrench = config.get(general, "enableEnderiumBattleWrench", true, "Enable registry of the Gelid Enderium BattleWrench. False removes it from the game entirely.").getBoolean(true);
		enableEnderiumPickaxe = config.get(general, "enableEnderiumPickaxe", true, "Enable registry of the Gelid Enderium Pickaxe. False removes it from the game entirely.").getBoolean(true);
		enableEnderiumShovel = config.get(general, "enableEnderiumShovel", true, "Enable registry of the Gelid Enderium Shovel. False removes it from the game entirely.").getBoolean(true);
		enableEnderiumSickle = config.get(general, "enableEnderiumSickle", true, "Enable registry of the Gelid Enderium Sickle. False removes it from the game entirely.").getBoolean(true);
		enableEnderiumSword = config.get(general, "enableEnderiumSword", true, "Enable registry of the Gelid Enderium Sword. False removes it from the game entirely.").getBoolean(true);

		enableEnderiumAxeCrafting = config.get(general, "enableEnderiumAxeCrafting", true, "Enable crafting of the Gelid Enderium Axe").getBoolean(true);
		enableEnderiumBattleWrenchCrafting = config.get(general, "enableEnderiumBattleWrenchCrafting", true, "Enable crafting of the Gelid Enderium BattleWrench").getBoolean(true);
		enableEnderiumPickaxeCrafting = config.get(general, "enableEnderiumPickaxeCrafting", true, "Enable crafting of the Gelid Enderium Pickaxe").getBoolean(true);
		enableEnderiumShovelCrafting = config.get(general, "enableEnderiumShovelCrafting", true, "Enable crafting of the Gelid Enderium Shovel").getBoolean(true);
		enableEnderiumSickleCrafting = config.get(general, "enableEnderiumSickleCrafting", true, "Enable crafting of the Gelid Enderium Sickle").getBoolean(true);
		enableEnderiumSwordCrafting = config.get(general, "enableEnderiumSwordCrafting", true, "Enable crafting of the Gelid Enderium Sword").getBoolean(true);

		empowerKey = config.get(general, "empowerKey", "V", "Key to press for empowering. Key names can be found here: http://www.lwjgl.org/javadoc/org/lwjgl/input/Keyboard.html - Do not add the 'KEY_' part.").getString();
		addItemLoreToItems = config.get(general, "addItemLoreToItems", false, "Adds extra information to the item tooltip (when Control is held) that explains the tool ability in a sarcastic way.").getBoolean(false);

		if(config.hasChanged()) {
			config.save();
		}
	}
}
