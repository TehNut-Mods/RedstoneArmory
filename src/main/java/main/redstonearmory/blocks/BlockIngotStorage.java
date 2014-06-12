package main.redstonearmory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.RFHelper;
import main.redstonearmory.util.RFItemUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIngotStorage extends Block {

	@SideOnly(Side.CLIENT)
	private Icon icon;

	public static final String[] NAMES = { "" };

	public static boolean enableDamage[] = new boolean[3];
	public static boolean enableDamageCharge[] = new boolean[3];
	public static double damage[] = new double[3];
	public static int chargeRate = 50;

	public BlockIngotStorage(int par1) {
		super(par1, Material.iron);
		setStepSound(soundMetalFootstep);
		setUnlocalizedName(ModInformation.ID + ".enderium.gelid");
		setCreativeTab(RedstoneArmory.tabRedstoneArmory);
		setHardness(4F);
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
	public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean isBeaconBase(World worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

		final float f = 0.0625F;
		return AxisAlignedBB.getAABBPool().getAABB(x + f, y, z + f, x + 1 - f, y + 1 - f, z + 1 - f);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {

		return 7;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

		if (!world.isRemote || entity instanceof EntityItem) {
			return;
		}
		double fluxDamage = 0;
		if (enableDamage[2]) {
			fluxDamage = damage[2];
		}
		if (fluxDamage > 0) {
			entity.attackEntityFrom(RFItemUtils.flux, (float) fluxDamage);

			if (entity instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) entity;
				if (RFHelper.isPlayerHoldingEnergyContainerItem(player)) {
					RFHelper.insertEnergyIntoHeldContainer(player, (int) (chargeRate * fluxDamage), false);
				}
			}
		}
	}
}

