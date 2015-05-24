package tehnut.redstonearmory.util;

import cofh.redstonearsenal.item.RAItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import tehnut.redstonearmory.ConfigHandler;
import tehnut.redstonearmory.items.ItemRegistry;
import tehnut.redstonearmory.items.tools.gelidenderium.ItemPickaxeGelidEnderium;
import tehnut.redstonearmory.network.HoldJumpPacket;
import tehnut.redstonearmory.network.PacketHandler;
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
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {

        if (!event.world.isRemote) {
            if (event.harvester != null && event.harvester.getHeldItem() != null && event.harvester.getHeldItem().getItem() instanceof ItemPickaxeGelidEnderium) {
                ItemStack pickaxe = event.harvester.getHeldItem();

                if (isEmpowered(pickaxe)) {
                    if (pickaxe.stackTagCompound == null)
                        pickaxe.stackTagCompound = new NBTTagCompound();

                    NBTTagCompound tag = pickaxe.stackTagCompound;
                    int coordX = tag.getInteger("CoordX");
                    int coordY = tag.getInteger("CoordY");
                    int coordZ = tag.getInteger("CoordZ");
                    int dimID = tag.getInteger("DimID");
                    int side = tag.getInteger("Side");

                    World boundWorld = DimensionManager.getWorld(dimID);

                    if (event.world.getBlock(event.x, event.y, event.z) != boundWorld.getBlock(coordX, coordY, coordZ)) {
                        TileEntity bound = boundWorld.getTileEntity(coordX, coordY, coordZ);
                        IInventory inventory;

                        if (bound instanceof IInventory)
                            inventory = (IInventory) bound;
                        else
                            return;

                        for (int drop = 0; drop < event.drops.size(); drop++)
                            for (int slot = 0; slot < inventory.getSizeInventory(); slot++)
                                if (inventory.isItemValidForSlot(slot, event.drops.get(drop)))
                                    Utils.insertStackIntoInventory(event.drops.get(drop), inventory, ForgeDirection.getOrientation(side));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onToolTip(ItemTooltipEvent event) {

        Item[] removeItems = new Item[]{
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
                    if (event.toolTip.get(i).contains(Utils.localize("info.RArm.tooltip.durability")))
                        event.toolTip.remove(i);
        }
    }

    public boolean isEmpowered(ItemStack stack) {
        return stack.stackTagCompound != null && stack.stackTagCompound.getBoolean("Empowered");
    }
}
