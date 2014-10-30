package main.redstonearmory.items;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.blocks.BlockRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import redstonearsenal.item.RAItems;
import thermalexpansion.item.TEItems;
import thermalfoundation.item.TFItems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@SuppressWarnings("all")
public class ItemRecipeRegistry {

	private static HashMap<Item, HashSet<Integer>> recipesToRemove = new HashMap<Item, HashSet<Integer>>();

	private static void registerShapedRecipes() {
		//Gelid Enderium Nugget -> Ingot
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.materials, 1, 0), new Object[]{"NNN", "NNN", "NNN", 'N', new ItemStack(ItemRegistry.materials, 1, 1)});
		GameRegistry.addShapedRecipe(new ItemStack(Items.iron_ingot), new Object[]{"NNN", "NNN", "NNN", 'N', new ItemStack(ItemRegistry.materials, 1, 5)});

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.materials, 1, 4), new Object[]{"  N", " N ", "N  ", 'N', "nuggetIron"}));

		//Armor recipes
		if (ConfigHandler.enableEnderiumFluxArmorCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumHelm, 1), new Object[]{"PHP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, -1), 'H', RAItems.armorFluxHelmet});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumChestplate, 1), new Object[]{"P P", "PCP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, -1), 'C', RAItems.armorFluxPlate});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumLeggings, 1), new Object[]{"PLP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, -1), 'L', RAItems.armorFluxLegs});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumBoots, 1), new Object[]{"P P", "PBP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, -1), 'B', RAItems.armorFluxBoots});
		}
		if (ConfigHandler.enablePowersuitCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPowersuitHelm, 1), new Object[]{"PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 6)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPowersuitChestplate, 1), new Object[]{"P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 6)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPowersuitLeggings, 1), new Object[]{"PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 6)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPowersuitBoots, 1), new Object[]{"P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 6)});
		}
		if (ConfigHandler.enableLumiumArmorCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumHelm, 1), new Object[]{"PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 1)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumChestplate, 1), new Object[]{"P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 1)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumLeggings, 1), new Object[]{"PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 1)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumBoots, 1), new Object[]{"P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 1)});
		}
		if (ConfigHandler.enableMithrilArmorCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorMithrilHelm, 1), new Object[]{"PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 2)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorMithrilChestplate, 1), new Object[]{"P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 2)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorMithrilLeggings, 1), new Object[]{"PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 2)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorMithrilBoots, 1), new Object[]{"P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 2)});
		}
		if (ConfigHandler.enableTinkersAlloyArmorCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorBronzeHelm, 1), new Object[]{"PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 3)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorBronzeChestplate, 1), new Object[]{"P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 3)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorBronzeLeggings, 1), new Object[]{"PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 3)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorBronzeBoots, 1), new Object[]{"P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 3)});
		}
		if (ConfigHandler.enableTuberousArmorCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorTuberousHelm, 1), new Object[]{"PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 4)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorTuberousChestplate, 1), new Object[]{"P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 4)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorTuberousLeggings, 1), new Object[]{"PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 4)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorTuberousBoots, 1), new Object[]{"P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 4)});
		}

		if (!ConfigHandler.overrideVanillaArmorRecipes) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.chainmail_helmet), new Object[]{"LLL", "L L", 'L', "chainLink"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.chainmail_chestplate), new Object[]{"L L", "LLL", "LLL", 'L', "chainLink"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.chainmail_leggings), new Object[]{"LLL", "L L", "L L", 'L', "chainLink"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.chainmail_boots), new Object[]{"L L", "L L", 'L', "chainLink"}));
		}

		if (ConfigHandler.overrideVanillaArmorRecipes) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 0), new Object[]{"NNN", "INI", "NNN", 'N', "stickWood", 'I', "leather"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 1), new Object[]{"NNN", "INI", "NNN", 'N', "nuggetIron", 'I', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 2), new Object[]{"NNN", "INI", "NNN", 'N', "nuggetIron", 'I', "chainLink"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 3), new Object[]{"NNN", "INI", "NNN", 'N', "nuggetIron", 'I', "ingotGold"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlatingVanilla, 1, 4), new Object[]{"NNN", "INI", "NNN", 'N', "nuggetIron", 'I', "gemDiamond"}));
		}
	}

	private static void registerShaplessRecipes() {
		//Gelid Block -> Ingot -> Nugget
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemRegistry.materials, 9, 0), new Object[]{new ItemStack(BlockRegistry.ingotStorage, 1, 0)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemRegistry.materials, 9, 1), new Object[]{new ItemStack(ItemRegistry.materials, 1, 0)}));

		//Iron Ingot -> Nugget
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemRegistry.materials, 9, 5), new Object[]{new ItemStack(Items.iron_ingot, 1, 0)}));
	}

	private static void registerMachineRecipes() {
		if (Loader.isModLoaded("ThermalExpansion")) {
			ThermalExpansionHelper.addTransposerFill(12000, RAItems.gemCrystalFlux, new ItemStack(ItemRegistry.materials, 1, 2), new FluidStack(FluidRegistry.getFluid("cryotheum"), 1000), false);
			ThermalExpansionHelper.addTransposerFill(12000, TFItems.ingotEnderium, new ItemStack(ItemRegistry.materials, 1, 0), new FluidStack(FluidRegistry.getFluid("cryotheum"), 1000), false);
			ThermalExpansionHelper.addTransposerFill(12000, new ItemStack(ItemRegistry.armorPlating, 1, 5), new ItemStack(ItemRegistry.armorPlating, 1, 6), new FluidStack(FluidRegistry.getFluid("mana"), 1000), false);
		} else {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.materials, 1, 0), new Object[]{"PSP", "SIS", "PSP", 'P', new ItemStack(Items.ender_pearl), 'S', new ItemStack(Blocks.snow), 'I', RAItems.ingotElectrumFlux}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.materials, 1, 2), new Object[]{"PSP", "SGS", "PSP", 'P', new ItemStack(Items.ender_pearl), 'S', new ItemStack(Blocks.ice), 'G', RAItems.gemCrystalFlux}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 6), new Object[]{TFItems.bucketMana, new ItemStack(ItemRegistry.armorPlating, 1, 5)}));
		}
	}

	private static void registerLateShapedRecipes() {

		//Gelid Rod
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.materials, 1, 3), new Object[]{"  G", " R ", "G  ", 'R', RAItems.rodObsidianFlux, 'G', new ItemStack(ItemRegistry.materials, 1, 2)});


		//Tool recipes
		if (ConfigHandler.enableGelidAxeCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.axeGelidEnderium, 1, 0), new Object[]{"II ", "IT ", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemAxeFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0)});
		}
		if (ConfigHandler.enableGelidBattleWrenchCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.battleWrenchGelidEnderium, 1, 0), new Object[]{"I I", "ITI", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemBattleWrenchFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0)});
		}
		if (ConfigHandler.enableGelidPickaxeCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.pickaxeGelidEnderium, 1, 0), new Object[]{"III", " T ", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemPickaxeFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0)});
		}
		if (ConfigHandler.enableGelidShovelCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.shovelGelidEnderium, 1, 0), new Object[]{" I ", " T ", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemShovelFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0)});
		}
		if (ConfigHandler.enableGelidSickleCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.sickleGelidEnderium, 1, 0), new Object[]{" I ", "  I", "RT ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemSickleFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0)});
		}
		if (ConfigHandler.enableGelidSwordCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.swordGelidEnderium, 1, 0), new Object[]{" I ", " T ", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemSwordFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0)});
		}

		//armor plating
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 0), new Object[]{"NNN", "GIG", "NNN", 'N', "nuggetEnderium", 'G', "gemCrystalFlux", 'I', "ingotEnderium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 1), new Object[]{"NNN", "GIG", "NNN", 'N', "nuggetLumium", 'G', "dustRedstone", 'I', "ingotLumium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 2), new Object[]{"NNN", "GIG", "NNN", 'N', "nuggetMithril", 'G', "dustRedstone", 'I', "ingotMithril"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 3), new Object[]{"NNN", "GIG", "NNN", 'N', "nuggetBronze", 'G', "dustRedstone", 'I', "ingotBronze"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 4), new Object[]{"PPP", "DCD", "PPP", 'P', new ItemStack(Items.potato), 'D', "dustRedstone", 'C', TEItems.capacitorPotato}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 5), new Object[]{"NNN", "GIG", "NNN", 'N', "nuggetIron", 'G', "dustRedstone", 'I', "ingotIron"}));

		//temporary mana bucket recipe until CoFH add one
		GameRegistry.addShapelessRecipe(TFItems.bucketMana, new Object[]{TFItems.bucketCryotheum, TFItems.bucketEnder, TFItems.bucketPyrotheum, TFItems.bucketRedstone, TFItems.bucketCoal, TFItems.bucketGlowstone});
	}

	public static void removeRecipe(ItemStack resultItem) {
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

		for (int i = 0; i < recipes.size(); i++) {

			IRecipe tmpRecipe = recipes.get(i);
			ItemStack recipeResult = tmpRecipe.getRecipeOutput();

			if (ItemStack.areItemStacksEqual(resultItem, recipeResult)) {
				recipes.remove(i--);
				RedstoneArmory.logger.info(resultItem.toString());
			}
		}
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