package redstonearsenal.item.tool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IEmpowerableItem {
    /**
     * Check whether or not a given item is currently in an empowered state.
     */
    boolean isEmpowered(ItemStack stack);

    /**
     * Attempt to set the empowered state of the item.
     *
     * @param stack ItemStack to be empowered/disempowered.
     * @param state Desired state.
     * @return TRUE if the operation was successful, FALSE if it was not.
     */
    boolean setEmpoweredState(ItemStack stack, boolean state);

    /**
     * Callback method for reacting to a state change. Useful in KeyBinding handlers.
     *
     * @param player Player holding the item, if applicable.
     * @param stack  The item being held.
     */
    void onStateChange(EntityPlayer player, ItemStack stack);
}
