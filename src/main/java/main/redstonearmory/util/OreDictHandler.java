package main.redstonearmory.util;

import main.redstonearmory.items.ItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {

	//Oredict entries
	//items
	public static String ingotGelidEnderium = "ingotGelidEnderium";
	public static String nuggetGelidEnderium = "nuggetGelidEnderium";
	public static String gemGelid = "gemGelid";
	public static String rodGelid = "rodGelid";

	//blocks

	public static void registerOreDict() {
		OreDictionary.registerOre(ingotGelidEnderium, new ItemStack(ItemRegistry.gelidMaterials, 1, 0));
		OreDictionary.registerOre(nuggetGelidEnderium, new ItemStack(ItemRegistry.gelidMaterials, 1, 1));
		OreDictionary.registerOre(gemGelid, new ItemStack(ItemRegistry.gelidMaterials, 1, 2));
		OreDictionary.registerOre(rodGelid, new ItemStack(ItemRegistry.gelidMaterials, 1, 3));
	}
}