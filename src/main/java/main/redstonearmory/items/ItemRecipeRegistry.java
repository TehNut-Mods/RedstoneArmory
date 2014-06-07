package main.redstonearmory.items;

public class ItemRecipeRegistry {

	private static void registerShapedRecipes() {

	}

	private static void registerShaplessRecipes() {
//		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ingotGelidEnderium, 9), new ItemStack(BlockRegistry.ingotStorage));
//		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.nuggetGelidEnderium, 9), new ItemStack(ItemRegistry.ingotGelidEnderium));
//		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ingotGelidEnderium), new ItemStack(ItemRegistry.nuggetGelidEnderium, 9));
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
