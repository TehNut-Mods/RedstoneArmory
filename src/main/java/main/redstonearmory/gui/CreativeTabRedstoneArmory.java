package main.redstonearmory.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Nick on 6/6/14.
 */
public class CreativeTabRedstoneArmory extends CreativeTabs {

	public CreativeTabRedstoneArmory(String tabLabel) {
		super(tabLabel);
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(Item.redstone, 1, 0);
	}

	@Override
	public Item getTabIconItem() {
		return Item.redstone;
	}
}
