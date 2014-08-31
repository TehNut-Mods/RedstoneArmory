package main.redstonearmory.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import main.redstonearmory.items.ItemRegistry;
import main.redstonearmory.network.HoldJumpPacket;
import main.redstonearmory.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class EventHandler {

    private boolean lastJumpState;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END)
            return;

        Minecraft mc = Minecraft.getMinecraft();

        if (mc.thePlayer != null) {
            boolean jumpState = mc.gameSettings.keyBindJump.getIsKeyPressed();
            EntityPlayer player = mc.thePlayer;
            if (player.inventory.armorInventory != null && player.inventory.armorInventory[1].getItem() == ItemRegistry.armorPowersuitChestplate)
                PacketHandler.INSTANCE.sendToServer(new HoldJumpPacket(jumpState));
        }
    }
}
