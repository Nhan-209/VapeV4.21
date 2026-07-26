package gg.vape.friend;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class GroupUserModel {
    @Nullable
    private final String C;
    private final UUID x;
    private final UserModel T;
    private final String s;
    private final int I;

    public long V() {
        return this.T.g();
    }

    public void f(ZeusPacketBuffer zeusPacketBuffer) {
        this.T.a(zeusPacketBuffer);
        zeusPacketBuffer.r(this.x);
        zeusPacketBuffer.y(this.s);
        ZeusPacketBuffer zeusPacketBuffer2 = zeusPacketBuffer;
        boolean bl = this.C != null;
        zeusPacketBuffer2.Y(bl);
        if (this.C != null) {
            zeusPacketBuffer.y(this.C);
        }
        zeusPacketBuffer.K(this.I);
    }

    public String i() {
        return this.s;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public GroupUserModel(ZeusPacketBuffer zeusPacketBuffer) {
        this.T = new UserModel(zeusPacketBuffer);
        this.x = zeusPacketBuffer.N();
        this.s = zeusPacketBuffer.v(16);
        this.C = zeusPacketBuffer.boolean_a() ? zeusPacketBuffer.v(128) : null;
        this.I = zeusPacketBuffer.k();
    }

    @Nullable
    public String n() {
        return this.C;
    }

    public GroupUserModel(UserModel userModel, UUID uUID, String string, @Nullable String string2, int n) {
        this.T = userModel;
        this.x = uUID;
        this.s = string;
        this.C = string2;
        this.I = n;
    }

    public String Y() {
        return this.T.T();
    }

    public UUID N() {
        return this.x;
    }

    public UserModel j() {
        return this.T;
    }

    public int e() {
        return this.I;
    }
}

