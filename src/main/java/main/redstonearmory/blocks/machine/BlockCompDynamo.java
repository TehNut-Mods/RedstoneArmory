package main.redstonearmory.blocks.machine;

import cofh.api.energy.IEnergyStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.RedstoneArmory;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import java.util.List;

@SuppressWarnings("all")
public class BlockCompDynamo extends Block implements IEnergyStorage {

	public int maxStored = 400000;

	public Icon[] icon = new Icon[4];

	public BlockCompDynamo(int id, Material material) {
		super(id, material);
		this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
		this.setLightOpacity(1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		icon[0] = register.registerIcon("redstonearmory:dynamo/dynamoCompSteam");
		icon[1]= register.registerIcon("redstonearmory:dynamo/dynamoCompMagmatic");
		icon[2] = register.registerIcon("redstonearmory:dynamo/dynamoCompCompression");
		icon[3] = register.registerIcon("redstonearmory:dynamo/dynamoCompReactant");
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 0;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean shouldRenderBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return this.icon[meta];
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < 4; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		return maxReceive;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return maxExtract;
	}

	@Override
	public int getEnergyStored() {
		return getMaxEnergyStored();
	}

	@Override
	public int getMaxEnergyStored() {
		return maxStored;
	}
}
