package main.redstonearmory.util;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {

	public static String ingotGelidEnderium = "ingotGelidEnderium";
	public static String nuggetGelidEnderium = "nuggetGelidEnderium";

	private static void registerOreDict() {
//		OreDictionary.registerOre(ingotGelidEnderium, new ItemStack(ItemRegistry.ingotGelidEnderium));
//		OreDictionary.registerOre(nuggetGelidEnderium, new ItemStack(ItemRegistry.nuggetGelidEnderium));
	}

	public static void registerFulloreDict() {
		registerOreDict();
		OreDictionary.initVanillaEntries();
	}
}
