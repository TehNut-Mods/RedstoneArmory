package main.redstonearmory.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.TextHelper;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemLumiumArmor extends ItemArmor {

	public ItemLumiumArmor(ArmorMaterial material, int renderIndex, int type) {

		super(material, renderIndex, type);
		this.isRepairable();
		this.setCreativeTab(RedstoneArmory.tabRArm);

		switch (type) {
			case 0: {
				this.setTextureName(ModInformation.ID + ":armor/lumiumHelm");
				this.setUnlocalizedName(ModInformation.ID + ".armor.lumium.helm");
				break;
			}
			case 1: {
				this.setTextureName(ModInformation.ID + ":armor/lumiumChestplate");
				this.setUnlocalizedName(ModInformation.ID + ".armor.lumium.chestplate");
				break;
			}
			case 2: {
				this.setTextureName(ModInformation.ID + ":armor/lumiumLeggings");
				this.setUnlocalizedName(ModInformation.ID + ".armor.lumium.leggings");
				break;
			}
			case 3: {
				this.setTextureName(ModInformation.ID + ":armor/lumiumBoots");
				this.setUnlocalizedName(ModInformation.ID + ".armor.lumium.boots");
				break;
			}
		}
	}

//	@Override
//	@SideOnly(Side.CLIENT)
//	public String getArmorTexture(ItemStack Stack, Entity entity, int Slot, String type) {
//		switch(Slot) {
//			case 0: {
//				return ModInformation.ID + ":textures/models/armor/lumiumArmor_1.png";
//			}
//			case 1: {
//				return ModInformation.ID + ":textures/models/armor/lumiumArmor_1.png";
//			}
//			case 2: {
//				return ModInformation.ID + ":textures/models/armor/lumiumArmor_2.png";
//			}
//			case 3: {
//				return ModInformation.ID + ":textures/models/armor/lumiumArmor_1.png";
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
		return TextHelper.YELLOW + super.getItemStackDisplayName(itemStack);
	}
}
