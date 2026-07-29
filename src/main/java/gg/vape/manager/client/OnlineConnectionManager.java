package gg.vape.manager.client;

import gg.vape.Vape;
import gg.vape.account.AccountEntitlements;
import gg.vape.api.ApiAccessTokenProvider;
import gg.vape.config.PublicProfileUser;
import gg.vape.friend.FriendModel;
import gg.vape.friend.FriendRequestModel;
import gg.vape.friend.IncomingFriendRequest;
import gg.vape.friend.LocalOnlineFriend;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineStatus;
import gg.vape.friend.OutgoingFriendRequest;
import gg.vape.friend.PartyInvite;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.friend.ui.OnlineFriendsFrame;
import gg.vape.friend.ui.PartyMemberRow;
import gg.vape.friend.ui.PartyMemberTextStatusComponent;
import gg.vape.manager.client.GlobalSettingsController;
import gg.vape.manager.client.OnlineAccountState;
import gg.vape.manager.client.OnlineConnectionState;
import gg.vape.manager.client.OnlineDisconnectReason;
import gg.vape.manager.client.OnlineSettings;
import gg.vape.module.none.ClientSettings;
import gg.vape.notification.NotificationType;
import gg.vape.protocol.ZeusClient;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.event.FriendChatMessageEvent;
import gg.vape.protocol.event.FriendMinecraftProfileUpdateEvent;
import gg.vape.protocol.event.FriendModelUpdateEvent;
import gg.vape.protocol.event.FriendPresenceStateEvent;
import gg.vape.protocol.event.FriendRemovedEvent;
import gg.vape.protocol.event.FriendRequestEvent;
import gg.vape.protocol.event.FriendRequestReceivedEvent;
import gg.vape.protocol.event.FriendRequestRemovedEvent;
import gg.vape.protocol.event.FriendRequestSentEvent;
import gg.vape.protocol.event.FriendServerAddressEvent;
import gg.vape.protocol.event.FriendVisibilityUpdateEvent;
import gg.vape.protocol.event.GroupChatMessageEvent;
import gg.vape.protocol.event.GroupCreatedEvent;
import gg.vape.protocol.event.GroupDeletedEvent;
import gg.vape.protocol.event.GroupInviteAcceptedEvent;
import gg.vape.protocol.event.GroupInviteSentEvent;
import gg.vape.protocol.event.GroupLeftEvent;
import gg.vape.protocol.event.GroupOptionUpdatedEvent;
import gg.vape.protocol.event.InitialOnlineFriendStateEvent;
import gg.vape.protocol.event.OnlineEventDispatcher;
import gg.vape.protocol.event.PartyInviteReceivedEvent;
import gg.vape.protocol.event.PartyInviteRemovedEvent;
import gg.vape.protocol.event.PartyLeaderChangedEvent;
import gg.vape.protocol.event.PartyMemberAction;
import gg.vape.protocol.event.PartyMemberUpdateEvent;
import gg.vape.protocol.event.UserDisplayNameChangedEvent;
import gg.vape.protocol.packet.AuthenticationResponsePacket;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionSettingsFrame;
import gg.vape.utils.TimerUtil;
import gg.vape.value.Value;
import io.netty.channel.Channel;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import org.jetbrains.annotations.Nullable;

public class OnlineConnectionManager {
    private long x = -1L;
    private boolean z = false;
    public static final OnlineConnectionManager T;
    private OnlineAccountState y;
    private final TimerUtil p;
    private static final String b;
    private boolean m = false;
    private boolean i = false;
    private boolean Z = false;
    private OnlineConnectionState v = OnlineConnectionState.OFFLINE;
    @Nullable
    private Thread u;
    private final OnlineSettings Y;
    private final GlobalSettingsController w;
    @Nullable
    private OnlineDisconnectReason B;
    private int r;

    private static void lambda$setState$5(OnlineConnectionState onlineConnectionState) {
        OnlineConnectionSettingsFrame.updateConnectionStateIfCreated(onlineConnectionState);
    }

    private static void lambda$setupListeners$12(FriendRequestReceivedEvent friendRequestReceivedEvent) {
        Vape.INSTANCE.getOnlineManager().D().O(new IncomingFriendRequest(friendRequestReceivedEvent.q()));
    }

    public void k(boolean bl) {
        this.i = bl;
    }

    public OnlineAccountState j() {
        return this.y;
    }

    private static void lambda$setupListeners$30(PartyLeaderChangedEvent partyLeaderChangedEvent) {
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        OnlineFriend onlineFriend = partyState.X(partyLeaderChangedEvent.z());
        if (onlineFriend == null) {
            return;
        }
        partyState.H(onlineFriend);
    }

    private void lambda$null$2(AtomicReference atomicReference) {
        OnlineDisconnectReason onlineDisconnectReason = this.B;
        OnlineConnectionState onlineConnectionState = this.v;
        if (onlineConnectionState == OnlineConnectionState.OUTDATED_CLIENT) {
            return;
        }
        if (onlineConnectionState != OnlineConnectionState.OUTDATED_SERVER) {
            this.l(OnlineConnectionState.OFFLINE);
        }
        if (onlineDisconnectReason == OnlineDisconnectReason.LOGGED_IN_FROM_ANOTHER_LOCATION) {
            ClientSettings.getFrame(OnlineFriendsFrame.class).e();
            return;
        }
        if (onlineDisconnectReason == OnlineDisconnectReason.BANNED) {
            this.o(OnlineAccountState.BANNED);
            return;
        }
        if (!this.i && (onlineDisconnectReason == null || onlineDisconnectReason.Z())) {
            int n;
            if ((n = 5 * ++this.r) > 30 || onlineConnectionState == OnlineConnectionState.OUTDATED_SERVER) {
                n = 30;
            }
            try {
                this.x = System.currentTimeMillis() + (long)(n *= 1000);
                Thread.sleep(n);
            }
            catch (InterruptedException interruptedException) {
                this.x = -1L;
                return;
            }
            if (this.u == atomicReference.get()) {
                this.I();
            }
            this.x = -1L;
        }
    }

    private static void lambda$setAccountState$6(OnlineAccountState onlineAccountState, OnlineConnectionState onlineConnectionState) {
        OnlineConnectionSettingsFrame.updateAccountStateIfCreated(onlineAccountState, onlineConnectionState);
    }

    private static OnlineFriend lambda$null$24(FriendRequestEvent friendRequestEvent) {
        return new OnlineFriend(friendRequestEvent.f());
    }

    public OnlineConnectionState n() {
        return this.v;
    }

    private static void lambda$setupListeners$18(Consumer consumer, GroupInviteAcceptedEvent groupInviteAcceptedEvent) {
        consumer.accept(groupInviteAcceptedEvent.P());
    }

    private static void lambda$setupListeners$36(FriendServerAddressEvent friendServerAddressEvent) {
        OnlineFriend onlineFriend = Vape.INSTANCE.getOnlineManager().u().m(friendServerAddressEvent.a());
        if (onlineFriend != null) {
            onlineFriend.V(friendServerAddressEvent.Z());
        }
    }

    public void a() {
        this.Q();
        this.x = -1L;
        if (this.u != null) {
            try {
                this.u.interrupt();
                this.u = null;
            }
            catch (Throwable throwable) {
                Vape.logThrowable(throwable);
            }
        }
        this.l(OnlineConnectionState.OFFLINE);
    }

    private static void lambda$setupListeners$20(Runnable runnable, GroupDeletedEvent groupDeletedEvent) {
        runnable.run();
    }

    private static void lambda$setupListeners$26(GroupInviteSentEvent groupInviteSentEvent) {
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        partyState.o(groupInviteSentEvent.n());
    }

    private void x() {
        String string = ApiAccessTokenProvider.i();
        ZeusConnectionManager.T().u().J(string, this::lambda$startup$4);
    }

    @Nullable
    public OnlineDisconnectReason T() {
        return this.B;
    }

    private static void lambda$setupListeners$23(PartyInviteReceivedEvent partyInviteReceivedEvent) {
        PartyInvite partyInvite = new PartyInvite(Vape.INSTANCE.getOnlineManager().u().Q(partyInviteReceivedEvent.R().g(), () -> OnlineConnectionManager.lambda$null$22(partyInviteReceivedEvent)));
        Vape.INSTANCE.getOnlineManager().y().C(partyInvite);
    }

    private static void lambda$setupListeners$32(GroupOptionUpdatedEvent groupOptionUpdatedEvent) {
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        Value<?, ?> value = partyState.L().get((Object)groupOptionUpdatedEvent.j());
        if (value != null) {
            ((Value)value).setValue(groupOptionUpdatedEvent.U());
        }
    }

    private static OnlineFriend lambda$null$8(FriendModelUpdateEvent friendModelUpdateEvent) {
        return new OnlineFriend(friendModelUpdateEvent.q());
    }

    public boolean k(long l) {
        long l2 = Vape.INSTANCE.getAccountInfo().i();
        return l2 != -1L && l2 == l;
    }

    private static OnlineFriend lambda$null$22(PartyInviteReceivedEvent partyInviteReceivedEvent) {
        return new OnlineFriend(partyInviteReceivedEvent.R());
    }

    public void o(OnlineAccountState onlineAccountState) {
        this.y = onlineAccountState;
        OnlineConnectionState onlineConnectionState = this.v;
        ClientSettings.UI_EXECUTOR.execute(() -> OnlineConnectionManager.lambda$setAccountState$6(onlineAccountState, onlineConnectionState));
    }

    private static void lambda$setupListeners$15(GroupChatMessageEvent groupChatMessageEvent) {
        OnlineFriend onlineFriend = Vape.INSTANCE.getOnlineFriendManager().Q(groupChatMessageEvent.V());
        if (onlineFriend == null) {
            return;
        }
        OnlineFriendUiHelper.l(onlineFriend, onlineFriend, groupChatMessageEvent.K());
    }

    public long b() {
        return this.x;
    }

    private void lambda$connect$3(AtomicReference atomicReference) {
        try {
            this.i = false;
            ZeusConnectionManager.T().V(this::lambda$null$1, () -> this.lambda$null$2(atomicReference));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void lambda$setupListeners$27(PartyInviteRemovedEvent partyInviteRemovedEvent) {
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        partyState.q(partyInviteRemovedEvent.D());
    }

    private static void lambda$setupListeners$25(FriendRequestEvent friendRequestEvent) {
        PartyInvite partyInvite = Vape.INSTANCE.getOnlineManager().y().k(Vape.INSTANCE.getOnlineManager().u().Q(friendRequestEvent.f().g(), () -> OnlineConnectionManager.lambda$null$24(friendRequestEvent)));
        if (partyInvite == null) {
            return;
        }
        Vape.INSTANCE.getOnlineManager().y().y(partyInvite);
    }

    private static void lambda$setupListeners$17(Consumer consumer, GroupCreatedEvent groupCreatedEvent) {
        consumer.accept(groupCreatedEvent.V());
    }

    public GlobalSettingsController g() {
        return this.w;
    }

    public void Q() {
        Channel channel = ZeusConnectionManager.T().u().D();
        if (channel == null) {
            return;
        }
        T.k(true);
        channel.close();
        T.l(OnlineConnectionState.OFFLINE);
    }

    public OnlineSettings S() {
        return this.Y;
    }

    private void lambda$initialize$0() {
        this.o(OnlineAccountState.REGISTRATION_OFFLINE);
        ClientSettings.getFrame(OnlineFriendsFrame.class).Z$src$V$vdheo7();
        this.m = false;
    }

    private void c() {
        if (this.Z) {
            return;
        }
        this.Z = true;
        OnlineEventDispatcher.O.M(InitialOnlineFriendStateEvent.class, OnlineConnectionManager::lambda$setupListeners$7);
        OnlineEventDispatcher.O.M(FriendModelUpdateEvent.class, this::lambda$setupListeners$9);
        OnlineEventDispatcher.O.M(FriendPresenceStateEvent.class, OnlineConnectionManager::lambda$setupListeners$10);
        OnlineEventDispatcher.O.M(FriendRemovedEvent.class, OnlineConnectionManager::lambda$setupListeners$11);
        OnlineEventDispatcher.O.M(FriendRequestReceivedEvent.class, OnlineConnectionManager::lambda$setupListeners$12);
        OnlineEventDispatcher.O.M(FriendRequestRemovedEvent.class, OnlineConnectionManager::lambda$setupListeners$13);
        OnlineEventDispatcher.O.M(FriendRequestSentEvent.class, OnlineConnectionManager::lambda$setupListeners$14);
        OnlineEventDispatcher.O.M(GroupChatMessageEvent.class, OnlineConnectionManager::lambda$setupListeners$15);
        Consumer<PartyState> consumer = OnlineConnectionManager::lambda$setupListeners$16;
        OnlineEventDispatcher.O.M(GroupCreatedEvent.class, arg_0 -> OnlineConnectionManager.lambda$setupListeners$17(consumer, arg_0));
        OnlineEventDispatcher.O.M(GroupInviteAcceptedEvent.class, arg_0 -> OnlineConnectionManager.lambda$setupListeners$18(consumer, arg_0));
        Runnable runnable = OnlineConnectionManager::lambda$setupListeners$19;
        OnlineEventDispatcher.O.M(GroupDeletedEvent.class, arg_0 -> OnlineConnectionManager.lambda$setupListeners$20(runnable, arg_0));
        OnlineEventDispatcher.O.M(GroupLeftEvent.class, arg_0 -> OnlineConnectionManager.lambda$setupListeners$21(runnable, arg_0));
        OnlineEventDispatcher.O.M(PartyInviteReceivedEvent.class, OnlineConnectionManager::lambda$setupListeners$23);
        OnlineEventDispatcher.O.M(FriendRequestEvent.class, OnlineConnectionManager::lambda$setupListeners$25);
        OnlineEventDispatcher.O.M(GroupInviteSentEvent.class, OnlineConnectionManager::lambda$setupListeners$26);
        OnlineEventDispatcher.O.M(PartyInviteRemovedEvent.class, OnlineConnectionManager::lambda$setupListeners$27);
        OnlineEventDispatcher.O.M(PartyMemberUpdateEvent.class, arg_0 -> OnlineConnectionManager.lambda$setupListeners$29(runnable, arg_0));
        OnlineEventDispatcher.O.M(PartyLeaderChangedEvent.class, OnlineConnectionManager::lambda$setupListeners$30);
        OnlineEventDispatcher.O.M(FriendChatMessageEvent.class, OnlineConnectionManager::lambda$setupListeners$31);
        OnlineEventDispatcher.O.M(GroupOptionUpdatedEvent.class, OnlineConnectionManager::lambda$setupListeners$32);
        OnlineEventDispatcher.O.M(FriendMinecraftProfileUpdateEvent.class, OnlineConnectionManager::lambda$setupListeners$33);
        OnlineEventDispatcher.O.M(FriendVisibilityUpdateEvent.class, OnlineConnectionManager::lambda$setupListeners$34);
        OnlineEventDispatcher.O.M(UserDisplayNameChangedEvent.class, OnlineConnectionManager::lambda$setupListeners$35);
        OnlineEventDispatcher.O.M(FriendServerAddressEvent.class, OnlineConnectionManager::lambda$setupListeners$36);
    }

    private void lambda$startup$4(AuthenticationResponsePacket authenticationResponsePacket) {
        this.r = 0;
        ClientSettings.getFrame(OnlineFriendsFrame.class).Q$src$V$v8j9by();
        this.l(OnlineConnectionState.ONLINE);
        this.z = true;
    }

    private void lambda$setupListeners$9(FriendModelUpdateEvent friendModelUpdateEvent) {
        OnlineFriend onlineFriend = Vape.INSTANCE.getOnlineManager().u().Q(friendModelUpdateEvent.q().M(), () -> OnlineConnectionManager.lambda$null$8(friendModelUpdateEvent));
        onlineFriend.f(friendModelUpdateEvent.q());
        Vape.INSTANCE.getOnlineFriendManager().D(onlineFriend);
        Vape.INSTANCE.getOnlineManager().D().y(onlineFriend);
        Boolean bl = this.S().X().O().get(onlineFriend.S().g());
        if (bl != null) {
            onlineFriend.O(bl);
        }
        OnlineFriendUiHelper.n$src$V$uh9sir();
    }

    public OnlineConnectionManager() {
        this.y = OnlineAccountState.CONNECTING;
        this.p = new TimerUtil();
        this.Y = new OnlineSettings();
        this.w = new GlobalSettingsController();
    }

    private static void lambda$setupListeners$10(FriendPresenceStateEvent friendPresenceStateEvent) {
        OnlineFriend onlineFriend = Vape.INSTANCE.getOnlineFriendManager().Q(friendPresenceStateEvent.f());
        if (onlineFriend == null) {
            return;
        }
        onlineFriend.g(OnlineStatus.f(friendPresenceStateEvent.O()));
        OnlineFriendUiHelper.n$src$V$uh9sir();
    }

    private static OnlineFriend lambda$null$28(PartyMemberUpdateEvent partyMemberUpdateEvent) {
        return new OnlineFriend(partyMemberUpdateEvent.S());
    }

    static {
        b = " joined the party";
        T = new OnlineConnectionManager();
    }

    private static void lambda$setupListeners$13(FriendRequestRemovedEvent friendRequestRemovedEvent) {
        Vape.INSTANCE.getOnlineManager().D().w(friendRequestRemovedEvent.v());
    }

    private static void lambda$setupListeners$7(InitialOnlineFriendStateEvent initialOnlineFriendStateEvent) {
        Vape.INSTANCE.getOnlineManager().t();
        ZeusClient zeusClient = ZeusConnectionManager.T().u();
        Vape.INSTANCE.getOnlineManager().r().i(zeusClient.i().T());
        for (FriendModel object : initialOnlineFriendStateEvent.q()) {
            new FriendModelUpdateEvent(zeusClient, object).u();
        }
        for (FriendRequestModel friendRequestModel : initialOnlineFriendStateEvent.z()) {
            new FriendRequestReceivedEvent(zeusClient, friendRequestModel).u();
        }
        for (FriendRequestModel friendRequestModel : initialOnlineFriendStateEvent.Z()) {
            new FriendRequestSentEvent(zeusClient, friendRequestModel).u();
        }
    }

    private static void lambda$setupListeners$19() {
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        Vape.INSTANCE.getOnlineManager().y().n(null);
        Vape.INSTANCE.getOnlineManager().r().K(-1);
        for (OnlineFriend onlineFriend : Vape.INSTANCE.getOnlineManager().u().r()) {
            onlineFriend.K(-1);
        }
    }

    private static void lambda$setupListeners$34(FriendVisibilityUpdateEvent friendVisibilityUpdateEvent) {
        OnlineFriend onlineFriend = Vape.INSTANCE.getOnlineManager().u().m(friendVisibilityUpdateEvent.N());
        if (onlineFriend != null) {
            onlineFriend.X(friendVisibilityUpdateEvent.q());
        }
    }

    private static void lambda$setupListeners$31(FriendChatMessageEvent friendChatMessageEvent) {
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        OnlineFriend onlineFriend = partyState.X(friendChatMessageEvent.U());
        if (onlineFriend == null) {
            return;
        }
        PartyMemberTextStatusComponent partyMemberTextStatusComponent = new PartyMemberTextStatusComponent(friendChatMessageEvent.g());
        if (friendChatMessageEvent.U().equals(Vape.INSTANCE.getOnlineManager().r().S())) {
            partyState.n(new PartyMemberRow(Vape.INSTANCE.getOnlineManager().r(), partyMemberTextStatusComponent));
        } else {
            partyState.n(new PartyMemberRow(onlineFriend, partyMemberTextStatusComponent));
        }
    }

    private static void lambda$setupListeners$11(FriendRemovedEvent friendRemovedEvent) {
        OnlineFriend onlineFriend = Vape.INSTANCE.getOnlineFriendManager().Q(friendRemovedEvent.f());
        if (onlineFriend == null) {
            return;
        }
        Vape.INSTANCE.getFriendManager().E(onlineFriend.q());
        Vape.INSTANCE.getOnlineFriendManager().g(onlineFriend);
    }

    private static void lambda$setupListeners$21(Runnable runnable, GroupLeftEvent groupLeftEvent) {
        runnable.run();
    }

    private static void lambda$setupListeners$14(FriendRequestSentEvent friendRequestSentEvent) {
        Vape.INSTANCE.getOnlineManager().D().O(new OutgoingFriendRequest(friendRequestSentEvent.q()));
    }

    public boolean Q$src$Z$x2tw73() {
        return this.i;
    }

    private static void lambda$setupListeners$35(UserDisplayNameChangedEvent userDisplayNameChangedEvent) {
        OnlineFriend onlineFriend;
        if (userDisplayNameChangedEvent.R() == Vape.INSTANCE.getOnlineManager().r().S().g()) {
            Vape.INSTANCE.getOnlineManager().r().i(userDisplayNameChangedEvent.v());
        }
        if ((onlineFriend = Vape.INSTANCE.getOnlineManager().u().m(userDisplayNameChangedEvent.R())) != null) {
            onlineFriend.i(userDisplayNameChangedEvent.v());
        }
    }

    public void f(@Nullable OnlineDisconnectReason onlineDisconnectReason) {
        this.B = onlineDisconnectReason;
    }

    private static void lambda$setupListeners$33(FriendMinecraftProfileUpdateEvent friendMinecraftProfileUpdateEvent) {
        OnlineFriend onlineFriend = Vape.INSTANCE.getOnlineManager().u().m(friendMinecraftProfileUpdateEvent.b());
        if (onlineFriend != null) {
            onlineFriend.d(friendMinecraftProfileUpdateEvent.h(), friendMinecraftProfileUpdateEvent.b$src$Ljava_lang_String_$171yzxt());
        }
    }

    private void lambda$null$1() {
        if (!this.z) {
            this.p.reset();
        }
        this.x();
    }

    public boolean u() {
        return this.z;
    }

    public TimerUtil V() {
        return this.p;
    }

    private static void lambda$setupListeners$16(PartyState partyState) {
        PartyState partyState2 = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState2 != null) {
            return;
        }
        LocalOnlineFriend localOnlineFriend = Vape.INSTANCE.getOnlineManager().r();
        OnlineFriend onlineFriend = partyState.X(localOnlineFriend.S());
        if (onlineFriend != null) {
            localOnlineFriend.K(onlineFriend.d());
        }
        Vape.INSTANCE.getOnlineManager().y().n(partyState);
    }

    public void I() {
        if (!this.v.G()) {
            return;
        }
        this.f(null);
        this.l(OnlineConnectionState.CONNECTING);
        if (this.u != null) {
            try {
                this.u.interrupt();
                this.u = null;
            }
            catch (Throwable throwable) {
                Vape.logThrowable(throwable);
            }
        }
        AtomicReference<Thread> atomicReference = new AtomicReference<Thread>();
        Thread thread = new Thread(() -> this.lambda$connect$3(atomicReference));
        atomicReference.set(thread);
        thread.start();
        this.u = thread;
    }

    private static void lambda$setupListeners$29(Runnable runnable, PartyMemberUpdateEvent partyMemberUpdateEvent) {
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        OnlineFriend onlineFriend = Vape.INSTANCE.getOnlineManager().u().Q(partyMemberUpdateEvent.S().V(), () -> OnlineConnectionManager.lambda$null$28(partyMemberUpdateEvent));
        if (partyMemberUpdateEvent.q() == PartyMemberAction.ADD) {
            partyState.q(onlineFriend);
            partyState.Q(onlineFriend);
            onlineFriend.K(partyMemberUpdateEvent.S().e());
            Vape.INSTANCE.getNotificationManager().show(onlineFriend.C() + b, "", NotificationType.FRIENDS_PARTY_GENERAL, 3000L);
        } else {
            if (onlineFriend.equals(Vape.INSTANCE.getOnlineManager().r())) {
                runnable.run();
            } else {
                partyState.Y(onlineFriend);
            }
            onlineFriend.K(-1);
        }
    }

    public void l(OnlineConnectionState onlineConnectionState) {
        if (onlineConnectionState == OnlineConnectionState.OFFLINE && this.v != onlineConnectionState) {
            Vape.INSTANCE.getOnlineManager().t();
            ClientSettings.getFrame(OnlineFriendsFrame.class).N$src$V$v6vvjv();
        }
        this.v = onlineConnectionState;
        ClientSettings.UI_EXECUTOR.execute(() -> OnlineConnectionManager.lambda$setState$5(onlineConnectionState));
    }

    public void E() throws Exception {
        if (this.m) {
            return;
        }
        this.m = true;
        this.c();
        this.o(OnlineAccountState.CONNECTING);
        Runnable runnable = this::lambda$initialize$0;
        this.w.K();
        try {
            AccountEntitlements accountEntitlements = Vape.INSTANCE.getAccountInfo().f();
            boolean bl = accountEntitlements.q();
            boolean bl2 = accountEntitlements.M();
            boolean bl3 = accountEntitlements.O();
            if (bl3) {
                this.o(OnlineAccountState.BANNED);
            } else if (bl2) {
                this.o(OnlineAccountState.REGISTERED);
                this.Y.B();
                if (this.Y.X$src$Lgg_vape_value_BooleanValue_$7rygmo().getEffectiveValue().booleanValue()) {
                    this.I();
                } else {
                    ClientSettings.getFrame(OnlineFriendsFrame.class).p$src$Lgg_vape_friend_ui_OnlineModeToggleComponent_$u0bbsl().u(false);
                }
            } else {
                this.o(OnlineAccountState.UNREGISTERED);
                ClientSettings.getFrame(OnlineFriendsFrame.class).e();
                ClientSettings.getFrame(OnlineFriendsFrame.class).o$src$V$vp134s();
            }
        }
        catch (Throwable throwable) {
            runnable.run();
        }
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    public boolean Q(@Nullable PublicProfileUser publicProfileUser) {
        long l = Vape.INSTANCE.getAccountInfo().i();
        return publicProfileUser != null && l != -1L && l == publicProfileUser.j();
    }
}
