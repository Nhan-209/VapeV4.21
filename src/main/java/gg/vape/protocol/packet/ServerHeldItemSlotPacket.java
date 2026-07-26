package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ServerHeldItemSlotPacket
implements ZeusSerializablePacket {
    private int R;
    private long T;

    public long S() {
        return this.T;
    }

    public ServerHeldItemSlotPacket(UserModel userModel, int n) {
        this.T = userModel.g();
        this.R = n;
    }

    public int e() {
        return this.R;
    }

    public ServerHeldItemSlotPacket() {
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.T = zeusPacketBuffer.long_a();
        this.R = zeusPacketBuffer.k();
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.T);
        zeusPacketBuffer.K(this.R);
    }
}

