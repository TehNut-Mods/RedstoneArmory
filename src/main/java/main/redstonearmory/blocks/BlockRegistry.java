package main.redstonearmory.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.blocks.machine.BlockCompDynamo;
import main.redstonearmory.items.blocks.ItemBlockCompDynamo;
import net.minecraft.block.Block;

public class BlockRegistry {

    public static Block ingotStorage;

	public static Block compDynamo;
    public static void registerBlocks() {
        ingotStorage = new BlockIngotStorage(BlockInfo.INGOT_STORAGE_ID).setUnlocalizedName(BlockInfo.INGOT_STORAGE_UNLOCALIZED_NAME);
        GameRegistry.registerBlock(ingotStorage, BlockInfo.INGOT_STORAGE_KEY);

	    compDynamo = new BlockCompDynamo(BlockInfo.COMP_DYNAMO_ID).setUnlocalizedName(BlockInfo.COMP_DYNAMO_UNLOCALIZED_NAME);
	    GameRegistry.registerBlock(compDynamo, ItemBlockCompDynamo.class, BlockInfo.COMP_DYNAMO_KEY);
    }

}
