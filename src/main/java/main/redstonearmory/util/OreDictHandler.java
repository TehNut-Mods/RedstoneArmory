package main.redstonearmory.util;

import main.redstonearmory.blocks.BlockRegistry;
import main.redstonearmory.items.ItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {

	//Oredict entries
	//items
	//materials
	public static String ingotGelidEnderium = "ingotGelidEnderium";
	public static String nuggetGelidEnderium = "nuggetGelidEnderium";
	public static String gemGelid = "gemGelid";
	public static String rodGelid = "rodGelid";

	//plating
	public static String platingEnderium = "platingEnderium";
	public static String platingLumium = "platingLumium";
	public static String platingMithril = "platingMithril";
	public static String platingBronze = "platingBronze";
	public static String platingTuberous = "platingTuberous";
	public static String platingPlatingEmpty = "platingPlatingEmpty";
	public static String platingPlatingFull = "platingPlatingFull";

	//blocks
	//storage
	public static String blockGelidEnderium = "blockGelidEnderium";

	public static void registerOreDict() {
		//items
		//materials
		OreDictionary.registerOre(ingotGelidEnderium, new ItemStack(ItemRegistry.gelidMaterials, 1, 0));
		OreDictionary.registerOre(nuggetGelidEnderium, new ItemStack(ItemRegistry.gelidMaterials, 1, 1));
		OreDictionary.registerOre(gemGelid, new ItemStack(ItemRegistry.gelidMaterials, 1, 2));
		OreDictionary.registerOre(rodGelid, new ItemStack(ItemRegistry.gelidMaterials, 1, 3));

		//plating
		OreDictionary.registerOre(platingEnderium, new ItemStack(ItemRegistry.armorPlating, 1, 0));
		OreDictionary.registerOre(platingLumium, new ItemStack(ItemRegistry.armorPlating, 1, 1));
		OreDictionary.registerOre(platingMithril, new ItemStack(ItemRegistry.armorPlating, 1, 2));
		OreDictionary.registerOre(platingBronze, new ItemStack(ItemRegistry.armorPlating, 1, 3));
		OreDictionary.registerOre(platingTuberous, new ItemStack(ItemRegistry.armorPlating, 1, 4));
		OreDictionary.registerOre(platingPlatingEmpty, new ItemStack(ItemRegistry.armorPlating, 1, 5));
		OreDictionary.registerOre(platingPlatingFull, new ItemStack(ItemRegistry.armorPlating, 1, 6));

		//blocks
		//storage
		OreDictionary.registerOre(blockGelidEnderium, new ItemStack(BlockRegistry.ingotStorage, 1, 0));
	}
}