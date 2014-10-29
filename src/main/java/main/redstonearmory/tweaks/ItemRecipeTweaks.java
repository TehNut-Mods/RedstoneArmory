package main.redstonearmory.tweaks;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.items.ItemRegistry;
import main.redstonearmory.util.TextHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class ItemRecipeTweaks {

	public static String[] unifyReason = {"Recipe changed to be unified", "with RArm armor recipes."};

	@RecipeRemoval
	public static void init() {
		if (ConfigHandler.overrideVanillaArmorRecipes) {
			// Remove vanilla recipes
			RedstoneArmory.logger.info(TextHelper.localize("info.RArm.console.recipes.remove") + ": " + TextHelper.localize("info.RArm.console.recipes.remove.vanilla.armor"));

			TweakingRegistry.markItemForRecipeRemoval(Items.leather_helmet, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.leather_chestplate, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.leather_leggings, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.leather_boots, -1, TweakingAction.CHANGED, unifyReason);

			TweakingRegistry.markItemForRecipeRemoval(Items.iron_helmet, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.iron_chestplate, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.iron_leggings, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.iron_boots, -1, TweakingAction.CHANGED, unifyReason);

			TweakingRegistry.markItemForRecipeRemoval(Items.chainmail_helmet, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.chainmail_chestplate, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.chainmail_leggings, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.chainmail_boots, -1, TweakingAction.CHANGED, unifyReason);

			TweakingRegistry.markItemForRecipeRemoval(Items.golden_helmet, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.golden_chestplate, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.golden_leggings, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.golden_boots, -1, TweakingAction.CHANGED, unifyReason);

			TweakingRegistry.markItemForRecipeRemoval(Items.diamond_helmet, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.diamond_chestplate, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.diamond_leggings, -1, TweakingAction.CHANGED, unifyReason);
			TweakingRegistry.markItemForRecipeRemoval(Items.diamond_boots, -1, TweakingAction.CHANGED, unifyReason);
		}
	}

	@RecipeAddition
	public static void addRecipes() {
		// Add unified armor recipes
		if (ConfigHandler.overrideVanillaArmorRecipes) {
			GameRegistry.addShapedRecipe(new ItemStack(Items.leather_helmet), new Object[]{"PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 0)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.leather_chestplate), new Object[]{"P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 0)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.leather_leggings), new Object[]{"PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 0)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.leather_boots), new Object[]{"P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 0)});

			GameRegistry.addShapedRecipe(new ItemStack(Items.iron_helmet), new Object[]{"PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 1)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.iron_chestplate), new Object[]{"P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 1)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.iron_leggings), new Object[]{"PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 1)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.iron_boots), new Object[]{"P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 1)});

			GameRegistry.addShapedRecipe(new ItemStack(Items.chainmail_helmet), new Object[]{"PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 2)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.chainmail_chestplate), new Object[]{"P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 2)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.chainmail_leggings), new Object[]{"PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 2)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.chainmail_boots), new Object[]{"P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 2)});

			GameRegistry.addShapedRecipe(new ItemStack(Items.golden_helmet), new Object[]{"PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 3)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.golden_chestplate), new Object[]{"P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 3)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.golden_leggings), new Object[]{"PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 3)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.golden_boots), new Object[]{"P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 3)});

			GameRegistry.addShapedRecipe(new ItemStack(Items.diamond_helmet), new Object[]{"PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 4)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.diamond_chestplate), new Object[]{"P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 4)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.diamond_leggings), new Object[]{"PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 4)});
			GameRegistry.addShapedRecipe(new ItemStack(Items.diamond_boots), new Object[]{"P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 4)});
		}
	}
}
