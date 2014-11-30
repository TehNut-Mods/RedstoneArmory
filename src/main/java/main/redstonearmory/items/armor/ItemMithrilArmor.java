package main.redstonearmory.items.armor;

import cofh.core.item.ItemArmorAdv;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.KeyboardHelper;
import main.redstonearmory.util.TextHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemMithrilArmor extends ItemArmorAdv {

	public ItemMithrilArmor(ArmorMaterial material, int type) {

		super(material, type);
		isRepairable();
		setRepairIngot("ingotMithril");
		setCreativeTab(RedstoneArmory.tabRArm);

		switch (type) {
			case 0: {
				setTextureName(ModInformation.ID + ":armor/mithrilHelm");
				setUnlocalizedName(ModInformation.ID + ".armor.mithril.helm");
				setMaxDamage(610);
				break;
			}
			case 1: {
				setTextureName(ModInformation.ID + ":armor/mithrilChestplate");
				setUnlocalizedName(ModInformation.ID + ".armor.mithril.chestplate");
				setMaxDamage(670);
				break;
			}
			case 2: {
				setTextureName(ModInformation.ID + ":armor/mithrilLeggings");
				setUnlocalizedName(ModInformation.ID + ".armor.mithril.leggings");
				setMaxDamage(650);
				break;
			}
			case 3: {
				setTextureName(ModInformation.ID + ":armor/mithrilBoots");
				setUnlocalizedName(ModInformation.ID + ".armor.mithril.boots");
				setMaxDamage(600);
				break;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack Stack, Entity entity, int Slot, String type) {
		if (Slot == 2)
			return ModInformation.ID + ":textures/models/armor/mithrilArmor_2.png";
		else
			return ModInformation.ID + ":textures/models/armor/mithrilArmor_1.png";

	}

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
		if (KeyboardHelper.isShiftDown()) {
			list.add(TextHelper.END);
			list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.armor.mithril.almost"));
			list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.armor.mithril.notquite"));
		}
	}
}
