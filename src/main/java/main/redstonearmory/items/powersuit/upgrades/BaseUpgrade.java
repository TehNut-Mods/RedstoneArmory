package main.redstonearmory.items.powersuit.upgrades;

import cofh.lib.util.helpers.EnergyHelper;
import main.redstonearmory.util.interfaces.IUpgrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class BaseUpgrade implements IUpgrade {

    public static boolean isHoldingJump;
    public int type;
    public int energyUsed;
    public int helmetType = 0;
    public int chestplateType = 1;
    public int leggingsType = 2;
    public int bootsType = 3;

    @Override
    public void onUse(World world, EntityPlayer player, ItemStack stack) {
    }

    @Override
    public int type() {
        return type;
    }

    @Override
    public int energyUsed() {
        return energyUsed;
    }

    public boolean isInstalled(String upgrade, ItemStack stack) {
        if (stack.stackTagCompound == null) stack.stackTagCompound = new NBTTagCompound();

        NBTTagCompound tag = stack.getTagCompound();
        return tag.getBoolean(upgrade);

    }

    public int getEnergyStored(ItemStack container) {
        if (container.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }
        return container.stackTagCompound.getInteger("Energy");
    }

    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if (container.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }
        int stored = container.stackTagCompound.getInteger("Energy");
        int extract = Math.min(maxExtract, stored);
        if (!simulate) {
            stored -= extract;
            container.stackTagCompound.setInteger("Energy", stored);
        }
        return extract;
    }
}
