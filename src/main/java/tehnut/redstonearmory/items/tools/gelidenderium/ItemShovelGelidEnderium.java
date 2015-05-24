package tehnut.redstonearmory.items.tools.gelidenderium;

import cofh.lib.util.helpers.StringHelper;
import cofh.redstonearsenal.item.tool.ItemShovelRF;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import tehnut.redstonearmory.ModInformation;
import tehnut.redstonearmory.RedstoneArmory;
import tehnut.redstonearmory.util.KeyboardHelper;
import tehnut.redstonearmory.util.TooltipHelper;

import java.util.List;

public class ItemShovelGelidEnderium extends ItemShovelRF {

    public int damage = 6;
    public int damageCharged = 1;
    IIcon activeIcon;
    IIcon drainedIcon;

    public ItemShovelGelidEnderium(ToolMaterial toolMaterial) {
        super(toolMaterial);
        setCreativeTab(RedstoneArmory.tabRArm);
        setUnlocalizedName(ModInformation.ID + ".tool.enderium.gelid.shovel");
        setNoRepair();
        setMaxDamage(0);

        maxEnergy = 320000;
        energyPerUse = 350;
        energyPerUseCharged = 600;

        effectiveMaterials.add(Material.ground);
        effectiveMaterials.add(Material.sand);
        effectiveMaterials.add(Material.clay);
        effectiveMaterials.add(Material.craftedSnow);
        effectiveMaterials.add(Material.snow);
        effectiveMaterials.add(Material.grass);
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {

        this.itemIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumShovel");
        this.activeIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumShovel_active");
        this.drainedIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumShovel_drained");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {
        if (!player.canPlayerEdit(x, y, z, hitSide, stack) || !player.capabilities.isCreativeMode && getEnergyStored(stack) < getEnergyPerUse(stack))
            return false;

        Block block = world.getBlock(x, y, z);
        BonemealEvent event = new BonemealEvent(player, world, block, x, y, z);

        if (MinecraftForge.EVENT_BUS.post(event))
            return false;

        if (block instanceof IGrowable) {
            IGrowable growable = (IGrowable) block;

            if (growable.func_149851_a(world, x, y, z, world.isRemote)) {
                if (!world.isRemote) {
                    if (growable.func_149852_a(world, world.rand, x, y, z)) {
                        growable.func_149853_b(world, world.rand, x, y, z);
                    }

                    if (growable.func_149852_a(world, world.rand, x, y, z) && isEmpowered(stack)) {
                        for (int i = 0; i <= 5; i++) {
                            growable.func_149853_b(world, world.rand, x, y, z);
                        }
                    }
                    if (!player.capabilities.isCreativeMode) {
                        useEnergy(stack, false);
                    }
                }
            }
            return true;
        }
        return false;
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
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0 - ((double) getEnergyStored(stack) / (double) getMaxEnergyStored(stack));
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {

        if (StringHelper.displayShiftForDetail && !KeyboardHelper.isShiftDown())
            list.add(StringHelper.shiftForDetails());

        if (!StringHelper.isShiftKeyDown())
            return;

        TooltipHelper.doEnergyTip(stack, list, getMaxEnergyStored(stack), getEnergyStored(stack), getEnergyPerUse(stack), energyPerUseCharged);
        TooltipHelper.doDamageTip(stack, list, getEnergyPerUse(stack), damage, damageCharged);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.rare;
    }
}
