package main.redstonearmory.items.blocks;

import main.redstonearmory.util.TextHelper;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockIngotStorage extends ItemBlock {


	public ItemBlockIngotStorage(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		String name = "";
		switch (itemstack.getItemDamage()) {
			case 0: {
				name = "enderium.gelid";
				break;
			}
			default:
				name = "nothing";
		}
		return getUnlocalizedName() + ".storage." + name + ".ingot";
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}
}