package main.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FlightStabilizerUpgrade extends BasicFlightUpgrade {

    public FlightStabilizerUpgrade() {
        energyUsed = 150;
        type = chestplateType;
    }

    @Override
    public void onUse(World world, EntityPlayer player, ItemStack stack) {
        if (isHoldingJump && isInstalled("ChestplateThruster", stack) && isInstalled("ChestplateStabilizer", stack)) {
            if (energyUsed <= getEnergyStored(stack)) {
                player.motionY += 0.1;
                player.fallDistance = 0;
                extractEnergy(stack, energyUsed, false);
            }
        }
    }
}
