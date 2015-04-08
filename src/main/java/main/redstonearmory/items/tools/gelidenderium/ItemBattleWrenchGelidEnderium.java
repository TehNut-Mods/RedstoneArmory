package main.redstonearmory.items.tools.gelidenderium;

import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.KeyboardHelper;
import main.redstonearmory.util.TextHelper;
import main.redstonearmory.util.TooltipHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cofh.redstonearsenal.item.tool.ItemWrenchBattleRF;

import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
public class ItemBattleWrenchGelidEnderium extends ItemWrenchBattleRF {

	IIcon activeIcon;
	IIcon drainedIcon;

	int radius = 2;
	Random random = new Random();
	int spinDamage = 2;
	int resistanceEffect = 1;

	public ItemBattleWrenchGelidEnderium(ToolMaterial toolMaterial) {
		super(toolMaterial);
		setCreativeTab(RedstoneArmory.tabRArm);
		setUnlocalizedName(ModInformation.ID + ".tool.enderium.gelid.battlewrench");
		setNoRepair();
		setMaxDamage(0);

		damage = 7;
		damageCharged = 1;
		maxEnergy = 320000;
		maxTransfer = 1600;
		energyPerUse = 350;
		energyPerUseCharged = 800;
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {

		return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {

		this.itemIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumBattleWrench");
		this.activeIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumBattleWrench_active");
		this.drainedIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumBattleWrench_drained");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		if (stack.getItemDamage() != stack.getMaxDamage() && stack.getItem() instanceof ItemBattleWrenchGelidEnderium) {
			stack.setItemDamage(stack.getMaxDamage());
		}
	}

	//	@Override
	//	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
	//		if (isEmpowered(stack)) {
	//			radius = 4;
	//			spinDamage = 4;
	//			resistanceEffect = 4;
	//		}
	//
	//		AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(player.posX - radius, player.posY - radius, player.posZ - radius, player.posX + radius, player.posY + radius, player.posZ + radius);
	//		Iterator iter = world.getEntitiesWithinAABB(EntityLivingBase.class, bb).iterator();
	//		player.addPotionEffect(new PotionEffect(Potion.resistance.id, 20, resistanceEffect, false));
	//		player.swingItem();
	//		if (iter != null) {
	//			while (iter.hasNext()) {
	//				EntityLivingBase entity = (EntityLivingBase) iter.next();
	//				entity.attackEntityFrom(Utils.causePlayerFluxDamage(player), spinDamage);
	//				player.setAngles(-180, 10);
	//				world.spawnParticle("largeexplode", player.posX, player.posY, player.posZ, 1, 1, 1);
	//				if (!player.capabilities.isCreativeMode && random.nextInt(5) == 0)
	//					useEnergy(stack, false);
	//			}
	//		}
	//		return stack;
	//	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
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

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {

		if (StringHelper.displayShiftForDetail && !KeyboardHelper.isShiftDown()) {
			list.add(StringHelper.shiftForDetails());
		}
		if (!StringHelper.isShiftKeyDown()) {
			return;
		}
		if (stack.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(stack, 0);
		}

		TooltipHelper.doEnergyTip(stack, list, maxEnergy, energyPerUse, energyPerUseCharged);
		TooltipHelper.doDamageTip(stack, list, energyPerUse, damage, damageCharged);
	}
}
