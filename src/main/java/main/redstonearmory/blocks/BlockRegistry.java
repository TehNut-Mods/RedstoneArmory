package main.redstonearmory.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ModInformation;
import main.redstonearmory.items.blocks.ItemBlockIngotStorage;
import net.minecraft.block.Block;

public class BlockRegistry {

	//blocks
	public static Block ingotStorage;

	private static void registerBlocks() {
		ingotStorage = new BlockIngotStorage().setBlockName(ModInformation.ID);
		GameRegistry.registerBlock(ingotStorage, ItemBlockIngotStorage.class, ingotStorage.getUnlocalizedName());
	}

	public static void registerAllBlocks() {
		registerBlocks();
	}

}
