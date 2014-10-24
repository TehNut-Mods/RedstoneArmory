package main.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FlightUpgradeMk1 extends BaseUpgrade {

	public FlightUpgradeMk1() {
		energyUsed = 100;
		type = chestplateType;
	}

	@Override
	public void onUse(World world, EntityPlayer player, ItemStack stack) {
		if (isHoldingJump && isInstalled("FlightMk1", stack) && !isInstalled("FlightMk2", stack)) {
			if (energyUsed <= getEnergyStored(stack)) {
				player.motionX *= 1.055;
				player.motionY += 0.065;
				player.motionZ *= 1.055;
				player.fallDistance = 0;
				extractEnergy(stack, energyUsed, false);
			}
			if (player.motionX >= Math.abs(2))
				player.motionX /= 1.5;
			if (player.motionZ >= Math.abs(2))
				player.motionZ /= 1.5;
		}
	}
}
