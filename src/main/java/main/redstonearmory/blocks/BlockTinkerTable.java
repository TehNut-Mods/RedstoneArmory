package main.redstonearmory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.items.armor.ItemPowersuit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockTinkerTable extends Block {

	public IIcon topIcon;
	public IIcon sideIcon;
	public IIcon bottomIcon;

	public BlockTinkerTable(Material material) {
		super(material);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeMetal);
		this.setCreativeTab(RedstoneArmory.tabRArm);
		this.setBlockName(ModInformation.ID + ".table.tinker");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(ModInformation.ID + ":tinkerTable_bottom");
		this.topIcon = iconRegister.registerIcon(ModInformation.ID + ":tinkerTable_top");
		this.sideIcon = iconRegister.registerIcon(ModInformation.ID + ":tinkerTable_side");
		this.bottomIcon = iconRegister.registerIcon(ModInformation.ID + ":tinkerTable_bottom");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if(side == 1) {
			return topIcon;
		} else if(side == 0) {
			return bottomIcon;
		} else {
			return sideIcon;
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemPowersuit && side == 1) {
			player.swingItem();
			player.inventory.consumeInventoryItem(player.getHeldItem().getItem());
		} else {
			if(!world.isRemote)
				player.addChatComponentMessage(new ChatComponentTranslation("info.RArm.chat.table.tinker.instruct"));
		}
		return false;
	}
}
