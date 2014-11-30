package main.redstonearmory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockRandomThings extends Block {

	public IIcon[] icon = new IIcon[16];
	public static final Random rand = new Random();

	public BlockRandomThings(Material material) {
		super(material);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeStone);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockName(ModInformation.ID + ".random");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.icon[0] = iconRegister.registerIcon(ModInformation.ID + ":purpleLapisBlock");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.icon[meta];
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < 1; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

		if (world.getBlockMetadata(x, y, z) == 0) {
			ItemStack firework = new ItemStack(Items.fireworks);
			firework.stackTagCompound = new NBTTagCompound();
			NBTTagCompound expl = new NBTTagCompound();
			expl.setBoolean("Flicker", true);
			expl.setBoolean("Trail", true);

			int[] colors = new int[rand.nextInt(8) + 1];
			for (int i = 0; i < colors.length; i++)
			{
				colors[i] = ItemDye.field_150922_c[rand.nextInt(16)];
			}
			expl.setIntArray("Colors", colors);
			byte type = (byte) (rand.nextInt(3) + 1);
			type = type == 3 ? 4 : type;
			expl.setByte("Type", type);

			NBTTagList explosions = new NBTTagList();
			explosions.appendTag(expl);

			NBTTagCompound fireworkTag = new NBTTagCompound();
			fireworkTag.setTag("Explosions", explosions);
			fireworkTag.setByte("Flight", (byte) 1);
			firework.stackTagCompound.setTag("Fireworks", fireworkTag);

			EntityFireworkRocket e = new EntityFireworkRocket(world, x + 0.5, y + 0.5, z + 0.5, firework);
			world.spawnEntityInWorld(e);
		}

		return false;
	}
}
