package main.redstonearmory.gui;

import main.redstonearmory.items.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabRArm extends CreativeTabs {

	public CreativeTabRArm(String tabLabel) {
		super(tabLabel);
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ItemRegistry.materials, 1, 3);
	}

	@Override
	public Item getTabIconItem() {
		return new Item();
	}
}
