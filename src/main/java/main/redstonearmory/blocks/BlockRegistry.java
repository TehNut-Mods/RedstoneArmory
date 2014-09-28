package main.redstonearmory.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.items.blocks.ItemBlockIngotStorage;
import main.redstonearmory.items.blocks.ItemBlockRandomThings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockRegistry {

	//blocks
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
		if(ConfigHandler.addNutsToys) {
			randomBlocks = new BlockRandomThings(Material.rock);
			GameRegistry.registerBlock(randomBlocks, ItemBlockRandomThings.class, "BlockRandomThings");
		}

		tinkerTable = new BlockTinkerTable(Material.iron);
		GameRegistry.registerBlock(tinkerTable, "BlockTinkerTable");
	}

	public static void registerAllBlocks() {
		registerBlocks();
	}
}
