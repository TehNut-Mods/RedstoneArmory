package tehnut.redstonearmory.items.tools.gelidenderium;

import cofh.lib.util.helpers.StringHelper;
import cofh.redstonearsenal.item.tool.ItemPickaxeRF;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tehnut.redstonearmory.ModInformation;
import tehnut.redstonearmory.RedstoneArmory;
import tehnut.redstonearmory.util.KeyboardHelper;
import tehnut.redstonearmory.util.TooltipHelper;
import tehnut.redstonearmory.util.Utils;

import java.util.List;

public class ItemPickaxeGelidEnderium extends ItemPickaxeRF {

    public int damage = 6;
    public int damageCharged = 1;
    IIcon activeIcon;
    IIcon drainedIcon;

    public ItemPickaxeGelidEnderium(ToolMaterial toolMaterial) {
        super(toolMaterial);
        setCreativeTab(RedstoneArmory.tabRArm);
        setUnlocalizedName(ModInformation.ID + ".tool.enderium.gelid.pickaxe");
        setNoRepair();
        setMaxDamage(0);

        maxEnergy = 320000;
        energyPerUse = 350;
        energyPerUseCharged = 600;

        effectiveMaterials.add(Material.iron);
        effectiveMaterials.add(Material.anvil);
        effectiveMaterials.add(Material.rock);
        effectiveMaterials.add(Material.glass);
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {

        this.itemIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumPickaxe");
        this.activeIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumPickaxe_active");
        this.drainedIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumPickaxe_drained");
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        if (!player.isSneaking())
            return super.onBlockStartBreak(stack, x, y, z, player);
        else
            return false;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {

        if (!world.isRemote && player.isSneaking()) {
            if (stack.stackTagCompound == null)
                stack.stackTagCompound = new NBTTagCompound();

            TileEntity tile = world.getTileEntity(x, y, z);

            if (tile != null && tile instanceof IInventory) {
                stack.stackTagCompound.setInteger("CoordX", x);
                stack.stackTagCompound.setInteger("CoordY", y);
                stack.stackTagCompound.setInteger("CoordZ", z);
                stack.stackTagCompound.setInteger("Side", hitSide);

                return true;
            }
        }

        return false;
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
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {

        if (StringHelper.displayShiftForDetail && !KeyboardHelper.isShiftDown())
            list.add(StringHelper.shiftForDetails());

        if (StringHelper.isShiftKeyDown()) {
            TooltipHelper.doEnergyTip(stack, list, getMaxEnergyStored(stack), getEnergyStored(stack), getEnergyPerUse(stack), energyPerUseCharged);
            TooltipHelper.doDamageTip(stack, list, getEnergyPerUse(stack), damage, damageCharged);
        }

        if (stack.stackTagCompound == null)
            stack.stackTagCompound = new NBTTagCompound();

        int coordX = stack.stackTagCompound.getInteger("CoordX");
        int coordY = stack.stackTagCompound.getInteger("CoordY");
        int coordZ = stack.stackTagCompound.getInteger("CoordZ");
        int side = stack.stackTagCompound.getInteger("Side");

        String sideString = ForgeDirection.getOrientation(side).toString().toLowerCase();

        if (KeyboardHelper.isControlDown()) {
            list.add(Utils.localizeFormatted("info.RArm.tooltip.bound", coordX, coordY, coordZ));
            list.add(Utils.localizeFormatted("info.RArm.tooltip.side", Character.toUpperCase(sideString.charAt(0)) + sideString.substring(1)));
        } else {
            list.add(Utils.localize("info.RArm.tooltip.hold") + " " + EnumChatFormatting.YELLOW + EnumChatFormatting.ITALIC + Utils.localize("info.RArm.tooltip.control") + " " + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + Utils.localize("info.RArm.tooltip.forDetails"));
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.rare;
    }
}
