package main.redstonearmory.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import main.redstonearmory.ModInformation;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInformation.CHANNEL);
    public static int id = 0;

    public static void init() {
        INSTANCE.registerMessage(HoldJumpPacket.class, HoldJumpPacket.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(JumpPacket.class, JumpPacket.class, id++, Side.CLIENT);
    }
}
