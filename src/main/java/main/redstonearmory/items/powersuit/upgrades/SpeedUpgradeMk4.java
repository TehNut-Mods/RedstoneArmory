package main.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpeedUpgradeMk4 extends SpeedUpgradeMk3 {

	public SpeedUpgradeMk4() {
		energyUsed = 13;
	}

	@Override
	public void onUse(World world, EntityPlayer player, ItemStack stack) {
		if (isInstalled("SpeedMk1", stack) && isInstalled("SpeedMk2", stack) && isInstalled("SpeedMk3", stack) && isInstalled("SpeedMk4", stack) && player.moveForward > 0F) {
			if (energyUsed <= getEnergyStored(stack)) {
				player.moveFlying(0F, 1F, 0.1F);
				extractEnergy(stack, energyUsed, false);
			}
		}
	}
}
