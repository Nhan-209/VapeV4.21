package gg.vape.manager.client;

public enum OnlineAccountState {
    REGISTERED,
    BANNED,
    UNREGISTERED,
    REGISTRATION_OFFLINE,
    CONNECTING;

    private static final /* synthetic */ OnlineAccountState[] O;

    static {
        String[] stringArray = new String[]{"REGISTRATION_OFFLINE", "CONNECTING", "BANNED", "REGISTERED", "UNREGISTERED"};





        O = new OnlineAccountState[]{REGISTERED, BANNED, UNREGISTERED, REGISTRATION_OFFLINE, CONNECTING};
    }
}

