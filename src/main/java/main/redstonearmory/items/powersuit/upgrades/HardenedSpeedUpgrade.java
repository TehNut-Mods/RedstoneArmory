package main.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HardenedSpeedUpgrade extends BasicSpeedUpgrade {

    public HardenedSpeedUpgrade() {
        energyUsed = 7;
    }

    @Override
    public void onUse(World world, EntityPlayer player, ItemStack stack) {
        if (isInstalled("BasicSpeed", stack) && isInstalled("HardenedSpeed", stack) && player.moveForward > 0F) {
            if (energyUsed <= getEnergyStored(stack)) {
                player.moveFlying(0F, 1F, 0.075F);
                extractEnergy(stack, energyUsed, false);
            }
        }
    }
}