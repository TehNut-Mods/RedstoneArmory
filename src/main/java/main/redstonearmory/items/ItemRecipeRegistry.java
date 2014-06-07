package main.redstonearmory.items;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.blocks.BlockRegistry;
import net.minecraft.item.ItemStack;

public class ItemRecipeRegistry {

	private static void registerShapedRecipes() {
		//Nugget -> Ingot
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0),new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 1)});
		//Ingot -> Storage
		GameRegistry.addRecipe(new ItemStack(BlockRegistry.ingotStorage),new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0)});
	}

	private static void registerShaplessRecipes() {
		//Storage -> Ingot
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.materialGelidEnderium, 9, 0), new ItemStack(BlockRegistry.ingotStorage));
		//Ingot -> Nugget
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.materialGelidEnderium, 9, 1), new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0));

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
