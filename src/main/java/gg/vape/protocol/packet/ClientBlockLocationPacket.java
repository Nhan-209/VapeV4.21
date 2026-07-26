package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ClientBlockLocationPacket
implements ZeusSerializablePacket {
    private int m;
    private int q;
    private int r;
    private long W;

    public int q() {
        return this.r;
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.W = zeusPacketBuffer.long_a();
        this.q = zeusPacketBuffer.k();
        this.m = zeusPacketBuffer.k();
        this.r = zeusPacketBuffer.k();
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.W);
        zeusPacketBuffer.K(this.q);
        zeusPacketBuffer.K(this.m);
        zeusPacketBuffer.K(this.r);
    }

    public int n() {
        return this.q;
    }

    public int K() {
        return this.m;
    }

    public ClientBlockLocationPacket(long l, int n, int n2, int n3) {
        this.W = l;
        this.q = n;
        this.m = n2;
        this.r = n3;
    }

    public ClientBlockLocationPacket() {
    }

    public long V() {
        return this.W;
    }
}

