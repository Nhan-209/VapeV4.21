package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ServerGroupChatMessagePacket
implements ZeusSerializablePacket {
    private String c;
    private long D;
    private long N;

    public long E() {
        return this.N;
    }

    public ServerGroupChatMessagePacket(long l, String string) {
        this.N = l;
        this.c = string;
        this.D = System.currentTimeMillis();
    }

    public long F() {
        return this.D;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.N);
        zeusPacketBuffer.y(this.c);
        zeusPacketBuffer.v(this.D);
    }

    public String z() {
        return this.c;
    }

    public ServerGroupChatMessagePacket() {
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.N = zeusPacketBuffer.long_a();
        this.c = zeusPacketBuffer.v(255);
        this.D = zeusPacketBuffer.long_a();
    }
}

