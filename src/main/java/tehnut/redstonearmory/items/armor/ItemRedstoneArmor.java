package tehnut.redstonearmory.items.armor;

import cofh.core.item.ItemArmorAdv;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tehnut.redstonearmory.ModInformation;
import tehnut.redstonearmory.RedstoneArmory;
import tehnut.redstonearmory.blocks.BlockRegistry;

public class ItemRedstoneArmor extends ItemArmorAdv {

    public ItemRedstoneArmor(int type) {

        super(ArmorMaterial.IRON, 3);
        isRepairable();
        setRepairIngot("dustRedstone");
        setCreativeTab(RedstoneArmory.tabRArm);

        switch (type) {
            case 3: {
                setTextureName(ModInformation.ID + ":armor/redstoneBoots");
                setUnlocalizedName(ModInformation.ID + ".armor.redstone.boots");
                break;
            }
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        int x = (int) Math.floor(player.posX);
        int y = (int) player.posY + 1;
        int z = (int) Math.floor(player.posZ);

        if (!world.isRemote) {
            if (world.getBlock(x, y - 1, z) == Blocks.air)
                world.setBlock(x, y - 1, z, BlockRegistry.invisiRedstone);
            else if (world.getBlock(x, y - 1, z) == Blocks.redstone_wire)
                world.setBlock(x, y, z, BlockRegistry.invisiRedstone);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack Stack, Entity entity, int slot, String type) {
        return ModInformation.ID + ":textures/models/armor/redstoneArmor_1.png";
    }
}
