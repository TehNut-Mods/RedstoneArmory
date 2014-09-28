package main.redstonearmory.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.TextHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemGelidMaterials extends Item {

	public IIcon[] icon = new IIcon[16];

	public ItemGelidMaterials() {
		this.setCreativeTab(RedstoneArmory.tabRArm);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
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
		return getUnlocalizedName() + ".material.enderium.gelid." + name;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return this.icon[meta];
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.icon[0] = iconRegister.registerIcon(ModInformation.ID + ":materials/ingotGelidEnderium");
		this.icon[1] = iconRegister.registerIcon(ModInformation.ID + ":materials/nuggetGelidEnderium");
		this.icon[2] = iconRegister.registerIcon(ModInformation.ID + ":materials/gemGelidEnderium");
		this.icon[3] = iconRegister.registerIcon(ModInformation.ID + ":materials/rodGelidEnderium");
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i <= 3; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
	}
}
