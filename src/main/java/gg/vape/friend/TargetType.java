package gg.vape.friend;

public enum TargetType {
    FRIEND,
    ENEMY;

    private static final TargetType[] d;

    static {
        String[] stringArray = new String[]{"ENEMY", "FRIEND"};


        d = new TargetType[]{FRIEND, ENEMY};
    }

}

