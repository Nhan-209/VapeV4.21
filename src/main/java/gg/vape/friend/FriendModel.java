package gg.vape.friend;

import gg.vape.friend.UserModel;
import gg.vape.protocol.PresenceState;
import gg.vape.protocol.ZeusPacketBuffer;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class FriendModel {
    @Nullable
    private final String d;
    private final UserModel D;
    private final PresenceState s;
    private final String E;
    private final UUID X;
    private final boolean P;

    public String N() {
        return this.D.T();
    }

    public UserModel L() {
        return this.D;
    }


    public FriendModel(ZeusPacketBuffer zeusPacketBuffer) {
        this.D = new UserModel(zeusPacketBuffer);
        this.X = zeusPacketBuffer.N();
        this.E = zeusPacketBuffer.v(16);
        this.P = zeusPacketBuffer.a$src$Z$1c50x8d();
        this.s = zeusPacketBuffer.Y(PresenceState.class);
        this.d = zeusPacketBuffer.a$src$Z$1c50x8d() ? zeusPacketBuffer.v(128) : null;
    }

    public boolean B() {
        return this.P;
    }

    public UUID R() {
        return this.X;
    }

    public FriendModel(UserModel userModel) {
        this(userModel, PresenceState.OFFLINE, UUID.randomUUID(), "", false, null);
    }

    @Nullable
    public String u() {
        return this.d;
    }

    public long M() {
        return this.D.g();
    }

    public PresenceState L$src$Lgg_vape_protocol_PresenceState_$o2vkpe() {
        return this.s;
    }

    public String toString() {
        return "FriendModel{userModel=" + this.D + ", minecraftUuid=" + this.X + ", minecraftUsername='" + this.E + '\'' + ", state=" + (Object)((Object)this.s) + ", minecraftServer='" + this.d + '\'' + '}';
    }

    public void h(ZeusPacketBuffer zeusPacketBuffer) {
        this.D.a(zeusPacketBuffer);
        zeusPacketBuffer.r(this.X);
        zeusPacketBuffer.y(this.E);
        zeusPacketBuffer.Y(this.P);
        zeusPacketBuffer.U(this.s);
        zeusPacketBuffer.Y(this.d != null);
        if (this.d != null) {
            zeusPacketBuffer.y(this.d);
        }
    }

    public String k() {
        return this.E;
    }

    public FriendModel(UserModel userModel, PresenceState presenceState, UUID uUID, String string, boolean bl, @Nullable String string2) {
        this.D = userModel;
        this.X = uUID;
        this.E = string;
        this.P = bl;
        this.s = presenceState;
        this.d = string2;
    }
}

