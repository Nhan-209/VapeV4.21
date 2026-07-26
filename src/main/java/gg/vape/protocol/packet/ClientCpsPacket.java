package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ClientCpsPacket
implements ZeusSerializablePacket {
    private int c;

    public ClientCpsPacket(int n) {
        this.c = n;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.i(this.c);
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.c = zeusPacketBuffer.Y();
    }

    public int s() {
        return this.c;
    }

    public ClientCpsPacket() {
    }
}

