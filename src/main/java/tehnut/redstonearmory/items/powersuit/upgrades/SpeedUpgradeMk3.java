package tehnut.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpeedUpgradeMk3 extends SpeedUpgradeMk2 {

    public SpeedUpgradeMk3() {
        energyUsed = 10;
    }

    @Override
    public void onUse(World world, EntityPlayer player, ItemStack stack) {
        if (isInstalled("SpeedMk1", stack) && isInstalled("SpeedMk2", stack) && isInstalled("SpeedMk3", stack) && player.moveForward > 0F) {
            if (energyUsed <= getEnergyStored(stack)) {
                player.moveFlying(0F, 1F, 0.08F);
                extractEnergy(stack, energyUsed, false);
            }
        }
    }
}
