package main.redstonearmory.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.items.ItemRegistry;
import net.minecraft.item.ItemStack;

public class BlockRecipeRegistry {

	private static void registerShapedRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.ingotStorage, 1, 0), new Object[]{"III", "III", "III", 'I', new ItemStack(ItemRegistry.gelidMaterials, 0)});
	}

	private static void registerShaplessRecipes() {

	}

	public static void registerBlockRecipes() {
		registerShapedRecipes();
		registerShaplessRecipes();
	}
}
