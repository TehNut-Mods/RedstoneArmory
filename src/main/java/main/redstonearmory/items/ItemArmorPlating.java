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

public class ItemArmorPlating extends Item {

	public IIcon[] icon = new IIcon[4];

	public ItemArmorPlating() {
		this.setCreativeTab(RedstoneArmory.tabRArm);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		String name = "";
		switch (itemstack.getItemDamage()) {
			case 0: {
				name = "enderium";
				break;
			}
			default:
				name = "nothing";
				break;
		}
		return getUnlocalizedName() + ".material." + name + ".plating";
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return this.icon[meta];
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.icon[0] = iconRegister.registerIcon(ModInformation.ID + ":materials/enderiumPlate");
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i <= 0; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
	}
}
