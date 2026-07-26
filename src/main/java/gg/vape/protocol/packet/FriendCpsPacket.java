package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class FriendCpsPacket
implements ZeusSerializablePacket {
    private long O;
    private int D;

    public long G() {
        return this.O;
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.O = zeusPacketBuffer.long_a();
        this.D = zeusPacketBuffer.Y();
    }

    public FriendCpsPacket(UserModel userModel, int n) {
        this.O = userModel.g();
        this.D = n;
    }

    public FriendCpsPacket() {
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.O);
        zeusPacketBuffer.i(this.D);
    }

    public int N() {
        return this.D;
    }
}

