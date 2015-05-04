package tehnut.redstonearmory.items.armor;

import cofh.core.item.ItemArmorAdv;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tehnut.redstonearmory.ModInformation;
import tehnut.redstonearmory.RedstoneArmory;
import tehnut.redstonearmory.items.ItemRegistry;
import tehnut.redstonearmory.util.KeyboardHelper;
import tehnut.redstonearmory.util.TextHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemTuberousArmor extends ItemArmorAdv {

    public ItemTuberousArmor(int type) {

        super(ItemRegistry.ARMOR_MATERIAL_TUBEROUS, type);
        setCreativeTab(RedstoneArmory.tabRArm);
        setRepairIngot("cropPotato");

        switch (type) {
            case 0: {
                setTextureName(ModInformation.ID + ":armor/tuberousHelm");
                setUnlocalizedName(ModInformation.ID + ".armor.tuberous.helm");
                setMaxDamage(32);
                break;
            }
            case 1: {
                setTextureName(ModInformation.ID + ":armor/tuberousChestplate");
                setUnlocalizedName(ModInformation.ID + ".armor.tuberous.chestplate");
                setMaxDamage(58);
                break;
            }
            case 2: {
                setTextureName(ModInformation.ID + ":armor/tuberousLeggings");
                setUnlocalizedName(ModInformation.ID + ".armor.tuberous.leggings");
                setMaxDamage(41);
                break;
            }
            case 3: {
                setTextureName(ModInformation.ID + ":armor/tuberousBoots");
                setUnlocalizedName(ModInformation.ID + ".armor.tuberous.boots");
                setMaxDamage(25);
                break;
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack Stack, Entity entity, int slot, String type) {
        if (slot == 2)
            return ModInformation.ID + ":textures/models/armor/tuberousArmor_2.png";
        else
            return ModInformation.ID + ":textures/models/armor/tuberousArmor_1.png";

    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (player.getFoodStats().needFood()) {
            player.inventory.damageArmor(1F);
            player.getFoodStats().addStats(1, 4.0f);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {

        if (KeyboardHelper.isShiftDown()) {
            list.add(TextHelper.END);
            list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.armor.tuberous.clever.1"));
            list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.armor.tuberous.clever.2"));
        }
    }
}
