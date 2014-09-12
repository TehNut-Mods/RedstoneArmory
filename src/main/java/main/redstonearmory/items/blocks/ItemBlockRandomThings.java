package main.redstonearmory.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockRandomThings extends ItemBlock {


	public ItemBlockRandomThings(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		String name = "";
		switch (itemstack.getItemDamage()) {
			case 0: {
				name = "lapis.purple";
				break;
			}
			default:
				name = "nothing";
		}
		return getUnlocalizedName() + "." + name;
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}