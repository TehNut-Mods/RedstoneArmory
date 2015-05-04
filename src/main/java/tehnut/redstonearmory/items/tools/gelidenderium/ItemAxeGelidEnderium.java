package tehnut.redstonearmory.items.tools.gelidenderium;

import cofh.lib.util.helpers.StringHelper;
import cofh.redstonearsenal.item.tool.ItemAxeRF;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumRarity;
import tehnut.redstonearmory.ConfigHandler;
import tehnut.redstonearmory.ModInformation;
import tehnut.redstonearmory.RedstoneArmory;
import tehnut.redstonearmory.util.KeyboardHelper;
import tehnut.redstonearmory.util.TooltipHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import tehnut.redstonearmory.util.interfaces.IGetRidOfDurabilityTooltips;

import java.util.List;
import java.util.Random;

public class ItemAxeGelidEnderium extends ItemAxeRF implements IGetRidOfDurabilityTooltips {

    IIcon activeIcon;
    IIcon drainedIcon;
    Random random = new Random();
    public int damage = 8;
    public int damageCharged = 1;

    public ItemAxeGelidEnderium(Item.ToolMaterial toolMaterial) {
        super(toolMaterial);
        setCreativeTab(RedstoneArmory.tabRArm);
        setUnlocalizedName(ModInformation.ID + ".tool.enderium.gelid.axe");
        setNoRepair();
        setMaxDamage(0);

        maxEnergy = 320000;
        energyPerUse = 350;
        energyPerUseCharged = 600;

        effectiveMaterials.add(Material.wood);
        effectiveMaterials.add(Material.plants);
        effectiveMaterials.add(Material.leaves);
        effectiveMaterials.add(Material.vine);
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {

        this.itemIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumAxe");
        this.activeIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumAxe_active");
        this.drainedIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumAxe_drained");
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {

        if (!(entity instanceof EntityPlayer))
            return false;

        if (block.getBlockHardness(world, x, y, z) == 0.0D)
            return true;

        EntityPlayer player = (EntityPlayer) entity;

        if (ConfigHandler.enableAxeMultiBreak) {
            if (block.getMaterial() == Material.wood && isEmpowered(stack)) {
                for (int i = x - 2; i <= x + 2; i++) {
                    for (int k = z - 2; k <= z + 2; k++) {
                        for (int j = y - 2; j <= y + 2; j++) {
                            if (world.getBlock(i, j, k).getMaterial() == Material.wood)
                                harvestBlock(world, i, j, k, player);
                        }
                    }
                }
            }
        }

        if (!player.capabilities.isCreativeMode)
            useEnergy(stack, false);

        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (ConfigHandler.enableAxeWeatherClear) {
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
        }
        return stack;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {
        if (ConfigHandler.enableAxeLightning) {
            if (!(getEnergyStored(stack) < getEnergyPerUse(stack))) {
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
        }

        if (!player.capabilities.isCreativeMode)
            useEnergy(stack, false);

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

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    @Override
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
