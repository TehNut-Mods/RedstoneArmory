package main.redstonearmory.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BlockRegistry {

	public static Block ingotStorage;

	public static void registerBlocks() {
		ingotStorage = new BlockIngotStorage(BlockInfo.INGOT_STORAGE_ID);
		GameRegistry.registerBlock(ingotStorage, BlockInfo.INGOT_STORAGE_KEY);
	}

}
