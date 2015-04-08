package main.redstonearmory.items;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.items.armor.*;
import main.redstonearmory.items.baubles.ItemBaubleCapacitor;
import main.redstonearmory.items.powersuit.ItemPowersuit;
import main.redstonearmory.items.random.ItemThrowableNut;
import main.redstonearmory.items.tools.gelidenderium.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;
import cofh.redstonearsenal.item.RAItems;
import tterrag.core.common.transform.TTCorePlugin;

public class ItemRegistry {

	// Armor Materials
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_LUMIUM = EnumHelper.addArmorMaterial("RA_LUMIUM", 10, new int[]{2, 5, 3, 2}, 6);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_TUBEROUS = EnumHelper.addArmorMaterial("RA_TUBEROUS", 10, new int[]{1, 2, 1, 1}, 6);

	// Tool Materials
	public static final ItemTool.ToolMaterial TOOL_MATERIAL_GELID_ENDERIUM = EnumHelper.addToolMaterial("RA_GELID_ENDERIUM", 15, Integer.MAX_VALUE, 20, 10, 15);

	// Items
	public static Item materials;
	public static Item armorPlating;

	public static Item nutThrowable;

    public static Item capacitorBauble;

	public static Item axeGelidEnderium;
	public static Item battleWrenchGelidEnderium;
	public static Item pickaxeGelidEnderium;
	public static Item shovelGelidEnderium;
	public static Item sickleGelidEnderium;
	public static Item swordGelidEnderium;

	public static Item armorEnderiumHelm;
	public static Item armorEnderiumChestplate;
	public static Item armorEnderiumLeggings;
	public static Item armorEnderiumBoots;

	public static Item armorPowersuitHelm;
	public static Item armorPowersuitChestplate;
	public static Item armorPowersuitLeggings;
	public static Item armorPowersuitBoots;

	public static Item armorLumiumHelm;
	public static Item armorLumiumChestplate;
	public static Item armorLumiumLeggings;
	public static Item armorLumiumBoots;

	public static Item armorTuberousHelm;
	public static Item armorTuberousChestplate;
	public static Item armorTuberousLeggings;
	public static Item armorTuberousBoots;

	private static void registerItems() {

		materials = new ItemMaterials().setUnlocalizedName(ModInformation.ID);
		GameRegistry.registerItem(materials, "ItemMaterials");
		armorPlating = new ItemArmorPlating().setUnlocalizedName(ModInformation.ID);
		GameRegistry.registerItem(armorPlating, "ItemArmorPlating");

		if (ConfigHandler.addNutsToys) {
			nutThrowable = new ItemThrowableNut();
			GameRegistry.registerItem(nutThrowable, "ItemThrowableNut");
		}

        capacitorBauble = new ItemBaubleCapacitor();
        GameRegistry.registerItem(capacitorBauble, "ItemBaubleCapacitor");

		axeGelidEnderium = new ItemAxeGelidEnderium(TOOL_MATERIAL_GELID_ENDERIUM);
		GameRegistry.registerItem(axeGelidEnderium, "ItemAxeGelidEnderium");
		battleWrenchGelidEnderium = new ItemBattleWrenchGelidEnderium(TOOL_MATERIAL_GELID_ENDERIUM);
		GameRegistry.registerItem(battleWrenchGelidEnderium, "ItemBattleWrenchGelidEnderium");
		pickaxeGelidEnderium = new ItemPickaxeGelidEnderium(TOOL_MATERIAL_GELID_ENDERIUM);
		GameRegistry.registerItem(pickaxeGelidEnderium, "ItemPickaxeGelidEnderium");
		shovelGelidEnderium = new ItemShovelGelidEnderium(TOOL_MATERIAL_GELID_ENDERIUM);
		GameRegistry.registerItem(shovelGelidEnderium, "ItemShovelGelidEnderium");
		sickleGelidEnderium = new ItemSickleGelidEnderium(TOOL_MATERIAL_GELID_ENDERIUM);
		GameRegistry.registerItem(sickleGelidEnderium, "ItemSickleGelidEnderium");
		swordGelidEnderium = new ItemSwordGelidEnderium(TOOL_MATERIAL_GELID_ENDERIUM);
		GameRegistry.registerItem(swordGelidEnderium, "ItemSwordGelidEnderium");

		armorEnderiumHelm = (ItemEnderiumArmor) new ItemEnderiumArmor(RAItems.ARMOR_MATERIAL_FLUX, 0);
		GameRegistry.registerItem(armorEnderiumHelm, "ItemEnderiumArmor.Helm");
		armorEnderiumChestplate = (ItemEnderiumArmor) new ItemEnderiumArmor(RAItems.ARMOR_MATERIAL_FLUX, 1);
		GameRegistry.registerItem(armorEnderiumChestplate, "ItemEnderiumArmor.Chestplate");
		armorEnderiumLeggings = (ItemEnderiumArmor) new ItemEnderiumArmor(RAItems.ARMOR_MATERIAL_FLUX, 2);
		GameRegistry.registerItem(armorEnderiumLeggings, "ItemEnderiumArmor.Leggings");
		armorEnderiumBoots = (ItemEnderiumArmor) new ItemEnderiumArmor(RAItems.ARMOR_MATERIAL_FLUX, 3);
		GameRegistry.registerItem(armorEnderiumBoots, "ItemEnderiumArmor.Boots");

		if (!TTCorePlugin.runtimeDeobfEnabled) {
			armorPowersuitHelm = (ItemPowersuit) new ItemPowersuit(ItemArmor.ArmorMaterial.CHAIN, 0);
			GameRegistry.registerItem(armorPowersuitHelm, "ItemPowersuit.Helm");
			armorPowersuitChestplate = (ItemPowersuit) new ItemPowersuit(ItemArmor.ArmorMaterial.CHAIN, 1);
			GameRegistry.registerItem(armorPowersuitChestplate, "ItemPowersuit.Chestplate");
			armorPowersuitLeggings = (ItemPowersuit) new ItemPowersuit(ItemArmor.ArmorMaterial.CHAIN, 2);
			GameRegistry.registerItem(armorPowersuitLeggings, "ItemPowersuit.Leggings");
			armorPowersuitBoots = (ItemPowersuit) new ItemPowersuit(ItemArmor.ArmorMaterial.CHAIN, 3);
			GameRegistry.registerItem(armorPowersuitBoots, "ItemPowersuit.Boots");
		}

		armorLumiumHelm = (ItemLumiumArmor) new ItemLumiumArmor(ARMOR_MATERIAL_LUMIUM, 0);
		GameRegistry.registerItem(armorLumiumHelm, "ItemLumiumArmor.Helm");
		armorLumiumChestplate = (ItemLumiumArmor) new ItemLumiumArmor(ARMOR_MATERIAL_LUMIUM, 1);
		GameRegistry.registerItem(armorLumiumChestplate, "ItemLumiumArmor.Chestplate");
		armorLumiumLeggings = (ItemLumiumArmor) new ItemLumiumArmor(ARMOR_MATERIAL_LUMIUM, 2);
		GameRegistry.registerItem(armorLumiumLeggings, "ItemLumiumArmor.Leggings");
		armorLumiumBoots = (ItemLumiumArmor) new ItemLumiumArmor(ARMOR_MATERIAL_LUMIUM, 3);
		GameRegistry.registerItem(armorLumiumBoots, "ItemLumiumArmor.Boots");

		armorTuberousHelm = (ItemTuberousArmor) new ItemTuberousArmor(ARMOR_MATERIAL_TUBEROUS, 0);
		GameRegistry.registerItem(armorTuberousHelm, "ItemTuberousArmor.Helm");
		armorTuberousChestplate = (ItemTuberousArmor) new ItemTuberousArmor(ARMOR_MATERIAL_TUBEROUS, 1);
		GameRegistry.registerItem(armorTuberousChestplate, "ItemTuberousArmor.Chestplate");
		armorTuberousLeggings = (ItemTuberousArmor) new ItemTuberousArmor(ARMOR_MATERIAL_TUBEROUS, 2);
		GameRegistry.registerItem(armorTuberousLeggings, "ItemTuberousArmor.Leggings");
		armorTuberousBoots = (ItemTuberousArmor) new ItemTuberousArmor(ARMOR_MATERIAL_TUBEROUS, 3);
		GameRegistry.registerItem(armorTuberousBoots, "ItemTuberousArmor.Boots");
	}

	public static void registerAllItems() {
		registerItems();
	}
}
