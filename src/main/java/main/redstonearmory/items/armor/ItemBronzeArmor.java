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
		this.isRepairable();
		this.setRepairIngot("ingotBronze");
		this.setCreativeTab(RedstoneArmory.tabRArm);

		switch (type) {
			case 0: {
				this.setTextureName(ModInformation.ID + ":armor/bronzeHelm");
				this.setUnlocalizedName(ModInformation.ID + ".armor.bronze.helm");
				break;
			}
			case 1: {
				this.setTextureName(ModInformation.ID + ":armor/bronzeChestplate");
				this.setUnlocalizedName(ModInformation.ID + ".armor.bronze.chestplate");
				break;
			}
			case 2: {
				this.setTextureName(ModInformation.ID + ":armor/bronzeLeggings");
				this.setUnlocalizedName(ModInformation.ID + ".armor.bronze.leggings");
				break;
			}
			case 3: {
				this.setTextureName(ModInformation.ID + ":armor/bronzeBoots");
				this.setUnlocalizedName(ModInformation.ID + ".armor.bronze.boots");
				break;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack Stack, Entity entity, int Slot, String type) {
		switch(Slot) {
			case 0: {
				return ModInformation.ID + ":textures/models/armor/bronzeArmor_1.png";
			}
			case 1: {
				return ModInformation.ID + ":textures/models/armor/bronzeArmor_1.png";
			}
			case 2: {
				return ModInformation.ID + ":textures/models/armor/bronzeArmor_2.png";
			}
			case 3: {
				return ModInformation.ID + ":textures/models/armor/bronzeArmor_1.png";
			}
		}
		return type;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {

		return EnumRarity.common;
	}
}