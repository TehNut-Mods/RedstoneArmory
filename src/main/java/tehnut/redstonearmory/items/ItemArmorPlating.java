package tehnut.redstonearmory.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tehnut.redstonearmory.ModInformation;
import tehnut.redstonearmory.RedstoneArmory;

import java.util.List;

public class ItemArmorPlating extends Item {

    private static final String[] names = {"enderium", "power.empty", "power.full"};
    public IIcon[] icon = new IIcon[16];

    public ItemArmorPlating() {
        setUnlocalizedName(ModInformation.ID + ".plating");
        setCreativeTab(RedstoneArmory.tabRArm);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + "." + names[stack.getItemDamage() % names.length];
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icon[meta];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.icon[0] = iconRegister.registerIcon(ModInformation.ID + ":materials/plateEnderium");
        this.icon[1] = iconRegister.registerIcon(ModInformation.ID + ":materials/plateCrafting_empty");
        this.icon[2] = iconRegister.registerIcon(ModInformation.ID + ":materials/plateCrafting_full");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < names.length; i++)
            list.add(new ItemStack(this, 1, i));
    }

    public EnumRarity getRarity(ItemStack stack) {
        if (stack.getItemDamage() == 0)
            return EnumRarity.rare;
        else if (stack.getItemDamage() == 1)
            return EnumRarity.uncommon;
        else if (stack.getItemDamage() == 2)
            return EnumRarity.epic;

        return EnumRarity.common;
    }
}
