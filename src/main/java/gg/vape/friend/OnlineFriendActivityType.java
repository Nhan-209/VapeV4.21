package gg.vape.friend;

public enum OnlineFriendActivityType {
    AFK("AFK"),
    MOVING("Moving"),
    COMBAT("Combat"),
    BUILDING("Building"),
    DEAD("Dead"),
    NONE("None");

    private static final /* synthetic */ OnlineFriendActivityType[] T;
    String x;

    private OnlineFriendActivityType(String string2) {
        this.x = string2;
    }

    static {
        String[] stringArray = new String[]{"None", "Building", "NONE", "AFK", "Dead", "DEAD", "BUILDING", "Combat", "MOVING", "COMBAT", "Moving", "AFK"};






        T = new OnlineFriendActivityType[]{AFK, MOVING, COMBAT, BUILDING, DEAD, NONE};
    }

}

