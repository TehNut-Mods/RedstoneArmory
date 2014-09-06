package main.redstonearmory.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import main.redstonearmory.items.powersuit.ItemPowersuit;
import main.redstonearmory.items.powersuit.upgrades.BaseUpgrade;

public class HoldJumpPacket implements IMessage, IMessageHandler<HoldJumpPacket, IMessage> {
    public boolean isHoldingJump;

    public HoldJumpPacket() {
    }

    public HoldJumpPacket(boolean b) {
        isHoldingJump = b;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        isHoldingJump = buf.readBoolean();
        BaseUpgrade.isHoldingJump = isHoldingJump;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(isHoldingJump);
    }

    @Override
    public IMessage onMessage(HoldJumpPacket message, MessageContext ctx) {
        BaseUpgrade.isHoldingJump = isHoldingJump;
        return null;
    }
}
