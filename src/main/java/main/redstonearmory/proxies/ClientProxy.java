package main.redstonearmory.proxies;

import cpw.mods.fml.common.FMLCommonHandler;
import main.redstonearmory.util.KeyHandler;
import net.minecraft.client.Minecraft;

public class ClientProxy extends CommonProxy {

	public static Minecraft minecraft = Minecraft.getMinecraft();

	@Override
	public void load() {
		FMLCommonHandler.instance().bus().register(new KeyHandler());
	}
}
