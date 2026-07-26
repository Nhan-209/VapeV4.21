package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ServerUserDisplayNamePacket
implements ZeusSerializablePacket {
    private long S;
    private String v;

    public ServerUserDisplayNamePacket() {
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.S = zeusPacketBuffer.long_a();
        this.v = zeusPacketBuffer.v(16);
    }

    public String N() {
        return this.v;
    }

    public long y() {
        return this.S;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.S);
        zeusPacketBuffer.y(this.v);
    }

    public ServerUserDisplayNamePacket(UserModel userModel, String string) {
        this.S = userModel.g();
        this.v = string;
    }
}

