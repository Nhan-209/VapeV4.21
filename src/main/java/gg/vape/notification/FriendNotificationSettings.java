package gg.vape.notification;

import gg.vape.value.BooleanValue;

public class FriendNotificationSettings {
    public final BooleanValue p;
    public final BooleanValue Q;
    public final BooleanValue q;
    public final BooleanValue I;
    public final BooleanValue P;
    public final BooleanValue K = BooleanValue.create(null, "Too many pings", true);

    public FriendNotificationSettings() {
        this.P = BooleanValue.create(null, "Friend requests", true);
        this.I = BooleanValue.create(null, "Chats", true);
        this.p = BooleanValue.create(null, "Friend online", true);
        this.q = BooleanValue.create(null, "Party invites", true);
        this.Q = BooleanValue.create(null, "Party invite accepted", true);
    }
}

