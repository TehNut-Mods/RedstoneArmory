package tehnut.redstonearmory.items.tools.gelidenderium;

import cofh.lib.util.helpers.StringHelper;
import cofh.redstonearsenal.item.tool.ItemSwordRF;
import cofh.repack.codechicken.lib.vec.Vector3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumRarity;
import tehnut.redstonearmory.ConfigHandler;
import tehnut.redstonearmory.ModInformation;
import tehnut.redstonearmory.RedstoneArmory;
import tehnut.redstonearmory.util.KeyboardHelper;
import tehnut.redstonearmory.util.TooltipHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
public class ItemSwordGelidEnderium extends ItemSwordRF {

    IIcon activeIcon;
    IIcon drainedIcon;
    Random random = new Random();
    int radius = 5;

    public ItemSwordGelidEnderium(ToolMaterial toolMaterial) {

        super(toolMaterial);
        setCreativeTab(RedstoneArmory.tabRArm);
        setUnlocalizedName(ModInformation.ID + ".tool.enderium.gelid.sword");
        setNoRepair();

        maxEnergy = 320000;
        maxTransfer = 1600;
        energyPerUse = 350;
        energyPerUseCharged = 600;
        damage = 15;
        damageCharged = 1;
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSword");
        this.activeIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSword_active");
        this.drainedIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSword_drained");
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        if (isEmpowered(stack))
            radius = 10;

        if (ConfigHandler.enableSwordSuckage) {
            if (!world.isRemote && entity instanceof EntityPlayer && ((EntityPlayer) entity).isUsingItem()) {
                if (((EntityPlayer) entity).getHeldItem().getItem() instanceof ItemSwordGelidEnderium) {
                    AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(entity.posX - radius, entity.posY - radius, entity.posZ - radius, entity.posX + radius, entity.posY + radius, entity.posZ + radius);
                    Iterator iter = world.getEntitiesWithinAABB(EntityItem.class, bb).iterator();
                    if (iter != null) {
                        while (iter.hasNext()) {
                            EntityItem item = (EntityItem) iter.next();
                            moveEntity(item, Vector3.fromEntityCenter(entity), 0.1);
                            if (random.nextInt(15) == 0)
                                world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
                        }
                    }
                }
            }
        }
    }

    public void moveEntity(Entity ent, Vector3 target, double speed) {
        double mx = getBlendDouble(ent.posX, target.x, speed);
        double my = getBlendDouble(ent.posY, target.y, speed);
        double mz = getBlendDouble(ent.posZ, target.z, speed);
        ent.velocityChanged = true;
        ent.isAirBorne = true;
        ent.addVelocity(mx, my, mz);
    }

    public double getBlendDouble(double d1, double d2, double blend) {
        if (d1 > d2)
            return -blend;
        if (d1 < d2)
            return blend;
        return 0;
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
