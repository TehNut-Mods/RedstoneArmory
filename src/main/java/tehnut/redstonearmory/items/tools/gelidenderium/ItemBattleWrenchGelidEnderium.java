package tehnut.redstonearmory.items.tools.gelidenderium;

import cofh.lib.util.helpers.StringHelper;
import cofh.redstonearsenal.item.tool.ItemWrenchBattleRF;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumRarity;
import tehnut.redstonearmory.ModInformation;
import tehnut.redstonearmory.RedstoneArmory;
import tehnut.redstonearmory.util.KeyboardHelper;
import tehnut.redstonearmory.util.TooltipHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tehnut.redstonearmory.util.interfaces.IGetRidOfDurabilityTooltips;

import java.util.List;
import java.util.Random;

public class ItemBattleWrenchGelidEnderium extends ItemWrenchBattleRF implements IGetRidOfDurabilityTooltips {

    IIcon activeIcon;
    IIcon drainedIcon;

    Random random = new Random();

    public ItemBattleWrenchGelidEnderium(ToolMaterial toolMaterial) {
        super(toolMaterial);
        setCreativeTab(RedstoneArmory.tabRArm);
        setUnlocalizedName(ModInformation.ID + ".tool.enderium.gelid.battlewrench");
        setNoRepair();
        setMaxDamage(0);

        damage = 7;
        damageCharged = 1;
        maxEnergy = 320000;
        maxTransfer = 1600;
        energyPerUse = 350;
        energyPerUseCharged = 800;
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {

        this.itemIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumBattleWrench");
        this.activeIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumBattleWrench_active");
        this.drainedIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumBattleWrench_drained");
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0 - ((double) getEnergyStored(stack) / (double) getMaxEnergyStored(stack));
    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
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
