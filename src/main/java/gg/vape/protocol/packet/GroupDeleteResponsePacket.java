package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.GroupDeletePacket;
import gg.vape.protocol.packet.GroupDeleteStatus;
import gg.vape.protocol.packet.ZeusTrackedPacket;

public class GroupDeleteResponsePacket
extends ZeusTrackedPacket<GroupDeletePacket> {
    private GroupDeleteStatus H;

    public GroupDeleteResponsePacket() {
    }

    public GroupDeleteResponsePacket(GroupDeletePacket gb_02, GroupDeleteStatus ls_22) {
        super(gb_02);
        this.H = ls_22;
    }

    @Override
    public void x(ZeusPacketBuffer gx_12) {
        this.H = gx_12.Y(GroupDeleteStatus.class);
    }

    @Override
    public void T(ZeusPacketBuffer gx_12) {
        gx_12.U(this.H);
    }

    public GroupDeleteStatus t() {
        return this.H;
    }
}

