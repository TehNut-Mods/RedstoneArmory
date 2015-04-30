package tehnut.redstonearmory.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tehnut.redstonearmory.items.blocks.ItemBlockIngotStorage;
import tehnut.redstonearmory.items.blocks.ItemBlockRandomThings;
import tehnut.redstonearmory.util.annot.Register;

public class BlockRegistry {

    // Blocks
    @Register(itemBlock = ItemBlockIngotStorage.class) public static Block ingotStorage = new BlockIngotStorage();
    @Register(enabled = "addNutsToys", itemBlock = ItemBlockRandomThings.class) public static Block randomBlocks = new BlockRandomThings(Material.rock);
    @Register(name = "BlockInvisible.Redstone", tileEntity = BlockInvisible.TileInvisible.class) public static Block invisiRedstone = new BlockInvisible(InvisibleType.REDSTONE);
    @Register(name = "BlockInvisible.Light", tileEntity = BlockInvisible.TileInvisible.class) public static Block invisiLight = new BlockInvisible(InvisibleType.LUMIUM);
    @Register(enabled = "enableTestingEnviro") public static Block tinkerTable;
}
