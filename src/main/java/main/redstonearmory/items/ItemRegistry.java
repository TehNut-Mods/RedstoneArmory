package main.redstonearmory.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by Nick on 6/6/14.
 */
public class ItemRegistry {

	//materials
	public static Item ingotGelidEnderium;
	public static Item nuggetGelidEnderium;
	public static Item gelidEnderiumMaterials;

	//tools


	public static void registerItems() {

		//materials
		ingotGelidEnderium = new ItemGelidEnderiumIngot(ItemInfo.INGOT_GELID_ENDERIUM_ID);
		GameRegistry.registerItem(ingotGelidEnderium, "ItemGelifiedEnderiumIngot");
		nuggetGelidEnderium = new ItemGelidEnderiumNugget(ItemInfo.NUGGET_GELID_ENDERIUM_ID);
		GameRegistry.registerItem(nuggetGelidEnderium, "ItemGelifiedEnderiumNugget");

		gelidEnderiumMaterials = new ItemGelidEnderiumMaterials(ItemInfo.MATERIAL_GELID_ENDERIUM_ID).setUnlocalizedName("itemGelidEnderiumMaterials");
		GameRegistry.registerItem(gelidEnderiumMaterials, "ItemGelidEnderiumMaterials");


		//tools

	}

}
