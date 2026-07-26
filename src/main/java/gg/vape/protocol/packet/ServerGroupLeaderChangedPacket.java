package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ServerGroupLeaderChangedPacket
implements ZeusSerializablePacket {
    private UserModel y;

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        this.y.a(zeusPacketBuffer);
    }

    public ServerGroupLeaderChangedPacket(UserModel userModel) {
        this.y = userModel;
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.y = new UserModel(zeusPacketBuffer);
    }

    public UserModel O() {
        return this.y;
    }

    public ServerGroupLeaderChangedPacket() {
    }
}

