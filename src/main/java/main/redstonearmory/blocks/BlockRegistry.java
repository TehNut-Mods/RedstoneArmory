package main.redstonearmory.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

/**
 * Created by Nick on 6/6/14.
 */
public class BlockRegistry {

	public static Block ingotStorage;

	private static void registerBlocks() {
		ingotStorage = new BlockIngotStorage(BlockInfo.INGOT_STORAGE_ID);
		GameRegistry.registerBlock(ingotStorage, BlockInfo.INGOT_STORAGE_KEY);
	}

	private static void registerNames() {
//		LanguageRegistry.addName(ingotStorage, BlockInfo.INGOT_STORAGE_NAME);
	}

	public static void registerFullBlocks() {
		registerBlocks();
		registerNames();
	}

}
