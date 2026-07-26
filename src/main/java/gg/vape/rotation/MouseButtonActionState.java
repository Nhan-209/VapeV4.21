package gg.vape.rotation;

public enum MouseButtonActionState {
    NONE,
    PRESS,
    RELEASE;

    private static final MouseButtonActionState[] j;

    static {
        String[] stringArray = new String[]{"NONE", "RELEASE", "PRESS"};



        j = new MouseButtonActionState[]{NONE, PRESS, RELEASE};
    }

}

