package main.redstonearmory.items.tools.gelidenderium;

import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.KeyboardHelper;
import main.redstonearmory.util.TextHelper;
import main.redstonearmory.util.TooltipHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import redstonearsenal.item.tool.ItemSickleRF;

import java.util.List;

@SuppressWarnings("all")
public class ItemSickleGelidEnderium extends ItemSickleRF {

	IIcon activeIcon;
	IIcon drainedIcon;

	public int radius;
	int damage = 2;
	int damageCharged = 1;

	public ItemSickleGelidEnderium(ToolMaterial toolMaterial) {
		super(toolMaterial);
		this.setCreativeTab(RedstoneArmory.tabRArm);
		this.setUnlocalizedName(ModInformation.ID + ".tool.enderium.gelid.sickle");
		this.setNoRepair();
		this.setMaxDamage(0);

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

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
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

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.addChatComponentMessage(new ChatComponentText(stack.getTagCompound().toString()));
		return stack;
	}

//	@SideOnly(Side.CLIENT)
//	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
//		if (StringHelper.displayShiftForDetail && !KeyboardHelper.isShiftDown()) {
//			list.add(StringHelper.shiftForDetails());
//		}
//		if (!StringHelper.isShiftKeyDown()) {
//			return;
//		}
//		if (stack.stackTagCompound == null) {
//			EnergyHelper.setDefaultEnergyTag(stack, 0);
//		}
//		list.add(TextHelper.localize("info.cofh.charge") + ": " + stack.stackTagCompound.getInteger("Energy") + " / " + maxEnergy + " RF");
//
//		list.add(TextHelper.ORANGE + getEnergyPerUse(stack) + " " + TextHelper.localize("info.redstonearsenal.tool.energyPerUse") + TextHelper.END);
//		if (isEmpowered(stack)) {
//			list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localize("info.cofh.press") + " " + Keyboard.getKeyName(KeyBindingEmpower.instance.getKey()) + " " + TextHelper.localize("info.redstonearsenal.tool.chargeOff") + TextHelper.END);
//		} else {
//			list.add(TextHelper.BRIGHT_BLUE + TextHelper.ITALIC + TextHelper.localize("info.cofh.press") + " " + Keyboard.getKeyName(KeyBindingEmpower.instance.getKey()) + " " + TextHelper.localize("info.redstonearsenal.tool.chargeOn") + TextHelper.END);
//		}
//		if (getEnergyStored(stack) >= getEnergyPerUse(stack)) {
//			list.add("");
//			list.add(TextHelper.LIGHT_BLUE + "+" + damage + " " + TextHelper.localize("info.cofh.damageAttack") + TextHelper.END);
//			list.add(TextHelper.BRIGHT_GREEN + "+" + (isEmpowered(stack) ? damageCharged : 1) + " " + TextHelper.localize("info.cofh.damageFlux") + TextHelper.END);
//		}
//		if(KeyboardHelper.isShiftDown()) {
//			if(ConfigHandler.enableAxeWeatherClear) {
//				list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.ability") + TextHelper.localize("info.RArm.tooltip.ability.sickle.sekrit"));
//			}
//		}
//	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {

		if (StringHelper.displayShiftForDetail && !KeyboardHelper.isShiftDown()) {
			list.add(StringHelper.shiftForDetails());
		}
		if (!StringHelper.isShiftKeyDown()) {
			return;
		}
		if (stack.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(stack, 0);
		}

		TooltipHelper.doEnergyTip(stack, player, list, maxEnergy, maxTransfer, energyPerUse, energyPerUseCharged);
		TooltipHelper.doDamageTip(stack, player, list, energyPerUse, damage, damageCharged);
	}
}
