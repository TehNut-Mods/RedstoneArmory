package main.redstonearmory.items;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ModInformation;
import main.redstonearmory.items.tools.gelidenderium.ItemAxeGelidEnderium;
import main.redstonearmory.items.tools.gelidenderium.ItemPickaxeGelidEnderium;
import main.redstonearmory.items.tools.gelidenderium.ItemShovelGelidEnderium;
import main.redstonearmory.items.tools.gelidenderium.ItemSwordGelidEnderium;
import net.minecraft.item.Item;

public class ItemRegistry {

	//items
	public static Item gelidMaterials;

	public static Item axeGelidEnderium;
	public static Item pickaxeGelidEnderium;
	public static Item shovelGelidEnderium;
	public static Item swordGelidEnderium;

	private static void registerItems() {

		gelidMaterials = new ItemGelidMaterials().setUnlocalizedName(ModInformation.ID);
		GameRegistry.registerItem(gelidMaterials, "ItemGelidMaterials");

		axeGelidEnderium = new ItemAxeGelidEnderium(Item.ToolMaterial.EMERALD);
		GameRegistry.registerItem(axeGelidEnderium, "ItemAxeGelidEnderium");
		pickaxeGelidEnderium = new ItemPickaxeGelidEnderium(Item.ToolMaterial.EMERALD);
		GameRegistry.registerItem(pickaxeGelidEnderium, "ItemPickaxeGelidEnderium");
		shovelGelidEnderium = new ItemShovelGelidEnderium(Item.ToolMaterial.EMERALD);
		GameRegistry.registerItem(shovelGelidEnderium, "ItemShovelGelidEnderium");
		swordGelidEnderium = new ItemSwordGelidEnderium(Item.ToolMaterial.EMERALD);
		GameRegistry.registerItem(swordGelidEnderium, "ItemSwordGelidEnderium");
	}

	public static void registerAllItems() {
		registerItems();
	}
}
