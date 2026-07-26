package gg.vape.runtime.state;

public class GlobalStringState {
    private static String D;

    public static void v(String string) {
        D = string;
    }

    public static String J() {
        return D;
    }

    static {
        if (GlobalStringState.J() != null) {
            GlobalStringState.v("IfADt");
        }
    }
}

