package main.redstonearmory.items;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ModInformation;
import main.redstonearmory.items.tools.ItemGelidEnderiumAxe;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;

public class ItemRegistry {

	//tool materials
	public static EnumToolMaterial enderium = EnumHelper.addToolMaterial("ENDERIUM_TOOLMATERIAL", Integer.MAX_VALUE, 160000, 16.0F, 1.0F, 10);

	//materials
	public static Item materialGelidEnderium;

	//tools
	public static Item axeGelidEnderium;

	public static void registerItems() {

		//materials
		materialGelidEnderium = new ItemGelidEnderiumMaterials(ItemInfo.MATERIAL_GELID_ENDERIUM_ID).setUnlocalizedName(ModInformation.ID + ItemInfo.MATERIAL_GELID_ENDERIUM_UNLOCALIZED_NAME);
		GameRegistry.registerItem(materialGelidEnderium, "ItemGelidEnderiumMaterials");


		//tools
		axeGelidEnderium = new ItemGelidEnderiumAxe(ItemInfo.AXE_GELID_ENDERIUM_ID, enderium).setUnlocalizedName(ModInformation.ID + ItemInfo.AXE_GELID_ENDERIUM_UNLOCALIZED_NAME);
		GameRegistry.registerItem(axeGelidEnderium, ItemInfo.AXE_GELID_ENDERIUM_KEY);
	}

}
