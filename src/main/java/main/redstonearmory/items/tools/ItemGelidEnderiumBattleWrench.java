package main.redstonearmory.items.tools;

import buildcraft.api.tools.IToolWrench;
import cofh.api.block.IDismantleable;
import cofh.api.energy.IEnergyContainerItem;
import ic2.api.tile.IWrenchable;
import main.redstonearmory.util.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import railcraft.api.core.items.IToolCrowbar;

import java.util.List;

public class ItemGelidEnderiumBattleWrench extends ItemGelidEnderiumSword implements IToolCrowbar, IToolWrench, IEnergyContainerItem {

	public ItemGelidEnderiumBattleWrench(int par1, EnumToolMaterial toolMaterial) {

		super(par1, toolMaterial);
		damage = 6;
		damageCharged = 3;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {

		entity.rotationYaw += 90;
		entity.rotationYaw %= 360;
		return super.hitEntity(stack, entity, player);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {

		return true;
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {

		if (stack.getItemDamage() > 0) {
			stack.setItemDamage(0);
		}
		if (!player.capabilities.isCreativeMode && getEnergyStored(stack) < getEnergyPerUse(stack)) {
			return false;
		}
		Block block = Block.blocksList[world.getBlockId(x, y, z)];

		if (world.isRemote && player.isSneaking() && block instanceof IDismantleable
				&& ((IDismantleable) block).canDismantle(player, world, x, y, z)) {
			((IDismantleable) block).dismantleBlock(player, world, x, y, z, false);

			if (!player.capabilities.isCreativeMode) {
				useEnergy(stack, false);
			}
			return true;
		}
		if (BlockHelper.canRotate(block)) {
			int bMeta = world.getBlockMetadata(x, y, z);

			if (player.isSneaking()) {
				world.setBlockMetadataWithNotify(x, y, z, BlockHelper.rotateVanillaBlockAlt(world, block, x, y, z), 3);

				if (!world.isRemote) {
					String soundName = block.stepSound.getBreakSound();
					//FMLClientHandler.instance().getClient().getSoundHandler().playSound(new SoundBase(soundName, 1.0F, 0.6F));
				}
			} else {
				world.setBlockMetadataWithNotify(x, y, z, BlockHelper.rotateVanillaBlock(world, block, x, y, z), 3);

				if (!world.isRemote) {
					String soundName = block.stepSound.getBreakSound();
					//FMLClientHandler.instance().getClient().getSoundHandler().playSound(new SoundBase(soundName, 1.0F, 0.8F));
				}
			}
			if (!player.capabilities.isCreativeMode) {
				useEnergy(stack, false);
			}
			return world.isRemote;
		} else if (!player.isSneaking() && block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(hitSide))) {
			if (!player.capabilities.isCreativeMode) {
				useEnergy(stack, false);
			}
			player.swingItem();
			return world.isRemote;
		}
		TileEntity tile = world.getBlockTileEntity(x, y, z);

		if (tile instanceof IWrenchable) {
			IWrenchable wrenchable = (IWrenchable) tile;

			if (player.isSneaking()) {
				hitSide = BlockHelper.SIDE_OPPOSITE[hitSide];
			}
			if (wrenchable.wrenchCanSetFacing(player, hitSide)) {
				if (world.isRemote) {
					wrenchable.setFacing((short) hitSide);
				}
			} else if (wrenchable.wrenchCanRemove(player)) {
				ItemStack dropBlock = wrenchable.getWrenchDrop(player);

				if (dropBlock != null) {
					world.setBlockToAir(x, y, z);
					if (world.isRemote) {
						List<ItemStack> drops = block.getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), 0);

						if (drops.isEmpty()) {
							drops.add(dropBlock);
						} else {
							drops.set(0, dropBlock);
						}
						for (ItemStack drop : drops) {
							float f = 0.7F;
							double x2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
							double y2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
							double z2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
							EntityItem entity = new EntityItem(world, x + x2, y + y2, z + z2, drop);
							entity.delayBeforeCanPickup = 10;
							world.spawnEntityInWorld(entity);
						}
					}
				}
			}
			if (!player.capabilities.isCreativeMode) {
				useEnergy(stack, false);
			}
			return world.isRemote;
		}
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isCurrentItem) {

		if (stack.getItemDamage() > 0) {
			stack.setItemDamage(0);
		}
		super.onUpdate(stack, world, entity, slot, isCurrentItem);
	}

//	@Override
	public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {

		return true;
	}

	/* IToolCrowbar */
	@Override
	public boolean canWhack(EntityPlayer player, ItemStack crowbar, int x, int y, int z) {

		return getEnergyStored(crowbar) >= getEnergyPerUse(crowbar) || player.capabilities.isCreativeMode;
	}

	@Override
	public void onWhack(EntityPlayer player, ItemStack crowbar, int x, int y, int z) {

		if (!player.capabilities.isCreativeMode) {
			useEnergy(crowbar, false);
		}
		player.swingItem();
	}

	@Override
	public boolean canLink(EntityPlayer player, ItemStack crowbar, EntityMinecart cart) {

		return player.isSneaking() && getEnergyStored(crowbar) >= getEnergyPerUse(crowbar) || player.capabilities.isCreativeMode;
	}

	@Override
	public void onLink(EntityPlayer player, ItemStack crowbar, EntityMinecart cart) {

		if (!player.capabilities.isCreativeMode) {
			useEnergy(crowbar, false);
		}
		player.swingItem();
	}

	@Override
	public boolean canBoost(EntityPlayer player, ItemStack crowbar, EntityMinecart cart) {

		return !player.isSneaking() && getEnergyStored(crowbar) >= getEnergyPerUse(crowbar);
	}

	@Override
	public void onBoost(EntityPlayer player, ItemStack crowbar, EntityMinecart cart) {

		if (!player.capabilities.isCreativeMode) {
			useEnergy(crowbar, false);
		}
		player.swingItem();
	}

	/* IToolWrench */
	@Override
	public boolean canWrench(EntityPlayer player, int x, int y, int z) {

		ItemStack stack = player.getCurrentEquippedItem();
		return getEnergyStored(stack) >= getEnergyPerUse(stack) || player.capabilities.isCreativeMode;
	}

	@Override
	public void wrenchUsed(EntityPlayer player, int x, int y, int z) {

		if (!player.capabilities.isCreativeMode) {
			useEnergy(player.getCurrentEquippedItem(), false);
		}
	}

}
