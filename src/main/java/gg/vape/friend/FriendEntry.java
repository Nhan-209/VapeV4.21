package gg.vape.friend;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.friend.Friend;
import gg.vape.friend.TargetEntry;
import gg.vape.friend.TargetType;

public abstract class FriendEntry
extends TargetEntry {
    private boolean m = true;
    private static int z;

    public static void t(int n) {
        z = n;
    }

    public String E() {
        return this.o();
    }

    public String o() {
        return this.s();
    }

    public static int r() {
        int n = FriendEntry.F();
        return 0;
    }

    public static int F() {
        return z;
    }

    static {
        if (FriendEntry.F() == 0) {
            FriendEntry.t(29);
        }
    }

    public FriendEntry() {
        super(TargetType.FRIEND);
    }

    public boolean m() {
        return true;
    }

    public void k(boolean bl) {
        this.m = bl;
        Vape.INSTANCE.getFriendManager().m();
    }

    public abstract Friend loadJson(JsonObject var1);

    public boolean c() {
        return this.m;
    }

    public abstract JsonObject toJson();

    public abstract String s();

}

