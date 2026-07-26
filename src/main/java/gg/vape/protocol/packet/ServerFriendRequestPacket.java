package gg.vape.protocol.packet;

import gg.vape.friend.FriendRequestModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ServerFriendRequestPacket
implements ZeusSerializablePacket {
    private FriendRequestModel q;

    public ServerFriendRequestPacket(FriendRequestModel friendRequestModel) {
        this.q = friendRequestModel;
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.q = new FriendRequestModel(zeusPacketBuffer);
    }

    public FriendRequestModel b() {
        return this.q;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        this.q.l(zeusPacketBuffer);
    }

    public ServerFriendRequestPacket() {
    }
}

