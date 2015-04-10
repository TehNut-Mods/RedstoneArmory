package tehnut.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IUpgrade {

    public void onUse(World world, EntityPlayer player, ItemStack stack);

    public int type();

    public int energyUsed();
}