package main.redstonearmory.util;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import main.redstonearmory.network.ActivationPacket;
import main.redstonearmory.network.PacketHandler;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyHandler {

    public static KeyBinding keyActivate = new KeyBinding("key.RArm.bind.powersuitActivate", Keyboard.KEY_F, "Redstone Armory");

    public KeyHandler() {
        ClientRegistry.registerKeyBinding(keyActivate);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (keyActivate.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new ActivationPacket());
        }
    }
}
