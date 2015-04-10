package tehnut.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WaterBreathingUpgrade extends BaseUpgrade {

    public WaterBreathingUpgrade() {
        type = helmetType;
        energyUsed = 100;
    }

    @Override
    public void onUse(World world, EntityPlayer player, ItemStack stack) {
        if (isInstalled("WaterBreathing", stack) && (energyUsed <= getEnergyStored(stack))) {
            if (player.getAir() <= 100) {
                player.setAir(player.getAir() + 200);
                extractEnergy(stack, energyUsed, false);
            }
        }
    }
}
