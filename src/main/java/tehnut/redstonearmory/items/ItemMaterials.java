package tehnut.redstonearmory.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tehnut.redstonearmory.ModInformation;
import tehnut.redstonearmory.RedstoneArmory;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemMaterials extends Item {

    public String[] names = {"enderium.gelid.ingot", "enderium.gelid.nugget", "enderium.gelid.gem", "enderium.gelid.rod", "chainlink", "iron.nugget", "string.fluxed"};
    public IIcon[] icon = new IIcon[names.length];

    public ItemMaterials() {
        setCreativeTab(RedstoneArmory.tabRArm);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + ".material." + names[stack.getItemDamage() % names.length];
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icon[meta];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.icon[0] = iconRegister.registerIcon(ModInformation.ID + ":materials/ingotGelidEnderium");
        this.icon[1] = iconRegister.registerIcon(ModInformation.ID + ":materials/nuggetGelidEnderium");
        this.icon[2] = iconRegister.registerIcon(ModInformation.ID + ":materials/gemGelidEnderium");
        this.icon[3] = iconRegister.registerIcon(ModInformation.ID + ":materials/rodGelidEnderium");
        this.icon[4] = iconRegister.registerIcon(ModInformation.ID + ":materials/chainLink");
        this.icon[5] = iconRegister.registerIcon(ModInformation.ID + ":materials/nuggetIron");
        this.icon[6] = iconRegister.registerIcon(ModInformation.ID + ":materials/stringFluxed");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i <= names.length - 1; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    public EnumRarity getRarity(ItemStack stack) {
        if (stack.getItemDamage() <= 3)
            return EnumRarity.rare;
        else if (stack.getItemDamage() == 6)
            return EnumRarity.uncommon;

        return EnumRarity.common;
    }
}