package main.redstonearmory.items;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.blocks.BlockRegistry;
import net.minecraft.item.ItemStack;

/**
 * Created by Nick on 6/6/14.
 */

public class ItemRecipeRegistry {

	private static void registerShapedRecipes() {

	}

	private static void registerShaplessRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ingotGelidEnderium, 9), new ItemStack(BlockRegistry.ingotStorage));
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.nuggetGelidEnderium, 9), new ItemStack(ItemRegistry.ingotGelidEnderium));
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ingotGelidEnderium), new ItemStack(ItemRegistry.nuggetGelidEnderium, 9));
	}

	private static void registerMachineRecipes() {

	}

	private static void registerSmeltingRecipes() {

	}

	public static void registerFullRecipes() {
		registerMachineRecipes();
		registerShapedRecipes();
		registerShaplessRecipes();
		registerSmeltingRecipes();
	}

}
