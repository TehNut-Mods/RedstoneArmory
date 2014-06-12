package main.redstonearmory.items.itemutil;

import cofh.api.energy.IEnergyContainerItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;

import java.util.List;

/*
 * DISCLAIMER ABOUT THIS CLASS
 *
 * This was originally by the CoFH team for (AFAIK) 1.7. We have backported it for our needs in 1.6.4. 99% of the code is exactly the same.
 */

public abstract class ItemToolRF extends ItemToolAdv implements IEmpowerableItem, IEnergyContainerItem {

    Icon activeIcon;
    Icon drainedIcon;

	String tool = "default";

    public int maxEnergy = 160000;
    public int maxTransfer = 1600;
    public int energyPerUse = 350;
    public int energyPerUseCharged = 800;

    public int damage = 0;

    public ItemToolRF(int id, EnumToolMaterial toolMaterial) {

        super(id, 0F, toolMaterial);
        this.setNoRepair();
        this.hasSubtypes = true;
        this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
    }

    public ItemToolRF(int id, EnumToolMaterial toolMaterial, int harvestLevel) {

        super(id, harvestLevel, toolMaterial);
    }

    @Override
    protected float getEfficiency(ItemStack stack) {

        if (isEmpowered(stack) && getEnergyStored(stack) >= energyPerUseCharged) {
            return efficiencyOnProperMaterial * 1.5F;
        }
        return efficiencyOnProperMaterial;
    }

    protected int useEnergy(ItemStack stack, boolean simulate) {

        int unbreakingLevel = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
        return extractEnergy(stack, isEmpowered(stack) ? energyPerUseCharged * (5 - unbreakingLevel) / 5 : energyPerUse * (5 - unbreakingLevel) / 5, simulate);
    }

    protected int getEnergyPerUse(ItemStack stack) {

        int unbreakingLevel = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
        return (isEmpowered(stack) ? energyPerUseCharged : energyPerUse) * (5 - unbreakingLevel) / 5;
    }

    @Override
    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {

        return false;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {

        return EnumRarity.uncommon;
    }

    @Override
    public void getSubItems(int item, CreativeTabs tab, List list) {

        list.add(RFHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
        list.add(RFHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta) {

        if (getEnergyStored(stack) < energyPerUse) {
            return 1.0F;
        }
        return super.getStrVsBlock(stack, block, meta);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {

        EntityPlayer thePlayer = (EntityPlayer) player;
        float fallingMult = (player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater()
                && !player.isPotionActive(Potion.blindness) && player.ridingEntity == null) ? 1.5F : 1.0F;

        if (thePlayer.capabilities.isCreativeMode || extractEnergy(stack, energyPerUse, false) == energyPerUse) {
            int fluxDamage = isEmpowered(stack) ? 2 : 1;
            float enchantDamage = damage + EnchantmentHelper.getEnchantmentModifierLiving(player, entity);

            entity.attackEntityFrom(RFItemUtils.causePlayerFluxDamage(thePlayer), fluxDamage);
            entity.attackEntityFrom(DamageSource.causePlayerDamage(thePlayer), (fluxDamage + enchantDamage) * fallingMult);
        } else {
            entity.attackEntityFrom(DamageSource.causePlayerDamage(thePlayer), 1 * fallingMult);
        }
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
        if (stack.stackTagCompound == null) {
            RFHelper.setDefaultEnergyTag(stack, 0);
        }
        if (!KeyboardHandler.isShiftDown() && !KeyboardHandler.isControlDown()) {
            list.add(TextHelper.shiftForMoreInfo);
            if (ConfigHandler.addItemLoreToItems) {
                list.add(TextHelper.controlForLore);
            }
        } else if (KeyboardHandler.isShiftDown() && KeyboardHandler.isControlDown()) {
            list.add(TextHelper.shiftForMoreInfo);
            if (ConfigHandler.addItemLoreToItems) {
                list.add(TextHelper.controlForLore);
            }
        } else if (KeyboardHandler.isShiftDown() && !KeyboardHandler.isControlDown()) {
            list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.redstonearmory.tool.charge") + " " + RFHelper.getRFStored(stack) + " / " + maxEnergy + " " + TextHelper.localize("info.redstonearmory.tool.rf") + TextHelper.END);
            list.add(TextHelper.ORANGE + energyPerUse + " " + TextHelper.localize("info.redstonearmory.tool.energyPerUse") + TextHelper.END);
            if (isEmpowered(stack)) {
                list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.tool.press") + " " + ConfigHandler.empowerKey + " " + TextHelper.localize("info.redstonearmory.tool.chargeOff") + TextHelper.END);
            } else {
                list.add(TextHelper.BRIGHT_BLUE + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.tool.press") + " " + ConfigHandler.empowerKey + " " + TextHelper.localize("info.redstonearmory.tool.chargeOn") + TextHelper.END);
            }
	        list.add(TextHelper.WHITE + TextHelper.localize("info.redstonearmory.tool.gelidenderium.default"));
        } else if (!KeyboardHandler.isShiftDown() && KeyboardHandler.isControlDown() && ConfigHandler.addItemLoreToItems) {
            list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.redstonearmory.lore." + tool) + TextHelper.END);
        }
    }

    @Override
    public int getDisplayDamage(ItemStack stack) {

        if (stack.stackTagCompound == null) {
            RFHelper.setDefaultEnergyTag(stack, 0);
        }
        return 1 + maxEnergy - stack.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxDamage(ItemStack stack) {

        return 1 + maxEnergy;
    }

    @Override
    public boolean isDamaged(ItemStack stack) {

        return stack.getItemDamage() != Short.MAX_VALUE;
    }

    @Override
    public Multimap getItemAttributeModifiers() {

        return HashMultimap.create();
    }

    @Override
    public Icon getIconIndex(ItemStack stack) {

        return getIcon(stack, 0);
    }

    @Override
    public Icon getIcon(ItemStack stack, int pass) {

        return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
    }

    @Override
    public void registerIcons(IconRegister ir) {

        this.itemIcon = ir.registerIcon(this.getIconString());
        this.activeIcon = ir.registerIcon(this.getIconString() + "_Active");
        this.drainedIcon = ir.registerIcon(this.getIconString() + "_Drained");
    }

    /* IEmpowerableItem */
    @Override
    public boolean isEmpowered(ItemStack stack) {

        return stack.stackTagCompound == null ? false : stack.stackTagCompound.getBoolean("Empowered");
    }

    @Override
    public boolean setEmpoweredState(ItemStack stack, boolean state) {

        if (getEnergyStored(stack) > 0) {
            stack.stackTagCompound.setBoolean("Empowered", state);
            return true;
        }
        stack.stackTagCompound.setBoolean("Empowered", false);
        return false;
    }

    /* IEnergyContainerItem */
    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

        if (container.stackTagCompound == null) {
            RFHelper.setDefaultEnergyTag(container, 0);
        }
        int stored = container.stackTagCompound.getInteger("Energy");
        int receive = Math.min(maxReceive, Math.min(maxEnergy - stored, maxTransfer));

        if (!simulate) {
            stored += receive;
            container.stackTagCompound.setInteger("Energy", stored);
        }
        return receive;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

        if (container.stackTagCompound == null) {
            RFHelper.setDefaultEnergyTag(container, 0);
        }
        int stored = container.stackTagCompound.getInteger("Energy");
        int extract = Math.min(maxExtract, stored);

        if (!simulate) {
            stored -= extract;
            container.stackTagCompound.setInteger("Energy", stored);

            if (stored == 0) {
                setEmpoweredState(container, false);
            }
        }
        return extract;
    }

    @Override
    public int getEnergyStored(ItemStack container) {

        if (container.stackTagCompound == null) {
            RFHelper.setDefaultEnergyTag(container, 0);
        }
        return container.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {

        return maxEnergy;
    }

}
