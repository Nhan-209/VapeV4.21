package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.FriendDeletePacket;
import gg.vape.protocol.packet.ZeusTrackedPacket;
import org.jetbrains.annotations.Nullable;

public class FriendDeleteResponsePacket
extends ZeusTrackedPacket<FriendDeletePacket> {
    private boolean J;

    @Override
    public void x(ZeusPacketBuffer zeusPacketBuffer) {
        this.J = zeusPacketBuffer.boolean_a();
    }

    public FriendDeleteResponsePacket(@Nullable FriendDeletePacket friendDeletePacket, boolean bl) {
        super(friendDeletePacket);
        this.J = bl;
    }

    @Override
    public void T(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.Y(this.J);
    }

    public boolean I() {
        return this.J;
    }

    public FriendDeleteResponsePacket() {
    }
}

