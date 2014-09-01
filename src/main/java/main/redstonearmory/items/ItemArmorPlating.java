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

	public IIcon[] icon = new IIcon[7];

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
			case 1: {
				name = "lumium";
				break;
			}
			case 2: {
				name = "mithril";
				break;
			}
			case 3: {
				name = "bronze";
				break;
			}
			case 4: {
				name = "tuberous";
				break;
			}
			case 5: {
				name = "crafting.empty";
				break;
			}
			case 6: {
				name = "crafting.full";
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
		this.icon[0] = iconRegister.registerIcon(ModInformation.ID + ":materials/plateEnderium");
		this.icon[1] = iconRegister.registerIcon(ModInformation.ID + ":materials/plateLumium");
		this.icon[2] = iconRegister.registerIcon(ModInformation.ID + ":materials/plateMithril");
		this.icon[3] = iconRegister.registerIcon(ModInformation.ID + ":materials/plateBronze");
		this.icon[4] = iconRegister.registerIcon(ModInformation.ID + ":materials/plateTuberous");
		this.icon[5] = iconRegister.registerIcon(ModInformation.ID + ":materials/plateCraftingEmpty");
		this.icon[6] = iconRegister.registerIcon(ModInformation.ID + ":materials/plateCraftingFull");
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i <= 6; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {

		switch(itemStack.getItemDamage()) {
			case 0: {
				return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
			}
			case 1: {
				return TextHelper.YELLOW + super.getItemStackDisplayName(itemStack);
			}
			case 2: {
				return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
			}
			default: {
				return TextHelper.WHITE + super.getItemStackDisplayName(itemStack);
			}
		}
	}
}
