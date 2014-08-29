package main.redstonearmory.items.tools.gelidenderium;

import cofh.core.util.KeyBindingEmpower;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.KeyboardHelper;
import main.redstonearmory.util.TextHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.lwjgl.input.Keyboard;
import redstonearsenal.item.tool.ItemPickaxeRF;

import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
public class ItemPickaxeGelidEnderium extends ItemPickaxeRF {

	IIcon activeIcon;
	IIcon drainedIcon;

	public int damage = 6;
	public int damageCharged = 3;
	int range = 4;

	Random random = new Random();

	public ItemPickaxeGelidEnderium(ToolMaterial toolMaterial) {
		super(toolMaterial);
		this.setCreativeTab(RedstoneArmory.tabRArm);
		this.setUnlocalizedName(ModInformation.ID + ".tool.enderium.gelid.pickaxe");
		this.setNoRepair();

		maxEnergy = 320000;
		energyPerUse = 350;
		energyPerUseCharged = 600;

		effectiveMaterials.add(Material.iron);
		effectiveMaterials.add(Material.anvil);
		effectiveMaterials.add(Material.rock);
		effectiveMaterials.add(Material.glass);
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return isEmpowered(stack) ? this.activeIcon : getEnergyStored(stack) <= 0 ? this.drainedIcon : this.itemIcon;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {

		this.itemIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumPickaxe");
		this.activeIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumPickaxe_active");
		this.drainedIcon = iconRegister.registerIcon(ModInformation.ID + ":tools/gelidEnderiumPickaxe_drained");
	}

//	@Override
//	public boolean onBlockDestroyed(ItemStack stack, World world, int block, int x, int y, int z, EntityLivingBase entity) {
//
//		if (!(entity instanceof EntityPlayer)) {
//			return false;
//		}
//		EntityPlayer player = (EntityPlayer) entity;
//
//		if (ConfigHandler.enablePickaxeEnderDislocation) {
//			if ((block == Block.cobblestone.blockID || block == Block.stone.blockID || block == Block.sandStone.blockID || block == Block.netherrack.blockID) && isEmpowered(stack)) {
//				for (int i = x - 1; i <= x + 1; i++) {
//					for (int k = z - 1; k <= z + 1; k++) {
//						for (int j = y - 1; j <= y + 1; j++) {
//							if (Block.blocksList[world.getBlockId(i, j, k)] == Block.cobblestone || Block.blocksList[world.getBlockId(i, j, k)] == Block.stone || Block.blocksList[world.getBlockId(i, j, k)] == Block.sandStone || Block.blocksList[world.getBlockId(i, j, k)] == Block.netherrack) {
//								int facing = MathHelper.floor(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
//
//								if (facing == 0) {
//									int coordZ = z - range;
//									if (world.isAirBlock(i, j, coordZ)) {
//										world.setBlockToAir(i, j, z);
//										world.setBlock(i, j, coordZ, block);
//										for (int n = 0; n <= 5; n++)
//											world.spawnParticle("portal", i, j, z, 1, 1, 1);
//										if (random.nextInt(10) == 0)
//											world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
//									}
//									// else {
//									//  harvestBlock(world, i, j, z, player);
//									//  }
//								} else if (facing == 1) {
//									int coordX = x + range;
//									if (world.isAirBlock(coordX, j, k)) {
//										world.setBlockToAir(x, j, k);
//										world.setBlock(coordX, j, k, block);
//										for (int n = 0; n <= 5; n++)
//											world.spawnParticle("portal", x, j, k, 1, 1, 1);
//										if (random.nextInt(10) == 0)
//											world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
//									}
//									//else {
//									//  harvestBlock(world, x, j, k, player);
//									// }
//								} else if (facing == 2) {
//									int coordZ = z + range;
//									if (world.isAirBlock(i, j, coordZ)) {
//										world.setBlockToAir(i, j, z);
//										world.setBlock(i, j, coordZ, block);
//										for (int n = 0; n <= 5; n++)
//											world.spawnParticle("portal", i, j, z, 1, 1, 1);
//										if (random.nextInt(10) == 0)
//											world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
//									}
//									//else {
//									//  harvestBlock(world, i, j, z, player);
//									//}
//								} else if (facing == 3) {
//									int coordX = x - range;
//									if (world.isAirBlock(coordX, j, k)) {
//										world.setBlockToAir(x, j, k);
//										world.setBlock(coordX, j, k, block);
//										for (int n = 0; n <= 5; n++)
//											world.spawnParticle("portal", x, j, k, 1, 1, 1);
//										if (random.nextInt(10) == 0)
//											world.playSoundAtEntity(entity, "mob.endermen.portal", 1.0F, 1.0F);
//									}
//									// /else {
//									//  harvestBlock(world, x, j, k, player);
//									//  }
//								} else {
//									harvestBlock(world, x, y, z, player);
//								}
//							}
//						}
//					}
//				}
//				if (!player.capabilities.isCreativeMode)
//					useEnergy(stack, false);
//
//				return true;
//			}
//		}
//		if (!player.capabilities.isCreativeMode) {
//			useEnergy(stack, false);
//		}
//		return true;
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
		list.add(TextHelper.localize("info.cofh.charge") + ": " + stack.stackTagCompound.getInteger("Energy") + " / " + maxEnergy + " RF");

		list.add(TextHelper.ORANGE + getEnergyPerUse(stack) + " " + TextHelper.localize("info.redstonearsenal.tool.energyPerUse") + TextHelper.END);
		if (isEmpowered(stack)) {
			list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localize("info.cofh.press") + " " + Keyboard.getKeyName(KeyBindingEmpower.instance.getKey()) + " " + TextHelper.localize("info.redstonearsenal.tool.chargeOff") + TextHelper.END);
		} else {
			list.add(TextHelper.BRIGHT_BLUE + TextHelper.ITALIC + TextHelper.localize("info.cofh.press") + " " + Keyboard.getKeyName(KeyBindingEmpower.instance.getKey()) + " " + TextHelper.localize("info.redstonearsenal.tool.chargeOn") + TextHelper.END);
		}
		if (getEnergyStored(stack) >= getEnergyPerUse(stack)) {
			list.add("");
			list.add(TextHelper.LIGHT_BLUE + "+" + damage + " " + TextHelper.localize("info.cofh.damageAttack") + TextHelper.END);
			list.add(TextHelper.BRIGHT_GREEN + "+" + (isEmpowered(stack) ? damageCharged : 1) + " " + TextHelper.localize("info.cofh.damageFlux") + TextHelper.END);
		}
		if(KeyboardHelper.isShiftDown() && ConfigHandler.enableAxeWeatherClear) {
			list.add(TextHelper.LIGHT_GRAY + TextHelper.localize("info.RArm.tooltip.ability") + TextHelper.localize("info.RArm.tooltip.ability.pickaxe"));
		}
	}
}
