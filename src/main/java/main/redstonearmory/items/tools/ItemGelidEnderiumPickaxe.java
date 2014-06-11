package main.redstonearmory.items.tools;

import gnu.trove.set.hash.THashSet;
import main.redstonearmory.ModInformation;
import main.redstonearmory.items.itemutil.ItemToolRF;
import main.redstonearmory.util.MathHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.Random;

public class ItemGelidEnderiumPickaxe extends ItemToolRF {

	Icon activeIcon;
	Icon drainedIcon;

	int range = 4;
    Random random = new Random();

    public THashSet<Block> effectiveBlocksCharged = new THashSet<Block>();

    public ItemGelidEnderiumPickaxe(int id, EnumToolMaterial toolMaterial) {

        super(id, toolMaterial);

        addToolClass("pickaxe");
        damage = 4;
        energyPerUseCharged = 800;

        effectiveMaterials.add(Material.iron);
        effectiveMaterials.add(Material.anvil);
        effectiveMaterials.add(Material.rock);
    }

    public ItemGelidEnderiumPickaxe(int id, EnumToolMaterial toolMaterial, int harvestLevel) {

        this(id, toolMaterial);
        this.harvestLevel = harvestLevel;
    }

	@Override
	public Icon getIcon(ItemStack stack, int pass) {

		return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
	}

	@Override
	public void registerIcons(IconRegister ir) {

		this.itemIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumPickaxe");
		this.activeIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumPickaxe_active");
		this.drainedIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumPickaxe_drained");
	}

    @Override
    protected THashSet<Block> getEffectiveBlocks(ItemStack stack) {

        return isEmpowered(stack) ? effectiveBlocksCharged : effectiveBlocks;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, int blockId, int x, int y, int z, EntityLivingBase entity) {

        Block block = Block.blocksList[world.getBlockId(x, y, z)];

        if (!(entity instanceof EntityPlayer)) {
            return false;
        }
        EntityPlayer player = (EntityPlayer) entity;

        if ((block == Block.cobblestone || block == Block.stone || block == Block.sandStone || block == Block.netherrack) && isEmpowered(stack)) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int k = z - 1; k <= z + 1; k++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (Block.blocksList[world.getBlockId(i, j, k)] == Block.cobblestone || Block.blocksList[world.getBlockId(i, j, k)] == Block.stone || Block.blocksList[world.getBlockId(i, j, k)] == Block.sandStone || Block.blocksList[world.getBlockId(i, j, k)] == Block.netherrack) {
                            int facing = MathHelper.floor(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

                            if (facing == 0) {
                                int coordZ = z - range;
                                if (world.isAirBlock(i, j, coordZ)) {
                                    world.setBlockToAir(i, j, z);
                                    world.setBlock(i, j, coordZ, block.blockID);
                                    for (int n = 0; n <= 5; n++)
                                        world.spawnParticle("portal", i, j, z, 1, 1, 1);
                                    if (random.nextInt(10) == 0)
                                        world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
                                } else
                                    harvestBlock(world, i, j, z, player);
                            } else if (facing == 1) {
                                int coordX = x + range;
                                if (world.isAirBlock(coordX, j, k)) {
                                    world.setBlockToAir(x, j, k);
                                    world.setBlock(coordX, j, k, block.blockID);
                                    for (int n = 0; n <= 5; n++)
                                        world.spawnParticle("portal", x, j, k, 1, 1, 1);
                                    if (random.nextInt(10) == 0)
                                        world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
                                } else
                                    harvestBlock(world, x, j, k, player);
                            } else if (facing == 2) {
                                int coordZ = z + range;
                                if (world.isAirBlock(i, j, coordZ)) {
                                    world.setBlockToAir(i, j, z);
                                    world.setBlock(i, j, coordZ, block.blockID);
                                    for (int n = 0; n <= 5; n++)
                                        world.spawnParticle("portal", i, j, z, 1, 1, 1);
                                    if (random.nextInt(10) == 0)
                                        world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
                                } else
                                    harvestBlock(world, i, j, z, player);
                            } else if (facing == 3) {
                                int coordX = x - range;
                                if (world.isAirBlock(coordX, j, k)) {
                                    world.setBlockToAir(x, j, k);
                                    world.setBlock(coordX, j, k, block.blockID);
                                    for (int n = 0; n <= 5; n++)
                                        world.spawnParticle("portal", x, j, k, 1, 1, 1);
                                    if (random.nextInt(10) == 0)
                                        world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
                                } else
                                    harvestBlock(world, x, j, k, player);
                            }
                        }
                    }
                }
            }
            if (!player.capabilities.isCreativeMode)
                useEnergy(stack, false);

            return true;
        }
        if (!player.capabilities.isCreativeMode) {
            useEnergy(stack, false);
        }
        return true;
    }

}
