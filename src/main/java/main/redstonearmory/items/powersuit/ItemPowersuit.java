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
                if (stack.stackTagCompound.getBoolean("activated")) {
                    WaterBreathingUpgrade waterBreathing = new WaterBreathingUpgrade();
                    waterBreathing.onUse(world, player, stack);
                    PotionNullificationUpgrade potionNullification = new PotionNullificationUpgrade();
                    potionNullification.onUse(world, player, stack);
                }
                break;
            }
            case 1: {
                if (stack.stackTagCompound.getBoolean("activated")) {
                    FlightUpgradeMk1 flightMk1 = new FlightUpgradeMk1();
                    flightMk1.onUse(world, player, stack);
                    FlightUpgradeMk2 flightMk2 = new FlightUpgradeMk2();
                    flightMk2.onUse(world, player, stack);
                    FlightUpgradeMk3 flightMk3 = new FlightUpgradeMk3();
                    flightMk3.onUse(world, player, stack);
                    FlightUpgradeMk4 flightMk4 = new FlightUpgradeMk4();
                    flightMk4.onUse(world, player, stack);
                    FlightUpgradeMk5 flightMk5 = new FlightUpgradeMk5();
                    flightMk5.onUse(world, player, stack);
                }
                break;
            }
            case 2: {
                if (stack.stackTagCompound.getBoolean("activated")) {
                    SpeedUpgradeMk1 speedMk1 = new SpeedUpgradeMk1();
                    speedMk1.onUse(world, player, stack);
                    SpeedUpgradeMk2 speedMk2 = new SpeedUpgradeMk2();
                    speedMk2.onUse(world, player, stack);
                    SpeedUpgradeMk3 speedMk3 = new SpeedUpgradeMk3();
                    speedMk3.onUse(world, player, stack);
                    SpeedUpgradeMk4 speedMk4 = new SpeedUpgradeMk4();
                    speedMk4.onUse(world, player, stack);
                }
                break;
            }
            case 3: {
                if (stack.stackTagCompound.getBoolean("activated")) {
                    StepAssistUpgrade stepAssist = new StepAssistUpgrade();
                    stepAssist.onUse(world, player, stack);
                } else {
                    player.stepHeight = 0.5F;
                }
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
        tag.setInteger("Energy", 3 * maxEnergy / 4);
        tag.setBoolean("FlightMk1", true);
        tag.setBoolean("FlightMk2", true);
        tag.setBoolean("FlightMk3", true);
        tag.setBoolean("FlightMk4", true);
        tag.setBoolean("FlightMk5", true);
        tag.setBoolean("FallPrevention", true);
        tag.setBoolean("SpeedMk1", true);
        tag.setBoolean("SpeedMk2", true);
        tag.setBoolean("SpeedMk3", true);
        tag.setBoolean("SpeedMk4", true);
        tag.setBoolean("StepAssist", true);
        tag.setBoolean("WaterBreathing", true);
        tag.setBoolean("PotionNullification", true);
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
