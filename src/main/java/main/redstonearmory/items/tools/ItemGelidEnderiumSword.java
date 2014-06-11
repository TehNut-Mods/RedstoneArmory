package main.redstonearmory.items.tools;

import codechicken.lib.vec.Vector3;
import cofh.api.energy.IEnergyContainerItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.items.itemutil.IEmpowerableItem;
import main.redstonearmory.util.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ItemGelidEnderiumSword extends ItemSword implements IEmpowerableItem, IEnergyContainerItem {

	Icon activeIcon;
	Icon drainedIcon;

	Random random = new Random();
	int radius = 5;
	public int maxEnergy = 160000;
	public int maxTransfer = 1600;
	public int energyPerUse = 200;
	public int energyPerUseCharged = 800;

	public int damage = 8;
	public int damageCharged = 4;

	public ItemGelidEnderiumSword(int par1, EnumToolMaterial toolMaterial) {

		super(par1, toolMaterial);
		setNoRepair();
		hasSubtypes();
		this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
	}

	@Override
	public Icon getIcon(ItemStack stack, int pass) {

		return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
	}

	@Override
	public void registerIcons(IconRegister ir) {

		this.itemIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSword");
		this.activeIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSword_active");
		this.drainedIcon = ir.registerIcon(ModInformation.ID + ":tools/gelidEnderiumSword_drained");
	}

	protected int useEnergy(ItemStack stack, boolean simulate) {

		int unbreakingLevel = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
		return extractEnergy(stack, isEmpowered(stack) ? energyPerUseCharged * (5 - unbreakingLevel) / 5 : energyPerUse * (5 - unbreakingLevel) / 5, simulate);
	}

	protected int getEnergyPerUse(ItemStack stack) {

		int unbreakingLevel = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
		return (isEmpowered(stack) ? energyPerUseCharged : energyPerUse) * (5 - unbreakingLevel) / 5;
	}

	public boolean hasSubtypes() {
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {

		return false;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {

		return EnumRarity.uncommon;
	}

	@Override
	public void getSubItems(int item, CreativeTabs tab, List list) {

		list.add(RFHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
		list.add(RFHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {

		if (stack.getItemDamage() > 0) {
			stack.setItemDamage(0);
		}
		EntityPlayer thePlayer = (EntityPlayer) player;
		float fallingMult = (player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater()
				&& !player.isPotionActive(Potion.blindness) && player.ridingEntity == null) ? 1.5F : 1.0F;

		if (thePlayer.capabilities.isCreativeMode || useEnergy(stack, false) == getEnergyPerUse(stack)) {
			float fluxDamage = isEmpowered(stack) ? damageCharged : 1;
			float enchantDamage = damage + EnchantmentHelper.getEnchantmentModifierLiving(player, entity);

			entity.attackEntityFrom(RFItemUtils.causePlayerFluxDamage(thePlayer), fluxDamage);
			entity.attackEntityFrom(DamageSource.causePlayerDamage(thePlayer), (fluxDamage + enchantDamage) * fallingMult);
		} else {
			entity.attackEntityFrom(DamageSource.causePlayerDamage(thePlayer), 1 * fallingMult);
		}
		return true;
	}

//	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {

		if (block.getBlockHardness(world, x, y, z) != 0.0D) {
			extractEnergy(stack, energyPerUse, false);
		}
		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {

		if (isEmpowered(stack))
			radius = 10;

		if (!world.isRemote && entity instanceof EntityPlayer && ((EntityPlayer) entity).isUsingItem()) {
			AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(entity.posX - radius, entity.posY - radius, entity.posZ - radius, entity.posX + radius, entity.posY + radius, entity.posZ + radius);
			Iterator iter = world.getEntitiesWithinAABB(EntityItem.class, bb).iterator();
			if (iter != null) {
				while (iter.hasNext()) {
					EntityItem item = (EntityItem) iter.next();
					moveEntity(item, Vector3.fromEntityCenter(entity), 0.1);
					if (random.nextInt(10) == 0)
						world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
				}
			}
		}
	}

	public void moveEntity(Entity ent, Vector3 target, double speed) {
		double mx = getBlendDouble(ent.posX, target.x, speed);
		double my = getBlendDouble(ent.posY, target.y, speed);
		double mz = getBlendDouble(ent.posZ, target.z, speed);

		ent.velocityChanged = true;
		ent.isAirBorne = true;
		ent.addVelocity(mx, my, mz);
	}

	public double getBlendDouble(double d1, double d2, double blend) {
		if (d1 > d2)
			return -blend;
		if (d1 < d2)
			return blend;
		return 0;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
		if (stack.stackTagCompound == null) {
			RFHelper.setDefaultEnergyTag(stack, 0);
		}
		if (!KeyboardHandler.isShiftDown() && !KeyboardHandler.isControlDown()) {
			list.add(TextHelper.shiftForMoreInfo);
			if(ConfigHandler.addItemLoreToItems) {
				list.add(TextHelper.controlForLore);
			}
		} else if(KeyboardHandler.isShiftDown() && KeyboardHandler.isControlDown()) {
			list.add(TextHelper.shiftForMoreInfo);
			if(ConfigHandler.addItemLoreToItems) {
				list.add(TextHelper.controlForLore);
			}
		} else if(KeyboardHandler.isShiftDown() && !KeyboardHandler.isControlDown()) {
			list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.redstonearmory.tool.charge") + " " + RFHelper.getRFStored(stack) + " / " + maxEnergy + " " + TextHelper.localize("info.redstonearmory.tool.rf") + TextHelper.END);
			list.add(TextHelper.ORANGE + energyPerUse + " " + TextHelper.localize("info.redstonearmory.tool.energyPerUse") + TextHelper.END);
			if (isEmpowered(stack)) {
				list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.tool.press") + " " + ConfigHandler.empowerKey + " " + TextHelper.localize("info.redstonearmory.tool.chargeOff") + TextHelper.END);
			} else {
				list.add(TextHelper.BRIGHT_BLUE + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.tool.press") + " " + ConfigHandler.empowerKey + " " + TextHelper.localize("info.redstonearmory.tool.chargeOn") + TextHelper.END);
			}
		} else if(!KeyboardHandler.isShiftDown() && KeyboardHandler.isControlDown() && ConfigHandler.addItemLoreToItems) {
			list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.redstonearmory.tools.default") + TextHelper.END);
		}
	}
//
// @Override
//	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
//
//		if (TextHelper.displayShiftForDetail && !TextHelper.isShiftKeyDown()) {
//			list.add();
//		}
//		if (!TextHelper.isShiftKeyDown()) {
//			return;
//		}
//		if (stack.stackTagCompound == null) {
//			EnergyHelper.setDefaultEnergyTag(stack, 0);
//		}
//		list.add(TextHelper.localize("info.redstonearmory.tool.charge") + ": " + stack.stackTagCompound.getInteger("Energy") + " / " + maxEnergy + " RF");
//
//		list.add(TextHelper.ORANGE + getEnergyPerUse(stack) + " " + TextHelper.localize("info.redstonearmory.tool.energyPerUse") + TextHelper.END);
//		if (isEmpowered(stack)) {
//			list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.tool.press") + " "
//					+ ConfigHandler.empowerKey + " " + TextHelper.localize("info.redstonearmory.tool.chargeOff")
//					+ TextHelper.END);
//		} else {
//			list.add(TextHelper.BRIGHT_BLUE + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.tool.press") + " "
//					+ ConfigHandler.empowerKey + " " + TextHelper.localize("info.redstonearmory.tool.chargeOn")
//					+ TextHelper.END);
//		}
//		if (getEnergyStored(stack) >= getEnergyPerUse(stack)) {
//			list.add("");
//			list.add(TextHelper.LIGHT_BLUE + "+" + damage + " " + TextHelper.localize("info.redstonearmory.tool.damageAttack") + TextHelper.END);
//			list.add(TextHelper.BRIGHT_GREEN + "+" + (isEmpowered(stack) ? damageCharged : 1) + " " + TextHelper.localize("info.redstonearmory.tool.damageFlux")
//					+ TextHelper.END);
//		}
//	}

	@Override
	public int getDisplayDamage(ItemStack stack) {

		if (stack.stackTagCompound == null) {
			RFHelper.setDefaultEnergyTag(stack, 0);
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
	public Multimap getItemAttributeModifiers() {

		return HashMultimap.create();
	}

	@Override
	public Icon getIconIndex(ItemStack stack) {

		return getIcon(stack, 0);
	}

	/* IEmpowerableItem */
	@Override
	public boolean isEmpowered(ItemStack stack) {

		return stack.stackTagCompound == null ? false : stack.stackTagCompound.getBoolean("Empowered");
	}

	@Override
	public boolean setEmpoweredState(ItemStack stack, boolean state) {

		if (getEnergyStored(stack) > 0) {
			stack.stackTagCompound.setBoolean("Empowered", state);
			return true;
		}
		stack.stackTagCompound.setBoolean("Empowered", false);
		return false;
	}

	/* IEnergyContainerItem */
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

		if (container.stackTagCompound == null) {
			RFHelper.setDefaultEnergyTag(container, 0);
		}
		int stored = container.stackTagCompound.getInteger("Energy");
		int receive = Math.min(maxReceive, Math.min(maxEnergy - stored, maxTransfer));

		if (!simulate) {
			stored += receive;
			container.stackTagCompound.setInteger("Energy", stored);
		}
		return receive;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

		if (container.stackTagCompound == null) {
			RFHelper.setDefaultEnergyTag(container, 0);
		}
		int stored = container.stackTagCompound.getInteger("Energy");
		int extract = Math.min(maxExtract, stored);

		if (!simulate) {
			stored -= extract;
			container.stackTagCompound.setInteger("Energy", stored);

			if (stored == 0) {
				setEmpoweredState(container, false);
			}
		}
		return extract;
	}

	@Override
	public int getEnergyStored(ItemStack container) {

		if (container.stackTagCompound == null) {
			RFHelper.setDefaultEnergyTag(container, 0);
		}
		return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {

		return maxEnergy;
	}

}
