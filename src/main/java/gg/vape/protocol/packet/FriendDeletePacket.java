package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.FriendDeleteResponsePacket;
import gg.vape.protocol.packet.ZeusTrackedPacket;

public class FriendDeletePacket
extends ZeusTrackedPacket<FriendDeleteResponsePacket> {
    private UserModel I;

    public FriendDeletePacket() {
    }

    @Override
    public void x(ZeusPacketBuffer zeusPacketBuffer) {
        this.I = new UserModel(zeusPacketBuffer);
    }

    @Override
    public void T(ZeusPacketBuffer zeusPacketBuffer) {
        this.I.a(zeusPacketBuffer);
    }

    public FriendDeletePacket(UserModel userModel) {
        this.I = userModel;
    }

    public UserModel c() {
        return this.I;
    }
}

