package gg.vape.protocol.packet;

import gg.vape.protocol.PresenceState;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class PresenceStateUpdatePacket
implements ZeusSerializablePacket {
    private PresenceState L;

    @Override
    public void S(ZeusPacketBuffer gx_12) {
        this.L = gx_12.Y(PresenceState.class);
    }

    public PresenceStateUpdatePacket() {
    }

    public PresenceStateUpdatePacket(PresenceState yE) {
        this.L = yE;
    }

    @Override
    public void o(ZeusPacketBuffer gx_12) {
        gx_12.U(this.L);
    }

    public PresenceState n() {
        return this.L;
    }
}

