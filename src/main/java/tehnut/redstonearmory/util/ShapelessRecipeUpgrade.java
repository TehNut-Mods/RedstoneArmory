package tehnut.redstonearmory.util;

import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.ItemHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ShapelessRecipeUpgrade extends ShapelessOreRecipe {

    public ShapelessRecipeUpgrade(ItemStack result, Object... recipe) {
        super(result, recipe);
    }

    public ItemStack getCraftingResult(InventoryCrafting craftMatrix) {
        for (int i = 0; i < craftMatrix.getSizeInventory(); ++i)
            if (craftMatrix.getStackInSlot(i) != null && craftMatrix.getStackInSlot(i).getItem() instanceof IEnergyContainerItem)
                return craftMatrix.getStackInSlot(i) != null && craftMatrix.getStackInSlot(i).stackTagCompound != null ? ItemHelper.copyTag(this.getRecipeOutput().copy(), craftMatrix.getStackInSlot(i)) : super.getCraftingResult(craftMatrix);

        return this.getRecipeOutput();
    }
}
