package gg.vape.manager.client;

import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.OnlineSettingsPayload;
import gg.vape.config.SettingsDataType;
import gg.vape.event.impl.EventKeyPress;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.manager.client.OnlineSettingsNoopBindAction;
import gg.vape.manager.client.OnlineSettingsPickPingBindAction;
import gg.vape.notification.FriendNotificationSettings;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.unmap.Bendable;
import gg.vape.unmap.ModeOption;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class OnlineSettings {
    private final ModeOption c;
    private final BooleanValue w;
    private final Bendable u;
    private final BooleanValue S;
    private final BooleanValue d;
    private static boolean t;
    private final ModeValue K;
    private final List<Bendable> T;
    private final Bendable V;
    private boolean E = false;
    private final ModeOption G;
    private final ModeOption f;
    private final FriendNotificationSettings Y = new FriendNotificationSettings();
    private final ModeOption m;
    private final BooleanValue r;
    private final ModeValue x;
    private final BooleanValue h;
    private final ModeOption N;
    private final BooleanValue g;
    private final BooleanValue a;
    private final BooleanValue j;
    private OnlineSettingsPayload Q;

    public OnlineSettings() {
        this.S = BooleanValue.create(null, "Auto login", true, "Automatically logs you into Vape online friend services when loading is complete");
        this.a = BooleanValue.create(null, "Share server", true, "Display your current server in friends list\nYour server may be shown if you join a party");
        this.w = BooleanValue.create(null, "Share username", true, "Display your Minecraft username in friends list\nYour username may be shown if you join a party\nFriend won't be able to sync you as a Minecraft friend with this disabled");
        this.r = BooleanValue.create(null, "Share inventory", true, "Shares your inventory contents to party members.");
        this.V = new OnlineSettingsPickPingBindAction(this);
        this.c = new ModeOption("Toggle");
        this.m = new ModeOption("Hold");
        this.x = ModeValue.create(null, "Bind mode", this.m, this.m, this.c);
        this.h = BooleanValue.create(null, "Show Self", true, "Shows your own overlay in the party overlay");
        this.f = new ModeOption("Party");
        this.N = new ModeOption("Team");
        this.G = new ModeOption("Friend");
        this.K = ModeValue.create(null, "Indicator color", this.f, this.f, this.N, this.G);
        this.j = BooleanValue.create(null, "Party overhead indicator", true, "Draws a circle above party members");
        this.g = BooleanValue.create(null, "Target indicators", true, "Shows who your party members are targeting");
        this.d = BooleanValue.create(null, "Self target indicators", true, "Draws indicators on your own target");
        this.u = new OnlineSettingsNoopBindAction(this);
        this.T = Arrays.asList(this.V);
    }

    public ModeOption v() {
        return this.f;
    }

    public void B() {
        try {
            ApiResponse apiResponse = ApiServices.d().v().h(SettingsDataType.ONLINE);
            this.E = false;
            if (apiResponse == null || !apiResponse.t()) {
                this.Q = OnlineSettingsPayload.j;
                ApiServices.d().v().u(SettingsDataType.ONLINE, this.Q);
            } else {
                this.Q = (OnlineSettingsPayload)apiResponse.T();
            }
        }
        catch (Exception exception) {
            this.Q.H();
            this.E = true;
        }
        this.S.o(this.Q.k());
        this.w.o(this.Q.a());
        this.a.o(this.Q.m());
        this.r.o(this.Q.A());
        this.h.o(this.Q.T());
        if (this.Q.x() != null) {
            this.x.M(this.Q.x());
        }
        if (this.Q.K() != null) {
            this.V.O(this.Q.K(), false);
        }
        if (this.Q.b() != null) {
            this.u.O(this.Q.b(), false);
        }
        if (!this.E) {
            Stream.of(this.w).forEach(OnlineSettings::lambda$initialize$1);
        }
    }

    public ModeOption j() {
        return this.c;
    }

    private static void lambda$initialize$1(BooleanValue booleanValue) {
        booleanValue.B(OnlineSettings::lambda$null$0);
    }

    public static void t(boolean bl) {
        t = bl;
    }

    public static boolean C() {
        boolean bl = OnlineSettings.P();
        return false;
    }

    public BooleanValue U() {
        return this.d;
    }

    public BooleanValue X$src$Lgg_vape_value_BooleanValue_$7rygmo() {
        return this.S;
    }

    public BooleanValue k$src$Lgg_vape_value_BooleanValue_$ffgfgd() {
        return this.g;
    }

    static {
        OnlineSettings.t(true);
    }

    public BooleanValue O() {
        return this.w;
    }

    public Bendable k() {
        return this.u;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public void y(EventKeyPress eventKeyPress) {
        if (eventKeyPress.getKey() <= 0) {
            return;
        }
        if (!eventKeyPress.isDown()) {
            return;
        }
        if (Minecraft.currentScreen().getObject() != null) {
            return;
        }
        for (Bendable bendable : this.T) {
            if (!bendable.f(eventKeyPress.getKey())) continue;
            eventKeyPress.setCancelled(true);
        }
    }

    public BooleanValue y() {
        return this.j;
    }

    public ModeOption I() {
        return this.G;
    }

    public BooleanValue j$src$Lgg_vape_value_BooleanValue_$1co7xi6() {
        return this.h;
    }

    public OnlineSettingsPayload X() {
        if (this.Q == null) {
            OnlineSettingsPayload onlineSettingsPayload = new OnlineSettingsPayload();
            onlineSettingsPayload.H();
            this.Q = onlineSettingsPayload;
        }
        return this.Q;
    }

    public Bendable p() {
        return this.V;
    }

    private static void lambda$null$0(BooleanValue booleanValue) {
        ZeusConnectionManager.T().u().Y();
    }

    public boolean M() {
        return this.E;
    }

    public static boolean P() {
        return t;
    }

    public FriendNotificationSettings m() {
        return this.Y;
    }

    public ModeValue r$src$Lgg_vape_value_ModeValue_$lqfla9() {
        return this.x;
    }

    public ModeOption r() {
        return this.m;
    }

    public BooleanValue z() {
        return this.a;
    }

    public ModeValue x() {
        return this.K;
    }

    public ModeOption o() {
        return this.N;
    }

    public void I(EventMouseButton eventMouseButton) {
        if (!eventMouseButton.getButtonState()) {
            return;
        }
        int n = -100 + eventMouseButton.getButton();
        for (Bendable bendable : this.T) {
            if (!bendable.f(n)) continue;
            eventMouseButton.setCancelled(true);
        }
    }

    public BooleanValue l() {
        return this.r;
    }
}

