package tehnut.redstonearmory.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import tehnut.redstonearmory.items.powersuit.ItemPowersuit;
import tehnut.redstonearmory.util.Utils;

public class ActivationPacket implements IMessage, IMessageHandler<ActivationPacket, IMessage> {

    public boolean isActivated;

    public ActivationPacket() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        isActivated = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(isActivated);
    }

    @Override
    public IMessage onMessage(ActivationPacket message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        if (player != null) {
            for (int i = 0; i < 4; i++) {
                ItemStack armor = player.inventory.armorItemInSlot(i);
                if (armor != null && armor.getItem() instanceof ItemPowersuit) {
                    armor.stackTagCompound.setBoolean("activated", !armor.stackTagCompound.getBoolean("activated"));
                    player.addChatComponentMessage(new ChatComponentText(Utils.localize("info.RArm.chat.armor.powersuit.active") + " " + String.valueOf(armor.stackTagCompound.getBoolean("activated")).toUpperCase()));
                }
            }
        }
        return null;
    }
}
