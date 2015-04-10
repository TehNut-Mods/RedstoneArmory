package tehnut.redstonearmory.items;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cofh.core.util.crafting.RecipeUpgrade;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.redstonearsenal.item.RAItems;
import cofh.thermalexpansion.item.ItemCapacitor;
import cofh.thermalexpansion.item.TEItems;
import cofh.thermalfoundation.item.TFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import tehnut.redstonearmory.items.baubles.CapacitorType;
import tehnut.redstonearmory.items.baubles.ItemBaubleCapacitor;
import tehnut.redstonearmory.items.tools.ItemPotahoeFluxed;
import tehnut.redstonearmory.util.RecipeUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tehnut.redstonearmory.ConfigHandler;
import tterrag.core.common.transform.TTCorePlugin;

public class ItemRecipeRegistry {

    private static void registerShapedRecipes() {
        RecipeUtils.addStepUpRecipe(new ItemStack(ItemRegistry.materials, 1, 0), "nuggetGelidEnderium");
        RecipeUtils.addStepUpRecipe(new ItemStack(Items.iron_ingot), "nuggetIron");
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.materials, 1, 4), "  N", " N ", "N  ", 'N', "nuggetIron"));

        if (ConfigHandler.enableCapacitorBaubleCrafting) {
            GameRegistry.addRecipe(new ShapedOreRecipe(EnergyHelper.setDefaultEnergyTag(ItemBaubleCapacitor.getStackItem(CapacitorType.TUBEROUS), CapacitorType.TUBEROUS.capacity), " S ", "S S", " C ", 'S', "stringFluxed", 'C', EnergyHelper.setDefaultEnergyTag(new ItemStack(TEItems.itemCapacitor, 1, ItemCapacitor.Types.POTATO.ordinal()), ItemCapacitor.CAPACITY[ItemCapacitor.Types.POTATO.ordinal()])));

            for (int i = 2; i < CapacitorType.values().length; i++)
                GameRegistry.addRecipe(new RecipeUpgrade(7, ItemBaubleCapacitor.getStackItem(CapacitorType.values()[i]), new Object[]{" S ", "S S", " C ", 'S', "stringFluxed", 'C', new ItemStack(TEItems.itemCapacitor, 1, i)}));
        }

        if (ConfigHandler.enablePotahoeCrafting)
            GameRegistry.addRecipe(new ShapedOreRecipe(EnergyHelper.setDefaultEnergyTag(new ItemStack(ItemRegistry.potahoe), ItemPotahoeFluxed.capacity), "PC", " P", " P", 'P', Items.potato, 'C', TEItems.capacitorPotato));

        //Armor recipes
        if (ConfigHandler.enableEnderiumFluxArmorCrafting) {
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumHelm, 1), "PHP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, -1), 'H', RAItems.armorFluxHelmet);
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumChestplate, 1), "P P", "PCP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, -1), 'C', RAItems.armorFluxPlate);
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumLeggings, 1), "PLP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, -1), 'L', RAItems.armorFluxLegs);
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorEnderiumBoots, 1), "P P", "PBP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, -1), 'B', RAItems.armorFluxBoots);
        }
        if (!TTCorePlugin.runtimeDeobfEnabled && ConfigHandler.enableTestingEnviro && ItemRegistry.armorPowersuitChestplate != null) {
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPowersuitHelm, 1), "PPP", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 6));
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPowersuitChestplate, 1), "P P", "PPP", "PPP", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 6));
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPowersuitLeggings, 1), "PPP", "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 6));
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorPowersuitBoots, 1), "P P", "P P", 'P', new ItemStack(ItemRegistry.armorPlating, 1, 6));
        }
        if (ConfigHandler.enableLumiumArmorCrafting) {
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumHelm, 1), "PPP", "P P", 'P', TFItems.ingotLumium);
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumChestplate, 1), "P P", "PPP", "PPP", 'P', TFItems.ingotLumium);
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumLeggings, 1), "PPP", "P P", "P P", 'P', TFItems.ingotLumium);
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorLumiumBoots, 1), "P P", "P P", 'P', TFItems.ingotLumium);
        }
        if (ConfigHandler.enableTuberousArmorCrafting) {
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorTuberousHelm, 1), "PPP", "P P", 'P', new ItemStack(Items.potato));
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorTuberousChestplate, 1), "P P", "PPP", "PPP", 'P', new ItemStack(Items.potato));
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorTuberousLeggings, 1), "PPP", "P P", "P P", 'P', new ItemStack(Items.potato));
            GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.armorTuberousBoots, 1), "P P", "P P", 'P', new ItemStack(Items.potato));
        }

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
            GameRegistry.addRecipe(new ItemStack(ItemRegistry.axeGelidEnderium, 1, 0), "II ", "IT ", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemAxeFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0));
        if (ConfigHandler.enableGelidBattleWrenchCrafting)
            GameRegistry.addRecipe(new ItemStack(ItemRegistry.battleWrenchGelidEnderium, 1, 0), "I I", "ITI", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemBattleWrenchFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0));
        if (ConfigHandler.enableGelidPickaxeCrafting)
            GameRegistry.addRecipe(new ItemStack(ItemRegistry.pickaxeGelidEnderium, 1, 0), "III", " T ", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemPickaxeFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0));
        if (ConfigHandler.enableGelidShovelCrafting)
            GameRegistry.addRecipe(new ItemStack(ItemRegistry.shovelGelidEnderium, 1, 0), " I ", " T ", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemShovelFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0));
        if (ConfigHandler.enableGelidSickleCrafting)
            GameRegistry.addRecipe(new ItemStack(ItemRegistry.sickleGelidEnderium, 1, 0), " I ", "  I", "RT ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemSickleFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0));
        if (ConfigHandler.enableGelidSwordCrafting)
            GameRegistry.addRecipe(new ItemStack(ItemRegistry.swordGelidEnderium, 1, 0), " I ", " T ", " R ", 'R', new ItemStack(ItemRegistry.materials, 1, 3), 'T', RAItems.itemSwordFlux, 'I', new ItemStack(ItemRegistry.materials, 1, 0));

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