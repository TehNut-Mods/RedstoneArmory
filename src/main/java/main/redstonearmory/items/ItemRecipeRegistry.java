package main.redstonearmory.items;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.blocks.BlockRegistry;
import net.minecraft.item.ItemStack;

public class ItemRecipeRegistry {

	private static void registerShapedRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.gelidMaterials, 1, 0), new Object[] { "NNN", "NNN", "NNN", 'N', new ItemStack(ItemRegistry.gelidMaterials, 1, 1) });
	}

	private static void registerShaplessRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.gelidMaterials, 9, 0), new Object[] { new ItemStack(BlockRegistry.ingotStorage, 0) });
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.gelidMaterials, 9, 1), new Object[] { new ItemStack(ItemRegistry.gelidMaterials, 0) });
	}

	public static void registerItemRecipes() {
		registerShapedRecipes();
		registerShaplessRecipes();
	}

}
