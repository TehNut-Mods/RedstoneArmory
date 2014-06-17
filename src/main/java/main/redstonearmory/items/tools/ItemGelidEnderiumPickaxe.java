package main.redstonearmory.items.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.KeyboardHandler;
import main.redstonearmory.util.MathHelper;
import main.redstonearmory.util.RFHelper;
import main.redstonearmory.util.TextHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import redstonearsenal.item.tool.ItemPickaxeRF;

import java.util.List;
import java.util.Random;

public class ItemGelidEnderiumPickaxe extends ItemPickaxeRF {

    String tool = "pickaxe";
    Icon activeIcon;
    Icon drainedIcon;
    int range = 4;
    Random random = new Random();

    public ItemGelidEnderiumPickaxe(int id, EnumToolMaterial toolMaterial) {
        super(id, toolMaterial);
        maxEnergy = 320000;
        energyPerUse = 350;
        energyPerUseCharged = 800;

        effectiveMaterials.add(Material.iron);
        effectiveMaterials.add(Material.anvil);
        effectiveMaterials.add(Material.rock);
        effectiveMaterials.add(Material.glass);
        this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
    }

    public ItemGelidEnderiumPickaxe(int id, EnumToolMaterial toolMaterial, int harvestLevel) {
        this(id, toolMaterial);
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

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        return TextHelper.BRIGHT_BLUE + super.getItemDisplayName(itemStack);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, int block, int x, int y, int z, EntityLivingBase entity) {

        if (!(entity instanceof EntityPlayer)) {
            return false;
        }
        EntityPlayer player = (EntityPlayer) entity;

        if ((block == Block.cobblestone.blockID || block == Block.stone.blockID || block == Block.sandStone.blockID || block == Block.netherrack.blockID) && isEmpowered(stack)) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int k = z - 1; k <= z + 1; k++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (Block.blocksList[world.getBlockId(i, j, k)] == Block.cobblestone || Block.blocksList[world.getBlockId(i, j, k)] == Block.stone || Block.blocksList[world.getBlockId(i, j, k)] == Block.sandStone || Block.blocksList[world.getBlockId(i, j, k)] == Block.netherrack) {
                            int facing = MathHelper.floor(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;

                            if (facing == 0) {
                                int coordZ = z - range;
                                if (world.isAirBlock(i, j, coordZ)) {
                                    world.setBlockToAir(i, j, z);
                                    world.setBlock(i, j, coordZ, block);
                                    for (int n = 0; n <= 5; n++)
                                        world.spawnParticle("portal", i, j, z, 1, 1, 1);
                                    if (random.nextInt(10) == 0)
                                        world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
                                }
                                // else {
                                //  harvestBlock(world, i, j, z, player);
                                //  }
                            } else if (facing == 1) {
                                int coordX = x + range;
                                if (world.isAirBlock(coordX, j, k)) {
                                    world.setBlockToAir(x, j, k);
                                    world.setBlock(coordX, j, k, block);
                                    for (int n = 0; n <= 5; n++)
                                        world.spawnParticle("portal", x, j, k, 1, 1, 1);
                                    if (random.nextInt(10) == 0)
                                        world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
                                }
                                //else {
                                //  harvestBlock(world, x, j, k, player);
                                // }
                            } else if (facing == 2) {
                                int coordZ = z + range;
                                if (world.isAirBlock(i, j, coordZ)) {
                                    world.setBlockToAir(i, j, z);
                                    world.setBlock(i, j, coordZ, block);
                                    for (int n = 0; n <= 5; n++)
                                        world.spawnParticle("portal", i, j, z, 1, 1, 1);
                                    if (random.nextInt(10) == 0)
                                        world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
                                }
                                //else {
                                //  harvestBlock(world, i, j, z, player);
                                //}
                            } else if (facing == 3) {
                                int coordX = x - range;
                                if (world.isAirBlock(coordX, j, k)) {
                                    world.setBlockToAir(x, j, k);
                                    world.setBlock(coordX, j, k, block);
                                    for (int n = 0; n <= 5; n++)
                                        world.spawnParticle("portal", x, j, k, 1, 1, 1);
                                    if (random.nextInt(10) == 0)
                                        world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
                                }
                                // /else {
                                //  harvestBlock(world, x, j, k, player);
                                //  }
                            } else {
                                harvestBlock(world, x, y, z, player);
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

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
        if (stack.stackTagCompound == null) {
            RFHelper.setDefaultEnergyTag(stack, 0);
        }
        if (!KeyboardHandler.isShiftDown() && !KeyboardHandler.isControlDown()) {
            list.add(TextHelper.shiftForMoreInfo);
            if (ConfigHandler.addItemLoreToItems) {
                list.add(TextHelper.controlForLore);
            }
        } else if (KeyboardHandler.isShiftDown() && KeyboardHandler.isControlDown()) {
            list.add(TextHelper.shiftForMoreInfo);
            if (ConfigHandler.addItemLoreToItems) {
                list.add(TextHelper.controlForLore);
            }
        } else if (KeyboardHandler.isShiftDown() && !KeyboardHandler.isControlDown()) {
            list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.redstonearmory.tool.charge") + " " + RFHelper.getRFStored(stack) + " / " + maxEnergy + " " + TextHelper.localize("info.redstonearmory.tool.rf") + TextHelper.END);
            list.add(TextHelper.ORANGE + energyPerUse + " " + TextHelper.localize("info.redstonearmory.tool.energyPerUse") + TextHelper.END);
            if (isEmpowered(stack)) {
                list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.tool.press") + " " + ConfigHandler.empowerKey + " " + TextHelper.localize("info.redstonearmory.tool.chargeOff") + TextHelper.END);
            } else {
                list.add(TextHelper.BRIGHT_BLUE + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.tool.press") + " " + ConfigHandler.empowerKey + " " + TextHelper.localize("info.redstonearmory.tool.chargeOn") + TextHelper.END);
            }
            list.add(TextHelper.WHITE + TextHelper.localize("info.redstonearmory.tool.gelidenderium.pickaxe"));
        } else if (!KeyboardHandler.isShiftDown() && KeyboardHandler.isControlDown() && ConfigHandler.addItemLoreToItems) {
            list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.redstonearmory.lore." + tool) + TextHelper.END);
        }
    }
}
