package tehnut.redstonearmory.items;

import cofh.redstonearsenal.item.RAItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;
import tehnut.redstonearmory.items.armor.ItemEnderiumArmor;
import tehnut.redstonearmory.items.armor.ItemLumiumArmor;
import tehnut.redstonearmory.items.armor.ItemTuberousArmor;
import tehnut.redstonearmory.items.baubles.ItemBaubleCapacitor;
import tehnut.redstonearmory.items.powersuit.ItemPowersuit;
import tehnut.redstonearmory.items.random.ItemThrowableNut;
import tehnut.redstonearmory.items.tools.ItemPotahoeFluxed;
import tehnut.redstonearmory.items.tools.gelidenderium.*;
import tehnut.redstonearmory.util.annot.Register;

public class ItemRegistry {

    // Armor Materials
    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_LUMIUM = EnumHelper.addArmorMaterial("RA_LUMIUM", 10, new int[]{2, 5, 3, 2}, 6);
    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_TUBEROUS = EnumHelper.addArmorMaterial("RA_TUBEROUS", 10, new int[]{1, 2, 1, 1}, 6);

    // Tool Materials
    public static final ItemTool.ToolMaterial TOOL_MATERIAL_GELID_ENDERIUM = EnumHelper.addToolMaterial("RA_GELID_ENDERIUM", 15, Integer.MAX_VALUE, 20, 10, 15);

    // Items
    @Register public static Item materials = new ItemMaterials();
    @Register public static Item armorPlating = new ItemArmorPlating();

    @Register(enabled = "false") public static Item nutThrowable = new ItemThrowableNut();

    @Register(dependency = "Baubles") public static Item capacitorBauble = new ItemBaubleCapacitor();

    @Register public static Item potahoe = new ItemPotahoeFluxed();

    @Register public static Item axeGelidEnderium = new ItemAxeGelidEnderium(TOOL_MATERIAL_GELID_ENDERIUM);
    @Register public static Item battleWrenchGelidEnderium = new ItemBattleWrenchGelidEnderium(TOOL_MATERIAL_GELID_ENDERIUM);
    @Register public static Item pickaxeGelidEnderium = new ItemPickaxeGelidEnderium(TOOL_MATERIAL_GELID_ENDERIUM);
    @Register public static Item shovelGelidEnderium = new ItemShovelGelidEnderium(TOOL_MATERIAL_GELID_ENDERIUM);
    @Register public static Item sickleGelidEnderium = new ItemSickleGelidEnderium(TOOL_MATERIAL_GELID_ENDERIUM);
    @Register public static Item swordGelidEnderium = new ItemSwordGelidEnderium(TOOL_MATERIAL_GELID_ENDERIUM);

    @Register(name = "ItemEnderiumArmor.Helm") public static Item armorEnderiumHelm = new ItemEnderiumArmor(RAItems.ARMOR_MATERIAL_FLUX, 0);
    @Register(name = "ItemEnderiumArmor.Chestplate") public static Item armorEnderiumChestplate = new ItemEnderiumArmor(RAItems.ARMOR_MATERIAL_FLUX, 1);
    @Register(name = "ItemEnderiumArmor.Leggings") public static Item armorEnderiumLeggings = new ItemEnderiumArmor(RAItems.ARMOR_MATERIAL_FLUX, 2);
    @Register(name = "ItemEnderiumArmor.Boots") public static Item armorEnderiumBoots = new ItemEnderiumArmor(RAItems.ARMOR_MATERIAL_FLUX, 3);

    @Register(name = "ItemPowersuit.Helm", enabled = "enableTestingEnviro") public static Item armorPowersuitHelm = new ItemPowersuit(ItemArmor.ArmorMaterial.CHAIN, 0);
    @Register(name = "ItemPowersuit.Chestplate", enabled = "enableTestingEnviro") public static Item armorPowersuitChestplate = new ItemPowersuit(ItemArmor.ArmorMaterial.CHAIN, 1);
    @Register(name = "ItemPowersuit.Leggings", enabled = "enableTestingEnviro") public static Item armorPowersuitLeggings = new ItemPowersuit(ItemArmor.ArmorMaterial.CHAIN, 2);
    @Register(name = "ItemPowersuit.Boots", enabled = "enableTestingEnviro") public static Item armorPowersuitBoots = new ItemPowersuit(ItemArmor.ArmorMaterial.CHAIN, 3);

    @Register(name = "ItemLumiumArmor.Helm") public static Item armorLumiumHelm = new ItemLumiumArmor(ARMOR_MATERIAL_LUMIUM, 0);
    @Register(name = "ItemLumiumArmor.Chestplate") public static Item armorLumiumChestplate = new ItemLumiumArmor(ARMOR_MATERIAL_LUMIUM, 1);
    @Register(name = "ItemLumiumArmor.Leggings") public static Item armorLumiumLeggings = new ItemLumiumArmor(ARMOR_MATERIAL_LUMIUM, 2);
    @Register(name = "ItemLumiumArmor.Boots") public static Item armorLumiumBoots = new ItemLumiumArmor(ARMOR_MATERIAL_LUMIUM, 3);

    @Register(name = "ItemTuberousArmor.Helm") public static Item armorTuberousHelm = new ItemTuberousArmor(ARMOR_MATERIAL_TUBEROUS, 0);
    @Register(name = "ItemTuberousArmor.Chestplate") public static Item armorTuberousChestplate = new ItemTuberousArmor(ARMOR_MATERIAL_TUBEROUS, 1);
    @Register(name = "ItemTuberousArmor.Leggings") public static Item armorTuberousLeggings = new ItemTuberousArmor(ARMOR_MATERIAL_TUBEROUS, 2);
    @Register(name = "ItemTuberousArmor.Boots") public static Item armorTuberousBoots = new ItemTuberousArmor(ARMOR_MATERIAL_TUBEROUS, 3);
}
