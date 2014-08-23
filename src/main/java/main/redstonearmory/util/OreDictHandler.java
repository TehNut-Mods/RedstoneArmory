package main.redstonearmory.util;

import main.redstonearmory.blocks.BlockRegistry;
import main.redstonearmory.items.ItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {

	//materials
    public static String ingotGelidEnderium = "ingotGelidEnderium";
    public static String nuggetGelidEnderium = "nuggetGelidEnderium";
	public static String gemGelidEnderium = "gemGelidEnderium";
	public static String rodGelidEnderium = "rodGelidEnderium";

	//blocks
	public static String blockGelidEnderium = "blockGelidEnderium";

    private static void registerItems() {
		OreDictionary.registerOre(ingotGelidEnderium, new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0));
		OreDictionary.registerOre(nuggetGelidEnderium, new ItemStack(ItemRegistry.materialGelidEnderium, 1, 1));
	    OreDictionary.registerOre(gemGelidEnderium, new ItemStack(ItemRegistry.materialGelidEnderium, 1, 2));
	    OreDictionary.registerOre(rodGelidEnderium, new ItemStack(ItemRegistry.materialGelidEnderium, 1, 3));
    }

	private static void registerBlocks() {
		OreDictionary.registerOre(blockGelidEnderium, new ItemStack(BlockRegistry.ingotStorage, 1, 0));
	}

    public static void registerFulloreDict() {
	    registerItems();
	    registerBlocks();
        OreDictionary.initVanillaEntries();
    }
}
