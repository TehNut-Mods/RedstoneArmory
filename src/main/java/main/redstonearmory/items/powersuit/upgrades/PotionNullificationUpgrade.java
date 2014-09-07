package main.redstonearmory.items.powersuit.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.LinkedList;

public class PotionNullificationUpgrade extends BaseUpgrade {

    public PotionNullificationUpgrade() {
        type = helmetType;
        energyUsed = 1000;
    }

    @Override
    public void onUse(World world, EntityPlayer player, ItemStack stack) {
        if (isInstalled("PotionNullification", stack)) {
            Iterator i$ = (new LinkedList(player.getActivePotionEffects())).iterator();
            do {
                if (!i$.hasNext()) {
                    break;
                }
                {
                    PotionEffect effect = (PotionEffect) i$.next();
                    int cost = energyUsed * (effect.getAmplifier() + 1);
                    if (energyUsed * cost <= getEnergyStored(stack)) {
                        ItemStack milk = (new ItemStack(Items.milk_bucket));
                        player.curePotionEffects(milk);
                        extractEnergy(stack, energyUsed, false);
                    }
                }
            }
            while (true);
        }
    }
}
