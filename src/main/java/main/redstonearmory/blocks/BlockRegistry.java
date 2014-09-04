package main.redstonearmory.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ModInformation;
import main.redstonearmory.items.blocks.ItemBlockIngotStorage;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockRegistry {

	//blocks
	public static Block ingotStorage;

	public static Block tinkerTable;

	private static void registerBlocks() {
		ingotStorage = new BlockIngotStorage().setBlockName(ModInformation.ID);
		GameRegistry.registerBlock(ingotStorage, ItemBlockIngotStorage.class, ingotStorage.getUnlocalizedName());

		tinkerTable = new BlockTinkerTable(Material.iron);
		GameRegistry.registerBlock(tinkerTable, "BlockTinkerTable");
	}

	public static void registerAllBlocks() {
		registerBlocks();
	}

}
