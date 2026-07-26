package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.FriendRequestResponsePacket;
import gg.vape.protocol.packet.ZeusTrackedPacket;

public class FriendRequestPacket
extends ZeusTrackedPacket<FriendRequestResponsePacket> {
    private String f;

    public FriendRequestPacket() {
    }

    public String j() {
        return this.f;
    }

    public FriendRequestPacket(String string) {
        this();
        this.f = string;
    }

    @Override
    public void x(ZeusPacketBuffer gx_12) {
        this.f = gx_12.v(16);
    }

    @Override
    public void T(ZeusPacketBuffer gx_12) {
        gx_12.y(this.f);
    }
}

