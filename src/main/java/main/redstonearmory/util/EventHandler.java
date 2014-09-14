package main.redstonearmory.util;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.network.HoldJumpPacket;
import main.redstonearmory.network.PacketHandler;
import net.minecraft.client.Minecraft;

public class EventHandler {
    public boolean lastKeyJumpHold;

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.modID.equals(ModInformation.ID)) {
            ConfigHandler.syncConfig();
            RedstoneArmory.logger.info(TextHelper.localize("info.RArm.console.config.refresh"));
        }
    }

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
}
