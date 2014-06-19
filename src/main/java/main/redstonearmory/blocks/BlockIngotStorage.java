package main.redstonearmory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.RedstoneArmory;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import redstonearsenal.block.BlockStorage;

import java.util.List;

public class BlockIngotStorage extends BlockStorage {

	@SideOnly(Side.CLIENT)
	private Icon icon;

	public BlockIngotStorage(int id) {
		super(id);
		this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		icon = register.registerIcon("redstonearmory:Block_GelidEnderium");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		return icon;
	}

	@Override
	public void getSubBlocks(int ItemID, CreativeTabs tab, List list) {
	
	}
}