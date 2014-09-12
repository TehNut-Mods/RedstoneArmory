package main.redstonearmory.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

@SuppressWarnings("all")
public class BlockRecipeRegistry {

	private static void registerShapedRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.ingotStorage, 1, 0), new Object[]{"III", "III", "III", 'I', "ingotGelidEnderium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.tinkerTable), new Object[]{"PPP", "DCD", "PPP", 'P', "platingCraftFull", 'D', "gemDiamond", 'C', new ItemStack(Blocks.crafting_table)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.randomBlocks, 1, 0), new Object[]{" D ", "DLD", " D ", 'D', "dyePurple", 'L', "blockLapis"}));
	}

	private static void registerShaplessRecipes() {

	}

	public static void registerBlockRecipes() {
		registerShapedRecipes();
		registerShaplessRecipes();
	}
}
