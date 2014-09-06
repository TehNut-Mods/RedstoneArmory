package main.redstonearmory.items.powersuit;

import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.items.powersuit.upgrades.*;
import main.redstonearmory.util.TextHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import redstonearsenal.item.armor.ItemArmorRF;

import java.util.List;

public class ItemPowersuit extends ItemArmorRF {

    public static boolean isJumping;

    public ItemPowersuit(ArmorMaterial armorMaterial, int type) {
        super(armorMaterial, type);
        this.setNoRepair();
        this.setCreativeTab(RedstoneArmory.tabRArm);
        maxEnergy = 10000000;
        energyPerDamage = 1;
        absorbRatio = 1D;
        maxTransfer = 4500;
        MinecraftForge.EVENT_BUS.register(this);

        switch (type) {
            case 0: {
                this.setTextureName(ModInformation.ID + ":armor/powersuitHelm");
                this.setUnlocalizedName(ModInformation.ID + ".armor.powersuit.helm");
                break;
            }
            case 1: {
                this.setTextureName(ModInformation.ID + ":armor/powersuitChestplate");
                this.setUnlocalizedName(ModInformation.ID + ".armor.powersuit.chestplate");
                break;
            }
            case 2: {
                this.setTextureName(ModInformation.ID + ":armor/powersuitLeggings");
                this.setUnlocalizedName(ModInformation.ID + ".armor.powersuit.leggings");
                break;
            }
            case 3: {
                this.setTextureName(ModInformation.ID + ":armor/powersuitBoots");
                this.setUnlocalizedName(ModInformation.ID + ".armor.powersuit.boots");
                break;
            }
        }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) event.entity;
            if ((entity.inventory.armorInventory[0] != null) && (entity.inventory.armorInventory[0].getItem() == this)) {
                FallPreventionUpgrade upgrade = new FallPreventionUpgrade();
                ItemStack stack = entity.inventory.armorInventory[0];
                if (isInstalled("FallPrevention", stack)) {
                    int fallDamage = (int) event.distance - 3;
                    int realCost = upgrade.energyUsed * fallDamage;
                    if (realCost <= getEnergyStored(stack)) {
                        extractEnergy(stack, realCost, false);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        switch (this.armorType) {
            case 0: {
                break;
            }
            case 1: {
                BasicFlightUpgrade basicFlight = new BasicFlightUpgrade();
                basicFlight.onUse(world, player, stack);
                FlightStabilizerUpgrade flightStabilizer = new FlightStabilizerUpgrade();
                flightStabilizer.onUse(world, player, stack);
                break;
            }
            case 2: {
                BasicSpeedUpgrade basicSpeed = new BasicSpeedUpgrade();
                basicSpeed.onUse(world, player, stack);
                HardenedSpeedUpgrade hardenedSpeed = new HardenedSpeedUpgrade();
                hardenedSpeed.onUse(world, player, stack);
                break;
            }
            case 3: {
                break;
            }
        }
    }

    public boolean isInstalled(String upgrade, ItemStack stack) {
        if (stack.stackTagCompound == null) stack.stackTagCompound = new NBTTagCompound();

        NBTTagCompound tag = stack.getTagCompound();
        return tag.getBoolean(upgrade);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int Slot, String type) {
        switch (Slot) {
            case 0: {
                return ModInformation.ID + ":textures/models/armor/powersuit_1.png";
            }
            case 1: {
                return ModInformation.ID + ":textures/models/armor/powersuit_1.png";
            }
            case 2: {
                return ModInformation.ID + ":textures/models/armor/powersuit_2.png";
            }
            case 3: {
                return ModInformation.ID + ":textures/models/armor/powersuit_1.png";
            }
        }
        return type;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {

        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
        ItemStack stack = new ItemStack(item, 1, 0);
        if (stack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(stack, maxEnergy);
        }
        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("ChestplateThruster", true);
        tag.setBoolean("ChestplateStabilizer", true);
        tag.setBoolean("FallPrevention", true);
        tag.setBoolean("BasicSpeed", true);
        tag.setBoolean("HardenedSpeed", true);
        stack.setTagCompound(tag);
        list.add(stack);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {

        if (StringHelper.displayShiftForDetail && !StringHelper.isShiftKeyDown()) {
            list.add(StringHelper.shiftForDetails());
            list.add(StringHelper.RED + StringHelper.localize("info.RArm.tooltip.armor.powersuit.ignore"));
        }
        if (!StringHelper.isShiftKeyDown()) {
            return;
        }
        if (stack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(stack, 0);
        }
        list.add(StringHelper.localize("info.cofh.charge") + ": " + stack.stackTagCompound.getInteger("Energy") + " / " + maxEnergy + " RF");
    }

    protected int getAbsorptionRatio() {

        switch (armorType) {
            case 0:
                return 15;
            case 1:
                return 45;
            case 2:
                return 30;
            case 3:
                return 10;
        }
        return 0;
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

    protected int getBaseAbsorption() {

        return 20;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {

        return EnumRarity.uncommon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
    }
}
