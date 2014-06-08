package main.redstonearmory.items.tools;

import main.redstonearmory.items.itemutil.ItemToolRF;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

import java.util.Random;

public class ItemGelidEnderiumAxe extends ItemToolRF {

	Random random = new Random();

	public ItemGelidEnderiumAxe(EnumToolMaterial toolMaterial) {

		super(toolMaterial);
		damage = 6;
		maxEnergy = 320000;
		energyPerUse = 350;
		energyPerUseCharged = 15000;

		effectiveMaterials.add(Material.wood);
		effectiveMaterials.add(Material.plants);
		effectiveMaterials.add(Material.leaves);
		effectiveMaterials.add(Material.vine);
	}

	public ItemGelidEnderiumAxe(EnumToolMaterial toolMaterial, int harvestLevel) {

		this(toolMaterial);
		this.harvestLevel = harvestLevel;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {

		if (!(entity instanceof EntityPlayer)) {
			return false;
		}
		if (block.getBlockHardness(world, x, y, z) == 0.0D) {
			return true;
		}
		EntityPlayer player = (EntityPlayer) entity;

		if (block.getMaterial() == Material.wood && isEmpowered(stack)) {
			for (int i = x - 2; i <= x + 2; i++) {
				for (int k = z - 2; k <= z + 2; k++) {
					for (int j = y - 2; j <= y + 2; j++) {
						if (world.getBlock(i, j, k).getMaterial() == Material.wood) {
							harvestBlock(world, i, j, k, player);
						}
					}
				}
			}
		}
		if (!player.capabilities.isCreativeMode) {
			useEnergy(stack, false);
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

		if (world.isRaining() && isEmpowered(stack) || world.isThundering() && isEmpowered(stack)) {
			WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
			WorldInfo worldinfo = worldserver.getWorldInfo();

			int i = (300 + (new Random()).nextInt(600)) * 20;

			worldinfo.setRaining(false);
			worldinfo.setThundering(false);
			worldinfo.setRainTime(i);

			if (random.nextInt(50) == 0)
				world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX, player.posY, player.posZ));

			if (!player.capabilities.isCreativeMode)
				useEnergy(stack, false);
			player.swingItem();
		}
		return stack;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {
		if(!(getEnergyStored(stack) < energyPerUse)) {
			if (!isEmpowered(stack)) {
				world.spawnEntityInWorld(new EntityLightningBolt(world, x, y, z));
			} else if (isEmpowered(stack) && getEnergyStored(stack) >= energyPerUseCharged) {
				for (int i = 0; i <= 10; i++) {
					world.spawnEntityInWorld(new EntityLightningBolt(world, x, y, z));
					if (random.nextInt(50) == 0)
						world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX, player.posY, player.posZ));
				}
			}
		}

		if (!player.capabilities.isCreativeMode)
			useEnergy(stack, false);

		return true;
	}

}
