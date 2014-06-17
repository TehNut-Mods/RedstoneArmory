package main.redstonearmory.items;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.blocks.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redstonearsenal.item.RAItems;

public class ItemRecipeRegistry {

	private static void registerShapedRecipes() {
		//Nugget -> Ingot
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 1)});
		//Ingot -> Storage
		GameRegistry.addRecipe(new ItemStack(BlockRegistry.ingotStorage), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0)});
		//Gelid Rod
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.materialGelidEnderium, 1, 3), new Object[] { "  G", " R ", "G  ", 'R', RAItems.rodObsidianFlux, 'G', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 2)});

		if(!Loader.isModLoaded("ThermalExpansion")) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0), new Object[] { "PSP", "SIS", "PSP", 'P', new ItemStack(Item.enderPearl), 'S', new ItemStack(Block.ice), 'I', RAItems.ingotElectrumFlux});
		}

		if(ConfigHandler.enableEnderiumAxeCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.axeGelidEnderium, 1, 0), new Object[] { "II ", "IT ", " R ", 'R', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 3), 'T', RAItems.fluxAxe, 'I', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0)});
		}
		if(ConfigHandler.enableEnderiumBattleWrenchCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.battleWrenchGelidEnderium, 1, 0), new Object[] { "I I", "ITI", " R ", 'R', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 3), 'T', RAItems.fluxBattleWrench, 'I', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0)});
		}
		if(ConfigHandler.enableEnderiumPickaxeCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.pickaxeGelidEnderium, 1, 0), new Object[] { "III", " T ", " R ", 'R', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 3), 'T', RAItems.fluxPickaxe, 'I', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0)});
		}
		if(ConfigHandler.enableEnderiumShovelCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.shovelGelidEnderium, 1, 0), new Object[] { " I ", " T ", " R ", 'R', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 3), 'T',RAItems.fluxShovel, 'I', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0)});
		}
		if(ConfigHandler.enableEnderiumSickleCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.sickleGelidEnderium, 1, 0), new Object[] { " I ", "  I", "RT ", 'R', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 3), 'T',RAItems.fluxSickle, 'I', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0)});
		}
		if(ConfigHandler.enableEnderiumSwordCrafting) {
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.swordGelidEnderium, 1, 0), new Object[] { " I ", " T ", " R ", 'R', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 3), 'T', RAItems.fluxSword, 'I', new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0)});
		}
	}

	private static void registerShaplessRecipes() {
		//Storage -> Ingot
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.materialGelidEnderium, 9, 0), new ItemStack(BlockRegistry.ingotStorage));
		//Ingot -> Nugget
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.materialGelidEnderium, 9, 1), new ItemStack(ItemRegistry.materialGelidEnderium, 1, 0));

	}

	private static void registerMachineRecipes() {

	}

	private static void registerSmeltingRecipes() {

	}

	public static void registerFullRecipes() {
		registerMachineRecipes();
		registerShapedRecipes();
		registerShaplessRecipes();
		registerSmeltingRecipes();
	}

}
