package gg.vape.friend;

import gg.vape.Vape;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.PartyInvite;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.OnlineFriendsFrame;
import gg.vape.friend.ui.PartyInviteRow;
import gg.vape.module.none.ClientSettings;
import gg.vape.notification.NotificationType;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public class PartyManager {
    @Nullable
    private PartyState W;
    private static final String b;
    private static int C;
    private final Map<OnlineFriend, PartyInvite> T = new LinkedHashMap<OnlineFriend, PartyInvite>();

    public static void K(int n) {
        C = n;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Nullable
    public PartyInvite k(OnlineFriend onlineFriend) {
        Map<OnlineFriend, PartyInvite> map = this.T;
        synchronized (map) {
            return this.T.get(onlineFriend);
        }
    }

    public static int B() {
        return C;
    }

    public static int S() {
        int n = PartyManager.B();
        return 0;
    }

    public void n(@Nullable PartyState partyState) {
        this.W = partyState;
        ClientSettings.g(OnlineFriendsFrame.class).l$src$V$1mibm4x();
        Vape.INSTANCE.getOnlineManager().V().T();
        Vape.INSTANCE.getOnlineManager().N().A();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void y(PartyInvite partyInvite) {
        Map<OnlineFriend, PartyInvite> map = this.T;
        synchronized (map) {
            this.T.remove(partyInvite.x());
        }
        ClientSettings.g(OnlineFriendsFrame.class).Y$src$Lgg_vape_friend_ui_PartyInvitesPanel_$1o49ve3().P(partyInvite);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void C(PartyInvite partyInvite) {
        Map<OnlineFriend, PartyInvite> map = this.T;
        synchronized (map) {
            this.T.put(partyInvite.x(), partyInvite);
        }
        ClientSettings.g(OnlineFriendsFrame.class).Y$src$Lgg_vape_friend_ui_PartyInvitesPanel_$1o49ve3().u(new PartyInviteRow(partyInvite));
        Vape.INSTANCE.getNotificationManager().t(b, partyInvite.x().C(), NotificationType.FRIENDS_PARTY_INVITE, 4000L);
    }

    static {
        PartyManager.K(117);
        b = "Party invite";
    }

    @Nullable
    public PartyState j() {
        return this.W;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void r() {
        this.n(null);
        for (PartyInvite partyInvite : this.T.values()) {
            this.y(partyInvite);
        }
    }

    public @UnmodifiableView Collection<PartyInvite> n() {
        return this.T.values();
    }
}

