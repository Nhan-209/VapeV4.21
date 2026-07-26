package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ServerFriendChatMessagePacket
implements ZeusSerializablePacket {
    private UserModel M;
    private String r;
    private long g;

    public ServerFriendChatMessagePacket() {
    }

    public String h() {
        return this.r;
    }

    public long g() {
        return this.g;
    }

    public UserModel C() {
        return this.M;
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.M = new UserModel(zeusPacketBuffer);
        this.r = zeusPacketBuffer.v(255);
        this.g = zeusPacketBuffer.long_a();
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        this.M.a(zeusPacketBuffer);
        zeusPacketBuffer.y(this.r);
        zeusPacketBuffer.v(this.g);
    }

    public ServerFriendChatMessagePacket(UserModel userModel, String string) {
        this.M = userModel;
        this.r = string;
        this.g = System.currentTimeMillis();
    }
}

