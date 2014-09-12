package main.redstonearmory.items.random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemThrowableNut extends ItemSnowball {

	public ItemThrowableNut() {
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setUnlocalizedName(ModInformation.ID + ".random.nut.throwable");
	}

	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(ModInformation.ID + ":random/nut_throwable");
	}

	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
		list.add("It'll throw a custom entity soon");
		list.add("Too much work right now >.>");
	}
}
