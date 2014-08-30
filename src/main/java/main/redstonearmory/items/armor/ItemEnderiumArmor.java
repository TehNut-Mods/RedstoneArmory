package main.redstonearmory.items.armor;

import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.TextHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redstonearsenal.item.armor.ItemArmorRF;

import java.util.List;

public class ItemEnderiumArmor extends ItemArmorRF {

	public static final ArmorProperties UNBLOCKABLE = new ArmorProperties(0, 0.0D, 0);
	public static final ArmorProperties FLUX = new ArmorProperties(0, 0.5D, Integer.MAX_VALUE);

	public int maxEnergy = 1000000;
	public int maxTransfer = 4500;

	public double absorbRatio = 1.2D;
	public int energyPerDamage = 200;

	public ItemEnderiumArmor(ArmorMaterial material, int type) {

		super(material, type);
		this.setNoRepair();
		this.setCreativeTab(RedstoneArmory.tabRArm);

		switch (type) {
			case 0: {
				this.setTextureName(ModInformation.ID + ":armor/enderiumHelm");
				this.setUnlocalizedName(ModInformation.ID + ".armor.enderium.helm");
				break;
			}
			case 1: {
				this.setTextureName(ModInformation.ID + ":armor/enderiumChestplate");
				this.setUnlocalizedName(ModInformation.ID + ".armor.enderium.chestplate");
				break;
			}
			case 2: {
				this.setTextureName(ModInformation.ID + ":armor/enderiumLeggings");
				this.setUnlocalizedName(ModInformation.ID + ".armor.enderium.leggings");
				break;
			}
			case 3: {
				this.setTextureName(ModInformation.ID + ":armor/enderiumBoots");
				this.setUnlocalizedName(ModInformation.ID + ".armor.enderium.boots");
				break;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack Stack, Entity entity, int Slot, String type) {
		switch(Slot) {
			case 0: {
				return ModInformation.ID + ":textures/models/armor/gelidEnderium_1.png";
			}
			case 1: {
				return ModInformation.ID + ":textures/models/armor/gelidEnderium_1.png";
			}
			case 2: {
				return ModInformation.ID + ":textures/models/armor/gelidEnderium_2.png";
			}
			case 3: {
				return ModInformation.ID + ":textures/models/armor/gelidEnderium_1.png";
			}
		}
		return type;
	}

	protected int getAbsorptionRatio() {

		switch (armorType) {
			case 0:
				return 15;
			case 1:
				return 45;
			case 2:
				return 30;
			case 3:
				return 10;
		}
		return 0;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {

		list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
		list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {

		if (StringHelper.displayShiftForDetail && !StringHelper.isShiftKeyDown()) {
			list.add(StringHelper.shiftForDetails());
		}
		if (!StringHelper.isShiftKeyDown()) {
			return;
		}
		if (stack.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(stack, 0);
		}
		list.add(StringHelper.localize("info.cofh.charge") + ": " + stack.stackTagCompound.getInteger("Energy") + " / " + maxEnergy + " RF");
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

	protected int getBaseAbsorption() {

		return 20;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {

		return EnumRarity.uncommon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
	}
}
