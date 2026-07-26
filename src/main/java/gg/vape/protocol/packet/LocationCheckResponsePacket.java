package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class LocationCheckResponsePacket
implements ZeusSerializablePacket {
    private boolean d;

    public boolean N() {
        return this.d;
    }

    public LocationCheckResponsePacket(boolean bl) {
        this.d = bl;
    }

    public LocationCheckResponsePacket() {
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.d = zeusPacketBuffer.boolean_a();
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.Y(this.d);
    }
}

