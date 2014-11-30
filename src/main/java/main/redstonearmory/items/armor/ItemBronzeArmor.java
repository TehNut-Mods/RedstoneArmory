package main.redstonearmory.items.armor;

import cofh.core.item.ItemArmorAdv;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemBronzeArmor extends ItemArmorAdv {

	public ItemBronzeArmor(ArmorMaterial material, int type) {

		super(material, type);
		isRepairable();
		setRepairIngot("ingotBronze");
		setCreativeTab(RedstoneArmory.tabRArm);

		switch (type) {
			case 0: {
				setTextureName(ModInformation.ID + ":armor/bronzeHelm");
				setUnlocalizedName(ModInformation.ID + ".armor.bronze.helm");
				setMaxDamage(320);
				break;
			}
			case 1: {
				setTextureName(ModInformation.ID + ":armor/bronzeChestplate");
				setUnlocalizedName(ModInformation.ID + ".armor.bronze.chestplate");
				setMaxDamage(450);
				break;
			}
			case 2: {
				setTextureName(ModInformation.ID + ":armor/bronzeLeggings");
				setUnlocalizedName(ModInformation.ID + ".armor.bronze.leggings");
				setMaxDamage(380);
				break;
			}
			case 3: {
				setTextureName(ModInformation.ID + ":armor/bronzeBoots");
				setUnlocalizedName(ModInformation.ID + ".armor.bronze.boots");
				setMaxDamage(290);
				break;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack Stack, Entity entity, int Slot, String type) {
		if (Slot == 2)
			return ModInformation.ID + ":textures/models/armor/bronzeArmor_2.png";
		else
			return ModInformation.ID + ":textures/models/armor/bronzeArmor_1.png";

	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {

		return EnumRarity.common;
	}
}