package gg.vape.mapping;

public class InsertedCallbackMarker {
    private static String[] k;

    public static void T() {
    }

    public static void X(String[] stringArray) {
        k = stringArray;
    }

    public static String[] b() {
        return k;
    }

    static {
        if (InsertedCallbackMarker.b() != null) {
            InsertedCallbackMarker.X(new String[3]);
        }
    }
}

