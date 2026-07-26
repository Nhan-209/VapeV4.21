package gg.vape.protocol.packet;

import gg.vape.friend.FriendModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ServerFriendModelPacket
implements ZeusSerializablePacket {
    private FriendModel C;

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        this.C.h(zeusPacketBuffer);
    }

    public FriendModel t() {
        return this.C;
    }

    public ServerFriendModelPacket(FriendModel friendModel) {
        this.C = friendModel;
    }

    public ServerFriendModelPacket() {
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.C = new FriendModel(zeusPacketBuffer);
    }
}

