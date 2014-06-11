package main.redstonearmory.items;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.items.tools.*;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;

public class ItemRegistry {

    //tool materials
    public static EnumToolMaterial enderium = EnumHelper.addToolMaterial("ENDERIUM_TOOLMATERIAL", Integer.MAX_VALUE, 320000, 16.0F, 1.0F, 10);

    //materials
    public static Item materialGelidEnderium;

    //tools
    public static Item axeGelidEnderium;
    public static Item battleWrenchGelidEnderium;
    public static Item pickaxeGelidEnderium;
    public static Item shovelGelidEnderium;
    public static Item sickleGelidEnderium;
    public static Item swordGelidEnderium;

    public static void registerItems() {

        //materials
        materialGelidEnderium = new ItemGelidEnderiumMaterials(ItemInfo.MATERIAL_GELID_ENDERIUM_ID).setUnlocalizedName(ModInformation.ID + ItemInfo.MATERIAL_GELID_ENDERIUM_UNLOCALIZED_NAME);
        GameRegistry.registerItem(materialGelidEnderium, "ItemGelidEnderiumMaterials");

//		//tools
        if (ConfigHandler.enableEnderiumAxe) {
            axeGelidEnderium = new ItemGelidEnderiumAxe(ItemInfo.AXE_GELID_ENDERIUM_ID, enderium, Integer.MAX_VALUE).setUnlocalizedName(ModInformation.ID + ItemInfo.AXE_GELID_ENDERIUM_UNLOCALIZED_NAME);
            GameRegistry.registerItem(axeGelidEnderium, ItemInfo.AXE_GELID_ENDERIUM_KEY);
        }
        if (ConfigHandler.enableEnderiumBattleWrench) {
            battleWrenchGelidEnderium = new ItemGelidEnderiumBattleWrench(ItemInfo.BATTLEWRENCH_GELID_ENDERIUM_ID, enderium).setUnlocalizedName(ModInformation.ID + ItemInfo.BATTLEWRENCH_GELID_ENDERIUM_UNLOCALIZED_NAME);
            GameRegistry.registerItem(battleWrenchGelidEnderium, ItemInfo.BATTLEWRENCH_GELID_ENDERIUM_KEY);
        }
        if (ConfigHandler.enableEnderiumPickaxe) {
            pickaxeGelidEnderium = new ItemGelidEnderiumPickaxe(ItemInfo.PICKAXE_GELID_ENDERIUM_ID, enderium, Integer.MAX_VALUE).setUnlocalizedName(ModInformation.ID + ItemInfo.PICKAXE_GELID_ENDERIUM_UNLOCALIZED_NAME);
            GameRegistry.registerItem(pickaxeGelidEnderium, ItemInfo.PICKAXE_GELID_ENDERIUM_KEY);
        }
        if (ConfigHandler.enableEnderiumShovel) {
            shovelGelidEnderium = new ItemGelidEnderiumShovel(ItemInfo.SHOVEL_GELID_ENDERIUM_ID, enderium, Integer.MAX_VALUE).setUnlocalizedName(ModInformation.ID + ItemInfo.SHOVEL_GELID_ENDERIUM_UNLOCALIZED_NAME);
            GameRegistry.registerItem(shovelGelidEnderium, ItemInfo.SHOVEL_GELID_ENDERIUM_KEY);
        }
        if (ConfigHandler.enableEnderiumSickle) {
            sickleGelidEnderium = new ItemGelidEnderiumSickle(ItemInfo.SICKLE_GELID_ENDERIUM_ID, enderium).setUnlocalizedName(ModInformation.ID + ItemInfo.SICKLE_GELID_ENDERIUM_UNLOCALIZED_NAME);
            GameRegistry.registerItem(sickleGelidEnderium, ItemInfo.SICKLE_GELID_ENDERIUM_KEY);
        }
        if (ConfigHandler.enableEnderiumSword) {
            swordGelidEnderium = new ItemGelidEnderiumSword(ItemInfo.SWORD_GELID_ENDERIUM_ID, enderium).setUnlocalizedName(ModInformation.ID + ItemInfo.SWORD_GELID_ENDERIUM_UNLOCALIZED_NAME);
            GameRegistry.registerItem(swordGelidEnderium, ItemInfo.SWORD_GELID_ENDERIUM_KEY);
        }
    }
}
