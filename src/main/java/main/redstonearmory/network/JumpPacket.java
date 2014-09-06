package main.redstonearmory.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import main.redstonearmory.items.powersuit.ItemPowersuit;

public class JumpPacket implements IMessage, IMessageHandler<JumpPacket, IMessage> {
    public boolean isJumping;

    public JumpPacket() {
    }

    public JumpPacket(boolean b) {
        isJumping = b;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        isJumping = buf.readBoolean();
        ItemPowersuit.isJumping = isJumping;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(isJumping);
    }

    @Override
    public IMessage onMessage(JumpPacket message, MessageContext ctx) {
        ItemPowersuit.isJumping = isJumping;
        return null;
    }
}
