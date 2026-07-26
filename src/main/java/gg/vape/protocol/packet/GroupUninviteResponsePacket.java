package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.GroupUninvitePacket;
import gg.vape.protocol.packet.GroupUninviteStatus;
import gg.vape.protocol.packet.ZeusTrackedPacket;

public class GroupUninviteResponsePacket
extends ZeusTrackedPacket<GroupUninvitePacket> {
    private GroupUninviteStatus j;

    @Override
    public void T(ZeusPacketBuffer gx_12) {
        gx_12.U(this.j);
    }

    public GroupUninviteStatus H() {
        return this.j;
    }

    public GroupUninviteResponsePacket() {
    }

    @Override
    public void x(ZeusPacketBuffer gx_12) {
        this.j = gx_12.Y(GroupUninviteStatus.class);
    }

    public GroupUninviteResponsePacket(GroupUninvitePacket pingPacket, GroupUninviteStatus py_02) {
        super(pingPacket);
        this.j = py_02;
    }
}

