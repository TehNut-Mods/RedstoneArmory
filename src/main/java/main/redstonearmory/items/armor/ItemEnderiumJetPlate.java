package main.redstonearmory.items.armor;

import cofh.lib.util.helpers.EnergyHelper;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.item.ItemIndex;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.setup.SJItems;

import java.util.List;

public class ItemEnderiumJetPlate extends ItemJetpack {

	public ItemEnderiumJetPlate(ItemIndex index, SJItems.ModType modType) {
		super(index, modType);
		this.setUnlocalizedName(ModInformation.ID + "jetpack");
		this.setCreativeTab(RedstoneArmory.tabRArm);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {

		list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
		list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), getMaxEnergyStored(new ItemStack(this, 1))));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + ".enderium";
	}

	@Override
	public int getMaxEnergyStored(ItemStack stack) {
		return 100000000;
	}
}
