package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;
import org.jetbrains.annotations.Nullable;

public class ClientServerAddressPacket
implements ZeusSerializablePacket {
    private String X;

    @Nullable
    public String A() {
        return this.X;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        ZeusPacketBuffer zeusPacketBuffer2 = zeusPacketBuffer;
        boolean bl = this.X != null;
        zeusPacketBuffer2.Y(bl);
        if (this.X != null) {
            zeusPacketBuffer.y(this.X);
        }
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        if (zeusPacketBuffer.boolean_a()) {
            this.X = zeusPacketBuffer.v(255);
        }
    }

    public ClientServerAddressPacket() {
    }

    public ClientServerAddressPacket(String string) {
        this.X = string;
    }

}

