package main.redstonearmory.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.util.KeyboardHandler;
import main.redstonearmory.util.TextHelper;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class ItemPortableTesseract extends Item {

	public ItemPortableTesseract(int id) {
		super(id);
		this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
		this.setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ri) {
		this.itemIcon = ri.registerIcon(ModInformation.ID + ":tools/portableTesseract_active");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if(player.isSneaking()) {
			if (world.getBlockTileEntity(x, y, z) instanceof IInventory) {
				stack.setTagCompound(new NBTTagCompound());
				stack.stackTagCompound.setInteger("x", x);
				stack.stackTagCompound.setInteger("y", y);
				stack.stackTagCompound.setInteger("z", z);
				return true;
			}
		}
		return false;
	}

	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		if (stack.stackTagCompound != null) {
			int x = stack.stackTagCompound.getInteger("x");
			int y = stack.stackTagCompound.getInteger("y");
			int z = stack.stackTagCompound.getInteger("z");
			TileEntity tile = world.getBlockTileEntity(x, y, z);

			if (tile != null && tile instanceof IInventory && entity instanceof EntityPlayer) {
				if (((IInventory) tile).getSizeInventory() > 0 && getItemInTileInv(tile) != null) {
					ItemStack target = getItemInTileInv(tile);
					if (getItemInPlayerInv((EntityPlayer) entity, target) != null) {
						if (getItemInPlayerInv((EntityPlayer) entity, target).itemID == target.itemID) {
							if (target.stackSize <= 64) {
								target.stackSize++;
								if (getItemInPlayerInv((EntityPlayer) entity, target).stackSize > 0)
									getItemInPlayerInv((EntityPlayer) entity, target).stackSize--;
							}
						}
					}
				}
			}
		}
	}

	public ItemStack getItemInPlayerInv(EntityPlayer player, ItemStack stack) {
		for (int i = 0; i < player.inventory.mainInventory.length; i++) {
			if ((player.inventory.mainInventory[i] != null) && player.inventory.mainInventory[i].itemID == stack.itemID) {
				return player.inventory.mainInventory[i];
			}
		}
		return null;
	}

	public ItemStack getItemInTileInv(TileEntity tile) {
		if (tile instanceof IInventory) {
			for (int i = 0; i < ((IInventory) tile).getSizeInventory(); i++) {
				if ((((IInventory) tile).getStackInSlot(i) != null)) {
					return ((IInventory) tile).getStackInSlot(i);
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

		if (!KeyboardHandler.isShiftDown() && !KeyboardHandler.isControlDown()) {
			list.add(TextHelper.shiftForMoreInfo);
			if (ConfigHandler.addItemLoreToItems) {
				list.add(TextHelper.controlForLore);
			}
		} else if (KeyboardHandler.isShiftDown() && KeyboardHandler.isControlDown()) {
			list.add(TextHelper.shiftForMoreInfo);
			if (ConfigHandler.addItemLoreToItems) {
				list.add(TextHelper.controlForLore);
			}
		} else if (KeyboardHandler.isShiftDown() && !KeyboardHandler.isControlDown()) {
			if (stack.stackTagCompound != null) {
				int x = stack.stackTagCompound.getInteger("x");
				int y = stack.stackTagCompound.getInteger("y");
				int z = stack.stackTagCompound.getInteger("z");

				list.add(TextHelper.localize("info.redstonearmory.tool.tesseract.coords"));
				list.add("X: " + String.valueOf(x));
				list.add("Y: " + String.valueOf(y));
				list.add("Z: " + String.valueOf(z));
			} else {
				list.add(TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.key.shift") + TextHelper.END + " + " + TextHelper.YELLOW + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.mouse.rc") + TextHelper.END + TextHelper.LIGHT_GRAY +  TextHelper.spacer + TextHelper.localize("info.redstonearmory.tool.tesseract.usage") + TextHelper.END);
			}
			list.add(TextHelper.BRIGHT_GREEN + TextHelper.localize("info.redstonearmory.tool.tesseract.info"));
			list.add(TextHelper.BRIGHT_GREEN + TextHelper.localize("info.redstonearmory.tool.tesseract.hotbar"));
			list.add(TextHelper.BRIGHT_BLUE + TextHelper.ITALIC + TextHelper.localize("info.redstonearmory.tool.tesseract.toggle"));
		}
	}
}