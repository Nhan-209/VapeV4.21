package gg.vape.friend;

import gg.vape.protocol.PresenceState;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public enum OnlineStatus {
    ONLINE("Online", PresenceState.ONLINE, new Color(5, 134, 105)),
    AWAY("Away", PresenceState.AWAY, new Color(180, 120, 50)),
    OFFLINE("Offline", PresenceState.OFFLINE, new Color(89, 88, 89));

    private Color h;
    private static final OnlineStatus[] y;
    private static final List<OnlineStatus> o;
    private final String N;
    private PresenceState S;

    public Color P() {
        return this.h;
    }

    public String f() {
        return this.N;
    }


    private OnlineStatus(String string2, PresenceState presenceState, Color color) {
        this.N = string2;
        this.S = presenceState;
        this.h = color;
    }

    static {
        String[] stringArray = new String[]{"AWAY", "Online", "Offline", "Away", "ONLINE", "OFFLINE"};



        y = new OnlineStatus[]{ONLINE, AWAY, OFFLINE};
        o = Arrays.asList(OnlineStatus.values());
    }

    public PresenceState x() {
        return this.S;
    }

    public static OnlineStatus f(PresenceState presenceState) {
        for (OnlineStatus onlineStatus : o) {
            if (onlineStatus.S != presenceState) continue;
            return onlineStatus;
        }
        return null;
    }
}

