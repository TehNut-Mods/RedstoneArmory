package main.redstonearmory.items.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.KeyboardHandler;
import main.redstonearmory.util.RFHelper;
import main.redstonearmory.util.TextHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import redstonearsenal.item.tool.ItemSickleRF;

import java.util.List;

public class ItemGelidEnderiumSickle extends ItemSickleRF {

    String tool = "sickle";
    Icon activeIcon;
    Icon drainedIcon;
    public int radius;

    public ItemGelidEnderiumSickle(int id, EnumToolMaterial toolMaterial) {
        super(id, toolMaterial);
        maxEnergy = 320000;
        energyPerUse = 350;
        energyPerUseCharged = 2000;
        radius = 4;
        effectiveMaterials.add(Material.leaves);
        effectiveMaterials.add(Material.plants);
        effectiveMaterials.add(Material.vine);
        effectiveMaterials.add(Material.web);

        this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
    }

    public ItemGelidEnderiumSickle setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    @Override
    public Icon getIcon(ItemStack stack, int pass) {
        return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
    }

    @Override
    public void registerIcons(IconRegister ir) {

        this.itemIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSickle");
        this.activeIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSickle_active");
        this.drainedIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSickle_drained");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        return TextHelper.BRIGHT_BLUE + super.getItemDisplayName(itemStack);
    }

    @Override
    public boolean canHarvestBlock(Block block) {
        return (block.blockID == Block.web.blockID) || (block.blockID == Block.vine.blockID);
    }

    @Override
    protected void harvestBlock(World world, int x, int y, int z, EntityPlayer player) {

        Block block = Block.blocksList[world.getBlockId(x, y, z)];

        if (block.getBlockHardness(world, x, y, z) < 0 || block.equals(Block.waterlily)) {
            return;
        }
        int bMeta = world.getBlockMetadata(x, y, z);

        if (block.canHarvestBlock(player, bMeta)) {
            block.harvestBlock(world, player, x, y, z, bMeta);
        }
        if (world.isRemote && block.equals(Block.vine)) {
            dropItemStackIntoWorldWithVelocity(new ItemStack(Block.vine), world, x, y, z);
        }
        world.setBlockToAir(x, y, z);
    }

    public static boolean dropItemStackIntoWorldWithVelocity(ItemStack stack, World world, double x, double y, double z) {
        return dropItemStackIntoWorld(stack, world, x, y, z, true);
    }

    public static boolean dropItemStackIntoWorld(ItemStack stack, World world, double x, double y, double z, boolean velocity) {

        if (stack == null) {
            return false;
        }
        float x2 = 0.5F;
        float y2 = 0.0F;
        float z2 = 0.5F;

        if (velocity) {
            x2 = world.rand.nextFloat() * 0.8F + 0.1F;
            y2 = world.rand.nextFloat() * 0.8F + 0.1F;
            z2 = world.rand.nextFloat() * 0.8F + 0.1F;
        }
        EntityItem entity = new EntityItem(world, x + x2, y + y2, z + z2, stack.copy());

        if (velocity) {
            entity.motionX = (float) world.rand.nextGaussian() * 0.05F;
            entity.motionY = (float) world.rand.nextGaussian() * 0.05F + 0.2F;
            entity.motionZ = (float) world.rand.nextGaussian() * 0.05F;
        } else {
            entity.motionY = -0.05F;
            entity.motionX = 0;
            entity.motionZ = 0;
        }
        world.spawnEntityInWorld(entity);

        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, int bId, int x, int y, int z, EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer)) {
            return false;
        }
        EntityPlayer player = (EntityPlayer) entity;
        if ((Block.blocksList[bId].getBlockHardness(world, x, y, z) != 0.0D) && (!effectiveMaterials.contains(Block.blocksList[bId].blockMaterial))) {
            if (!player.capabilities.isCreativeMode) {
                useEnergy(stack, false);
            }
            return false;
        }
        boolean used = false;
        int boost = isEmpowered(stack) ? 1 : 0;
        for (int i = x - (this.radius + boost); i <= x + (this.radius + boost); i++) {
            for (int k = z - (this.radius + boost); k <= z + (this.radius + boost); k++) {
                for (int j = y - boost; j <= y + boost; j++) {
                    if (effectiveMaterials.contains(world.getBlockMaterial(i, j, k))) {
                        harvestBlock(world, i, j, k, player);
                        used = true;
                    }
                }
            }
        }
        if (used) {
            useEnergy(stack, false);
        }
        return used;
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
            list.add(TextHelper.WHITE + TextHelper.localize("info.redstonearmory.tool.gelidenderium.sickle"));
        } else if (!KeyboardHandler.isShiftDown() && KeyboardHandler.isControlDown() && ConfigHandler.addItemLoreToItems) {
            list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.redstonearmory.lore." + tool) + TextHelper.END);
        }
    }
}
