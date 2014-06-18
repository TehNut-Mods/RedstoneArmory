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
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import org.lwjgl.input.Keyboard;
import redstonearsenal.core.ProxyClient;
import redstonearsenal.item.tool.ItemAxeRF;

import java.util.List;
import java.util.Random;

public class ItemGelidEnderiumAxe extends ItemAxeRF {

    Icon activeIcon;
    Icon drainedIcon;
    String tool = "axe";
    Random random = new Random();

    public int damage = 8;
    public int damageCharged = 4;

    public ItemGelidEnderiumAxe(int id, EnumToolMaterial toolMaterial) {
        super(id, toolMaterial);
        maxEnergy = 320000;
        energyPerUse = 350;
        energyPerUseCharged = 15000;

        effectiveMaterials.add(Material.wood);
        effectiveMaterials.add(Material.plants);
        effectiveMaterials.add(Material.leaves);
        effectiveMaterials.add(Material.vine);
        this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
    }

    public ItemGelidEnderiumAxe(int id, EnumToolMaterial toolMaterial, int harvestLevel) {
        this(id, toolMaterial);
    }

    @Override
    public Icon getIcon(ItemStack stack, int pass) {

        return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
    }

    @Override
    public void registerIcons(IconRegister ir) {

        this.itemIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumAxe");
        this.activeIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumAxe_active");
        this.drainedIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumAxe_drained");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        return TextHelper.BRIGHT_BLUE + super.getItemDisplayName(itemStack);
    }

    public boolean onBlockDestroyed(ItemStack stack, World world, int bId, int x, int y, int z, EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer)) {
            return false;
        }
        if (Block.blocksList[bId].getBlockHardness(world, x, y, z) == 0.0D) {
            return true;
        }
        EntityPlayer player = (EntityPlayer) entity;

        if (ConfigHandler.disableAxeMultiBreak) {
            if ((Block.blocksList[bId].blockMaterial == Material.wood) && isEmpowered(stack)) {
                for (int i = x - 2; i <= x + 2; i++) {
                    for (int k = z - 2; k <= z + 2; k++) {
                        for (int j = y - 2; j <= y + 2; j++) {
                            if (world.getBlockMaterial(i, j, k) == Material.wood) {
                                harvestBlock(world, i, j, k, player);
                            }
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
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!ConfigHandler.disableAxeWeatherClear) {
            if (world.isRaining() && isEmpowered(stack) || world.isThundering() && isEmpowered(stack)) {
                WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
                WorldInfo worldinfo = worldserver.getWorldInfo();

                int i = (300 + (new Random()).nextInt(600)) * 20;

                worldinfo.setRaining(false);
                worldinfo.setThundering(false);
                worldinfo.setRainTime(i);

                if (random.nextInt(50) == 0) {
                    world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX, player.posY, player.posZ));
                }
                if (!player.capabilities.isCreativeMode) {
                    useEnergy(stack, false);
                }
                player.swingItem();
            }
        }
        return stack;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {
        if (!ConfigHandler.disableAxeLightning) {
            if (!(getEnergyStored(stack) < energyPerUse)) {
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
	        if(!ConfigHandler.disableAxeWeatherClear) {
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
