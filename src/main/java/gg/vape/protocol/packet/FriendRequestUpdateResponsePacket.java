package gg.vape.protocol.packet;

import gg.vape.friend.FriendModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.FriendRequestUpdatePacket;
import gg.vape.protocol.packet.FriendRequestUpdateStatus;
import gg.vape.protocol.packet.ZeusTrackedPacket;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class FriendRequestUpdateResponsePacket
extends ZeusTrackedPacket<FriendRequestUpdatePacket> {
    private FriendModel a;
    private FriendRequestUpdateStatus z;
    private static boolean l;
    private long g;

    public static boolean z() {
        boolean bl = FriendRequestUpdateResponsePacket.v();
        return !bl;
    }

    public long q$src$J$b6dfns() {
        return this.g;
    }

    public FriendRequestUpdateResponsePacket() {
    }

    public FriendRequestUpdateStatus l() {
        return this.z;
    }

    public FriendModel S() {
        return this.a;
    }

    public static boolean v() {
        return l;
    }

    public FriendRequestUpdateResponsePacket(FriendRequestUpdatePacket friendRequestUpdatePacket, long l, FriendRequestUpdateStatus friendRequestUpdateStatus) {
        super(friendRequestUpdatePacket);
        this.g = l;
        this.z = friendRequestUpdateStatus;
    }

    static {
        if (FriendRequestUpdateResponsePacket.z()) {
            FriendRequestUpdateResponsePacket.Y(true);
        }
    }

    public static void Y(boolean bl) {
        l = bl;
    }

    @Override
    public void T(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.g);
        zeusPacketBuffer.U(this.z);
        if (this.z == FriendRequestUpdateStatus.ACCEPTED) {
            this.a.h(zeusPacketBuffer);
        }
    }

    public FriendRequestUpdateResponsePacket(FriendRequestUpdatePacket friendRequestUpdatePacket, long l, FriendModel friendModel) {
        this(friendRequestUpdatePacket, l, FriendRequestUpdateStatus.ACCEPTED);
        this.a = friendModel;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void x(ZeusPacketBuffer zeusPacketBuffer) {
        this.g = zeusPacketBuffer.a();
        this.z = zeusPacketBuffer.Y(FriendRequestUpdateStatus.class);
        if (this.z == FriendRequestUpdateStatus.ACCEPTED) {
            this.a = new FriendModel(zeusPacketBuffer);
        }
    }
}

