package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.GroupInviteResponsePacket;
import gg.vape.protocol.packet.ZeusTrackedPacket;

public class GroupInvitePacket
extends ZeusTrackedPacket<GroupInviteResponsePacket> {
    private UserModel x;

    public UserModel x() {
        return this.x;
    }

    @Override
    public void x(ZeusPacketBuffer zeusPacketBuffer) {
        this.x = new UserModel(zeusPacketBuffer);
    }

    public GroupInvitePacket() {
    }

    public GroupInvitePacket(UserModel userModel) {
        this.x = userModel;
    }

    @Override
    public void T(ZeusPacketBuffer zeusPacketBuffer) {
        this.x.a(zeusPacketBuffer);
    }
}

