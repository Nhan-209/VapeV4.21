package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.GroupLeavePacket;
import gg.vape.protocol.packet.GroupLeaveStatus;
import gg.vape.protocol.packet.ZeusTrackedPacket;

public class GroupLeaveResponsePacket
extends ZeusTrackedPacket<GroupLeavePacket> {
    private GroupLeaveStatus j;

    public GroupLeaveStatus D() {
        return this.j;
    }

    @Override
    public void T(ZeusPacketBuffer gx_12) {
        gx_12.U(this.j);
    }

    @Override
    public void x(ZeusPacketBuffer gx_12) {
        this.j = gx_12.Y(GroupLeaveStatus.class);
    }

    public GroupLeaveResponsePacket() {
    }

    public GroupLeaveResponsePacket(GroupLeavePacket gL, GroupLeaveStatus qo_12) {
        super(gL);
        this.j = qo_12;
    }
}

