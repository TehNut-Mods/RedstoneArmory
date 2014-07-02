package main.redstonearmory.blocks.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.RedstoneArmory;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import thermalexpansion.block.dynamo.BlockDynamo;

import java.util.List;

@SuppressWarnings("all")
public class BlockCompDynamo extends BlockDynamo {

	@SideOnly(Side.CLIENT)
	private Icon compSteam;
	@SideOnly(Side.CLIENT)
	private Icon compCompression;
	@SideOnly(Side.CLIENT)
	private Icon compMagmatic;
	@SideOnly(Side.CLIENT)
	private Icon compReactant;

	public BlockCompDynamo(int id) {
		super(id);
		this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon("redstonearmory:dynamoCompSteam");
		compSteam = register.registerIcon("redstonearmory:dynamoCompSteam");
		compMagmatic = register.registerIcon("redstonearmory:dynamoCompMagmatic");
		compCompression = register.registerIcon("redstonearmory:dynamoCompCompression");
		compReactant = register.registerIcon("redstonearmory:dynamoCompReactant");
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (meta == 0) {
			return compSteam;
		} else if (meta == 1) {
			return compCompression;
		} else if (meta == 2) {
			return compMagmatic;
		} else if (meta == 3) {
			return compReactant;
		}
		return compSteam;
	}

	@Override
	public void getSubBlocks(int block, CreativeTabs tab, List list) {
		list.add(new ItemStack(block, 1, 0));
		list.add(new ItemStack(block, 1, 1));
		list.add(new ItemStack(block, 1, 2));
		list.add(new ItemStack(block, 1, 3));
	}

	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
		return false;
	}
}
