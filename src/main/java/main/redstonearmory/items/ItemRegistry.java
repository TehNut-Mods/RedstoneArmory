package main.redstonearmory.items;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ModInformation;
import main.redstonearmory.items.armor.ItemEnderiumArmor;
import main.redstonearmory.items.armor.ItemLumiumArmor;
import main.redstonearmory.items.armor.ItemMithrilArmor;
import main.redstonearmory.items.armor.ItemPowersuit;
import main.redstonearmory.items.tools.gelidenderium.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ItemRegistry {

	//materials
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_ENDERIUM = EnumHelper.addArmorMaterial("RA_ENDERIUM", 10, new int[]{3, 8, 6, 3}, 20);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_LUMIUM = EnumHelper.addArmorMaterial("RA_LUMIUM", 10, new int[]{2, 5, 3, 2}, 20);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_MITHRIL = EnumHelper.addArmorMaterial("RA_MITHRIL", 10, new int[]{3, 6, 4, 3}, 20);

	//items
	public static Item gelidMaterials;
	public static Item armorPlating;

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

	private static void registerItems() {

		gelidMaterials = new ItemGelidMaterials().setUnlocalizedName(ModInformation.ID);
		GameRegistry.registerItem(gelidMaterials, "ItemGelidMaterials");
		armorPlating = new ItemArmorPlating().setUnlocalizedName(ModInformation.ID);
		GameRegistry.registerItem(armorPlating, "ItemArmorPlating");

		axeGelidEnderium = new ItemAxeGelidEnderium(Item.ToolMaterial.EMERALD);
		GameRegistry.registerItem(axeGelidEnderium, "ItemAxeGelidEnderium");
		battleWrenchGelidEnderium = new ItemBattleWrenchGelidEnderium(Item.ToolMaterial.EMERALD);
		GameRegistry.registerItem(battleWrenchGelidEnderium, "ItemBattleWrenchGelidEnderium");
		pickaxeGelidEnderium = new ItemPickaxeGelidEnderium(Item.ToolMaterial.EMERALD);
		GameRegistry.registerItem(pickaxeGelidEnderium, "ItemPickaxeGelidEnderium");
		shovelGelidEnderium = new ItemShovelGelidEnderium(Item.ToolMaterial.EMERALD);
		GameRegistry.registerItem(shovelGelidEnderium, "ItemShovelGelidEnderium");
		sickleGelidEnderium = new ItemSickleGelidEnderium(Item.ToolMaterial.EMERALD);
		GameRegistry.registerItem(sickleGelidEnderium, "ItemSickleGelidEnderium");
		swordGelidEnderium = new ItemSwordGelidEnderium(Item.ToolMaterial.EMERALD);
		GameRegistry.registerItem(swordGelidEnderium, "ItemSwordGelidEnderium");

		armorEnderiumHelm = (ItemEnderiumArmor) new ItemEnderiumArmor(ARMOR_MATERIAL_ENDERIUM, 0);
		GameRegistry.registerItem(armorEnderiumHelm, "ItemEnderiumArmor.Helm");
		armorEnderiumChestplate = (ItemEnderiumArmor) new ItemEnderiumArmor(ARMOR_MATERIAL_ENDERIUM, 1);
		GameRegistry.registerItem(armorEnderiumChestplate, "ItemEnderiumArmor.Chestplate");
		armorEnderiumLeggings = (ItemEnderiumArmor) new ItemEnderiumArmor(ARMOR_MATERIAL_ENDERIUM, 2);
		GameRegistry.registerItem(armorEnderiumLeggings, "ItemEnderiumArmor.Leggings");
		armorEnderiumBoots = (ItemEnderiumArmor) new ItemEnderiumArmor(ARMOR_MATERIAL_ENDERIUM, 3);
		GameRegistry.registerItem(armorEnderiumBoots, "ItemEnderiumArmor.Boots");

        armorPowersuitHelm = (ItemPowersuit) new ItemPowersuit(ItemArmor.ArmorMaterial.CHAIN, 0);
        GameRegistry.registerItem(armorPowersuitHelm, "ItemPowersuit.Helm");
        armorPowersuitChestplate = (ItemPowersuit) new ItemPowersuit(ItemArmor.ArmorMaterial.CHAIN, 1);
        GameRegistry.registerItem(armorPowersuitChestplate, "ItemPowersuit.Chestplate");
        armorPowersuitLeggings = (ItemPowersuit) new ItemPowersuit(ItemArmor.ArmorMaterial.CHAIN, 2);
        GameRegistry.registerItem(armorPowersuitLeggings, "ItemPowersuit.Leggings");
        armorPowersuitBoots = (ItemPowersuit) new ItemPowersuit(ItemArmor.ArmorMaterial.CHAIN, 3);
        GameRegistry.registerItem(armorPowersuitBoots, "ItemPowersuit.Boots");

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
	}

	public static void registerAllItems() {
		registerItems();
	}
}
