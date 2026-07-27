package gg.vape.manager.client;

import gg.vape.Vape;
import gg.vape.friend.FriendRequestManager;
import gg.vape.friend.LocalOnlineFriend;
import gg.vape.friend.OnlineFriendCache;
import gg.vape.friend.PartyManager;
import gg.vape.manager.client.OnlineActivityManager;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.manager.client.OnlineConnectionState;
import gg.vape.manager.client.OnlineInventoryTracker;
import gg.vape.ui.click.component.GuiComponent;

public class OnlineManager {
    private static GuiComponent[] O;
    private final OnlineFriendCache i;
    private final OnlineInventoryTracker P;
    private final FriendRequestManager C;
    private final OnlineActivityManager p;
    private final PartyManager n;
    private final LocalOnlineFriend b = new LocalOnlineFriend();

    public OnlineActivityManager V() {
        return this.p;
    }

    public LocalOnlineFriend r() {
        return this.b;
    }

    public OnlineInventoryTracker N() {
        return this.P;
    }

    public OnlineFriendCache u() {
        return this.i;
    }

    static {
        if (OnlineManager.p() == null) {
            OnlineManager.G(new GuiComponent[5]);
        }
    }

    public PartyManager y() {
        return this.n;
    }

    public static void G(GuiComponent[] guiComponentArray) {
        O = guiComponentArray;
    }

    public OnlineManager() {
        this.i = new OnlineFriendCache();
        this.C = new FriendRequestManager();
        this.n = new PartyManager();
        this.p = new OnlineActivityManager();
        this.P = new OnlineInventoryTracker();
    }

    public boolean B() {
        return OnlineConnectionManager.T.n() == OnlineConnectionState.OFFLINE;
    }

    public void t() {
        if (!OnlineConnectionManager.T.u()) {
            return;
        }
        this.i.C();
        this.C.g();
        this.n.r();
        this.b.W("");
        this.b.V(null);
        Vape.INSTANCE.getOnlineFriendManager().s();
        this.p.T();
        this.P.A();
    }


    public FriendRequestManager D() {
        return this.C;
    }

    public static GuiComponent[] p() {
        return O;
    }
}

