package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;
import org.jetbrains.annotations.Nullable;

public class FriendServerAddressPacket
implements ZeusSerializablePacket {
    private long Z;
    private String j;

    public FriendServerAddressPacket(UserModel userModel, String string) {
        this.Z = userModel.g();
        this.j = string;
    }

    public long b() {
        return this.Z;
    }


    public FriendServerAddressPacket() {
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.Z);
        ZeusPacketBuffer zeusPacketBuffer2 = zeusPacketBuffer;
        boolean bl = this.j != null;
        zeusPacketBuffer2.Y(bl);
        if (this.j != null) {
            zeusPacketBuffer.y(this.j);
        }
    }

    @Nullable
    public String w() {
        return this.j;
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.Z = zeusPacketBuffer.long_a();
        if (zeusPacketBuffer.boolean_a()) {
            this.j = zeusPacketBuffer.v(128);
        }
    }
}

