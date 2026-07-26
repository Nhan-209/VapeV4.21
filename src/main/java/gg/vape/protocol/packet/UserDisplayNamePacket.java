package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.UserDisplayNameResponsePacket;
import gg.vape.protocol.packet.ZeusTrackedPacket;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class UserDisplayNamePacket
extends ZeusTrackedPacket<UserDisplayNameResponsePacket> {
    private String S;
    private static boolean f;

    public static void Y(boolean bl) {
        f = bl;
    }

    public UserDisplayNamePacket() {
    }

    public String m() {
        return this.S;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static boolean r() {
        boolean bl = UserDisplayNamePacket.I();
        return !bl;
    }

    @Override
    public void T(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.y(this.S);
    }

    public static boolean I() {
        return f;
    }

    public UserDisplayNamePacket(String string) {
        this.S = string;
    }

    @Override
    public void x(ZeusPacketBuffer zeusPacketBuffer) {
        this.S = zeusPacketBuffer.v(16);
    }

    static {
        if (!UserDisplayNamePacket.r()) {
            UserDisplayNamePacket.Y(true);
        }
    }
}

