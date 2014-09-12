package main.redstonearmory.proxies;

import cpw.mods.fml.common.FMLCommonHandler;
import main.redstonearmory.util.KeyHandler;

public class ClientProxy extends CommonProxy {

    @Override
    public void load() {
        FMLCommonHandler.instance().bus().register(new KeyHandler());
    }

}
