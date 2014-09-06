package main.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FlightUpgradeMk2 extends FlightUpgradeMk1 {

    public FlightUpgradeMk2() {
        energyUsed = 150;
    }

    @Override
    public void onUse(World world, EntityPlayer player, ItemStack stack) {
        if (isHoldingJump && isInstalled("FlightMk1", stack) && isInstalled("FlightMk2", stack)) {
            if (energyUsed <= getEnergyStored(stack)) {
                player.motionY += 0.1;
                player.fallDistance = 0;
                extractEnergy(stack, energyUsed, false);
            }
        }
    }
}
