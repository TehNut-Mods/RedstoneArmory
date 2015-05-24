package tehnut.redstonearmory.items;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cofh.core.util.crafting.RecipeUpgrade;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.redstonearsenal.item.RAItems;
import cofh.thermalexpansion.item.TEItems;
import cofh.thermalfoundation.item.TFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tehnut.redstonearmory.ConfigHandler;
import tehnut.redstonearmory.items.tools.ItemPotahoeFluxed;
import tehnut.redstonearmory.util.RecipeUtils;
import tterrag.core.common.transform.TTCorePlugin;

public class ItemRecipeRegistry {

    private static void registerShapedRecipes() {
        RecipeUtils.addStepUpRecipe(new ItemStack(ItemRegistry.materials, 1, 0), "nuggetGelidEnderium");
        RecipeUtils.addStepUpRecipe(new ItemStack(Items.iron_ingot), "nuggetIron");
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.materials, 1, 4), " NN", " N ", "NN ", 'N', "nuggetIron"));

        if (ConfigHandler.enablePotahoeCrafting)
            GameRegistry.addRecipe(new ShapedOreRecipe(EnergyHelper.setDefaultEnergyTag(new ItemStack(ItemRegistry.potahoe), ItemPotahoeFluxed.capacity), "PC", " P", " P", 'P', Items.potato, 'C', TEItems.capacitorPotato));

        //Armor recipes
        if (ConfigHandler.enableEnderiumFluxArmorCrafting) {
            GameRegistry.addRecipe(new RecipeUpgrade(1, new ItemStack(ItemRegistry.armorEnderiumHelm), new Object[]{"PHP", "P P", "   ", 'P', "platingEnderium", 'H', RAItems.armorFluxHelmet}));
            GameRegistry.addRecipe(new RecipeUpgrade(new ItemStack(ItemRegistry.armorEnderiumChestplate), new Object[]{"P P", "PCP", "PPP", 'P', "platingEnderium", 'C', RAItems.armorFluxPlate}));
            GameRegistry.addRecipe(new RecipeUpgrade(1, new ItemStack(ItemRegistry.armorEnderiumLeggings), new Object[]{"PLP", "P P", "P P", 'P', "platingEnderium", 'L', RAItems.armorFluxLegs}));
            GameRegistry.addRecipe(new RecipeUpgrade(7, new ItemStack(ItemRegistry.armorEnderiumBoots), new Object[]{"   ", "P P", "PBP", 'P', "platingEnderium", 'B', RAItems.armorFluxBoots}));
        }
        if (!TTCorePlugin.runtimeDeobfEnabled && ConfigHandler.enableTestingEnviro && ItemRegistry.armorPowersuitChestplate != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorPowersuitHelm, "PPP", "P P", 'P', "platingCraftFull"));
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorPowersuitChestplate, "P P", "PPP", "PPP", 'P', "platingCraftFull"));
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorPowersuitLeggings, "PPP", "P P", "P P", 'P', "platingCraftFull"));
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorPowersuitBoots, "P P", "P P", 'P', "platingCraftFull"));
        }
        if (ConfigHandler.enableLumiumArmorCrafting) {
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorLumiumHelm, "PPP", "P P", 'P', "ingotLumium"));
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorLumiumChestplate, "P P", "PPP", "PPP", 'P', "ingotLumium"));
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorLumiumLeggings, "PPP", "P P", "P P", 'P', "ingotLumium"));
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorLumiumBoots, "P P", "P P", 'P', "ingotLumium"));
        }
        if (ConfigHandler.enableTuberousArmorCrafting) {
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorTuberousHelm, "PPP", "P P", 'P', new ItemStack(Items.potato)));
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorTuberousChestplate, "P P", "PPP", "PPP", 'P', new ItemStack(Items.potato)));
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorTuberousLeggings, "PPP", "P P", "P P", 'P', new ItemStack(Items.potato)));
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorTuberousBoots, "P P", "P P", 'P', new ItemStack(Items.potato)));
        }

        if (ConfigHandler.enableRedstoneBootsCrafting)
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemRegistry.armorRedstoneBoots, "I", "B", 'I', Items.iron_boots, 'B', "blockRedstone"));

        // Add a recipe for Chainmail
        if (ConfigHandler.addCustomChainmailRecipe) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.chainmail_helmet), "LLL", "L L", 'L', "chainLink"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.chainmail_chestplate), "L L", "LLL", "LLL", 'L', "chainLink"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.chainmail_leggings), "LLL", "L L", "L L", 'L', "chainLink"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.chainmail_boots), "L L", "L L", 'L', "chainLink"));
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
        ThermalExpansionHelper.addTransposerFill(12000, RAItems.gemCrystalFlux, new ItemStack(ItemRegistry.materials, 1, 2), new FluidStack(FluidRegistry.getFluid("cryotheum"), 1000), false);
        ThermalExpansionHelper.addTransposerFill(12000, TFItems.ingotEnderium, new ItemStack(ItemRegistry.materials, 1, 0), new FluidStack(FluidRegistry.getFluid("cryotheum"), 1000), false);
        ThermalExpansionHelper.addTransposerFill(12000, new ItemStack(ItemRegistry.armorPlating, 1, 5), new ItemStack(ItemRegistry.armorPlating, 1, 6), new FluidStack(FluidRegistry.getFluid("mana"), 1000), false);
        ThermalExpansionHelper.addTransposerFill(6000, new ItemStack(Items.string), new ItemStack(ItemRegistry.materials, 1, 6), new FluidStack(FluidRegistry.getFluid("redstone"), 200), false);
    }

    private static void registerLateShapedRecipes() {

        //Gelid Rod
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.materials, 1, 3), "  G", " R ", "G  ", 'R', RAItems.rodObsidianFlux, 'G', new ItemStack(ItemRegistry.materials, 1, 2));

        //Tool recipes
        if (ConfigHandler.enableGelidAxeCrafting)
            GameRegistry.addRecipe(new RecipeUpgrade(new ItemStack(ItemRegistry.axeGelidEnderium), new Object[]{"II ", "IT ", " R ", 'R', "rodGelid", 'T', RAItems.itemAxeFlux, 'I', "ingotGelidEnderium"}));
        if (ConfigHandler.enableGelidBattleWrenchCrafting)
            GameRegistry.addRecipe(new RecipeUpgrade(new ItemStack(ItemRegistry.battleWrenchGelidEnderium), new Object[]{"I I", "ITI", " R ", 'R', "rodGelid", 'T', RAItems.itemBattleWrenchFlux, 'I', "ingotGelidEnderium"}));
        if (ConfigHandler.enableGelidPickaxeCrafting)
            GameRegistry.addRecipe(new RecipeUpgrade(new ItemStack(ItemRegistry.pickaxeGelidEnderium), new Object[]{"III", " T ", " R ", 'R', "rodGelid", 'T', RAItems.itemPickaxeFlux, 'I', "ingotGelidEnderium"}));
        if (ConfigHandler.enableGelidShovelCrafting)
            GameRegistry.addRecipe(new RecipeUpgrade(new ItemStack(ItemRegistry.shovelGelidEnderium), new Object[]{" I ", " T ", " R ", 'R', "rodGelid", 'T', RAItems.itemShovelFlux, 'I', "ingotGelidEnderium"}));
        if (ConfigHandler.enableGelidSickleCrafting)
            GameRegistry.addRecipe(new RecipeUpgrade(7, new ItemStack(ItemRegistry.sickleGelidEnderium), new Object[]{" I ", "  I", "RT ", 'R', "rodGelid", 'T', RAItems.itemSickleFlux, 'I', "ingotGelidEnderium"}));
        if (ConfigHandler.enableGelidSwordCrafting)
            GameRegistry.addRecipe(new RecipeUpgrade(new ItemStack(ItemRegistry.swordGelidEnderium), new Object[]{" I ", " T ", " R ", 'R', "rodGelid", 'T', RAItems.itemSwordFlux, 'I', "ingotGelidEnderium"}));

        //armor plating
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 0), "NNN", "GIG", "NNN", 'N', "nuggetEnderium", 'G', "gemCrystalFlux", 'I', "ingotEnderium"));
        if (!TTCorePlugin.runtimeDeobfEnabled && ConfigHandler.enableTestingEnviro && ItemRegistry.armorPowersuitChestplate != null)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.armorPlating, 1, 1), "NNN", "GIG", "NNN", 'N', "chainLink", 'G', "dustRedstone", 'I', "ingotIron"));

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