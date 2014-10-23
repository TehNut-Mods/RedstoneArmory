package main.redstonearmory.items.tools.gelidenderium;

import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.KeyboardHelper;
import main.redstonearmory.util.TextHelper;
import main.redstonearmory.util.TooltipHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import redstonearsenal.item.tool.ItemAxeRF;

import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
public class ItemAxeGelidEnderium extends ItemAxeRF {

	IIcon activeIcon;
	IIcon drainedIcon;

	private int damage = 8;
	private int damageCharged = 1;
	Random random = new Random();

	public ItemAxeGelidEnderium(ToolMaterial toolMaterial) {
		super(toolMaterial);
		this.setCreativeTab(RedstoneArmory.tabRArm);
		this.setUnlocalizedName(ModInformation.ID + ".tool.enderium.gelid.axe");
		this.setNoRepair();
		this.setMaxDamage(0);

		maxEnergy = 320000;
		energyPerUse = 350;
		energyPerUseCharged = 600;

		effectiveMaterials.add(Material.wood);
		effectiveMaterials.add(Material.plants);
		effectiveMaterials.add(Material.leaves);
		effectiveMaterials.add(Material.vine);
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {

		return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {

		this.itemIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumAxe");
		this.activeIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumAxe_active");
		this.drainedIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumAxe_drained");
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {

		if (!(entity instanceof EntityPlayer)) {
			return false;
		}
		if (block.getBlockHardness(world, x, y, z) == 0.0D) {
			return true;
		}
		EntityPlayer player = (EntityPlayer) entity;

		if (block.getMaterial() == Material.wood && isEmpowered(stack)) {
			for (int i = x - 2; i <= x + 2; i++) {
				for (int k = z - 2; k <= z + 2; k++) {
					for (int j = y - 2; j <= y + 2; j++) {
						if (world.getBlock(i, j, k).getMaterial() == Material.wood) {
							harvestBlock(world, i, j, k, player);
						}
					}
				}
			}
		}
		if (!player.capabilities.isCreativeMode) {
			useEnergy(stack, false);
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (ConfigHandler.enableAxeWeatherClear) {
			if (world.isRaining() && isEmpowered(stack) || world.isThundering() && isEmpowered(stack)) {
				WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
				WorldInfo worldinfo = worldserver.getWorldInfo();

				int i = (300 + (new Random()).nextInt(600)) * 20;

				worldinfo.setRaining(false);
				worldinfo.setThundering(false);
				worldinfo.setRainTime(i);

				if (random.nextInt(50) == 0) {
					world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX, player.posY, player.posZ));
				}
				if (!player.capabilities.isCreativeMode) {
					useEnergy(stack, false);
				}
				player.swingItem();
			}
		}
		return stack;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {
		if (ConfigHandler.enableAxeLightning) {
			if (!(getEnergyStored(stack) < energyPerUse)) {
				if (!isEmpowered(stack)) {
					world.spawnEntityInWorld(new EntityLightningBolt(world, x, y, z));
				} else if (isEmpowered(stack) && getEnergyStored(stack) >= energyPerUseCharged) {
					for (int i = 0; i <= 10; i++) {
						world.spawnEntityInWorld(new EntityLightningBolt(world, x, y, z));
						if (random.nextInt(50) == 0)
							world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX, player.posY, player.posZ));
					}
				}
			}
		}

		if (!player.capabilities.isCreativeMode)
			useEnergy(stack, false);

		return true;
	}

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

//	@SideOnly(Side.CLIENT)
	//	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
	//		if (StringHelper.displayShiftForDetail && !KeyboardHelper.isShiftDown()) {
	//			list.add(StringHelper.shiftForDetails());
	//		}
	//		if (!StringHelper.isShiftKeyDown()) {
	//			return;
	//		}
	//		if (stack.stackTagCompound == null) {
	//			EnergyHelper.setDefaultEnergyTag(stack, 0);
	//		}
	//		list.add(TextHelper.localize("info.cofh.charge") + ": " + stack.stackTagCompound.getInteger("Energy") + " / " + maxEnergy + " RF");
	//
	//		list.add(TextHelper.ORANGE + getEnergyPerUse(stack) + " " + TextHelper.localize("info.redstonearsenal.tool.energyPerUse") + TextHelper.END);
	//		if (isEmpowered(stack)) {
	//			list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localize("info.cofh.press") + " " + Keyboard.getKeyName(KeyBindingEmpower.instance.getKey()) + " " + TextHelper.localize("info.redstonearsenal.tool.chargeOff") + TextHelper.END);
	//		} else {
	//			list.add(TextHelper.BRIGHT_BLUE + TextHelper.ITALIC + TextHelper.localize("info.cofh.press") + " " + Keyboard.getKeyName(KeyBindingEmpower.instance.getKey()) + " " + TextHelper.localize("info.redstonearsenal.tool.chargeOn") + TextHelper.END);
	//		}
	//		if (getEnergyStored(stack) >= getEnergyPerUse(stack)) {
	//			list.add("");
	//			list.add(TextHelper.LIGHT_BLUE + "+" + damage + " " + TextHelper.localize("info.cofh.damageAttack") + TextHelper.END);
	//			list.add(TextHelper.BRIGHT_GREEN + "+" + (isEmpowered(stack) ? damageCharged : 1) + " " + TextHelper.localize("info.cofh.damageFlux") + TextHelper.END);
	//		}
	//		if(KeyboardHelper.isShiftDown()) {
	//			if(ConfigHandler.enableAxeWeatherClear) {
	//				list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.ability") + TextHelper.localize("info.RArm.tooltip.ability.axe.weather"));
	//			}
	//			if(ConfigHandler.enableAxeMultiBreak) {
	//				list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.ability") + TextHelper.localize("info.RArm.tooltip.ability.axe.break"));
	//			}
	//			if(ConfigHandler.enableAxeLightning) {
	//				list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.ability") + TextHelper.localize("info.RArm.tooltip.ability.axe.lightning"));
	//			}
	//		}
	//	}

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

		TooltipHelper.doEnergyTip(stack, player, list, maxEnergy, maxTransfer, energyPerUse, energyPerUseCharged);
		TooltipHelper.doDamageTip(stack, player, list, energyPerUse, damage, damageCharged);
	}
}
