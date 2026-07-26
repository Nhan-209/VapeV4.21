package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineStatus;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.OnlineFriendActionIconButton;
import gg.vape.friend.ui.OnlineFriendActionPanelPopupOutsideClickFilter;
import gg.vape.friend.ui.OnlineFriendNotificationsValue;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.module.none.ClientSettings;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.FriendDeleteResponsePacket;
import gg.vape.protocol.packet.GroupCreateResponsePacket;
import gg.vape.protocol.packet.GroupCreateStatus;
import gg.vape.protocol.packet.GroupInviteResponsePacket;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.gui.IconActionButton;
import gg.vape.ui.click.component.gui.TextActionButton;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.DimmedCenteredPopupFrame;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.notification.NotificationMessage;
import gg.vape.ui.notification.NotificationType;
import gg.vape.value.BooleanValue;
import java.awt.Color;

public class OnlineFriendActionPanel
extends PanelComponent {
    private final OnlineFriend R7;
    private final TextActionButton RI;
    private final BooleanValue RY;
    private final BooleanToggleComponent Rw;
    private boolean RV;
    private final IconActionButton RT;
    private final IconActionButton RM;
    private boolean RP;

    @Override
    public double C() {
        return 35.0;
    }

    private void lambda$null$5() {
        this.RV = false;
    }

    private void lambda$null$10(PopupFrame popupFrame) {
        ClientSettings.K(popupFrame);
        this.RV = false;
    }

    private void x(OnlineFriend onlineFriend, GroupInviteResponsePacket groupInviteResponsePacket) {
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

    private void lambda$null$9(PopupFrame popupFrame) {
        ClientSettings.K(popupFrame);
        ZeusConnectionManager.T().u().i(this.R7.S(), this::lambda$null$7, this::lambda$null$8);
    }

    static boolean V(OnlineFriendActionPanel onlineFriendActionPanel) {
        return onlineFriendActionPanel.RP;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void lambda$null$3() {
        this.RV = false;
    }

    public OnlineFriendActionPanel(OnlineFriend onlineFriend) {
        super(99.0, 35.0);
        boolean bl;
        this.RT = new IconActionButton("newtrash", 0.2, 20.0, 13.0, OnlineFriendActionPanel.J.d, 0.9);
        this.RM = new OnlineFriendActionIconButton(this, "party@2x", 0.2, 20.0, 13.0, OnlineFriendActionPanel.J.B, 0.9);
        this.RI = new TextActionButton("CHAT", 0.7, false, 46.0, 13.0, OnlineFriendActionPanel.J.B, 0.9);
        this.RY = new OnlineFriendNotificationsValue(this, (Object)null, "Sync with Friends", false);
        this.Rw = new BooleanToggleComponent("Sync with friends", 0.8, this.RY);
        this.RV = false;
        this.R7 = onlineFriend;
        boolean bl2 = bl = Vape.INSTANCE.getOnlineManager().y().j() != null;
        if (bl) {
            boolean bl3 = Vape.INSTANCE.getOnlineManager().y().j().c().contains(onlineFriend);
            boolean bl4 = Vape.INSTANCE.getOnlineManager().y().j().S().contains(onlineFriend);
            this.RP = onlineFriend.F().equals((Object)OnlineStatus.OFFLINE) || bl3 || bl4;
            this.RM.e(new ColorAnimation(0.15, this.RP ? OnlineFriendActionPanel.J.m : new Color(45, 45, 45), this.RP ? OnlineFriendActionPanel.J.m : this.RM.P$src$Ljava_awt_Color_$va33hp()));
            this.RM.w(new ColorAnimation(0.15, this.RP ? OnlineFriendActionPanel.J.K : OnlineFriendActionPanel.J.W, this.RP ? OnlineFriendActionPanel.J.K : OnlineFriendActionPanel.J.f));
            this.RY.o(onlineFriend.y());
            this.d(false);
            this.Rw.P(true);
            this.Rw.o(90.0);
            this.Rw.q(90.0);
            this.Rw.d(false);
            this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap, widthwrap");
            this.RT.w("Remove friend");
            this.RM.w("Invite to party");
            this.Rw.w("Automatically add friend to Minecraft Friends list");
            this.b$src$V$1c62ivh();
            this.RM.r(() -> this.lambda$new$6(onlineFriend));
            this.H(new SpacerComponent(99.0, 1.0), new SpacerComponent(6.0, 1.0), this.RT, new SpacerComponent(2.0, 1.0), this.RM, new SpacerComponent(2.0, 1.0), this.RI, new SpacerComponent(99.0, 2.0), new SpacerComponent(2.0, 1.0), this.Rw);
            return;
        }
        boolean bl5 = false;
        boolean bl6 = false;
        this.RP = onlineFriend.F().equals((Object)OnlineStatus.OFFLINE);
        this.RM.e(new ColorAnimation(0.15, this.RP ? OnlineFriendActionPanel.J.m : new Color(45, 45, 45), this.RP ? OnlineFriendActionPanel.J.m : this.RM.P$src$Ljava_awt_Color_$va33hp()));
        this.RM.w(new ColorAnimation(0.15, this.RP ? OnlineFriendActionPanel.J.K : OnlineFriendActionPanel.J.W, this.RP ? OnlineFriendActionPanel.J.K : OnlineFriendActionPanel.J.f));
        this.RY.o(onlineFriend.y());
        this.d(false);
        this.Rw.P(true);
        this.Rw.o(90.0);
        this.Rw.q(90.0);
        this.Rw.d(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap, widthwrap");
        this.RT.w("Remove friend");
        this.RM.w("Invite to party");
        this.Rw.w("Automatically add friend to Minecraft Friends list");
        this.b$src$V$1c62ivh();
        this.RM.r(() -> this.lambda$new$6(onlineFriend));
        this.H(new SpacerComponent(99.0, 1.0), new SpacerComponent(6.0, 1.0), this.RT, new SpacerComponent(2.0, 1.0), this.RM, new SpacerComponent(2.0, 1.0), this.RI, new SpacerComponent(99.0, 2.0), new SpacerComponent(2.0, 1.0), this.Rw);
    }

    static OnlineFriend O(OnlineFriendActionPanel onlineFriendActionPanel) {
        return onlineFriendActionPanel.R7;
    }

    private void lambda$null$1() {
        this.RV = false;
    }

    @Override
    public void u() {
        boolean bl;
        boolean bl2;
        super.u();
        boolean bl3 = bl2 = Vape.INSTANCE.getOnlineManager().y().j() != null;
        if (bl2) {
            boolean bl4;
            boolean bl5 = Vape.INSTANCE.getOnlineManager().y().j().c().contains(this.R7);
            boolean bl6 = Vape.INSTANCE.getOnlineManager().y().j().S().contains(this.R7);
            boolean bl7 = bl4 = this.R7.F().equals((Object)OnlineStatus.OFFLINE) || bl5 || bl6;
            if (this.RP != bl4) {
                this.RP = bl4;
                this.RM.e(new ColorAnimation(0.15, this.RP ? OnlineFriendActionPanel.J.m : new Color(45, 45, 45), this.RP ? OnlineFriendActionPanel.J.m : this.RM.P$src$Ljava_awt_Color_$va33hp()));
                this.RM.w(new ColorAnimation(0.15, this.RP ? OnlineFriendActionPanel.J.K : OnlineFriendActionPanel.J.W, this.RP ? OnlineFriendActionPanel.J.K : OnlineFriendActionPanel.J.f));
            }
            return;
        }
        boolean bl8 = false;
        boolean bl9 = false;
        boolean bl10 = bl = this.R7.F().equals((Object)OnlineStatus.OFFLINE);
        if (this.RP != bl) {
            this.RP = bl;
            this.RM.e(new ColorAnimation(0.15, this.RP ? OnlineFriendActionPanel.J.m : new Color(45, 45, 45), this.RP ? OnlineFriendActionPanel.J.m : this.RM.P$src$Ljava_awt_Color_$va33hp()));
            this.RM.w(new ColorAnimation(0.15, this.RP ? OnlineFriendActionPanel.J.K : OnlineFriendActionPanel.J.W, this.RP ? OnlineFriendActionPanel.J.K : OnlineFriendActionPanel.J.f));
        }
    }

    private void lambda$null$0(OnlineFriend onlineFriend, GroupInviteResponsePacket groupInviteResponsePacket) {
        this.x(onlineFriend, groupInviteResponsePacket);
    }

    private void lambda$new$6(OnlineFriend onlineFriend) {
        if (this.RV) {
            return;
        }
        if (onlineFriend.F() == OnlineStatus.OFFLINE) {
            return;
        }
        this.RV = true;
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState != null) {
            if (!partyState.t()) {
                this.RV = false;
                return;
            }
            if (partyState.c().contains(onlineFriend)) {
                this.RV = false;
                OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.ERROR, "Cannot send party invite to a party member"));
                return;
            }
            if (partyState.S().contains(onlineFriend)) {
                this.RV = false;
                OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.ERROR, "Cannot send party invite to an already invited person"));
                return;
            }
            ZeusConnectionManager.T().u().J(onlineFriend.S(), arg_0 -> this.lambda$null$0(onlineFriend, arg_0), this::lambda$null$1);
            return;
        }
        ZeusConnectionManager.T().u().w(arg_0 -> this.lambda$null$4(onlineFriend, arg_0), this::lambda$null$5);
    }

    private void lambda$null$8() {
        this.RV = false;
    }

    @Override
    public void c() {
        super.c();
    }

    public TextActionButton b$src$Lgg_vape_ui_click_component_gui_TextActionButton$efmaux() {
        return this.RI;
    }

    private void lambda$null$2(OnlineFriend onlineFriend, GroupInviteResponsePacket groupInviteResponsePacket) {
        this.x(onlineFriend, groupInviteResponsePacket);
    }

    private void b$src$V$1c62ivh() {
        this.RT.r(this::lambda$addDeleteListener$11);
    }

    @Override
    public double x() {
        return 99.0;
    }

    private void lambda$null$4(OnlineFriend onlineFriend, GroupCreateResponsePacket groupCreateResponsePacket) {
        if (groupCreateResponsePacket.q$src$Lgg_vape_protocol_packet_GroupCreateStatus_$1c0kqtl() == GroupCreateStatus.SUCCESS) {
            this.RV = true;
            ZeusConnectionManager.T().u().J(onlineFriend.S(), arg_0 -> this.lambda$null$2(onlineFriend, arg_0), this::lambda$null$3);
        }
    }

    private void lambda$null$7(FriendDeleteResponsePacket friendDeleteResponsePacket) {
        if (friendDeleteResponsePacket.I()) {
            Vape.INSTANCE.getFriendManager().E(this.R7.q());
            Vape.INSTANCE.getOnlineFriendManager().g(this.R7);
        }
    }

    private void lambda$addDeleteListener$11() {
        if (this.RV) {
            return;
        }
        this.RV = true;
        ConfirmationDialogComponent confirmationDialogComponent = new ConfirmationDialogComponent("Are you sure you want to remove this friend?", "REMOVE", "newtrash");
        DimmedCenteredPopupFrame dimmedCenteredPopupFrame = ClientSettings.g(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), confirmationDialogComponent, DimmedCenteredPopupFrame.class);
        confirmationDialogComponent.T$src$Lgg_vape_ui_click_component_gui_TextButton_$17m2d4e().r(() -> this.lambda$null$9(dimmedCenteredPopupFrame));
        confirmationDialogComponent.E().r(() -> this.lambda$null$10(dimmedCenteredPopupFrame));
        dimmedCenteredPopupFrame.j(new OnlineFriendActionPanelPopupOutsideClickFilter(this, dimmedCenteredPopupFrame));
    }
}
