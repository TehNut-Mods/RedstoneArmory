package tehnut.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class StepAssistUpgrade extends BaseUpgrade {

    public StepAssistUpgrade() {
        type = leggingsType;
        energyUsed = 5;
    }

    @Override
    public void onUse(World world, EntityPlayer player, ItemStack stack) {
        if (isInstalled("StepAssist", stack)) {
            player.stepHeight = 1.1F;
            if (world.rand.nextInt(10000) <= 25)
                extractEnergy(stack, energyUsed, false);
        }
    }
}
