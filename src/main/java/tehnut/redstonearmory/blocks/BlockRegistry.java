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
    @Register(tileEntity = BlockInvisiLight.TileInvisibleLight.class) public static Block invisiLight = new BlockInvisiLight(Material.air);
    @Register(enabled = "enableTestingEnviro") public static Block tinkerTable;
}
