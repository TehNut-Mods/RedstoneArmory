package main.redstonearmory.gui;

import main.redstonearmory.items.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabRedstoneArmory extends CreativeTabs {

	public CreativeTabRedstoneArmory(String tabLabel) {
		super(tabLabel);
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ItemRegistry.axeGelidEnderium, 1, 0);
	}

	@Override
	public Item getTabIconItem() {
		return ItemRegistry.axeGelidEnderium;
	}
}
