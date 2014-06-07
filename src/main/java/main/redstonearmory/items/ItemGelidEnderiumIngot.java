package main.redstonearmory.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ItemGelidEnderiumIngot extends Item {

	public Icon icon;

	public ItemGelidEnderiumIngot(int id) {
		super(id);
		this.setCreativeTab(RedstoneArmory.tabRedstoneArmory);
		setUnlocalizedName(ModInformation.ID + ".items.ingot.gelidenderium");
	}

	@Override
	public void registerIcons(IconRegister register) {
		icon = register.registerIcon("redstonearmory:materials/IngotGelidEnderium");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int dmg) {
		return icon;
	}
}
