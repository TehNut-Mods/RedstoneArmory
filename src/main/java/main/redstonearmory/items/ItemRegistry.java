package main.redstonearmory.items;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ModInformation;
import main.redstonearmory.items.armor.ItemEnderiumArmor;
import main.redstonearmory.items.tools.gelidenderium.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ItemRegistry {

	//materials
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_ENDERIUM = EnumHelper.addArmorMaterial("RA_ENDERIUM", 10, new int[]{3, 8, 6, 3}, 20);

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
	}

	public static void registerAllItems() {
		registerItems();
	}
}
