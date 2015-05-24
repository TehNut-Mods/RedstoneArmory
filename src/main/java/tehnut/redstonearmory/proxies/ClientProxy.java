package tehnut.redstonearmory.proxies;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import tehnut.redstonearmory.util.KeyHandler;

public class ClientProxy extends CommonProxy {

    public static Minecraft minecraft = Minecraft.getMinecraft();

    @Override
    public void load() {
        FMLCommonHandler.instance().bus().register(new KeyHandler());
    }
}
