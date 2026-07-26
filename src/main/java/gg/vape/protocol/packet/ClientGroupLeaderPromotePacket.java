package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ClientGroupLeaderPromoteResponsePacket;
import gg.vape.protocol.packet.ZeusTrackedPacket;

public class ClientGroupLeaderPromotePacket
extends ZeusTrackedPacket<ClientGroupLeaderPromoteResponsePacket> {
    private long U;

    @Override
    public void x(ZeusPacketBuffer zeusPacketBuffer) {
        this.U = zeusPacketBuffer.long_a();
    }

    public ClientGroupLeaderPromotePacket(UserModel userModel) {
        this.U = userModel.g();
    }

    public ClientGroupLeaderPromotePacket() {
    }

    public long d() {
        return this.U;
    }

    @Override
    public void T(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.U);
    }
}

