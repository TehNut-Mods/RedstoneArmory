package tehnut.redstonearmory.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import tehnut.redstonearmory.items.blocks.ItemBlockIngotStorage;
import tehnut.redstonearmory.items.blocks.ItemBlockRandomThings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tehnut.redstonearmory.ConfigHandler;

public class BlockRegistry {

    // Blocks
    public static Block ingotStorage;
    public static Block randomBlocks;
    public static Block invisiLight;

    public static Block tinkerTable;

    private static void registerBlocks() {
        ingotStorage = new BlockIngotStorage();
        GameRegistry.registerBlock(ingotStorage, ItemBlockIngotStorage.class, "BlockIngotStorage");
        invisiLight = new BlockInvisiLight(Material.air);
        GameRegistry.registerBlock(invisiLight, "BlockInvisiLight");
        GameRegistry.registerTileEntity(BlockInvisiLight.TileInvisibleLight.class, "TileEntityInvisiLight");
        if (ConfigHandler.addNutsToys) {
            randomBlocks = new BlockRandomThings(Material.rock);
            GameRegistry.registerBlock(randomBlocks, ItemBlockRandomThings.class, "BlockRandomThings");
        }

        if (ConfigHandler.enableTestingEnviro) {
            tinkerTable = new BlockTinkerTable(Material.iron);
            GameRegistry.registerBlock(tinkerTable, "BlockTinkerTable");
        }
    }

    public static void registerAllBlocks() {
        registerBlocks();
    }
}
