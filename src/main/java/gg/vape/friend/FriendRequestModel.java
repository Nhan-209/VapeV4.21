package gg.vape.friend;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class FriendRequestModel {
    private final UserModel h;
    private final UserModel L;
    private final long c;

    public int hashCode() {
        return (int)(this.c ^ this.c >>> 32);
    }

    public void l(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.c);
        this.h.a(zeusPacketBuffer);
        this.L.a(zeusPacketBuffer);
    }

    public FriendRequestModel(ZeusPacketBuffer zeusPacketBuffer) {
        this.c = zeusPacketBuffer.long_a();
        this.h = new UserModel(zeusPacketBuffer);
        this.L = new UserModel(zeusPacketBuffer);
    }

    public UserModel U() {
        return this.L;
    }

    public UserModel J() {
        return this.h;
    }

    public FriendRequestModel(long l, UserModel userModel, UserModel userModel2) {
        this.c = l;
        this.h = userModel;
        this.L = userModel2;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        FriendRequestModel friendRequestModel = (FriendRequestModel)object;
        boolean bl = this.c == friendRequestModel.c;
        return bl;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public long b() {
        return this.c;
    }
}

