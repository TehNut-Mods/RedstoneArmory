package tehnut.redstonearmory.items.tools.gelidenderium;

import cofh.lib.util.helpers.StringHelper;
import cofh.redstonearsenal.item.tool.ItemSickleRF;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumRarity;
import tehnut.redstonearmory.ModInformation;
import tehnut.redstonearmory.RedstoneArmory;
import tehnut.redstonearmory.util.KeyboardHelper;
import tehnut.redstonearmory.util.TooltipHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tehnut.redstonearmory.util.interfaces.IGetRidOfDurabilityTooltips;

import java.util.List;

@SuppressWarnings("all")
public class ItemSickleGelidEnderium extends ItemSickleRF implements IGetRidOfDurabilityTooltips {

    public int radius;
    IIcon activeIcon;
    IIcon drainedIcon;
    int damage = 2;
    int damageCharged = 1;

    public ItemSickleGelidEnderium(ToolMaterial toolMaterial) {
        super(toolMaterial);
        setCreativeTab(RedstoneArmory.tabRArm);
        setUnlocalizedName(ModInformation.ID + ".tool.enderium.gelid.sickle");
        setNoRepair();
        setMaxDamage(0);

        maxEnergy = 320000;
        energyPerUse = 350;
        energyPerUseCharged = 2000;
        radius = 4;

        effectiveMaterials.add(Material.leaves);
        effectiveMaterials.add(Material.plants);
        effectiveMaterials.add(Material.vine);
        effectiveMaterials.add(Material.web);
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {

        return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {

        this.itemIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSickle");
        this.activeIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSickle_active");
        this.drainedIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSickle_drained");
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
