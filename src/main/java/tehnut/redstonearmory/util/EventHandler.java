package tehnut.redstonearmory.util;

import com.enderio.core.common.Handlers;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.world.BlockEvent;
import tehnut.redstonearmory.items.tools.gelidenderium.ItemPickaxeGelidEnderium;

@Handlers.Handler
@SuppressWarnings("unchecked")
public class EventHandler {

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

    public boolean isEmpowered(ItemStack stack) {
        return stack.stackTagCompound != null && stack.stackTagCompound.getBoolean("Empowered");
    }
}
