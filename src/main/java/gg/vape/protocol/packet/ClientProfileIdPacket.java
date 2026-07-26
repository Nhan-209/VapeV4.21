package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ClientProfileIdPacket
implements ZeusSerializablePacket {
    private long f;

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.f = zeusPacketBuffer.long_a();
    }

    public long i() {
        return this.f;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.f);
    }

    public ClientProfileIdPacket(long l) {
        this.f = l;
    }

    public ClientProfileIdPacket() {
    }
}

