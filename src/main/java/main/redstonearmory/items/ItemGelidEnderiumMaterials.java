package main.redstonearmory.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.TextHelper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import java.util.List;

public class ItemGelidEnderiumMaterials extends Item {

	public Icon[] icon = new Icon[500];

	public ItemGelidEnderiumMaterials(int id) {
		super(id);
		this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		String name = "";
		switch (itemstack.getItemDamage()) {
			case 0: {
				name = "ingot";
				break;
			}
			case 1: {
				name = "nugget";
				break;
			}
			case 2: {
				name = "gem";
				break;
			}
			case 3: {
				name = "rod";
				break;
			}
			default:
				name = "nothing";
				break;
		}
		return getUnlocalizedName() + "." + name;
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta) {
		return this.icon[meta];
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ri) {
		this.icon[0] = ri.registerIcon(ModInformation.ID + ":materials/IngotGelidEnderium");
		this.icon[1] = ri.registerIcon(ModInformation.ID + ":materials/NuggetGelidEnderium");
		this.icon[2] = ri.registerIcon(ModInformation.ID + ":materials/gemGelidEnderium");
		this.icon[3] = ri.registerIcon(ModInformation.ID + ":materials/rodGelidEnderium");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemDisplayName(ItemStack itemStack) {
		return TextHelper.BRIGHT_BLUE + super.getItemDisplayName(itemStack);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs par2CreativeTabs, List list) {
		for (int i = 0; i <= 3; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}
}
