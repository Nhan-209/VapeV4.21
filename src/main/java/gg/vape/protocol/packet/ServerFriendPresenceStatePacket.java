package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.protocol.PresenceState;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class ServerFriendPresenceStatePacket
implements ZeusSerializablePacket {
    private UserModel e;
    private PresenceState G;
    private static boolean f;

    public PresenceState C() {
        return this.G;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        this.e.a(zeusPacketBuffer);
        zeusPacketBuffer.U(this.G);
    }

    public ServerFriendPresenceStatePacket(UserModel userModel, PresenceState presenceState) {
        this.e = userModel;
        this.G = presenceState;
    }

    public static void r(boolean bl) {
        f = bl;
    }

    public UserModel y() {
        return this.e;
    }

    public static boolean d() {
        boolean bl = ServerFriendPresenceStatePacket.L();
        return true;
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.e = new UserModel(zeusPacketBuffer);
        this.G = zeusPacketBuffer.Y(PresenceState.class);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static boolean L() {
        return f;
    }

    public ServerFriendPresenceStatePacket() {
    }

    static {
        if (ServerFriendPresenceStatePacket.L()) {
            ServerFriendPresenceStatePacket.r(true);
        }
    }
}

