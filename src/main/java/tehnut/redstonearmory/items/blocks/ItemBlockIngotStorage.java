package tehnut.redstonearmory.items.blocks;

import tehnut.redstonearmory.util.TextHelper;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockIngotStorage extends ItemBlock {

    private static final String[] names = {"enderium.gelid.ingot", "gem.gelid"};

    public ItemBlockIngotStorage(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + "." + names[stack.getItemDamage() % names.length];
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.uncommon;
    }
}