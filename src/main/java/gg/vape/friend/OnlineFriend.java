package gg.vape.friend;

import gg.vape.Vape;
import gg.vape.friend.ExternalFriend;
import gg.vape.friend.FriendModel;
import gg.vape.friend.GroupUserModel;
import gg.vape.friend.OnlineStatus;
import gg.vape.friend.UserModel;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.notification.INotification;
import gg.vape.notification.Notification;
import gg.vape.notification.NotificationType;
import gg.vape.notification.TextNotificationContent;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class OnlineFriend {
    private int B = -1;
    private UUID p;
    private static boolean I;
    private boolean k;
    private ExternalFriend Q;
    private boolean o;
    private boolean D;
    protected UserModel q;
    @Nullable
    private String P;
    private OnlineStatus n = OnlineStatus.OFFLINE;
    private String y = "";
    private boolean g = true;
    protected String K;

    public void W(String string) {
        this.y = string;
    }

    public void J(boolean bl) {
        this.o = bl;
    }

    public void K(int n) {
        this.B = n;
    }

    public void O(boolean bl) {
        this.k = bl;
        if (bl) {
            if (this.n != OnlineStatus.OFFLINE) {
                Vape.INSTANCE.getFriendManager().u(this.Q);
                OnlineFriendUiHelper.U();
            }
        } else {
            Vape.INSTANCE.getFriendManager().E(this.Q);
            OnlineFriendUiHelper.U();
        }
    }

    public void F(boolean bl) {
        this.D = bl;
    }

    public OnlineFriend(GroupUserModel groupUserModel) {
        this(groupUserModel.j());
        this.n = OnlineStatus.ONLINE;
        this.p = groupUserModel.N();
        this.y = groupUserModel.i();
        this.B = groupUserModel.e();
    }

    public static boolean I$src$Z$1kta3hb() {
        return I;
    }


    public OnlineFriend(String string) {
        this(null, string);
    }

    public void d(UUID uUID, String string) {
        this.p = uUID;
        this.y = string;
    }

    public boolean r() {
        return this.o;
    }

    public OnlineFriend(FriendModel friendModel) {
        this(friendModel.L());
        this.n = OnlineStatus.f(friendModel.L$src$Lgg_vape_protocol_PresenceState_$o2vkpe());
        this.p = friendModel.R();
        this.y = friendModel.k();
        this.g = friendModel.B();
        this.P = friendModel.u();
    }

    public UserModel S() {
        return this.q;
    }

    static {
        OnlineFriend.q(false);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof OnlineFriend)) {
            return false;
        }
        OnlineFriend onlineFriend = (OnlineFriend)object;
        return this.q.g() == onlineFriend.q.g();
    }

    @Nullable
    public UUID k() {
        return this.p;
    }

    public static void q(boolean bl) {
        I = bl;
    }

    public void X(boolean bl) {
        this.g = bl;
    }

    public ExternalFriend q() {
        return this.Q;
    }

    public void V(@Nullable String string) {
        this.P = string;
    }

    public OnlineStatus F() {
        return this.n;
    }

    public int hashCode() {
        return this.q.hashCode();
    }

    public String I() {
        return this.y;
    }

    @Nullable
    public String v() {
        return this.P;
    }

    public void i(String string) {
        this.K = string;
    }

    public String C() {
        return this.K;
    }

    public int d() {
        return this.B;
    }

    public boolean B() {
        return this.D;
    }

    public void g(OnlineStatus onlineStatus) {
        this.n = onlineStatus;
        if (onlineStatus.equals((Object)OnlineStatus.ONLINE)) {
            Notification notification = new Notification(NotificationType.FRIENDS_ONLINE, "\u00a7f" + this.C() + " \u00a77is online", new TextNotificationContent("", NotificationType.FRIENDS_ONLINE), 0.0, 0.0, 4000L);
            boolean bl = true;
            for (INotification iNotification : Vape.INSTANCE.getNotificationManager().getNotifications()) {
                if (!(iNotification instanceof Notification)
                        || !((Notification)iNotification).getTitle().equals(notification.getTitle())) continue;
                bl = false;
                break;
            }
            if (bl) {
                Vape.INSTANCE.getNotificationManager().show(notification);
            }
        }
        if (this.k) {
            if (onlineStatus == OnlineStatus.ONLINE) {
                Vape.INSTANCE.getFriendManager().u(this.Q);
                OnlineFriendUiHelper.U();
            } else if (onlineStatus == OnlineStatus.OFFLINE) {
                Vape.INSTANCE.getFriendManager().E(this.Q);
                OnlineFriendUiHelper.U();
            }
        }
    }

    public boolean u() {
        return this.g;
    }

    public static boolean k$src$Z$1lbz3nl() {
        boolean bl = OnlineFriend.I$src$Z$1kta3hb();
        return true;
    }

    public OnlineFriend(UserModel userModel, String string) {
        this.q = userModel;
        this.i(string);
        this.Q = new ExternalFriend(this);
    }

    public OnlineFriend(UserModel userModel) {
        this(userModel, userModel.T());
    }

    public boolean y() {
        return this.k;
    }

    public void f(FriendModel friendModel) {
        this.n = OnlineStatus.f(friendModel.L$src$Lgg_vape_protocol_PresenceState_$o2vkpe());
        this.y = friendModel.k();
        this.p = friendModel.R();
        this.P = friendModel.u();
    }
}

