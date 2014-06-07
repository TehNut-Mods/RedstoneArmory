package main.redstonearmory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockIngotStorage extends Block {

	@SideOnly(Side.CLIENT)
	private Icon icon;

	public BlockIngotStorage(int par1) {
			super(par1, Material.iron);
			setStepSound(soundMetalFootstep);
			setUnlocalizedName(ModInformation.ID + ".blocks.enderium.gelid");
			setCreativeTab(RedstoneArmory.tabRedstoneArmory);
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

}
