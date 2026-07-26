package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ServerFriendRequestRemovedPacket
implements ZeusSerializablePacket {
    private long x;

    public long R() {
        return this.x;
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.x = zeusPacketBuffer.long_a();
    }

    public ServerFriendRequestRemovedPacket() {
    }

    public ServerFriendRequestRemovedPacket(long l) {
        this.x = l;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.x);
    }
}

