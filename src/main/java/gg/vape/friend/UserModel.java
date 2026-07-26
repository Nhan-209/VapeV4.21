package gg.vape.friend;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;

public class UserModel {
    private static GuiComponent[] P;
    private final String D;
    private final long i;

    public String toString() {
        return "UserModel{id=" + this.i + ", displayName='" + this.D + '\'' + '}';
    }

    public UserModel(long l, String string) {
        this.i = l;
        this.D = string;
    }

    static {
        UserModel.m(new GuiComponent[5]);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void a(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.i);
        zeusPacketBuffer.y(this.D);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        UserModel userModel = (UserModel)object;
        return this.i == userModel.i;
    }

    public static GuiComponent[] O() {
        return P;
    }

    public long g() {
        return this.i;
    }

    public int hashCode() {
        return (int)(this.i ^ this.i >>> 32);
    }

    public String T() {
        return this.D;
    }

    public static void m(GuiComponent[] guiComponentArray) {
        P = guiComponentArray;
    }

    public UserModel(ZeusPacketBuffer zeusPacketBuffer) {
        this.i = zeusPacketBuffer.a();
        this.D = zeusPacketBuffer.v(16);
    }
}

