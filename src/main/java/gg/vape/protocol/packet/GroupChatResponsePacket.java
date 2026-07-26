package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.GroupChatPacket;
import gg.vape.protocol.packet.GroupChatStatus;
import gg.vape.protocol.packet.ZeusTrackedPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GroupChatResponsePacket
extends ZeusTrackedPacket<GroupChatPacket> {
    private GroupChatStatus a;
    private long P;
    @Nullable
    private String W;

    @Override
    public void T(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.U(this.a);
        zeusPacketBuffer.y(this.W);
        zeusPacketBuffer.v(this.P);
    }

    public GroupChatResponsePacket(@Nullable GroupChatPacket groupChatPacket, GroupChatStatus groupChatStatus) {
        super(groupChatPacket);
        this.a = groupChatStatus;
    }

    public GroupChatStatus M() {
        return this.a;
    }

    public GroupChatResponsePacket() {
    }

    @Override
    public void x(ZeusPacketBuffer zeusPacketBuffer) {
        this.a = zeusPacketBuffer.Y(GroupChatStatus.class);
        this.W = zeusPacketBuffer.v(255);
        this.P = zeusPacketBuffer.a();
    }

    @Nullable
    public String x() {
        return this.W;
    }

    public long o() {
        return this.P;
    }

    public GroupChatResponsePacket(@Nullable GroupChatPacket groupChatPacket, @NotNull String string) {
        this(groupChatPacket, GroupChatStatus.SUCCESS);
        this.W = string;
        this.P = System.currentTimeMillis();
    }
}

