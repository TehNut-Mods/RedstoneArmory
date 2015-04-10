package tehnut.redstonearmory.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import tehnut.redstonearmory.ConfigHandler;
import tehnut.redstonearmory.network.HoldJumpPacket;
import tehnut.redstonearmory.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import tterrag.core.common.Handlers;

@Handlers.Handler
public class PlayerEvents {

    public boolean lastKeyJumpHold;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (ConfigHandler.enableTestingEnviro) {
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
    }

    @SubscribeEvent
    public void onToolTip(ItemTooltipEvent event) {
        if (ConfigHandler.enableDebugThingsAndStuff)
            event.toolTip.add(event.itemStack.getItem().getClass().toString());
    }
}
