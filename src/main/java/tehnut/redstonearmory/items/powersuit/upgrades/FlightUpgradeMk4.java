package tehnut.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FlightUpgradeMk4 extends FlightUpgradeMk3 {

    public FlightUpgradeMk4() {
        energyUsed = 230;
    }

    @Override
    public void onUse(World world, EntityPlayer player, ItemStack stack) {
        if (isInstalled("FlightMk1", stack) && isInstalled("FlightMk2", stack) && isInstalled("FlightMk3", stack) && isInstalled("FlightMk4", stack)) {
            if (isHoldingJump && (energyUsed <= getEnergyStored(stack))) {
                player.motionY += 0.1;
                player.fallDistance = 0;
                extractEnergy(stack, energyUsed, false);
            }
            if (!player.onGround && player.motionY < 0 && energyUsed / 4 <= getEnergyStored(stack) && !player.isSneaking()) {
                player.motionY /= 1.3;
                extractEnergy(stack, energyUsed / 4, false);
                player.fallDistance = 0;
            }
        }
    }
}
