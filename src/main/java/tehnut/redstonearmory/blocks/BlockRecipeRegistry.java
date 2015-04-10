package tehnut.redstonearmory.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import tehnut.redstonearmory.util.RecipeUtils;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

@SuppressWarnings("all")
public class BlockRecipeRegistry {

    private static void registerShapedRecipes() {

        RecipeUtils.addStepUpRecipe(new ItemStack(BlockRegistry.ingotStorage, 1, 0), "ingotGelidEnderium");
        if (BlockRegistry.tinkerTable != null)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.tinkerTable), "PPP", "DCD", "PPP", 'P', "platingCraftFull", 'D', "gemDiamond", 'C', Blocks.crafting_table));
    }

    private static void registerShaplessRecipes() {
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BlockRegistry.randomBlocks, 1, 0), "dyePurple", "blockLapis"));
    }

    public static void registerBlockRecipes() {
        registerShapedRecipes();
        registerShaplessRecipes();
    }
}
