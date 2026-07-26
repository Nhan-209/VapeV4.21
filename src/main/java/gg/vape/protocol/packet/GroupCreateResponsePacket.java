package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.GroupCreatePacket;
import gg.vape.protocol.packet.GroupCreateStatus;
import gg.vape.protocol.packet.ZeusTrackedPacket;

public class GroupCreateResponsePacket
extends ZeusTrackedPacket<GroupCreatePacket> {
    private GroupCreateStatus g;
    private static String X;

    public GroupCreateStatus q$src$Lgg_vape_protocol_packet_GroupCreateStatus_$1c0kqtl() {
        return this.g;
    }

    public GroupCreateResponsePacket(GroupCreatePacket groupCreatePacket, GroupCreateStatus groupCreateStatus) {
        super(groupCreatePacket);
        this.g = groupCreateStatus;
    }

    public static void m(String string) {
        X = string;
    }

    static {
        if (GroupCreateResponsePacket.Z() != null) {
            GroupCreateResponsePacket.m("koPAI");
        }
    }

    public static String Z() {
        return X;
    }

    @Override
    public void T(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.U(this.g);
    }

    @Override
    public void x(ZeusPacketBuffer zeusPacketBuffer) {
        this.g = zeusPacketBuffer.Y(GroupCreateStatus.class);
    }

    public GroupCreateResponsePacket() {
    }
}

