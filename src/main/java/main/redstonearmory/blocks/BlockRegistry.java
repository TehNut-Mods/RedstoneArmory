package main.redstonearmory.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.items.blocks.ItemBlockIngotStorage;
import main.redstonearmory.items.blocks.ItemBlockRandomThings;
import main.redstonearmory.items.blocks.ItemBlockSolars;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import static main.redstonearmory.ConfigHandler.addNutsToys;
import static main.redstonearmory.ConfigHandler.enableTestingEnviro;

public class BlockRegistry {

	//blocks
	public static Block ingotStorage;
	public static Block randomBlocks;
	public static Block invisiLight;

	public static Block solars;

	public static Block tinkerTable;

	private static void registerBlocks() {
		ingotStorage = new BlockIngotStorage();
		GameRegistry.registerBlock(ingotStorage, ItemBlockIngotStorage.class, "BlockIngotStorage");
		invisiLight = new BlockInvisiLight(Material.air);
		GameRegistry.registerBlock(invisiLight, "BlockInvisiLight");
		GameRegistry.registerTileEntity(BlockInvisiLight.TileInvisibleLight.class, "TileEntityInvisiLight");
		if (addNutsToys) {
			randomBlocks = new BlockRandomThings(Material.rock);
			GameRegistry.registerBlock(randomBlocks, ItemBlockRandomThings.class, "BlockRandomThings");
		}

		if (enableTestingEnviro) {
			solars = new BlockSolars();
			GameRegistry.registerBlock(solars, ItemBlockSolars.class, "BlockSolars");

			tinkerTable = new BlockTinkerTable(Material.iron);
			GameRegistry.registerBlock(tinkerTable, "BlockTinkerTable");
		}
	}

	public static void registerAllBlocks() {
		registerBlocks();
	}
}
