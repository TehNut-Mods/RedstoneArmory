package main.redstonearmory.items;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.util.EnviroChecks;
import main.redstonearmory.util.RecipeUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cofh.redstonearsenal.item.RAItems;
import thermalfoundation.item.TFItems;

import static main.redstonearmory.ConfigHandler.*;

@SuppressWarnings("all")
public class ItemRecipeRegistry {

	private static void registerShapedRecipes() {
		RecipeUtils.addStepUpRecipe(new ItemStack(ItemRegistry.materials, 1, 0), "nuggetGelidEnderium");
		RecipeUtils.addStepUpRecipe(new ItemStack(Items.iron_ingot), "nuggetIron");

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.materials, 1, 4), "  N", " N ", "N  ", 'N', "nuggetIron"));

		//Armor recipes
		if (enableEnderiumFluxArmorCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumHelm, 1), "PHP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, -1), 'H', RAItems.armorFluxHelmet);
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumChestplate, 1), "P P", "PCP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, -1), 'C', RAItems.armorFluxPlate);
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumLeggings, 1), "PLP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, -1), 'L', RAItems.armorFluxLegs);
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumBoots, 1), "P P", "PBP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, -1), 'B', RAItems.armorFluxBoots);
		}
		if (enablePowersuitCrafting && enableTestingEnviro && ItemRegistry.armorPowersuitChestplate != null) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPowersuitHelm, 1), "PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 6));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPowersuitChestplate, 1), "P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 6));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPowersuitLeggings, 1), "PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 6));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPowersuitBoots, 1), "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 6));
		}
		if (enableLumiumArmorCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumHelm, 1), "PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 1));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumChestplate, 1), "P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 1));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumLeggings, 1), "PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 1));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumBoots, 1), "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 1));
		}
		if (enableMithrilArmorCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorMithrilHelm, 1), "PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 2));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorMithrilChestplate, 1), "P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 2));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorMithrilLeggings, 1), "PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 2));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorMithrilBoots, 1), "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 2));
		}
		if (enableTinkersAlloyArmorCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorBronzeHelm, 1), "PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 3));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorBronzeChestplate, 1), "P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 3));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorBronzeLeggings, 1), "PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 3));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorBronzeBoots, 1), "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 3));
		}
		if (enableTuberousArmorCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorTuberousHelm, 1), "PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 4));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorTuberousChestplate, 1), "P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 4));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorTuberousLeggings, 1), "PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 4));
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorTuberousBoots, 1), "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 4));
		}

		// Add a recipe for Chainmail
		if (!overrideVanillaArmorRecipes && addCustomChainmailRecipe) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.chainmail_helmet), "LLL", "L L", 'L', "chainLink"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.chainmail_chestplate), "L L", "LLL", "LLL", 'L', "chainLink"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.chainmail_leggings), "LLL", "L L", "L L", 'L', "chainLink"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.chainmail_boots), "L L", "L L", 'L', "chainLink"));
		}

		if (overrideVanillaArmorRecipes) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 0), "NNN", "INI", "NNN", 'N', "stickWood", 'I', "leather"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 1), "NNN", "INI", "NNN", 'N', "nuggetIron", 'I', "ingotIron"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 2), "NNN", "INI", "NNN", 'N', "nuggetIron", 'I', "chainLink"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 3), "NNN", "INI", "NNN", 'N', "nuggetIron", 'I', "ingotGold"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 4), "NNN", "INI", "NNN", 'N', "nuggetIron", 'I', "gemDiamond"));
		}
	}

	private static void registerShaplessRecipes() {
		//Gelid Block -> Ingot -> Nugget
		RecipeUtils.addStepDownRecipe(new ItemStack(ItemRegistry.materials, 9, 0), "blockGelidEnderium");
		RecipeUtils.addStepDownRecipe(new ItemStack(ItemRegistry.materials, 9, 1), "ingotGelidEnderium");

		//Iron Ingot -> Nugget
		RecipeUtils.addStepDownRecipe(new ItemStack(ItemRegistry.materials, 9, 5), "ingotIron");
	}

	private static void registerMachineRecipes() {
		if (!EnviroChecks.isTELoaded()) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.materials, 1, 0), "PSP", "SIS", "PSP", 'P', new ItemStack(Items.ender_pearl), 'S', new ItemStack(Blocks.snow), 'I', RAItems.ingotElectrumFlux));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.materials, 1, 2), "PSP", "SGS", "PSP", 'P', new ItemStack(Items.ender_pearl), 'S', new ItemStack(Blocks.ice), 'G', RAItems.gemCrystalFlux));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 6), TFItems.bucketMana, new ItemStack(ItemRegistry.armorPlating, 1, 5)));
		}
	}

	private static void registerLateShapedRecipes() {

		//Gelid Rod
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.materials, 1, 3), "  G", " R ", "G  ", 'R', RAItems.rodObsidianFlux, 'G', new ItemStack(ItemRegistry.materials, 1, 2));


		//Tool recipes
		if (enableGelidAxeCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.axeGelidEnderium, 1, 0), "II ", "IT ", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemAxeFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0));
		}
		if (enableGelidBattleWrenchCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.battleWrenchGelidEnderium, 1, 0), "I I", "ITI", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemBattleWrenchFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0));
		}
		if (enableGelidPickaxeCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.pickaxeGelidEnderium, 1, 0), "III", " T ", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemPickaxeFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0));
		}
		if (enableGelidShovelCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.shovelGelidEnderium, 1, 0), " I ", " T ", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemShovelFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0));
		}
		if (enableGelidSickleCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.sickleGelidEnderium, 1, 0), " I ", "  I", "RT ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemSickleFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0));
		}
		if (enableGelidSwordCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.swordGelidEnderium, 1, 0), " I ", " T ", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemSwordFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0));
		}

		//armor plating
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 0), "NNN", "GIG", "NNN", 'N', "nuggetEnderium", 'G', "gemCrystalFlux", 'I', "ingotEnderium"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 1), "NNN", "GIG", "NNN", 'N', "nuggetLumium", 'G', "dustRedstone", 'I', "ingotLumium"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 2), "NNN", "GIG", "NNN", 'N', "nuggetMithril", 'G', "dustRedstone", 'I', "ingotMithril"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 3), "NNN", "GIG", "NNN", 'N', "nuggetBronze", 'G', "dustRedstone", 'I', "ingotBronze"));
		if(!EnviroChecks.isTELoaded())
      		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 4), "PPP", "DLD", "PPP", 'P', Items.potato, 'D', "dustRedstone", 'L', "ingotLead"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 5), "NNN", "GIG", "NNN", 'N', "nuggetIron", 'G', "dustRedstone", 'I', "ingotIron"));

		//temporary mana bucket recipe until CoFH add one
		GameRegistry.addShapelessRecipe(TFItems.bucketMana, TFItems.bucketCryotheum, TFItems.bucketEnder, TFItems.bucketPyrotheum, TFItems.bucketRedstone, TFItems.bucketCoal, TFItems.bucketGlowstone);
	}

	//Internal/vanilla only recipes go here
	public static void registerItemRecipes() {
		registerShapedRecipes();
		registerShaplessRecipes();
	}

	//Anything that uses TF, TE, or RArs goes here
	public static void registerLateItemRecipes() {
		registerMachineRecipes();
		registerLateShapedRecipes();
	}
}