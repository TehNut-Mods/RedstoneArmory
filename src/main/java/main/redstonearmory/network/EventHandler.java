package main.redstonearmory.network;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class EventHandler {

    public boolean lastKeyJumpHold;

    public static KeyBinding keyActivate = new KeyBinding("key.powersuitActivate", Keyboard.KEY_F, "Redstone Armory");

    public EventHandler() {
        ClientRegistry.registerKeyBinding(keyActivate);
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

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (keyActivate.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new ActivationPacket());
        }
    }
}
