package tehnut.redstonearmory.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import tehnut.redstonearmory.ModInformation;
import tehnut.redstonearmory.RedstoneArmory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBaubleBase extends Item implements IBauble {

    BaubleType type;

    public ItemBaubleBase(String name, BaubleType type) {
        super();

        setUnlocalizedName(ModInformation.ID + ".bauble." + name);
        setCreativeTab(RedstoneArmory.tabRArm);
        setMaxStackSize(1);

        this.type = type;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return this.type;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }
}
