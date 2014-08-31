package main.redstonearmory.items;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.blocks.BlockRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import redstonearsenal.item.RAItems;
import thermalfoundation.item.TFItems;

@SuppressWarnings("all")
public class ItemRecipeRegistry {

	private static void registerShapedRecipes() {
		//Gelid Enderium Nuggest -> Ingot
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.gelidMaterials, 1, 0), new Object[] { "NNN", "NNN", "NNN", 'N', new ItemStack(ItemRegistry.gelidMaterials, 1, 1) });
	}

	private static void registerShaplessRecipes() {
		//Block -> Ingot -> Nugget
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.gelidMaterials, 9, 0), new Object[] { new ItemStack(BlockRegistry.ingotStorage, 0) });
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.gelidMaterials, 9, 1), new Object[] { new ItemStack(ItemRegistry.gelidMaterials, 0) });
	}

	private static void registerMachineRecipes() {
		ThermalExpansionHelper.addTransposerFill(12000, RAItems.gemCrystalFlux, new ItemStack(ItemRegistry.gelidMaterials, 1, 2), new FluidStack(FluidRegistry.getFluid("cryotheum"), 1000), false);
		ThermalExpansionHelper.addTransposerFill(12000, TFItems.ingotEnderium, new ItemStack(ItemRegistry.gelidMaterials, 1, 0), new FluidStack(FluidRegistry.getFluid("cryotheum"), 1000), false);
	}

	private static void registerLateShapedRecipes() {

		//Gelid Rod
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.gelidMaterials, 1, 3), new Object[]{"  G", " R ", "G  ", 'R', RAItems.rodObsidianFlux, 'G', new ItemStack(ItemRegistry.gelidMaterials, 1, 2)});

		if(ConfigHandler.enableMithrilScimitarCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.mithrilScimitar, 1), new Object[]{"  I", "SII", 'I', TFItems.ingotMithril, 'S', new ItemStack(Items.stick)});
		}

		//Tool recipes
		if(ConfigHandler.enableGelidAxeCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.axeGelidEnderium, 1, 0), new Object[] { "II ", "IT ", " R ", 'R', new ItemStack(ItemRegistry.gelidMaterials, 1, 3), 'T', RAItems.itemAxeFlux, 'I', new ItemStack(ItemRegistry.gelidMaterials, 1, 0)});
		}
		if(ConfigHandler.enableGelidBattleWrenchCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.battleWrenchGelidEnderium, 1, 0), new Object[] { "I I", "ITI", " R ", 'R', new ItemStack(ItemRegistry.gelidMaterials, 1, 3), 'T', RAItems.itemBattleWrenchFlux, 'I', new ItemStack(ItemRegistry.gelidMaterials, 1, 0)});
		}
		if(ConfigHandler.enableGelidPickaxeCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.pickaxeGelidEnderium, 1, 0), new Object[] { "III", " T ", " R ", 'R', new ItemStack(ItemRegistry.gelidMaterials, 1, 3), 'T', RAItems.itemPickaxeFlux, 'I', new ItemStack(ItemRegistry.gelidMaterials, 1, 0)});
		}
		if(ConfigHandler.enableGelidShovelCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.shovelGelidEnderium, 1, 0), new Object[] { " I ", " T ", " R ", 'R', new ItemStack(ItemRegistry.gelidMaterials, 1, 3), 'T', RAItems.itemShovelFlux, 'I', new ItemStack(ItemRegistry.gelidMaterials, 1, 0)});
		}
		if(ConfigHandler.enableGelidSickleCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.sickleGelidEnderium, 1, 0), new Object[] { " I ", "  I", "RT ", 'R', new ItemStack(ItemRegistry.gelidMaterials, 1, 3), 'T', RAItems.itemSickleFlux, 'I', new ItemStack(ItemRegistry.gelidMaterials, 1, 0)});
		}
		if(ConfigHandler.enableGelidSwordCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.swordGelidEnderium, 1, 0), new Object[] { " I ", " T ", " R ", 'R', new ItemStack(ItemRegistry.gelidMaterials, 1, 3), 'T', RAItems.itemSwordFlux, 'I', new ItemStack(ItemRegistry.gelidMaterials, 1, 0)});
		}

		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 0), new Object[] { "NNN", "GIG", "NNN", 'N', TFItems.nuggetEnderium, 'G', RAItems.gemCrystalFlux, 'I', TFItems.ingotEnderium});
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 1), new Object[] { "NNN", "GIG", "NNN", 'N', TFItems.nuggetLumium, 'G', new ItemStack(Items.redstone), 'I', TFItems.ingotLumium});
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 2), new Object[] { "NNN", "GIG", "NNN", 'N', TFItems.nuggetMithril, 'G', new ItemStack(Items.redstone), 'I', TFItems.ingotMithril});
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 3), new Object[] { "NNN", "GIG", "NNN", 'N', TFItems.nuggetBronze, 'G', new ItemStack(Items.redstone), 'I', TFItems.ingotBronze});

		//Armor recipes
		if(ConfigHandler.enableEnderiumFluxArmorCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumHelm, 1), new Object[]{"PHP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 0), 'H', RAItems.armorFluxHelmet});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumChestplate, 1), new Object[] { "P P", "PCP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 0), 'C', RAItems.armorFluxPlate});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumLeggings, 1), new Object[] { "PLP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 0), 'L', RAItems.armorFluxLegs});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumBoots, 1), new Object[] { "P P", "PBP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 0), 'B', RAItems.armorFluxBoots});
		}
		if(ConfigHandler.enableLumiumArmorCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumHelm, 1), new Object[]{"PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 1)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumChestplate, 1), new Object[] { "P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 1)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumLeggings, 1), new Object[] { "PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 1)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumBoots, 1), new Object[] { "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 1)});
		}
	}

	public static void registerItemRecipes() {
		registerShapedRecipes();
		registerShaplessRecipes();
	}

	public static void registerLateItemRecipes() {
		registerMachineRecipes();
		registerLateShapedRecipes();
	}
}
