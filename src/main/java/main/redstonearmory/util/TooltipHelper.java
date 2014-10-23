package main.redstonearmory.util;

import cofh.core.util.KeyBindingEmpower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

import java.util.List;

@SuppressWarnings("unchecked")
public class TooltipHelper {

	public static void doEnergyTip(ItemStack stack, EntityPlayer player, List list, int maxEnergy, int maxTransfer, int energyPerUse, int energyPerUseCharged) {

		list.add(TextHelper.localize("info.cofh.charge") + ": " + stack.stackTagCompound.getInteger("Energy") + " / " + maxEnergy + " RF");

		if (isEmpowered(stack)) {
			list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localize("info.cofh.press") + " " + Keyboard.getKeyName(KeyBindingEmpower.instance.getKey()) + " " + TextHelper.localize("info.redstonearsenal.tool.chargeOff") + TextHelper.END);
			list.add(TextHelper.ORANGE + energyPerUseCharged + " " + TextHelper.localize("info.redstonearsenal.tool.energyPerUse") + TextHelper.END);
		} else {
			list.add(TextHelper.BRIGHT_BLUE + TextHelper.ITALIC + TextHelper.localize("info.cofh.press") + " " + Keyboard.getKeyName(KeyBindingEmpower.instance.getKey()) + " " + TextHelper.localize("info.redstonearsenal.tool.chargeOn") + TextHelper.END);
			list.add(TextHelper.ORANGE + energyPerUse + " " + TextHelper.localize("info.redstonearsenal.tool.energyPerUse") + TextHelper.END);
		}
	}

	public static void doDamageTip(ItemStack stack, EntityPlayer player, List list, int energyPerUse, int damage, int damageCharged) {
		list.add("");

		if (stack.stackTagCompound.getInteger("Energy") >= energyPerUse) {
			list.add(TextHelper.LIGHT_BLUE + "+" + damage + " " + TextHelper.localize("info.cofh.damageAttack") + TextHelper.END);
			if (isEmpowered(stack))
				list.add(TextHelper.BRIGHT_GREEN + "+" + damageCharged + " " + TextHelper.localize("info.cofh.damageFlux") + TextHelper.END);
		} else {
			list.add(TextHelper.LIGHT_BLUE + "+" + 0 + " " + TextHelper.localize("info.cofh.damageAttack") + TextHelper.END);
		}
	}

	private static boolean isEmpowered(ItemStack stack) {
		return stack.getTagCompound().getBoolean("Empowered");
	}
}