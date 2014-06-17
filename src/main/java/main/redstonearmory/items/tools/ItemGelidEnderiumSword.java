package main.redstonearmory.items.tools;

import cofh.util.EnergyHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.codechicken.lib.vec.Vector3;
import main.redstonearmory.util.KeyboardHandler;
import main.redstonearmory.util.RFHelper;
import main.redstonearmory.util.RFItemUtils;
import main.redstonearmory.util.TextHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import redstonearsenal.item.tool.ItemSwordRF;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ItemGelidEnderiumSword extends ItemSwordRF {

    String tool = "sword";
    Icon activeIcon;
    Icon drainedIcon;
    Random random = new Random();
    int radius = 5;
    public int maxEnergy = 320000;
    public int maxTransfer = 1600;
    public int energyPerUse = 350;
    public int energyPerUseCharged = 800;
    public int damage = 15;
    public int damageCharged = 8;

    public ItemGelidEnderiumSword(int par1, EnumToolMaterial toolMaterial) {
        super(par1, toolMaterial);
        setNoRepair();
        this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
    }

    @Override
    public Icon getIcon(ItemStack stack, int pass) {
        return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        this.itemIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSword");
        this.activeIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSword_active");
        this.drainedIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSword_drained");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        return TextHelper.BRIGHT_BLUE + super.getItemDisplayName(itemStack);
    }

    @Override
    public void getSubItems(int item, CreativeTabs tab, List list) {
        list.add(RFHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
        list.add(RFHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {
        if (stack.getItemDamage() > 0) {
            stack.setItemDamage(0);
        }
        EntityPlayer thePlayer = (EntityPlayer) player;
        float fallingMult = (player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater()
                && !player.isPotionActive(Potion.blindness) && player.ridingEntity == null) ? 1.5F : 1.0F;

        if (thePlayer.capabilities.isCreativeMode || useEnergy(stack, false) == getEnergyPerUse(stack)) {
            float fluxDamage = isEmpowered(stack) ? damageCharged : 1;
            float enchantDamage = damage + EnchantmentHelper.getEnchantmentModifierLiving(player, entity);

            entity.attackEntityFrom(RFItemUtils.causePlayerFluxDamage(thePlayer), fluxDamage);
            entity.attackEntityFrom(DamageSource.causePlayerDamage(thePlayer), (fluxDamage + enchantDamage) * fallingMult);
        } else {
            entity.attackEntityFrom(DamageSource.causePlayerDamage(thePlayer), 1 * fallingMult);
        }
        return true;
    }

    //	@Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
        if (block.getBlockHardness(world, x, y, z) != 0.0D) {
            extractEnergy(stack, energyPerUse, false);
        }
        return true;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        if (isEmpowered(stack))
            radius = 10;

        if (!world.isRemote && entity instanceof EntityPlayer && ((EntityPlayer) entity).isUsingItem()) {
            AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(entity.posX - radius, entity.posY - radius, entity.posZ - radius, entity.posX + radius, entity.posY + radius, entity.posZ + radius);
            Iterator iter = world.getEntitiesWithinAABB(EntityItem.class, bb).iterator();
            if (iter != null) {
                while (iter.hasNext()) {
                    EntityItem item = (EntityItem) iter.next();
                    moveEntity(item, Vector3.fromEntityCenter(entity), 0.1);
                    if (random.nextInt(10) == 0)
                        world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
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
            list.add(TextHelper.WHITE + TextHelper.localize("info.redstonearmory.tool.gelidenderium.sword"));
	        list.add(TextHelper.spacer);
	        list.add(TextHelper.LIGHT_BLUE + "+" + damage + " " + TextHelper.localize("info.redstonearmory.tool.damageAttack"));
	        list.add(TextHelper.BRIGHT_GREEN + "+" + damageCharged + " " + TextHelper.localize("info.redstonearmory.tool.damageFlux"));
        } else if (!KeyboardHandler.isShiftDown() && KeyboardHandler.isControlDown() && ConfigHandler.addItemLoreToItems) {
            list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.redstonearmory.lore." + tool) + TextHelper.END);
        }
    }

    @Override
    public int getDisplayDamage(ItemStack stack) {

        if (stack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(stack, 0);
        }
        return 1 + maxEnergy - stack.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxDamage(ItemStack stack) {

        return 1 + maxEnergy;
    }

    @Override
    public boolean isDamaged(ItemStack stack) {

        return stack.getItemDamage() != Short.MAX_VALUE;
    }
}
