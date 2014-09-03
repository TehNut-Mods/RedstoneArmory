package main.redstonearmory.items.armor;

import cofh.core.item.ItemArmorAdv;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.KeyboardHelper;
import main.redstonearmory.util.TextHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemMithrilArmor extends ItemArmorAdv {

	public ItemMithrilArmor(ArmorMaterial material, int type) {

		super(material, type);
		this.isRepairable();
		this.setRepairIngot("ingotMithril");
		this.setCreativeTab(RedstoneArmory.tabRArm);

		switch (type) {
			case 0: {
				this.setTextureName(ModInformation.ID + ":armor/mithrilHelm");
				this.setUnlocalizedName(ModInformation.ID + ".armor.mithril.helm");
				break;
			}
			case 1: {
				this.setTextureName(ModInformation.ID + ":armor/mithrilChestplate");
				this.setUnlocalizedName(ModInformation.ID + ".armor.mithril.chestplate");
				break;
			}
			case 2: {
				this.setTextureName(ModInformation.ID + ":armor/mithrilLeggings");
				this.setUnlocalizedName(ModInformation.ID + ".armor.mithril.leggings");
				break;
			}
			case 3: {
				this.setTextureName(ModInformation.ID + ":armor/mithrilBoots");
				this.setUnlocalizedName(ModInformation.ID + ".armor.mithril.boots");
				break;
			}
		}
	}

	//	@Override
	//	@SideOnly(Side.CLIENT)
	//	public String getArmorTexture(ItemStack Stack, Entity entity, int Slot, String type) {
	//		switch(Slot) {
	//			case 0: {
	//				return ModInformation.ID + ":textures/models/armor/mithrilArmor_1.png";
	//			}
	//			case 1: {
	//				return ModInformation.ID + ":textures/models/armor/mithrilArmor_1.png";
	//			}
	//			case 2: {
	//				return ModInformation.ID + ":textures/models/armor/mithrilArmor_2.png";
	//			}
	//			case 3: {
	//				return ModInformation.ID + ":textures/models/armor/mithrilArmor_1.png";
	//			}
	//		}
	//		return type;
	//	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {

		return EnumRarity.common;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
		if(KeyboardHelper.isShiftDown()) {
			list.add(TextHelper.END);
			list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.armor.mithril.almost"));
			list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.armor.mithril.notquite"));
		}
	}
}
