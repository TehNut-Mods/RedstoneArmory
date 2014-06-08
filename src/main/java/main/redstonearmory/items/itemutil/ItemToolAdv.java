package main.redstonearmory.items.itemutil;

/*
 * DISCLAIMER ABOUT THIS CLASS
 *
 * This was originally by the CoFH team for (AFAIK) 1.7. We have backported it for our needs in 1.6.4. 99% of the code is exactly the same.
 */

import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TLinkedHashSet;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

import java.util.Set;

public abstract class ItemToolAdv extends ItemTool {

	private final TLinkedHashSet<String> toolClasses = new TLinkedHashSet<String>();
	private final Set<String> immutableClasses = java.util.Collections.unmodifiableSet(toolClasses);

	protected THashSet<Block> effectiveBlocks = new THashSet<Block>();
	protected THashSet<Material> effectiveMaterials = new THashSet<Material>();
	protected int harvestLevel = -1;

	public ItemToolAdv(int id, float baseDamage, EnumToolMaterial toolMaterial) {

		super(id, baseDamage, toolMaterial, null);
	}

	public ItemToolAdv( int id, float baseDamage, EnumToolMaterial toolMaterial, Block[] listOfBlocks) {

		this(id, baseDamage, toolMaterial);
	}

	protected void addToolClass(String string) {

		toolClasses.add(string);
	}

	protected THashSet<Block> getEffectiveBlocks(ItemStack stack) {

		return effectiveBlocks;
	}

	protected THashSet<Material> getEffectiveMaterials(ItemStack stack) {

		return effectiveMaterials;
	}

	protected boolean isClassValid(String toolClass, ItemStack stack) {

		return true;
	}

	protected float getEfficiency(ItemStack stack) {

		return efficiencyOnProperMaterial;
	}

	protected int getHarvestLevel(ItemStack stack, int level) {

		return level;
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {

		return false;
	}

	public float func_150893_a(ItemStack stack, Block block) {

		return (getEffectiveMaterials(stack).contains(block.blockMaterial) || getEffectiveBlocks(stack).contains(block)) ? getEfficiency(stack) : 1.0F;
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack) {

		return func_150893_a(stack, block) > 1.0f;
	}

	protected void harvestBlock(World world, int x, int y, int z, EntityPlayer player) {

		int block = world.getBlockId(x, y, z);

		if (block.getBlockHardness(world, x, y, z) < 0) {
			return;
		}
		int bMeta = world.getBlockMetadata(x, y, z);

		if (block.canHarvestBlock(player, bMeta)) {
			block.harvestBlock(world, player, x, y, z, bMeta);
		}
		world.setBlockToAir(x, y, z);
	}

	protected boolean isValidHarvestMaterial(ItemStack stack, World world, int id, int x, int y, int z) {

		return getEffectiveMaterials(stack).contains(world.getBlockId(x, y, z));
	}

	public int getHarvestLevel(ItemStack stack, String toolClass) {

		if (harvestLevel != -1) {
			return harvestLevel;
		}
		int level = getHarvestLevel(stack, toolClass);
		if (level == -1 && isClassValid(toolClass, stack) && toolClasses.contains(toolClass)) {
			level = toolMaterial.getHarvestLevel();
		}
		return getHarvestLevel(stack, level);
	}

	public Set<String> getToolClasses(ItemStack stack) {

		return toolClasses.isEmpty() ? getToolClasses(stack) : immutableClasses;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {

		for (String type : getToolClasses(stack)) {
			int level = getHarvestLevel(stack, type);

			if (type.equals(block.getHarvestTool(meta))) {
				if (block.getHarvestLevel(meta) < level) {
					return getEfficiency(stack);
				}
			}
		}
		return getDigSpeed(stack, block, meta);
	}

}
