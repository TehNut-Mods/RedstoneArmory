package main.redstonearmory.tile;

import cpw.mods.fml.common.registry.GameRegistry;

public class TERegistry {

	public static void registerTileEntities() {

		GameRegistry.registerTileEntity(TileEntityCompDynamo.class, "TileEntityCompDynamo");

	}

}
