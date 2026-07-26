package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class FriendVisibilityUpdatePacket
implements ZeusSerializablePacket {
    private boolean k;
    private long O;

    public long S() {
        return this.O;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.O);
        zeusPacketBuffer.Y(this.k);
    }

    public FriendVisibilityUpdatePacket() {
    }

    public FriendVisibilityUpdatePacket(UserModel userModel, boolean bl) {
        this.O = userModel.g();
        this.k = bl;
    }

    public boolean N() {
        return this.k;
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.O = zeusPacketBuffer.long_a();
        this.k = zeusPacketBuffer.boolean_a();
    }
}

