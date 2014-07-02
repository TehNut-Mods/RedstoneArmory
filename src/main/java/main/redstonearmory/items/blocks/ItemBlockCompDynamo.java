package main.redstonearmory.items.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockCompDynamo extends ItemBlock {

	public ItemBlockCompDynamo(int id) {
		super(id);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		String name = "";
		switch (itemstack.getItemDamage()) {
			case 0: {
				name = "steam";
				break;
			}
			case 1: {
				name = "magmatic";
				break;
			}
			case 2: {
				name = "compression";
				break;
			}
			case 3: {
				name = "reactant";
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
