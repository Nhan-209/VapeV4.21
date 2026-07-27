package gg.vape.api;

import gg.vape.runtime.NativeBridge;

public class ApiAccessTokenProvider {
    private static String z;
    private static String b;

    public static void K(String string) {
        b = string;
    }

    public static String h() {
        return ApiAccessTokenProvider.H(true);
    }

    public static String H(boolean bl) {
        if (z == null) {
            ApiAccessTokenProvider.Q();
        }
        return z;
    }

    private static void Q() {
        z = NativeBridge.gat();
    }

    public static String p() {
        return b;
    }

    public static String i() {
        return ApiAccessTokenProvider.H(false);
    }


    static {
        if (ApiAccessTokenProvider.p() == null) {
            ApiAccessTokenProvider.K("Sx5Qoc");
        }
    }
}

