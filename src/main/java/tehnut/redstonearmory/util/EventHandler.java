package tehnut.redstonearmory.util;

import cofh.redstonearsenal.item.RAItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.item.Item;
import tehnut.redstonearmory.ConfigHandler;
import tehnut.redstonearmory.items.ItemRegistry;
import tehnut.redstonearmory.network.HoldJumpPacket;
import tehnut.redstonearmory.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import tterrag.core.common.Handlers;

@Handlers.Handler
public class EventHandler {

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

        Item[] removeItems = new Item[] {
                ItemRegistry.armorEnderiumHelm,
                ItemRegistry.armorEnderiumChestplate,
                ItemRegistry.armorEnderiumLeggings,
                ItemRegistry.armorEnderiumBoots,
                ItemRegistry.axeGelidEnderium,
                ItemRegistry.battleWrenchGelidEnderium,
                ItemRegistry.pickaxeGelidEnderium,
                ItemRegistry.shovelGelidEnderium,
                ItemRegistry.sickleGelidEnderium,
                ItemRegistry.swordGelidEnderium,
                RAItems.itemHelmetFlux,
                RAItems.itemPlateFlux,
                RAItems.itemLegsFlux,
                RAItems.itemBootsFlux,
                RAItems.itemAxeFlux,
                RAItems.itemBattleWrenchFlux,
                RAItems.itemPickaxeFlux,
                RAItems.itemShovelFlux,
                RAItems.itemSickleFlux,
                RAItems.itemSwordFlux
        };

        for (Item item : removeItems) {
            if (event.itemStack.getItem() == item)
                for (int i = 0; i < event.toolTip.size(); i++)
                    if (event.toolTip.get(i).contains(TextHelper.localize("info.RArm.tooltip.durability")))
                        event.toolTip.remove(i);
        }
    }
}
