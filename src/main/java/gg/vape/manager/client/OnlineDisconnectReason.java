package gg.vape.manager.client;

public enum OnlineDisconnectReason {
    UNKNOWN(true, true),
    LOGGED_IN_FROM_ANOTHER_LOCATION(false, true),
    BANNED(false, false),
    AUTH_FAILED(false, false);

    private final boolean P;
    private static final OnlineDisconnectReason[] z;
    private final boolean V;

    private OnlineDisconnectReason(boolean bl, boolean bl2) {
        this.P = bl;
        this.V = bl2;
    }

    public boolean Z() {
        return this.P;
    }

    static {
        String[] stringArray = new String[]{"LOGGED_IN_FROM_ANOTHER_LOCATION", "AUTH_FAILED", "BANNED", "UNKNOWN"};




        z = new OnlineDisconnectReason[]{UNKNOWN, LOGGED_IN_FROM_ANOTHER_LOCATION, BANNED, AUTH_FAILED};
    }

    public boolean K() {
        return this.V;
    }

}

