package main.redstonearmory.util;

import main.redstonearmory.ConfigHandler;
import main.redstonearmory.blocks.BlockRegistry;
import main.redstonearmory.items.ItemRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tterrag.core.common.OreDict;

public class OreDictHandler {

	//Oredict entries
	//items
	//materials
	public static String ingotGelidEnderium = "ingotGelidEnderium";
	public static String nuggetGelidEnderium = "nuggetGelidEnderium";
	public static String gemGelid = "gemGelid";
	public static String rodGelid = "rodGelid";
	public static String chainLink = "chainLink";
	public static String nuggetIron = "nuggetIron";
    public static String stringFluxed = "stringFluxed";
	public static String leather = "leather";

	//plating
	public static String platingEnderium = "platingEnderium";
	public static String platingLumium = "platingLumium";
	public static String platingMithril = "platingMithril";
	public static String platingBronze = "platingBronze";
	public static String platingTuberous = "platingTuberous";
	public static String platingPlatingEmpty = "platingCraftEmpty";
	public static String platingPlatingFull = "platingCraftFull";

	//blocks
	//storage
	public static String blockGelidEnderium = "blockGelidEnderium";
	public static String blockLapisPurple = "blockLapisPurple";

	public static void registerOreDict() {
		//items
		//materials
		OreDict.safeRegister(ingotGelidEnderium, new ItemStack(ItemRegistry.materials, 1, 0));
		OreDict.safeRegister(nuggetGelidEnderium, new ItemStack(ItemRegistry.materials, 1, 1));
		OreDict.safeRegister(gemGelid, new ItemStack(ItemRegistry.materials, 1, 2));
		OreDict.safeRegister(rodGelid, new ItemStack(ItemRegistry.materials, 1, 3));
		OreDict.safeRegister(chainLink, new ItemStack(ItemRegistry.materials, 1, 4));
		OreDict.safeRegister(nuggetIron, new ItemStack(ItemRegistry.materials, 1, 5));
		OreDict.safeRegister(stringFluxed, new ItemStack(ItemRegistry.materials, 1, 6));
		OreDict.safeRegister(leather, Items.leather);

		//plating
		OreDict.safeRegister(platingEnderium, new ItemStack(ItemRegistry.armorPlating, 1, 0));
		OreDict.safeRegister(platingLumium, new ItemStack(ItemRegistry.armorPlating, 1, 1));
		OreDict.safeRegister(platingMithril, new ItemStack(ItemRegistry.armorPlating, 1, 2));
		OreDict.safeRegister(platingBronze, new ItemStack(ItemRegistry.armorPlating, 1, 3));
		OreDict.safeRegister(platingTuberous, new ItemStack(ItemRegistry.armorPlating, 1, 4));
		OreDict.safeRegister(platingPlatingEmpty, new ItemStack(ItemRegistry.armorPlating, 1, 5));
		OreDict.safeRegister(platingPlatingFull, new ItemStack(ItemRegistry.armorPlating, 1, 6));

		//blocks
		//storage
		OreDict.safeRegister(blockGelidEnderium, new ItemStack(BlockRegistry.ingotStorage, 1, 0));
		if (ConfigHandler.addNutsToys) {
			OreDict.safeRegister(blockLapisPurple, new ItemStack(BlockRegistry.randomBlocks, 1, 0));
		}
	}
}