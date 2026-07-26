package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ClientHeldItemSlotPacket
implements ZeusSerializablePacket {
    private int B;

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.K(this.B);
    }

    public int N() {
        return this.B;
    }

    public ClientHeldItemSlotPacket(int n) {
        this.B = n;
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.B = zeusPacketBuffer.k();
    }

    public ClientHeldItemSlotPacket() {
    }
}

