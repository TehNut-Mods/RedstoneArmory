package main.redstonearmory.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSolars extends ItemBlock {

	public static final String[] names = {"basic", "hardened", "reinforced", "resonant"};

	public ItemBlockSolars(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}

	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName() + "." + names[stack.getItemDamage() % names.length];
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}
