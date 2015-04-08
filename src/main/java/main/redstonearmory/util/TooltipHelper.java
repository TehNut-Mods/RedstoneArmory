package main.redstonearmory.util;

import cofh.core.util.KeyBindingEmpower;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.ItemHelper;
import cofh.lib.util.helpers.MathHelper;
import cofh.lib.util.helpers.StringHelper;
import main.redstonearmory.items.baubles.CapacitorType;
import main.redstonearmory.items.baubles.ItemBaubleCapacitor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

import java.util.List;

@SuppressWarnings("unchecked")
public class TooltipHelper {

    /**
     *
     * @param stack - ItemStack to apply to.
     * @param list - The list to add to.
     * @param maxEnergy - Max stack energy.
     * @param energyPerUse - Energy per use of stack.
     * @param energyPerUseCharged - Energy per use of stack while charged
     */
	public static void doEnergyTip(ItemStack stack, List list, int maxEnergy, int energyPerUse, int energyPerUseCharged) {

		// yay for easy yet stupid ways to do things :>
		int currentEnergy = stack.stackTagCompound.getInteger("Energy"); // y u no allow .toString()?

		String getCurrentEnergy = "" + currentEnergy;
		String getMaxEnergy = "" + maxEnergy;
		String getUseCharged = "" + getEnergyPerUse(stack, energyPerUse, energyPerUseCharged);

		list.add(TextHelper.localize("info.RArm.tooltip.getenergy").replace("%currentenergy%", getCurrentEnergy).replace("%maxenergy%", getMaxEnergy));

		if (isEmpowered(stack)) {
			list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localizeFormatted("info.RArm.tooltip.extinguish", Keyboard.getKeyName(KeyBindingEmpower.instance.getKey())) + TextHelper.END);
			list.add(TextHelper.ORANGE + TextHelper.localizeFormatted("info.RArm.tooltip.peruse", getUseCharged) + TextHelper.END);
		} else {
            list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localizeFormatted("info.RArm.tooltip.empower", Keyboard.getKeyName(KeyBindingEmpower.instance.getKey())) + TextHelper.END);
            list.add(TextHelper.ORANGE + TextHelper.localizeFormatted("info.RArm.tooltip.peruse", getUseCharged) + TextHelper.END);
		}
	}
    
    public static void doCapacitorTip(ItemStack stack, List list) {
        if(StringHelper.displayShiftForDetail && !StringHelper.isShiftKeyDown()) {
            list.add(StringHelper.shiftForDetails());
        }

        if(stack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(stack, 0);
        }

        if(StringHelper.isShiftKeyDown()) {
            if(stack.getItemDamage() == CapacitorType.CREATIVE.ordinal()) {
                list.add(StringHelper.localize("info.cofh.charge") + ": 1.21G RF");
                list.add(StringHelper.localize("info.cofh.send") + "/" + StringHelper.localize("info.cofh.receive") + ": " + CapacitorType.CREATIVE.send + " RF/t");
            } else {
                list.add(StringHelper.localize("info.cofh.charge") + ": " + StringHelper.getScaledNumber((long) stack.stackTagCompound.getInteger("Energy")) + " / " + StringHelper.getScaledNumber((long) CapacitorType.values()[stack.getItemDamage()].capacity) + " RF");
                list.add(StringHelper.localize("info.cofh.send") + "/" + StringHelper.localize("info.cofh.receive") + ": " + CapacitorType.values()[stack.getItemDamage()].send + "/" + CapacitorType.values()[stack.getItemDamage()].recieve + " RF/t");
            }

            if(isActive(stack)) {
                list.add(StringHelper.getInfoText("info.thermalexpansion.capacitor.2"));
                list.add(StringHelper.getInfoText("info.RArm.capacitor.slot"));
                list.add(StringHelper.getDeactivationText("info.thermalexpansion.capacitor.3"));
            } else {
                list.add(StringHelper.getInfoText("info.thermalexpansion.capacitor.0"));
                list.add(StringHelper.getInfoText("info.RArm.capacitor.slot"));
                list.add(StringHelper.getActivationText("info.thermalexpansion.capacitor.1"));
            }
        }
    }

    /**
     *
     * @param stack - ItemStack to apply to.
     * @param list - The list to add to.
     * @param energyPerUse - Energy per use of stack.
     * @param damage - Damage the stack inflicts.
     * @param damageCharged - Damage the stack inflicts while charged.
     */
	public static void doDamageTip(ItemStack stack, List list, int energyPerUse, int damage, int damageCharged) {
		String getDamage = "" + damage;
		String getDamageCharged = "" + damageCharged;

		list.add("");

		// I have no clue why it's changing fluxdamage to sluxdamage and damage to samage. Stupid fix for now :D

		if (stack.stackTagCompound.getInteger("Energy") >= energyPerUse) {
			list.add(TextHelper.LIGHT_BLUE + TextHelper.localizeFormatted("info.RArm.tooltip.damage", getDamage) + TextHelper.END);
			if (isEmpowered(stack)) {
				list.add(TextHelper.BRIGHT_GREEN + TextHelper.localizeFormatted("info.RArm.tooltip.damage.flux", getDamageCharged) + TextHelper.END);
			}
		} else {
			list.add(TextHelper.LIGHT_BLUE + TextHelper.localizeFormatted("info.RArm.tooltip.damage", "0") + TextHelper.END);
		}
	}

    /**
     *
     * @param stack - ItemStack to check for empowerment.
     * @return - If the stack is empowered.
     */
	private static boolean isEmpowered(ItemStack stack) {
		return stack.getTagCompound().getBoolean("Empowered");
	}

    /**
     *
     * @param stack - ItemStack to check active.
     * @return - If the stack is active.
     */
    private static boolean isActive(ItemStack stack) {
        return stack.getTagCompound().getBoolean("Active");
    }

    /**
     *
     * @param stack - ItemStack to check.
     * @param energyPerUse - Energy per use of stack.
     * @param energyPerUseCharged - Energy per use of stack while charged.
     * @return
     */
	private static int getEnergyPerUse(ItemStack stack, int energyPerUse, int energyPerUseCharged) {

		int unbreakingLevel = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
		return (isEmpowered(stack) ? energyPerUseCharged : energyPerUse) * (5 - unbreakingLevel) / 5;
	}
}