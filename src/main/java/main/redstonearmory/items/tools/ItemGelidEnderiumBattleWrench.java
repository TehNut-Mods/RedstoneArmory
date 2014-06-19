package main.redstonearmory.items.tools;

import cofh.api.block.IDismantleable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.tile.IWrenchable;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.BlockHelper;
import main.redstonearmory.util.KeyboardHandler;
import main.redstonearmory.util.RFHelper;
import main.redstonearmory.util.TextHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import org.lwjgl.input.Keyboard;
import redstonearsenal.core.ProxyClient;
import redstonearsenal.item.tool.ItemWrenchBattleRF;
import redstonearsenal.util.Utils;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ItemGelidEnderiumBattleWrench extends ItemWrenchBattleRF {

    String tool = "battlewrench";
    int spinDamage = 2;
    int resistanceEffect = 3;
    Icon activeIcon;
    Icon drainedIcon;
    int radius = 4;
    Random random = new Random();

    public int maxEnergy = 320000;
    public int maxTransfer = 1600;
    public int energyPerUse = 350;
    public int energyPerUseCharged = 800;


    public ItemGelidEnderiumBattleWrench(int par1, EnumToolMaterial toolMaterial) {
        super(par1, toolMaterial);
        damage = 6;
        damageCharged = 3;
        this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
    }

    @Override
    public Icon getIcon(ItemStack stack, int pass) {

        return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
    }

    @Override
    public void registerIcons(IconRegister ir) {

        this.itemIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumBattleWrench");
        this.activeIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumBattleWrench_active");
        this.drainedIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumBattleWrench_drained");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        return TextHelper.BRIGHT_BLUE + super.getItemDisplayName(itemStack);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {

        entity.rotationYaw += 90;
        entity.rotationYaw %= 360;
        return super.hitEntity(stack, entity, player);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {

        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (isEmpowered(stack)) {
            radius = 4;
            spinDamage = 4;
            resistanceEffect = 4;
        }

        if (!ConfigHandler.disableBattleWrenchSpin) {
            AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(player.posX - radius, player.posY - radius, player.posZ - radius, player.posX + radius, player.posY + radius, player.posZ + radius);
            Iterator iter = world.getEntitiesWithinAABB(EntityLivingBase.class, bb).iterator();
            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 20, resistanceEffect, false));
            player.swingItem();
            if (iter != null) {
                while (iter.hasNext()) {
                    EntityLivingBase entity = (EntityLivingBase) iter.next();
                    entity.attackEntityFrom(Utils.causePlayerFluxDamage(player), spinDamage);
                    player.setAngles(-180, 10);
                    world.spawnParticle("largeexplode", player.posX, player.posY, player.posZ, 1, 1, 1);
                    if (!player.capabilities.isCreativeMode && random.nextInt(5) == 0)
                        useEnergy(stack, false);
                }
            }
        }
        return stack;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {

        if (stack.getItemDamage() > 0) {
            stack.setItemDamage(0);
        }
        if (!player.capabilities.isCreativeMode && getEnergyStored(stack) < getEnergyPerUse(stack)) {
            return false;
        }
        Block block = Block.blocksList[world.getBlockId(x, y, z)];

        if (world.isRemote && player.isSneaking() && block instanceof IDismantleable
                && ((IDismantleable) block).canDismantle(player, world, x, y, z)) {
            ((IDismantleable) block).dismantleBlock(player, world, x, y, z, false);

            if (!player.capabilities.isCreativeMode) {
                useEnergy(stack, false);
            }
            return true;
        }
        if (BlockHelper.canRotate(block)) {
            int bMeta = world.getBlockMetadata(x, y, z);

            if (player.isSneaking()) {
                world.setBlockMetadataWithNotify(x, y, z, BlockHelper.rotateVanillaBlockAlt(world, block, x, y, z), 3);

                if (!world.isRemote) {
                    String soundName = block.stepSound.getBreakSound();
                    //FMLClientHandler.instance().getClient().getSoundHandler().playSound(new SoundBase(soundName, 1.0F, 0.6F));
                }
            } else {
                world.setBlockMetadataWithNotify(x, y, z, BlockHelper.rotateVanillaBlock(world, block, x, y, z), 3);

                if (!world.isRemote) {
                    String soundName = block.stepSound.getBreakSound();
                    //FMLClientHandler.instance().getClient().getSoundHandler().playSound(new SoundBase(soundName, 1.0F, 0.8F));
                }
            }
            if (!player.capabilities.isCreativeMode) {
                useEnergy(stack, false);
            }
            return world.isRemote;
        } else if (!player.isSneaking() && block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(hitSide))) {
            if (!player.capabilities.isCreativeMode) {
                useEnergy(stack, false);
            }
            player.swingItem();
            return world.isRemote;
        }
        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile instanceof IWrenchable) {
            IWrenchable wrenchable = (IWrenchable) tile;

            if (player.isSneaking()) {
                hitSide = BlockHelper.SIDE_OPPOSITE[hitSide];
            }
            if (wrenchable.wrenchCanSetFacing(player, hitSide)) {
                if (world.isRemote) {
                    wrenchable.setFacing((short) hitSide);
                }
            } else if (wrenchable.wrenchCanRemove(player)) {
                ItemStack dropBlock = wrenchable.getWrenchDrop(player);

                if (dropBlock != null) {
                    world.setBlockToAir(x, y, z);
                    if (world.isRemote) {
                        List<ItemStack> drops = block.getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), 0);

                        if (drops.isEmpty()) {
                            drops.add(dropBlock);
                        } else {
                            drops.set(0, dropBlock);
                        }
                        for (ItemStack drop : drops) {
                            float f = 0.7F;
                            double x2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                            double y2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                            double z2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                            EntityItem entity = new EntityItem(world, x + x2, y + y2, z + z2, drop);
                            entity.delayBeforeCanPickup = 10;
                            world.spawnEntityInWorld(entity);
                        }
                    }
                }
            }
            if (!player.capabilities.isCreativeMode) {
                useEnergy(stack, false);
            }
            return world.isRemote;
        }
        return false;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isCurrentItem) {

        if (stack.getItemDamage() > 0) {
            stack.setItemDamage(0);
        }
        super.onUpdate(stack, world, entity, slot, isCurrentItem);
    }

    //	@Override
    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {

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
                list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.tool.press") + " " + Keyboard.getKeyName(ProxyClient.empower.keyCode) + " " + TextHelper.localize("info.redstonearmory.tool.chargeOff") + TextHelper.END);
            } else {
                list.add(TextHelper.BRIGHT_BLUE + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.tool.press") + " " + Keyboard.getKeyName(ProxyClient.empower.keyCode) + " " + TextHelper.localize("info.redstonearmory.tool.chargeOn") + TextHelper.END);
            }
            list.remove(TextHelper.WHITE + TextHelper.localize("info.redstonearmory.tool.gelidenderium.sword"));
	        if(!ConfigHandler.disableBattleWrenchSpin) {
		        list.add(TextHelper.WHITE + TextHelper.localize("info.redstonearmory.tool.gelidenderium." + tool));
	        } else {
		        list.add(TextHelper.localize("info.redstonearmory.tool.disabled"));
	        }
            list.add(TextHelper.spacer);
            list.add(TextHelper.LIGHT_BLUE + "+" + damage + " " + TextHelper.localize("info.redstonearmory.tool.damageAttack"));
            if (isEmpowered(stack)) {
                list.add(TextHelper.BRIGHT_GREEN + "+" + damageCharged + " " + TextHelper.localize("info.redstonearmory.tool.damageFlux"));
            }
        } else if (!KeyboardHandler.isShiftDown() && KeyboardHandler.isControlDown() && ConfigHandler.addItemLoreToItems) {
            list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.redstonearmory.lore." + tool) + TextHelper.END);
        }
    }
}
