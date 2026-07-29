package gg.vape.friend;

import gg.vape.Vape;
import gg.vape.friend.OnlineFriend;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.manager.client.OnlineSettings;
import gg.vape.unmap.ModeSelection;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;

public class OnlineFriendColorUtil {
    private static final Map<Integer, Color> Y;
    private static final Color W;

    public static Color V(OnlineFriend onlineFriend) {
        OnlineSettings onlineSettings = OnlineConnectionManager.T.S();
        if (((ModeSelection)onlineSettings.x().getValue()).equals(onlineSettings.v())) {
            return OnlineFriendColorUtil.u(onlineFriend);
        }
        if (((ModeSelection)onlineSettings.x().getValue()).equals(onlineSettings.I())) {
            return OnlineFriendColorUtil.P();
        }
        return Color.WHITE;
    }


    public static Color P() {
        return Vape.INSTANCE.getFriendManager().R.getMutableColor();
    }

    public static Color f(int n) {
        if (n == -1 || n > 8) {
            return W;
        }
        return Y.getOrDefault(n, W);
    }

    private static void Q() {
        Y.put(0, new Color(5, 134, 105));
        Y.put(1, new Color(47, 122, 229));
        Y.put(2, new Color(250, 50, 56));
        Y.put(3, new Color(126, 84, 217));
        Y.put(4, new Color(242, 99, 33));
        Y.put(5, new Color(252, 179, 22));
        Y.put(6, new Color(232, 96, 152));
        Y.put(7, new Color(145, 145, 145));
        Y.put(8, new Color(126, 65, 19));
    }

    static {
        W = new Color(255, 255, 255);
        Y = new LinkedHashMap<Integer, Color>();
        OnlineFriendColorUtil.Q();
    }

    public static Color u(OnlineFriend onlineFriend) {
        return OnlineFriendColorUtil.f(onlineFriend.d());
    }
}

