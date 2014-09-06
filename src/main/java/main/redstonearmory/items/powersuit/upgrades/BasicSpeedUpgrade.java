package main.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BasicSpeedUpgrade extends BaseUpgrade {

    public BasicSpeedUpgrade() {
        energyUsed = 5;
        type = leggingsType;
    }

    @Override
    public void onUse(World world, EntityPlayer player, ItemStack stack) {
        if (isInstalled("BasicSpeed", stack) && player.moveForward > 0F) {
            if (energyUsed <= getEnergyStored(stack)) {
                player.moveFlying(0F, 1F, 0.055F);
                extractEnergy(stack, energyUsed, false);
            }
        }
    }
}
