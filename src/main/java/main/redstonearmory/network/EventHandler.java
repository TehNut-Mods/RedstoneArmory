package main.redstonearmory.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EventHandler {

    public boolean lastKeyJumpHold;
    public boolean lastKeyJump;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END)
            return;
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null) {
            boolean jumpState = mc.gameSettings.keyBindJump.getIsKeyPressed();
            if (jumpState != lastKeyJumpHold) {
                lastKeyJumpHold = jumpState;
                PacketHandler.INSTANCE.sendToServer(new HoldJumpPacket(jumpState));
            }
        }
    }

    @SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.entity instanceof EntityPlayer)) return;
        Minecraft mc = Minecraft.getMinecraft();
        PacketHandler.INSTANCE.sendToServer(new JumpPacket(mc.gameSettings.keyBindJump.isPressed()));
    }
}
