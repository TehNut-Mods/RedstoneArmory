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

	public String[] names = { "enderium.gelid.ingot", "enderium.gelid.nugget", "enderium.gelid.gem", "enderium.gelid.rod", "chainlink", "iron.nugget" };
	public IIcon[] icon = new IIcon[16];

	public ItemMaterials() {
		setCreativeTab(RedstoneArmory.tabRArm);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName() + ".material." + names[stack.getItemDamage() % names.length];
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
	public String getItemStackDisplayName(ItemStack stack) {
		switch (stack.getItemDamage()) {
			case 0: {
				return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(stack);
			}
			case 1: {
				return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(stack);
			}
			case 2: {
				return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(stack);
			}
			case 3: {
				return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(stack);
			}
			case 4: {
				return TextHelper.END + super.getItemStackDisplayName(stack);
			}
			case 5: {
				return TextHelper.END + super.getItemStackDisplayName(stack);
			}
			default: {
				return super.getItemStackDisplayName(stack);
			}
		}
	}
}