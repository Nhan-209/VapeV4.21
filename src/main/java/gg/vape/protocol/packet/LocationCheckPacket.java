package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class LocationCheckPacket
implements ZeusSerializablePacket {
    private int a;
    private int H;
    private int j;

    public int L() {
        return this.j;
    }

    @Override
    public void S(ZeusPacketBuffer gx_12) {
        this.H = gx_12.k();
        this.j = gx_12.k();
        this.a = gx_12.k();
    }

    @Override
    public void o(ZeusPacketBuffer gx_12) {
        gx_12.K(this.H);
        gx_12.K(this.j);
        gx_12.K(this.a);
    }

    public int f() {
        return this.H;
    }

    public LocationCheckPacket(int n, int n2, int n3) {
        this.H = n;
        this.j = n2;
        this.a = n3;
    }

    public LocationCheckPacket() {
    }

    public int r() {
        return this.a;
    }
}

