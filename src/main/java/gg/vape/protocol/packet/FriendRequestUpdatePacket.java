package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.FriendRequestUpdateResponsePacket;
import gg.vape.protocol.packet.ZeusTrackedPacket;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class FriendRequestUpdatePacket
extends ZeusTrackedPacket<FriendRequestUpdateResponsePacket> {
    private boolean w;
    private long h;
    private static int R;

    public FriendRequestUpdatePacket() {
    }

    public long M() {
        return this.h;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void T(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.h);
        zeusPacketBuffer.Y(this.w);
    }

    public FriendRequestUpdatePacket(long l, boolean bl) {
        this();
        this.h = l;
        this.w = bl;
    }

    public static int F() {
        int n = FriendRequestUpdatePacket.Q();
        return 0;
    }

    public static void e(int n) {
        R = n;
    }

    public boolean E() {
        return this.w;
    }

    public static int Q() {
        return R;
    }

    @Override
    public void x(ZeusPacketBuffer zeusPacketBuffer) {
        this.h = zeusPacketBuffer.long_a();
        this.w = zeusPacketBuffer.boolean_a();
    }

    static {
        if (FriendRequestUpdatePacket.Q() == 0) {
            FriendRequestUpdatePacket.e(124);
        }
    }
}

