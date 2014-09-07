package main.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FlightUpgradeMk5 extends FlightUpgradeMk4 {

    public FlightUpgradeMk5() {
        energyUsed = 300;
    }

    @Override
    public void onUse(World world, EntityPlayer player, ItemStack stack) {
        if (isInstalled("FlightMk1", stack) && isInstalled("FlightMk2", stack) && isInstalled("FlightMk3", stack) && isInstalled("FlightMk4", stack) && isInstalled("FlightMk5", stack)) {
            if (isHoldingJump && (energyUsed <= getEnergyStored(stack))) {
                player.motionY += 0.1;
                player.fallDistance = 0;
                extractEnergy(stack, energyUsed, false);
            }
            if (!player.onGround && player.motionY < 0 && energyUsed / 2 <= getEnergyStored(stack) && !player.isSneaking()) {
                player.motionY = 0;
                extractEnergy(stack, energyUsed / 2, false);
                player.fallDistance = 0;
            }
        }
    }
}
