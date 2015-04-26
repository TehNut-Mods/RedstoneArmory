package tehnut.redstonearmory.blocks;

import cofh.redstonearsenal.block.BlockStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tehnut.redstonearmory.ModInformation;
import tehnut.redstonearmory.RedstoneArmory;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class BlockIngotStorage extends BlockStorage {

    public IIcon[] icon = new IIcon[16];

    public BlockIngotStorage() {
        super();
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundTypeMetal);
        this.setCreativeTab(RedstoneArmory.tabRArm);
        this.setBlockName(ModInformation.ID + ".storage");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.icon[0] = iconRegister.registerIcon(ModInformation.ID + ":storage/gelidEnderiumIngotBlock");
        this.icon[1] = icon[0];
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
        for (int i = 0; i < 2; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }
}
