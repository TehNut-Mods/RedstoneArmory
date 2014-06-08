package main.redstonearmory.items.tools;

import main.redstonearmory.items.itemutil.ItemToolRF;
import main.redstonearmory.util.KeyboardHandler;
import main.redstonearmory.util.TextHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.BonemealEvent;

import java.util.List;

public class ItemGelidEnderiumShovel extends ItemToolRF {

	int range = 5;

	public ItemGelidEnderiumShovel(EnumToolMaterial toolMaterial) {

		super(toolMaterial);

		addToolClass("shovel");
		maxEnergy = 320000;
		energyPerUse = 350;
		energyPerUseCharged = 950;

		effectiveMaterials.add(Material.ground);
		effectiveMaterials.add(Material.sand);
		effectiveMaterials.add(Material.clay);
		effectiveMaterials.add(Material.craftedSnow);
		effectiveMaterials.add(Material.snow);
	}

	public ItemGelidEnderiumShovel(EnumToolMaterial toolMaterial, int harvestLevel) {

		this(toolMaterial);
		this.harvestLevel = harvestLevel;
	}

//	@Override
	public boolean func_150897_b(Block block) {

		return block == Block.snow || block == Block.blockSnow;
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

		if (effectiveBlocks.contains(block) && isEmpowered(stack)) {
			for (int i = x - 2; i <= x + 2; i++) {
				for (int k = z - 2; k <= z + 2; k++) {
					for (int j = y - 2; j <= y + 2; j++) {
						if (world.getBlock(i, j, k) == block) {
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
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {
		if (!player.canPlayerEdit(x, y, z, hitSide, stack) || !player.capabilities.isCreativeMode && getEnergyStored(stack) < getEnergyPerUse(stack)) {
			return false;
		}

		Block block = world.getBlock(x, y, z);
		BonemealEvent event = new BonemealEvent(player, world, block, x, y, z);

		if (MinecraftForge.EVENT_BUS.post(event)) {
			return false;
		}

		if (event.getResult() == Result.ALLOW) {
			if (!player.capabilities.isCreativeMode) {
				useEnergy(stack, false);
			}
			return true;
		}

		if (block instanceof IGrowable) {
			IGrowable growable = (IGrowable) block;

			if (growable.$plus$eq(world, x, y, z, world.isRemote)) {
				if (!world.isRemote) {
					if (growable.func_149852_a(world, world.rand, x, y, z)) {
						growable.func_149853_b(world, world.rand, x, y, z);
					}

					if (growable.func_149852_a(world, world.rand, x, y, z) && isEmpowered(stack)) {
						for (int i = 0; i <= 5; i++) {
							growable.func_149853_b(world, world.rand, x, y, z);
						}
					}
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {

		super.addInformation(stack, player, list, check);
		if (!KeyboardHandler.isShiftDown()) {
			return;
		}
		list.add(TextHelper.localize("info.redstonearmory.tool.gelidenderium.shovel"));
	}

}
