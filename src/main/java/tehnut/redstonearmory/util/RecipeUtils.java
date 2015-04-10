package tehnut.redstonearmory.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeUtils {

    /**
     * @param output - Output ItemStack of recipe.
     * @param input  - Input OreDict name.
     */
    public static void addStepUpRecipe(ItemStack output, String input) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "XXX", "XXX", "XXX", 'X', input));
    }

    /**
     * @param output - Output ItemStack of recipe.
     * @param input  - Input item.
     */
    public static void addStepUpRecipe(ItemStack output, Item input) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "XXX", "XXX", "XXX", 'X', input));
    }

    /**
     * @param output - Output ItemStack of recipe.
     * @param input  - Input ItemStack.
     */
    public static void addStepUpRecipe(ItemStack output, ItemStack input) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "XXX", "XXX", "XXX", 'X', input));
    }

    /**
     * @param output - Output ItemStack of recipe.
     * @param input  - Input OreDict name.
     */
    public static void addStepDownRecipe(ItemStack output, String input) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, input));
    }

    /**
     * @param output - Output ItemStack of recipe.
     * @param input  - Input item.
     */
    public static void addStepDownRecipe(ItemStack output, Item input) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, input));
    }

    /**
     * @param output - Output ItemStack of recipe.
     * @param input  - Input ItemStack.
     */
    public static void addStepDownRecipe(ItemStack output, ItemStack input) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, input));
    }
}
