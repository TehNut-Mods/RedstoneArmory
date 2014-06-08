package main.redstonearmory.items.tools;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.items.ItemInfo;
import main.redstonearmory.util.KeyboardHandler;
import main.redstonearmory.util.RFHelper;
import main.redstonearmory.util.TextHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.List;

public class ItemGelidEnderiumSword extends ItemSword implements IEnergyContainerItem {

	@SuppressWarnings("unused")
	private Icon activeIcon;
	private Icon drainedIcon;
	public int capacity = 320000;
	public int cost = 200;
	public int empoweredCost = 600;
	public int transferLimit = 1000;

	public ItemGelidEnderiumSword(int id, EnumToolMaterial material) {
		super(id, material);
		this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
		this.setUnlocalizedName(ModInformation.ID + ItemInfo.SWORD_GELID_ENDERIUM_UNLOCALIZED_NAME);
		this.setTextureName("redstonearmory:tools/gelidEnderiumSword_drained");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("redstonearmory:tools/gelidEnderiumSword");
		this.activeIcon = iconRegister.registerIcon("redstonearmory:tools/gelidEnderiumSword_active");
		this.drainedIcon = iconRegister.registerIcon("redstonearmory:tools/gelidEnderiumSword_drained");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(ItemStack container, int renderPass) {
		if (getEnergyStored(container) < getUsedEnergy(container)) {
			return drainedIcon;
		} else
			return itemIcon;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(RFHelper.setDefaultEnergyTag(new ItemStack(this, 1, 0), 0));
		list.add(RFHelper.setDefaultEnergyTag(new ItemStack(this, 1, 0), this.capacity));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack container, EntityPlayer player, List list, boolean check) {
		if (container.stackTagCompound == null) {
			RFHelper.setDefaultEnergyTag(container, 0);
		}
		if (!KeyboardHandler.isShiftDown() && !KeyboardHandler.isControlDown()) {
			list.add(TextHelper.shiftForMoreInfo);
			if(ConfigHandler.addItemLoreToItems) {
				list.add(TextHelper.controlForLore);
			}
		} else if(KeyboardHandler.isShiftDown() && !KeyboardHandler.isControlDown()) {
			list.add(TextHelper.LIGHT_GRAY + "Charge: " + RFHelper.getRFStored(container) + " / " + capacity + " RF");
			list.add(TextHelper.ORANGE + this.cost + " RF Per Use");
			list.add(TextHelper.blueItalic + "Press " + KeyboardHandler.empowerKey() + " to Empower");
			list.add(TextHelper.ITALIC + "Also acts as a magnet");
		} else if(!KeyboardHandler.isShiftDown() && KeyboardHandler.isControlDown() && ConfigHandler.addItemLoreToItems){
			list.add(TextHelper.LIGHT_GRAY + "Ever wanted the ability to");
			list.add(TextHelper.LIGHT_GRAY + "suck in nearby items while");
			list.add(TextHelper.LIGHT_GRAY + "blocking? No? Well now you");
			list.add(TextHelper.LIGHT_GRAY + "can!");
			list.add("");
			list.add(TextHelper.RED + "Empower for more suckage.");
		}
	}

	@Override
	public int getMaxDamage(ItemStack container) {
		return this.capacity + 1;
	}

	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public int getDisplayDamage(ItemStack container) {
		if (container.stackTagCompound == null)
			RFHelper.setDefaultEnergyTag(container, 0);

		return (this.capacity + 1 - RFHelper.getRFStored(container));
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		return RFHelper.receiveEnergy(container, maxReceive, simulate, this.capacity, this.transferLimit);
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		return RFHelper.extractEnergy(container, maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return RFHelper.getRFStored(container);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return capacity;
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {
		return false;
	}

	public int useEnergy(ItemStack container, boolean simulate) {
		int unbreaking = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, container);
		return extractEnergy(container, (this.cost - (unbreaking * 10)), simulate);
		// if (PLACEEMPOWEREDTHINGHERE == false) {
		// return extractEnergy(container, (this.cost - (unbreaking * 10)), simulate);
		// } else {
		// return extractEnergy(container, (this.empoweredCost - (unbreaking * 10)), simulate);
		// }
	}

	public int getUsedEnergy(ItemStack container) {
		int unbreaking = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, container);
		return (this.cost - (unbreaking * 10));
	}

	//	@Override
	public boolean onBlockDestroyed(ItemStack container, World world, Block block, int par4, int par5, int par6, EntityLivingBase entityLiving) {
		useEnergy(container, false);
		return true;
	}

	//	@Override
	public float getDigSpeed(ItemStack container) {
		if (getEnergyStored(container) < getUsedEnergy(container)) {
			return 0.5F;
		} else {
			// return int to specify dig speed
			return 100F;
		}
	}

	@Override
	public boolean hitEntity(ItemStack container, EntityLivingBase entityliving, EntityLivingBase attacker) {
		useEnergy(container, false);
		return true;
	}

	// Temporary way to charge the items
	@Override
	public void onUpdate(ItemStack container, World world, Entity entity, int par4, boolean par5) {
		if (!world.isRemote && container != null && (entity instanceof EntityPlayer) && KeyboardHandler.isCDown())
			receiveEnergy(container, transferLimit, false);
	}
}
