package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.UserDisplayNamePacket;
import gg.vape.protocol.packet.UserDisplayNameStatus;
import gg.vape.protocol.packet.ZeusTrackedPacket;
import org.jetbrains.annotations.Nullable;

public class UserDisplayNameResponsePacket
extends ZeusTrackedPacket<UserDisplayNamePacket> {
    private long G;
    private String V;
    private static String y;
    private UserDisplayNameStatus t;


    @Override
    public void T(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.U(this.t);
        if (this.t == UserDisplayNameStatus.SUCCESSFUL) {
            zeusPacketBuffer.y(this.V);
            zeusPacketBuffer.v(this.G);
        } else if (this.t == UserDisplayNameStatus.COOLDOWN) {
            zeusPacketBuffer.v(this.G);
        }
    }

    static {
        if (UserDisplayNameResponsePacket.q$src$Ljava_lang_String_$12vxeoi() == null) {
            UserDisplayNameResponsePacket.x("YnPS4b");
        }
    }

    public UserDisplayNameResponsePacket() {
    }

    public long f() {
        return this.G;
    }

    public UserDisplayNameResponsePacket(@Nullable UserDisplayNamePacket userDisplayNamePacket, UserDisplayNameStatus userDisplayNameStatus) {
        super(userDisplayNamePacket);
        this.t = userDisplayNameStatus;
    }

    public UserDisplayNameStatus S() {
        return this.t;
    }

    public static void x(String string) {
        y = string;
    }

    public String A() {
        return this.V;
    }

    public static String q$src$Ljava_lang_String_$12vxeoi() {
        return y;
    }

    @Override
    public void x(ZeusPacketBuffer zeusPacketBuffer) {
        this.t = zeusPacketBuffer.Y(UserDisplayNameStatus.class);
        if (this.t == UserDisplayNameStatus.SUCCESSFUL) {
            this.V = zeusPacketBuffer.v(16);
            this.G = zeusPacketBuffer.a();
        } else if (this.t == UserDisplayNameStatus.COOLDOWN) {
            this.G = zeusPacketBuffer.a();
        }
    }

    public UserDisplayNameResponsePacket(@Nullable UserDisplayNamePacket userDisplayNamePacket, long l) {
        this(userDisplayNamePacket, UserDisplayNameStatus.COOLDOWN);
        this.G = l;
    }

    public UserDisplayNameResponsePacket(@Nullable UserDisplayNamePacket userDisplayNamePacket, String string, long l) {
        this(userDisplayNamePacket, UserDisplayNameStatus.SUCCESSFUL);
        this.V = string;
        this.G = l;
    }
}

