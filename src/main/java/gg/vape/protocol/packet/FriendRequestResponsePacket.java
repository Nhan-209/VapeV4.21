package gg.vape.protocol.packet;

import gg.vape.friend.FriendRequestModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.FriendRequestPacket;
import gg.vape.protocol.packet.FriendRequestResponseStatus;
import gg.vape.protocol.packet.ZeusTrackedPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FriendRequestResponsePacket
extends ZeusTrackedPacket<FriendRequestPacket> {
    @Nullable
    private FriendRequestModel Y;
    private FriendRequestResponseStatus J;

    @Override
    public void T(ZeusPacketBuffer gx_12) {
        gx_12.U(this.J);
        if (this.Y != null) {
            this.Y.l(gx_12);
        }
    }

    @Nullable
    public FriendRequestModel c() {
        return this.Y;
    }


    public FriendRequestResponsePacket() {
    }

    public FriendRequestResponsePacket(@Nullable FriendRequestPacket friendRequestPacket, FriendRequestResponseStatus hi_12) {
        super(friendRequestPacket);
        this.J = hi_12;
    }

    @Override
    public void x(ZeusPacketBuffer gx_12) {
        this.J = gx_12.Y(FriendRequestResponseStatus.class);
        if (this.J == FriendRequestResponseStatus.SENT) {
            this.Y = new FriendRequestModel(gx_12);
        }
    }

    public FriendRequestResponseStatus n() {
        return this.J;
    }

    public FriendRequestResponsePacket(@Nullable FriendRequestPacket friendRequestPacket, @NotNull FriendRequestModel friendRequestModel) {
        this(friendRequestPacket, FriendRequestResponseStatus.SENT);
        this.Y = friendRequestModel;
    }
}

