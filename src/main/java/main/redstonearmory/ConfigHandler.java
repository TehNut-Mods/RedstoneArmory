package main.redstonearmory;

import main.redstonearmory.blocks.BlockInfo;
import net.minecraftforge.common.Configuration;

import java.io.File;

/**
 * Created by Nick on 6/6/14.
 */

public class ConfigHandler {

	//Categories
	public static String itemId = "1- Item ID's";
	public static String blockId = "2- Block ID's";
	public static String general = "3 - General";

	//Options

	public static void registerConfig(File file) {
		Configuration config = new Configuration(file);

		config.load();

		BlockInfo.INGOT_STORAGE_ID = config.getBlock(BlockInfo.INGOT_STORAGE_KEY, BlockInfo.INGOT_STORAGE_DEFAULT).getInt();

		if(config.hasChanged()) {
			config.save();
		}
	}

}
