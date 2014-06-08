package main.redstonearmory.items.itemutil;

import net.minecraft.item.ItemStack;

public interface IEmpowerableItem {

	boolean isEmpowered(ItemStack stack);

	boolean setEmpoweredState(ItemStack stack, boolean state);

}