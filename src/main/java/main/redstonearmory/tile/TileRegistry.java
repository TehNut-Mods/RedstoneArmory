package main.redstonearmory.tile;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileRegistry {

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileSolarBase.class, "SolarBase");
	}

}
