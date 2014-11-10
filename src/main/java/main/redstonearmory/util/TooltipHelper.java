package main.redstonearmory.util;

import cofh.core.util.KeyBindingEmpower;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

import java.util.List;

@SuppressWarnings("unchecked")
public class TooltipHelper {

	public static void doEnergyTip(ItemStack stack, EntityPlayer player, List list, int maxEnergy, int maxTransfer, int energyPerUse, int energyPerUseCharged) {

		// yay for easy yet stupid ways to do things :>
		int currentEnergy = stack.stackTagCompound.getInteger("Energy"); // y u no allow .toString()?

		String getCurrentEnergy = "" + currentEnergy;
		String getMaxEnergy = "" + maxEnergy;
		String getUseCharged = "" + getEnergyPerUse(stack, energyPerUse, energyPerUseCharged);

		list.add(TextHelper.localize("info.RArm.tooltip.getenergy").replace("%currentenergy%", getCurrentEnergy).replace("%maxenergy%", getMaxEnergy));

		if (isEmpowered(stack)) {
			list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localize("info.RArm.tooltip.extinguish").replace("%key%", Keyboard.getKeyName(KeyBindingEmpower.instance.getKey())) + TextHelper.END);
			list.add(TextHelper.ORANGE + TextHelper.localize("info.RArm.tooltip.peruse").replace("%energyperuse%", getUseCharged) + TextHelper.END);
		} else {
			list.add(TextHelper.BRIGHT_BLUE + TextHelper.ITALIC + TextHelper.localize("info.RArm.tooltip.empower").replace("%key%", Keyboard.getKeyName(KeyBindingEmpower.instance.getKey())) + TextHelper.END);
			list.add(TextHelper.ORANGE + TextHelper.localize("info.RArm.tooltip.peruse").replace("%energyperuse%", getUseCharged) + TextHelper.END);
		}
	}

	public static void doDamageTip(ItemStack stack, EntityPlayer player, List list, int energyPerUse, int damage, int damageCharged) {
		String getDamage = "" + damage;
		String getDamageCharged = "" + damageCharged;

		list.add("");

		// I have no clue why it's changing fluxdamage to sluxdamage and damage to samage. Stupid fix for now :D

		if (stack.stackTagCompound.getInteger("Energy") >= energyPerUse) {
			list.add(TextHelper.LIGHT_BLUE + TextHelper.localize("info.RArm.tooltip.damage").replace("%samage%", "%damage%").replace("%damage%", getDamage) + TextHelper.END);
			if (isEmpowered(stack)) {
				list.add(TextHelper.BRIGHT_GREEN + TextHelper.localize("info.RArm.tooltip.damage.flux").replace("%sluxdamage%", "%fluxdamage%").replace("%fluxdamage%", getDamageCharged) + TextHelper.END);
			}
		} else {
			list.add(TextHelper.LIGHT_BLUE + TextHelper.localize("info.RArm.tooltip.damage").replace("%samage%", "%damage%").replace("%damage%", "0") + TextHelper.END);
		}
	}

	private static boolean isEmpowered(ItemStack stack) {
		return stack.getTagCompound().getBoolean("Empowered");
	}

	private static int getEnergyPerUse(ItemStack stack, int energyPerUse, int energyPerUseCharged) {

		int unbreakingLevel = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
		return (isEmpowered(stack) ? energyPerUseCharged : energyPerUse) * (5 - unbreakingLevel) / 5;
	}
}