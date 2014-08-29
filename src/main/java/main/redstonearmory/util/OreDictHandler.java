package main.redstonearmory.util;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {

	//Oredict entries
	//items
	public static String powderBlaze = "powderBlaze";

	//blocks

	public static void registerOreDict() {
		OreDictionary.registerOre(powderBlaze, new ItemStack(Items.blaze_powder, 1, 0));
	}
}