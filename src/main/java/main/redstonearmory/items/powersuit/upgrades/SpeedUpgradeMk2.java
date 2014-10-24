package main.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpeedUpgradeMk2 extends SpeedUpgradeMk1 {

	public SpeedUpgradeMk2() {
		energyUsed = 7;
	}

	@Override
	public void onUse(World world, EntityPlayer player, ItemStack stack) {
		if (isInstalled("SpeedMk1", stack) && isInstalled("SpeedMk2", stack) && player.moveForward > 0F) {
			if (energyUsed <= getEnergyStored(stack)) {
				player.moveFlying(0F, 1F, 0.07F);
				extractEnergy(stack, energyUsed, false);
			}
		}
	}
}