package tehnut.redstonearmory.util;

import cpw.mods.fml.common.Loader;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

public class Utils {

    /**
     * @param key - Unlocalized string to localize.
     * @return - Localized String.
     */
    public static String localize(String key) {
        return StatCollector.translateToLocal(key);
    }

    /**
     * @param key  - Unlocalized string to localize.
     * @param info - Info to inject into key
     * @return - Localized String.
     */
    public static String localizeFormatted(String key, Object... info) {
        return String.format(localize(key), info);
    }

    /**
     * Adapted from the Blood Magic repository. https://github.com/WayofTime/BloodMagic/
     * Licensed under the Creative Commons Attribution 4.0 International License
     * This is a bit more copypasta than I had hoped. Will do some cleanup/reformatting/rewriting
     * later.
     *
     * @param stack     - Stack to insert
     * @param inventory - Inventory to insert to
     * @param dir       - Direction to insert from
     * @return - Inserted ItemStack
     */
    public static ItemStack insertStackIntoInventory(ItemStack stack, IInventory inventory, ForgeDirection dir) {
        if (stack == null)
            return null;

        boolean[] canBeInserted = new boolean[inventory.getSizeInventory()];

        if (inventory instanceof ISidedInventory) {
            int[] array = ((ISidedInventory) inventory).getAccessibleSlotsFromSide(dir.ordinal());
            for (int in : array)
                canBeInserted[in] = inventory.isItemValidForSlot(in, stack) && ((ISidedInventory) inventory).canInsertItem(in, stack, dir.ordinal());

        } else {
            for (int i = 0; i < canBeInserted.length; i++)
                canBeInserted[i] = inventory.isItemValidForSlot(i, stack);
        }

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            if (!canBeInserted[i])
                continue;

            ItemStack[] combinedStacks = combineStacks(stack, inventory.getStackInSlot(i));
            stack = combinedStacks[0];
            inventory.setInventorySlotContents(i, combinedStacks[1]);

            if (stack.stackSize <= 0)
                return stack;
        }

        return stack;
    }

    /**
     * Adapted from the Blood Magic repository. https://github.com/WayofTime/BloodMagic/
     * Licensed under the Creative Commons Attribution 4.0 International License
     * This is a bit more copypasta than I had hoped. Will do some cleanup/reformatting/rewriting
     * later.
     *
     * @param initial - First stack to combine
     * @param merge   - Second stack to combine
     * @return - Combined stacks
     */
    public static ItemStack[] combineStacks(ItemStack initial, ItemStack merge) {
        ItemStack[] returned = new ItemStack[2];

        if (canCombine(initial, merge)) {
            int transferredAmount = merge == null ? initial.stackSize : Math.min(merge.getMaxStackSize() - merge.stackSize, initial.stackSize);

            if (transferredAmount > 0) {
                ItemStack copyStack = initial.splitStack(transferredAmount);

                if (merge == null)
                    merge = copyStack;
                else
                    merge.stackSize += transferredAmount;
            }
        }

        returned[0] = initial;
        returned[1] = merge;

        return returned;
    }

    /**
     * Adapted from the Blood Magic repository. https://github.com/WayofTime/BloodMagic/
     * Licensed under the Creative Commons Attribution 4.0 International License
     * This is a bit more copypasta than I had hoped. Will do some cleanup/reformatting/rewriting
     * later.
     *
     * @param initial - First stack to check
     * @param merge   - Second stack to check
     * @return - If the stacks can be combined
     */
    public static boolean canCombine(ItemStack initial, ItemStack merge) {
        if (initial == null)
            return false;

        if (merge == null)
            return true;

        if (initial.isItemStackDamageable() ^ merge.isItemStackDamageable())
            return false;

        return initial.getItem() == merge.getItem() && initial.getItemDamage() == merge.getItemDamage() && ItemStack.areItemStackTagsEqual(initial, merge);
    }

    /**
     *
     * @param clazz - Class to load
     * @param modid - Modid required to load class
     */
    public static void registerCompat(Class clazz, String modid) {
        if (Loader.isModLoaded(modid)) {
            try {
                Class.forName(clazz.getCanonicalName());
            } catch (ClassNotFoundException e) {
                LogHelper.error("Could not find compatibility class for mod { " + modid + " }. Please report this.");
            }
        }
    }
}
