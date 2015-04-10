package tehnut.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FlightUpgradeMk3 extends FlightUpgradeMk2 {

    public FlightUpgradeMk3() {
        energyUsed = 200;
    }

    @Override
    public void onUse(World world, EntityPlayer player, ItemStack stack) {
        if (isHoldingJump && isInstalled("FlightMk1", stack) && isInstalled("FlightMk2", stack) && isInstalled("FlightMk3", stack)) {
            if (energyUsed <= getEnergyStored(stack)) {
                player.motionY += 0.15;
                player.fallDistance = 0;
                extractEnergy(stack, energyUsed, false);
            }
        }
    }
}
