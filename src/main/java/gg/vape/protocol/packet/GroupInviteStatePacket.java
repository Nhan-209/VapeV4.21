package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.GroupInviteStateResponsePacket;
import gg.vape.protocol.packet.ZeusTrackedPacket;

public class GroupInviteStatePacket
extends ZeusTrackedPacket<GroupInviteStateResponsePacket> {
    private boolean c;
    private long H;

    public GroupInviteStatePacket() {
    }

    public long Y() {
        return this.H;
    }

    public boolean v() {
        return this.c;
    }

    @Override
    public void T(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.H);
        zeusPacketBuffer.Y(this.c);
    }

    @Override
    public void x(ZeusPacketBuffer zeusPacketBuffer) {
        this.H = zeusPacketBuffer.long_a();
        this.c = zeusPacketBuffer.boolean_a();
    }

    public GroupInviteStatePacket(UserModel userModel, boolean bl) {
        this.H = userModel.g();
        this.c = bl;
    }
}

