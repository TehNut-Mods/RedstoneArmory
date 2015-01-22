package main.redstonearmory.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeUtils {

    public static void addStepUpRecipe(ItemStack output, String input) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "XXX", "XXX", "XXX", 'X', input));
    }

    public static void addStepUpRecipe(ItemStack output, Item input) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "XXX", "XXX", "XXX", 'X', input));
    }

    public static void addStepUpRecipe(ItemStack output, ItemStack input) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "XXX", "XXX", "XXX", 'X', input));
    }
    
    public static void addStepDownRecipe(ItemStack output, String input) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, input));
    }

    public static void addStepDownRecipe(ItemStack output, Item input) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, input));
    }

    public static void addStepDownRecipe(ItemStack output, ItemStack input) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, input));
    }
}
