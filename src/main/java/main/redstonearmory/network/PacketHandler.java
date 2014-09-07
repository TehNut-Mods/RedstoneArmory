package main.redstonearmory.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import main.redstonearmory.ModInformation;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInformation.CHANNEL);

    public static void init() {
        INSTANCE.registerMessage(HoldJumpPacket.class, HoldJumpPacket.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(ActivationPacket.class, ActivationPacket.class, 1, Side.SERVER);
    }
}
