package main.redstonearmory;

import main.redstonearmory.blocks.BlockInfo;
import main.redstonearmory.items.ItemInfo;
import net.minecraftforge.common.Configuration;

import java.io.File;

public class ConfigHandler {

    //Categories
    public static String id = "1- ID's";
    public static String general = "2 - General";
    public static String abilities = "3 - Abilities";

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

    public static boolean addItemLoreToItems = false;

    //abilities
    public static boolean disableAxeLightning;
    public static boolean disableAxeWeatherClear;
    public static boolean disableAxeMultiBreak;
    public static boolean disableBattleWrenchSpin;
    public static boolean disablePickaxeEnderDislocation;
    public static boolean disableShovelMultiBreak;
    public static boolean disableShovelBonemeal;
    public static boolean disableSwordSuckage;

    public static void registerConfig(File file) {
        Configuration config = new Configuration(file);
        config.load();

        //materials
        ItemInfo.MATERIAL_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.MATERIAL_GELID_ENDERIUM_KEY, ItemInfo.MATERIAL_GELID_ENDERIUM_DEFAULT).getInt();
	    ItemInfo.MATERIAL_TESSERACT_ID = config.getItem(id, ItemInfo.MATERIAL_TESSERACT_KEY, ItemInfo.MATERIAL_TESSERACT_DEFAULT).getInt();

        //tools
        ItemInfo.AXE_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.AXE_GELID_ENDERIUM_KEY, ItemInfo.AXE_GELID_ENDERIUM_DEFAULT).getInt();
        ItemInfo.BATTLEWRENCH_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.BATTLEWRENCH_GELID_ENDERIUM_KEY, ItemInfo.BATTLEWRENCH_GELID_ENDERIUM_DEFAULT).getInt();
        ItemInfo.PICKAXE_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.PICKAXE_GELID_ENDERIUM_KEY, ItemInfo.PICKAXE_GELID_ENDERIUM_DEFAULT).getInt();
        ItemInfo.SHOVEL_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.SHOVEL_GELID_ENDERIUM_KEY, ItemInfo.SHOVEL_GELID_ENDERIUM_DEFAULT).getInt();
        ItemInfo.SICKLE_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.SICKLE_GELID_ENDERIUM_KEY, ItemInfo.SICKLE_GELID_ENDERIUM_DEFAULT).getInt();
        ItemInfo.SWORD_GELID_ENDERIUM_ID = config.getItem(id, ItemInfo.SWORD_GELID_ENDERIUM_KEY, ItemInfo.SWORD_GELID_ENDERIUM_DEFAULT).getInt();

	    //other items
	    ItemInfo.PORTABLE_TESSERACT_ID = config.getItem(id, ItemInfo.PORTABLE_TESSERACT_KEY, ItemInfo.PORTABLE_TESSERACT_DEFAULT).getInt();

        //blocks
        BlockInfo.INGOT_STORAGE_ID = config.getBlock(id, BlockInfo.INGOT_STORAGE_KEY, BlockInfo.INGOT_STORAGE_DEFAULT).getInt();
	    BlockInfo.COMP_DYNAMO_ID = config.getBlock(id, BlockInfo.COMP_DYNAMO_KEY, BlockInfo.COMP_DYNAMO_DEFAULT).getInt();

        //general
        enableEnderiumAxe = config.get(general, "enableEnderiumAxe", true, "Enable registry of the Gelid Enderium Axe. False removes it from the game entirely.").getBoolean(enableEnderiumAxe);
        enableEnderiumBattleWrench = config.get(general, "enableEnderiumBattleWrench", true, "Enable registry of the Gelid Enderium BattleWrench. False removes it from the game entirely.").getBoolean(enableEnderiumBattleWrench);
        enableEnderiumPickaxe = config.get(general, "enableEnderiumPickaxe", true, "Enable registry of the Gelid Enderium Pickaxe. False removes it from the game entirely.").getBoolean(enableEnderiumPickaxe);
        enableEnderiumShovel = config.get(general, "enableEnderiumShovel", true, "Enable registry of the Gelid Enderium Shovel. False removes it from the game entirely.").getBoolean(enableEnderiumShovel);
        enableEnderiumSickle = config.get(general, "enableEnderiumSickle", true, "Enable registry of the Gelid Enderium Sickle. False removes it from the game entirely.").getBoolean(enableEnderiumSickle);
        enableEnderiumSword = config.get(general, "enableEnderiumSword", true, "Enable registry of the Gelid Enderium Sword. False removes it from the game entirely.").getBoolean(enableEnderiumSword);

        enableEnderiumAxeCrafting = config.get(general, "enableEnderiumAxeCrafting", true, "Enable crafting of the Gelid Enderium Axe").getBoolean(enableEnderiumAxeCrafting);
        enableEnderiumBattleWrenchCrafting = config.get(general, "enableEnderiumBattleWrenchCrafting", true, "Enable crafting of the Gelid Enderium BattleWrench").getBoolean(enableEnderiumBattleWrenchCrafting);
        enableEnderiumPickaxeCrafting = config.get(general, "enableEnderiumPickaxeCrafting", true, "Enable crafting of the Gelid Enderium Pickaxe").getBoolean(enableEnderiumPickaxeCrafting);
        enableEnderiumShovelCrafting = config.get(general, "enableEnderiumShovelCrafting", true, "Enable crafting of the Gelid Enderium Shovel").getBoolean(enableEnderiumShovelCrafting);
        enableEnderiumSickleCrafting = config.get(general, "enableEnderiumSickleCrafting", true, "Enable crafting of the Gelid Enderium Sickle").getBoolean(enableEnderiumSickleCrafting);
        enableEnderiumSwordCrafting = config.get(general, "enableEnderiumSwordCrafting", true, "Enable crafting of the Gelid Enderium Sword").getBoolean(enableEnderiumSwordCrafting);

//		addItemLoreToItems = config.get(general, "addItemLoreToItems", false, "Adds extra information to the item tooltip (when Control is held) that explains the tool ability in a sarcastic way.").getBoolean(false);

        disableAxeLightning = config.get(abilities, "disableAxeLightning", false, "Disables a main feature of the axe - Summoning lightning on right clicking a block.").getBoolean(disableAxeLightning);
        disableAxeWeatherClear = config.get(abilities, "disableAxeWeatherClear", false, "Disables a main feature of the axe - Clearing weather when stormy/rainy.").getBoolean(disableAxeWeatherClear);
        disableAxeMultiBreak = config.get(abilities, "disableAxeMultiBreak", false, "Disables a main feature of the axe - Breaking a large area of wood materials.").getBoolean(disableAxeMultiBreak);
        disableBattleWrenchSpin = config.get(abilities, "disableBattleWrenchSpin", false, "Disables a main feature of the battle wrench - Spin attack").getBoolean(disableBattleWrenchSpin);
        disablePickaxeEnderDislocation = config.get(abilities, "disablePickaxeEnderDislocation", false, "Disables a main feature of the pickaxe - Moving a 3x3 of blocks instead of breaking them.").getBoolean(disablePickaxeEnderDislocation);
        disableShovelMultiBreak = config.get(abilities, "disableShovelMultiBreak", false, "Disables a main feature of the shovel - Breaking a large area of ground materials.").getBoolean(disableShovelMultiBreak);
        disableShovelBonemeal = config.get(abilities, "disableShovelBonemeal", false, "Disables a main feature of the shovel - Acting like bonemeal on right-click").getBoolean(disableShovelBonemeal);
        disableSwordSuckage = config.get(abilities, "disableSwordSuckage", false, "Disables a main feature of the sword - Magnet mode while blocking.").getBoolean(disableSwordSuckage);

        if (config.hasChanged()) {
            config.save();
        }
    }
}
