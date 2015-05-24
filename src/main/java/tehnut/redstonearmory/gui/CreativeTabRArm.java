package tehnut.redstonearmory.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tehnut.redstonearmory.items.ItemRegistry;

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
