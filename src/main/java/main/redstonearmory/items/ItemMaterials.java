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

public class ItemMaterials extends Item {

	public IIcon[] icon = new IIcon[16];

	public ItemMaterials() {
		this.setCreativeTab(RedstoneArmory.tabRArm);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		String name;
		switch (itemstack.getItemDamage()) {
			case 0: {
				name = "enderium.gelid.ingot";
				break;
			}
			case 1: {
				name = "enderium.gelid.nugget";
				break;
			}
			case 2: {
				name = "enderium.gelid.gem";
				break;
			}
			case 3: {
				name = "enderium.gelid.rod";
				break;
			}
			case 4: {
				name = "chainlink";
				break;
			}
			case 5: {
				name = "iron.nugget";
				break;
			}
			default:
				name = "nothing";
				break;
		}
		return getUnlocalizedName() + ".material." + name;
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
		this.icon[4] = iconRegister.registerIcon(ModInformation.ID + ":materials/chainLink");
		this.icon[5] = iconRegister.registerIcon(ModInformation.ID + ":materials/nuggetIron");
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i <= 5; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		switch (itemStack.getItemDamage()) {
			case 0: {
				return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
			}
			case 1: {
				return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
			}
			case 2: {
				return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
			}
			case 3: {
				return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
			}
			case 4: {
				return TextHelper.END + super.getItemStackDisplayName(itemStack);
			}
			case 5: {
				return TextHelper.END + super.getItemStackDisplayName(itemStack);
			}
		}
		return super.getItemStackDisplayName(itemStack);
	}
}