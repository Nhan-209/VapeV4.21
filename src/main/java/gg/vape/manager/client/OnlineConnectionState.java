package gg.vape.manager.client;

import gg.vape.runtime.ObfuscatedRuntimeException;

public enum OnlineConnectionState {
    OFFLINE,
    OUTDATED_CLIENT,
    OUTDATED_SERVER,
    CONNECTING,
    ONLINE;

    private static final /* synthetic */ OnlineConnectionState[] G;
    private static String[] B;

    public static void Q(String[] stringArray) {
        B = stringArray;
    }

    public boolean G() {
        return this == OFFLINE || this == OUTDATED_CLIENT || this == OUTDATED_SERVER;
    }

    static {
        if (OnlineConnectionState.d() == null) {
            OnlineConnectionState.Q(new String[3]);
        }
        String[] stringArray = new String[]{"OFFLINE", "OUTDATED_CLIENT", "ONLINE", "OUTDATED_SERVER", "CONNECTING"};





        G = new OnlineConnectionState[]{OFFLINE, OUTDATED_CLIENT, OUTDATED_SERVER, CONNECTING, ONLINE};
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static String[] d() {
        return B;
    }
}

