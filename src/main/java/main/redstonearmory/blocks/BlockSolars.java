package main.redstonearmory.blocks;

import cofh.core.block.BlockCoFHBase;
import cofh.core.util.CoreUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.items.blocks.ItemBlockSolars;
import main.redstonearmory.tile.TileSolarBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockSolars extends BlockCoFHBase {

	private IIcon machineBottom;

	private IIcon solarTop_on;
	private IIcon solarTop_off;
	private IIcon solarTop_dirty;

	private IIcon solarSide_basicOn;
	private IIcon solarSide_basicOff;

	private IIcon solarSide_hardenedOn;
	private IIcon solarSide_hardenedOff;

	private IIcon solarSide_reinforcedOn;
	private IIcon solarSide_reinforcedOff;

	private IIcon solarSide_resonantOn;
	private IIcon solarSide_resonantOff;


	public BlockSolars() {
		super(Material.iron);

		setBlockName(ModInformation.ID + ".solar");
		setCreativeTab(RedstoneArmory.tabRArm);
		setHardness(5F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return machineBottom;
		}
		if (side == 1) {
			if (TileSolarBase.isProducing && TileSolarBase.canProduce) {
				return solarTop_on;
			} else if (!TileSolarBase.isProducing && TileSolarBase.canProduce) {
				return solarTop_off;
			} else if (!TileSolarBase.isProducing && !TileSolarBase.canProduce) {
				return solarTop_dirty;
			}
		}
		if (side == 2 || side == 3 || side == 4 || side == 5) {
			if(TileSolarBase.isProducing) {
				if (meta == 0) {
					return solarSide_basicOn;
				}
				if (meta == 1) {
					return solarSide_hardenedOn;
				}
				if (meta == 2) {
					return solarSide_reinforcedOn;
				}
				if (meta == 3) {
					return solarSide_resonantOn;
				}
			} else {
				if (meta == 0) {
					return solarSide_basicOff;
				}
				if (meta == 1) {
					return solarSide_hardenedOff;
				}
				if (meta == 2) {
					return solarSide_reinforcedOff;
				}
				if (meta == 3) {
					return solarSide_resonantOff;
				}
			}
		}

		return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = machineBottom;
		machineBottom = iconRegister.registerIcon(ModInformation.ID + ":solars/Machine_bottom");

		solarTop_on = iconRegister.registerIcon(ModInformation.ID + ":solars/SolarTop_on");
		solarTop_off = iconRegister.registerIcon(ModInformation.ID + ":solars/SolarTop_off");
		solarTop_dirty = iconRegister.registerIcon(ModInformation.ID + ":solars/SolarTop_dirty");

		solarSide_basicOn = iconRegister.registerIcon(ModInformation.ID + ":solars/SolarSide_basicOn");
		solarSide_basicOff = iconRegister.registerIcon(ModInformation.ID + ":solars/SolarSide_basicOff");

		solarSide_hardenedOn = iconRegister.registerIcon(ModInformation.ID + ":solars/SolarSide_hardenedOn");
		solarSide_hardenedOff = iconRegister.registerIcon(ModInformation.ID + ":solars/SolarSide_hardenedOff");

		solarSide_reinforcedOn = iconRegister.registerIcon(ModInformation.ID + ":solars/SolarSide_reinforcedOn");
		solarSide_reinforcedOff = iconRegister.registerIcon(ModInformation.ID + ":solars/SolarSide_reinforcedOff");

		solarSide_resonantOn = iconRegister.registerIcon(ModInformation.ID + ":solars/SolarSide_resonantOn");
		solarSide_resonantOff = iconRegister.registerIcon(ModInformation.ID + ":solars/SolarSide_resonantOff");
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < ItemBlockSolars.names.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileSolarBase();
	}

	@Override
	public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, NBTTagCompound nbtTagCompound, World world, int x, int y, int z, boolean returnDrops, boolean simulate) {
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
		int meta = world.getBlockMetadata(x, y, z);

		stacks.add(new ItemStack(this, 1, meta));

		if(!simulate) {
			world.setBlockToAir(x, y, z);

			if(!returnDrops) {
				for(ItemStack stack: stacks) {
					CoreUtils.dropItemStackIntoWorldWithVelocity(stack, world, x, y, z);
				}
			}
		}

		if(!returnDrops) {
			stacks.clear();
		}

		return stacks;
	}

	@Override
	public boolean initialize() {
		return false;
	}

	@Override
	public boolean postInit() {
		return false;
	}
}
