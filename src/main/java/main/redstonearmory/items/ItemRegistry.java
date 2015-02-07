package main.redstonearmory.items;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.items.armor.*;
import main.redstonearmory.items.powersuit.ItemPowersuit;
import main.redstonearmory.items.random.ItemThrowableNut;
import main.redstonearmory.items.tools.gelidenderium.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;
import cofh.redstonearsenal.item.RAItems;

public class ItemRegistry {

	//armor materials
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_LUMIUM = EnumHelper.addArmorMaterial("RA_LUMIUM", 10, new int[]{2, 5, 3, 2}, 6);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_MITHRIL = EnumHelper.addArmorMaterial("RA_MITHRIL", 10, new int[]{3, 6, 4, 3}, 6);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_BRONZE = EnumHelper.addArmorMaterial("RA_BRONZE", 10, new int[]{4, 7, 5, 4}, 6);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_TUBEROUS = EnumHelper.addArmorMaterial("RA_TUBEROUS", 10, new int[]{1, 2, 1, 1}, 6);

	//tool materials
	public static final ItemTool.ToolMaterial TOOL_MATERIAL_GELID_ENDERIUM = EnumHelper.addToolMaterial("RA_GELID_ENDERIUM", 15, Integer.MAX_VALUE, 20, 10, 15);

	//items
	public static Item materials;
	public static Item armorPlating;
	public static Item armorPlatingVanilla;

	public static Item nutThrowable;

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

	public static Item armorMithrilHelm;
	public static Item armorMithrilChestplate;
	public static Item armorMithrilLeggings;
	public static Item armorMithrilBoots;

	public static Item armorBronzeHelm;
	public static Item armorBronzeChestplate;
	public static Item armorBronzeLeggings;
	public static Item armorBronzeBoots;

	public static Item armorTuberousHelm;
	public static Item armorTuberousChestplate;
	public static Item armorTuberousLeggings;
	public static Item armorTuberousBoots;

	private static void registerItems() {

		materials = new ItemMaterials().setUnlocalizedName(ModInformation.ID);
		GameRegistry.registerItem(materials, "ItemMaterials");
		armorPlating = new ItemArmorPlating().setUnlocalizedName(ModInformation.ID);
		GameRegistry.registerItem(armorPlating, "ItemArmorPlating");
		if (ConfigHandler.overrideVanillaArmorRecipes) {
			armorPlatingVanilla = new ItemArmorPlatingVanilla().setUnlocalizedName(ModInformation.ID);
			GameRegistry.registerItem(armorPlatingVanilla, "ItemArmorPlatingVanilla");
		}

		if (ConfigHandler.addNutsToys) {
			nutThrowable = new ItemThrowableNut();
			GameRegistry.registerItem(nutThrowable, "ItemThrowableNut");
		}

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

		if (ConfigHandler.enableTestingEnviro) {
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

		armorMithrilHelm = (ItemMithrilArmor) new ItemMithrilArmor(ARMOR_MATERIAL_MITHRIL, 0);
		GameRegistry.registerItem(armorMithrilHelm, "ItemMithrilArmor.Helm");
		armorMithrilChestplate = (ItemMithrilArmor) new ItemMithrilArmor(ARMOR_MATERIAL_MITHRIL, 1);
		GameRegistry.registerItem(armorMithrilChestplate, "ItemMithrilArmor.Chestplate");
		armorMithrilLeggings = (ItemMithrilArmor) new ItemMithrilArmor(ARMOR_MATERIAL_MITHRIL, 2);
		GameRegistry.registerItem(armorMithrilLeggings, "ItemMithrilArmor.Leggings");
		armorMithrilBoots = (ItemMithrilArmor) new ItemMithrilArmor(ARMOR_MATERIAL_MITHRIL, 3);
		GameRegistry.registerItem(armorMithrilBoots, "ItemMithrilArmor.Boots");

		armorBronzeHelm = (ItemBronzeArmor) new ItemBronzeArmor(ARMOR_MATERIAL_BRONZE, 0);
		GameRegistry.registerItem(armorBronzeHelm, "ItemBronzeArmor.Helm");
		armorBronzeChestplate = (ItemBronzeArmor) new ItemBronzeArmor(ARMOR_MATERIAL_BRONZE, 1);
		GameRegistry.registerItem(armorBronzeChestplate, "ItemBronzeArmor.Chestplate");
		armorBronzeLeggings = (ItemBronzeArmor) new ItemBronzeArmor(ARMOR_MATERIAL_BRONZE, 2);
		GameRegistry.registerItem(armorBronzeLeggings, "ItemBronzeArmor.Leggings");
		armorBronzeBoots = (ItemBronzeArmor) new ItemBronzeArmor(ARMOR_MATERIAL_BRONZE, 3);
		GameRegistry.registerItem(armorBronzeBoots, "ItemBronzeArmor.Boots");

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
