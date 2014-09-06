package main.redstonearmory.items.armor;

import cofh.core.item.ItemArmorAdv;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.TextHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemTuberousArmor extends ItemArmorAdv {

	public ItemTuberousArmor(ArmorMaterial material, int type) {

		super(material, type);
		this.isRepairable();
		this.setCreativeTab(RedstoneArmory.tabRArm);
		this.setRepairIngot("cropPotato");

		switch (type) {
			case 0: {
				this.setTextureName(ModInformation.ID + ":armor/tuberousHelm");
				this.setUnlocalizedName(ModInformation.ID + ".armor.tuberous.helm");
				this.setMaxDamage(32);
				break;
			}
			case 1: {
				this.setTextureName(ModInformation.ID + ":armor/tuberousChestplate");
				this.setUnlocalizedName(ModInformation.ID + ".armor.tuberous.chestplate");
				this.setMaxDamage(58);
				break;
			}
			case 2: {
				this.setTextureName(ModInformation.ID + ":armor/tuberousLeggings");
				this.setUnlocalizedName(ModInformation.ID + ".armor.tuberous.leggings");
				this.setMaxDamage(41);
				break;
			}
			case 3: {
				this.setTextureName(ModInformation.ID + ":armor/tuberousBoots");
				this.setUnlocalizedName(ModInformation.ID + ".armor.tuberous.boots");
				this.setMaxDamage(25);
				break;
			}
		}
	}

		@Override
		@SideOnly(Side.CLIENT)
		public String getArmorTexture(ItemStack Stack, Entity entity, int Slot, String type) {
			switch(Slot) {
				case 0: {
					return ModInformation.ID + ":textures/models/armor/tuberousArmor_1.png";
				}
				case 1: {
					return ModInformation.ID + ":textures/models/armor/tuberousArmor_1.png";
				}
				case 2: {
					return ModInformation.ID + ":textures/models/armor/tuberousArmor_2.png";
				}
				case 3: {
					return ModInformation.ID + ":textures/models/armor/tuberousArmor_1.png";
				}
			}
			return type;
		}

	//Very simple, very temporary. I'm tired :(
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if(player.getFoodStats().needFood()) {
			player.inventory.damageArmor(1F);
			player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 1);
			player.getFoodStats().setFoodSaturationLevel(1F);
		}
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.common;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
		list.add(TextHelper.END);
		list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.armor.tuberous.clever.1"));
		list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.armor.tuberous.clever.2"));
	}
}
