package gg.vape.mapping;

import gg.vape.mapping.InsertedCallbackMarker;

public class InsertedCallbackLockMarker
extends InsertedCallbackMarker {
    private static int t;
    private static int r;
    private static boolean y;

    public static void unlock() {
        y = false;
    }

    public static void lock() {
        y = true;
    }

    public static boolean check(int n, int n2) {
        return y;
    }

    static {
        y = false;
    }
}

