package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ShowUsernamePacket
implements ZeusSerializablePacket {
    private boolean Q;

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.Q = zeusPacketBuffer.boolean_a();
    }

    public ShowUsernamePacket(boolean bl) {
        this.Q = bl;
    }

    public ShowUsernamePacket() {
    }

    public boolean p() {
        return this.Q;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.Y(this.Q);
    }
}

