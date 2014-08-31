package main.redstonearmory.items.tools;

import cofh.core.item.tool.ItemSwordAdv;
import cofh.lib.util.helpers.ItemHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.KeyboardHelper;
import main.redstonearmory.util.TextHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemMithScimmy extends ItemSwordAdv {

	public String repairIngot = "ingotMithril";

	public ItemMithScimmy(ToolMaterial toolMaterial) {

		super(toolMaterial);
		this.setCreativeTab(RedstoneArmory.tabRArm);
		this.setUnlocalizedName(ModInformation.ID + ".tool.mithril.scimitar");
		this.setTextureName(ModInformation.ID + ":tools/scimitarMithril");
	}

	public ItemSwordAdv setRepairIngot(String repairIngot) {

		this.repairIngot = repairIngot;
		return this;
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {

		return ItemHelper.isOreNameEqual(stack, repairIngot);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
	}

	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
		if(KeyboardHelper.isShiftDown()) {
			list.add(TextHelper.END);
			list.add(TextHelper.BRIGHT_BLUE + TextHelper.localize("info.RArm.tooltip.scimitar.mithril.selling"));
			list.add(TextHelper.BRIGHT_BLUE + TextHelper.localize("info.RArm.tooltip.scimitar.mithril.selling"));
			list.add(TextHelper.BRIGHT_BLUE + TextHelper.localize("info.RArm.tooltip.scimitar.mithril.doubling"));
			list.add(TextHelper.BRIGHT_BLUE + TextHelper.localize("info.RArm.tooltip.scimitar.mithril.selling"));
		}
	}
}