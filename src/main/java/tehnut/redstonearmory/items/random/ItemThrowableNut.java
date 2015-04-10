package tehnut.redstonearmory.items.random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tehnut.redstonearmory.ModInformation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemThrowableNut extends ItemSnowball {

    public ItemThrowableNut() {
        setUnlocalizedName(ModInformation.ID + ".random.nut.throwable");
        setTextureName(ModInformation.ID + ":random/nut_throwable");
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(64);
    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
        list.add("It'll throw a custom entity soon");
        list.add("Too much work right now >.>");
    }
}
