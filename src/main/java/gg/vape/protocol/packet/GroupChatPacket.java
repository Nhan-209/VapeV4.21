package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.GroupChatResponsePacket;
import gg.vape.protocol.packet.ZeusTrackedPacket;

public class GroupChatPacket
extends ZeusTrackedPacket<GroupChatResponsePacket> {
    private String U;

    public GroupChatPacket(String string) {
        this.U = string;
    }

    public String J() {
        return this.U;
    }

    public GroupChatPacket() {
    }

    @Override
    public void x(ZeusPacketBuffer gx_12) {
        this.U = gx_12.v(255);
    }

    @Override
    public void T(ZeusPacketBuffer gx_12) {
        gx_12.y(this.U);
    }
}

