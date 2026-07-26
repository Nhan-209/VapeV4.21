package gg.vape.protocol.event;

import gg.vape.friend.UserModel;
import gg.vape.protocol.ZeusClient;
import gg.vape.protocol.event.OnlineEvent;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class FriendRequestEvent
extends OnlineEvent {
    private static int y;
    private final UserModel k;

    public static void i(int n) {
        y = n;
    }

    public static int R() {
        int n = FriendRequestEvent.c();
        if (n == 0) {
            return 36;
        }
        return 0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public FriendRequestEvent(ZeusClient oZ, UserModel oj_12) {
        super(oZ);
        this.k = oj_12;
    }

    public static int c() {
        return y;
    }

    public UserModel f() {
        return this.k;
    }

    static {
        if (FriendRequestEvent.R() == 0) {
            FriendRequestEvent.i(18);
        }
    }
}

