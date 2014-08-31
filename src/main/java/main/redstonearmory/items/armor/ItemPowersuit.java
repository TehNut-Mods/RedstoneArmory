package main.redstonearmory.items.armor;

import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.TextHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (world.isRemote) {
            boolean isJumping = Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed();
            boolean isHoldingJump = Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed();
            boolean isSneaking = Minecraft.getMinecraft().gameSettings.keyBindSneak.getIsKeyPressed();

            if (isHoldingJump) {
                player.motionX *= 1.1;
                player.motionY = 1;
                player.motionZ *= 1.1;
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack Stack, Entity entity, int Slot, String type) {
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
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {

        if (StringHelper.displayShiftForDetail && !StringHelper.isShiftKeyDown()) {
            list.add(StringHelper.shiftForDetails());
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
