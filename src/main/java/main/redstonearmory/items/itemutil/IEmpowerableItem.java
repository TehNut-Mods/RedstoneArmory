package main.redstonearmory.items.itemutil;

import net.minecraft.item.ItemStack;

/*
 * DISCLAIMER ABOUT THIS CLASS
 *
 * This was originally by the CoFH team for (AFAIK) 1.7. We have backported it for our needs in 1.6.4. 99% of the code is exactly the same.
 */

public interface IEmpowerableItem {

	boolean isEmpowered(ItemStack stack);

	boolean setEmpoweredState(ItemStack stack, boolean state);

}