package main.redstonearmory.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import main.redstonearmory.network.HoldJumpPacket;
import main.redstonearmory.network.PacketHandler;
import net.minecraft.client.Minecraft;

public class EventHandler {

    private boolean lastJumpState;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END)
            return;
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null) {
            boolean jumpState = mc.gameSettings.keyBindJump.getIsKeyPressed();
            if (jumpState != lastJumpState) {
                lastJumpState = jumpState;
                PacketHandler.INSTANCE.sendToServer(new HoldJumpPacket(jumpState));
            }
        }
    }
}
