package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.PingPacket;
import gg.vape.protocol.packet.ZeusTrackedPacket;
import org.jetbrains.annotations.Nullable;

public class PingResponsePacket
extends ZeusTrackedPacket<PingPacket> {
    private long v;
    private boolean Y;
    private int i;

    @Override
    public void x(ZeusPacketBuffer zeusPacketBuffer) {
        this.Y = zeusPacketBuffer.boolean_a();
        this.i = zeusPacketBuffer.Y();
        this.v = zeusPacketBuffer.long_a();
    }

    public PingResponsePacket(@Nullable PingPacket pingPacket, boolean bl, int n, long l) {
        super(pingPacket);
        this.Y = bl;
        this.i = n;
        this.v = l;
    }

    @Override
    public void T(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.Y(this.Y);
        zeusPacketBuffer.i(this.i);
        zeusPacketBuffer.v(this.v);
    }

    public long p() {
        return this.v;
    }

    public boolean v() {
        return this.Y;
    }

    public int h() {
        return this.i;
    }

    public PingResponsePacket() {
    }
}

