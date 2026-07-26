package gg.vape.notification;

public enum NotificationGroup {
    NONE,
    FRIENDS;

    private static final NotificationGroup[] p;

    static {
        String[] stringArray = new String[]{"FRIENDS", "NONE"};


        p = new NotificationGroup[]{NONE, FRIENDS};
    }

}

