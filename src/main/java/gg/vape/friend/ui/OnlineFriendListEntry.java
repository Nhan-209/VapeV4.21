package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineStatus;
import gg.vape.friend.PartyInvite;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.OnlineFriendCard;
import gg.vape.friend.ui.OnlineFriendListEntryClosePopupClickHandler;
import gg.vape.friend.ui.OnlineFriendListEntryOpenActionsPopupClickHandler;
import gg.vape.friend.ui.OnlineFriendListEntryOpenPopupClickHandler;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.module.none.ClientSettings;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.GroupCreateResponsePacket;
import gg.vape.protocol.packet.GroupCreateStatus;
import gg.vape.protocol.packet.GroupInviteResponsePacket;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.notification.NotificationMessage;
import gg.vape.ui.notification.NotificationType;

public class OnlineFriendListEntry
extends PanelComponent {
    boolean i1;
    private PopupFrame ia;
    private OnlineFriend iI;
    private static int ii;
    private OnlineFriendCard iX;
    private boolean iy;

    public static int E() {
        int n = OnlineFriendListEntry.J$src$I$1n1hmp4();
        return 0;
    }

    private void Z$src$V$1naaci3() {
        this.I(this.iX);
        this.ia = ClientSettings.g(this, this.iX, PopupFrame.class);
        this.iX.x(true);
        this.l$src$V$1mibm4x();
    }

    public static void d(OnlineFriendListEntry onlineFriendListEntry) {
        onlineFriendListEntry.e$src$V$1ngc312();
    }

    private void lambda$null$0(OnlineFriend onlineFriend, GroupInviteResponsePacket groupInviteResponsePacket) {
        this.F(onlineFriend, groupInviteResponsePacket);
    }

    private void lambda$null$5() {
        this.i1 = false;
    }

    private void lambda$null$3() {
        this.i1 = false;
    }

    @Override
    public void z(boolean bl) {
        super.z(bl);
    }

    private void W() {
        Vape.INSTANCE.getOnlineManager().y().C(new PartyInvite(Vape.INSTANCE.getOnlineManager().r()));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void lambda$null$1() {
        this.i1 = false;
    }

    private void F(OnlineFriend onlineFriend, GroupInviteResponsePacket groupInviteResponsePacket) {
        switch (groupInviteResponsePacket.a()) {
            case SUCCESS: {
                OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.SUCCESS, "Invited " + onlineFriend.C() + " to party"));
                break;
            }
            case TOO_MANY_INVITES: {
                OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.ERROR, "Sent too many invites"));
                break;
            }
            case NOT_ONLINE: 
            case ALREADY_INVITED: 
            case FAILED: {
                OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.ERROR, "Error inviting " + onlineFriend.C() + " to party"));
            }
        }
    }

    private void s$src$V$1no17c4() {
        if (!this.iy) {
            return;
        }
        Frame frame = this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa();
        this.ia.K(this.G$src$D$1b2f02a());
        this.ia.S(frame.n() + frame.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L());
        double d = frame.L() - frame.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L() - 50.0;
        this.iX.d$src$Lgg_vape_friend_ui_OnlineFriendDetailsPanel_$1i561zb().o$src$Lgg_vape_friend_ui_OnlineChatPanel_$15yewwy().z().u(d);
        this.iX.d$src$Lgg_vape_friend_ui_OnlineFriendDetailsPanel_$1i561zb().o$src$Lgg_vape_friend_ui_OnlineChatPanel_$15yewwy().z().t(d);
        this.iX.d$src$Lgg_vape_friend_ui_OnlineFriendDetailsPanel_$1i561zb().o$src$Lgg_vape_friend_ui_OnlineChatPanel_$15yewwy().z().l$src$V$1mibm4x();
        this.ia.l$src$V$1mibm4x();
    }

    public static int J$src$I$1n1hmp4() {
        return ii;
    }

    private void lambda$null$4(OnlineFriend onlineFriend, GroupCreateResponsePacket groupCreateResponsePacket) {
        if (groupCreateResponsePacket.q$src$Lgg_vape_protocol_packet_GroupCreateStatus_$1c0kqtl() == GroupCreateStatus.SUCCESS) {
            this.i1 = true;
            ZeusConnectionManager.T().u().J(onlineFriend.S(), arg_0 -> this.lambda$null$2(onlineFriend, arg_0), this::lambda$null$3);
        }
    }

    public static void G(OnlineFriendListEntry onlineFriendListEntry) {
        onlineFriendListEntry.n$src$V$1nla8db();
    }

    static {
        OnlineFriendListEntry.T(70);
    }

    private void lambda$null$2(OnlineFriend onlineFriend, GroupInviteResponsePacket groupInviteResponsePacket) {
        this.F(onlineFriend, groupInviteResponsePacket);
    }

    private void e$src$V$1ngc312() {
        ClientSettings.K(this.ia);
        this.H(this.iX);
        this.iX.x(false);
        this.iy = false;
        this.ia.l$src$V$1mibm4x();
        this.l$src$V$1mibm4x();
    }

    public OnlineFriendListEntry(OnlineFriend onlineFriend) {
        super(99.0, 24.0);
        this.iI = onlineFriend;
        this.iX = new OnlineFriendCard(onlineFriend);
        this.d(false);
        this.H(this.iX);
        this.iX.d$src$Lgg_vape_friend_ui_OnlineFriendDetailsPanel_$1i561zb().j$src$Lgg_vape_friend_ui_OnlineFriendActionPanel_$1bao1mp().b$src$Lgg_vape_ui_click_component_gui_TextActionButton$efmaux().r(new OnlineFriendListEntryOpenActionsPopupClickHandler(this));
        this.iX.j$src$Lgg_vape_ui_click_component_IconButtonComponent_$1ooa9pe().r(new OnlineFriendListEntryOpenPopupClickHandler(this));
        this.iX.A$src$Lgg_vape_ui_click_component_IconButtonComponent_$10cdcvf().r(() -> this.lambda$new$6(onlineFriend));
        this.iX.b$src$Lgg_vape_ui_click_component_IconButtonComponent_$txugmi().r(new OnlineFriendListEntryClosePopupClickHandler(this));
    }

    public OnlineFriend g$src$Lgg_vape_friend_OnlineFriend_$1a3nbft() {
        return this.iX.i$src$Lgg_vape_friend_OnlineFriend_$zehrml();
    }

    private void lambda$new$6(OnlineFriend onlineFriend) {
        if (this.i1) {
            return;
        }
        if (onlineFriend.F() == OnlineStatus.OFFLINE) {
            return;
        }
        this.i1 = true;
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState != null) {
            if (!partyState.t()) {
                this.i1 = false;
                return;
            }
            if (partyState.c().contains(onlineFriend)) {
                this.i1 = false;
                OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.ERROR, "Cannot send party invite to a party member"));
                return;
            }
            if (partyState.S().contains(onlineFriend)) {
                this.i1 = false;
                OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.ERROR, "Cannot send party invite to an already invited person"));
                return;
            }
            ZeusConnectionManager.T().u().J(onlineFriend.S(), arg_0 -> this.lambda$null$0(onlineFriend, arg_0), this::lambda$null$1);
            return;
        }
        ZeusConnectionManager.T().u().w(arg_0 -> this.lambda$null$4(onlineFriend, arg_0), this::lambda$null$5);
    }

    @Override
    public void c() {
        super.c();
        this.s$src$V$1no17c4();
    }

    private void n$src$V$1nla8db() {
        this.Z$src$V$1naaci3();
        this.iy = true;
    }

    public static void T(int n) {
        ii = n;
    }

    public OnlineFriendCard s$src$Lgg_vape_friend_ui_OnlineFriendCard_$urytiw() {
        return this.iX;
    }

    @Override
    public double C() {
        return this.iy ? super.C() : this.iX.L();
    }
}

