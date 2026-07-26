package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;
import java.util.UUID;

public class FriendMinecraftProfileUpdatePacket
implements ZeusSerializablePacket {
    private String v;
    private long p;
    private UUID Q;

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.p = zeusPacketBuffer.long_a();
        this.Q = zeusPacketBuffer.N();
        this.v = zeusPacketBuffer.v(16);
    }

    public FriendMinecraftProfileUpdatePacket() {
    }

    public String G() {
        return this.v;
    }

    public FriendMinecraftProfileUpdatePacket(UserModel userModel, UUID uUID, String string) {
        this.p = userModel.g();
        this.Q = uUID;
        this.v = string;
    }

    public UUID l() {
        return this.Q;
    }

    public long H() {
        return this.p;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.p);
        zeusPacketBuffer.r(this.Q);
        zeusPacketBuffer.y(this.v);
    }
}

