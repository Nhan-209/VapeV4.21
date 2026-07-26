package gg.vape.notification;

public enum NotificationType {
    INFO("Info", -1),
    WARNING("Warning", -1277652),
    ALERT("Alert", -380360),
    FRIENDS_GENERAL("Info", -1, NotificationGroup.FRIENDS),
    FRIENDS_NEW_REQUEST("friend_request", -1, NotificationGroup.FRIENDS),
    FRIENDS_NEW_CHAT("chat", -1, NotificationGroup.FRIENDS),
    FRIENDS_ONLINE("online", -1, NotificationGroup.FRIENDS),
    FRIENDS_PARTY_GENERAL("group", -1, NotificationGroup.FRIENDS),
    FRIENDS_PARTY_INVITE("group", -1, NotificationGroup.FRIENDS);

    private final String X;
    private NotificationGroup E = NotificationGroup.NONE;
    private final String i;
    private final int Q;
    private static boolean r;

    NotificationType(String displayName, int color) {
        this(displayName, color, NotificationGroup.NONE);
    }

    NotificationType(String displayName, int color, NotificationGroup group) {
        this.X = displayName;
        this.Q = color;
        this.i = "noti_" + displayName.toLowerCase();
        this.E = group;
    }

    public NotificationGroup T() {
        return this.E;
    }

    public static boolean P() {
        return r;
    }

    public static void T(boolean value) {
        r = value;
    }

    public String i() {
        return this.i;
    }

    public int v() {
        return this.Q;
    }

    public static boolean J() {
        NotificationType.P();
        return true;
    }

    public String M() {
        return this.X;
    }

    static {
        NotificationType.T(false);
    }
}
