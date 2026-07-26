package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineStatus;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.friend.ui.PartyFriendRowComponent;
import gg.vape.friend.ui.PartyInviteActionLabelComponent;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.GroupCreateResponsePacket;
import gg.vape.protocol.packet.GroupCreateStatus;
import gg.vape.protocol.packet.GroupInviteResponsePacket;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.gui.AnimatedCenteredTextLabelComponent;
import gg.vape.ui.notification.NotificationMessage;
import gg.vape.ui.notification.NotificationType;

public class PartyInviteFriendRowComponent
extends PartyFriendRowComponent {
    boolean Ff;
    private final AnimatedCenteredTextLabelComponent Fs;

    @Override
    protected void b() {
    }

    @Override
    public void c() {
        this.Fs.Z(this.w$src$Z$e457mb());
        super.c();
    }

    public PartyInviteFriendRowComponent(OnlineFriend onlineFriend) {
        super(onlineFriend, false, false);
        this.Fs = new PartyInviteActionLabelComponent(this, "INVITE", PartyInviteFriendRowComponent.J.l);
        this.Ff = false;
        this.Fs.r(this::a$src$V$18kn00v);
        this.Xq.S();
        this.Xq.h(this.Xj, new Object[0]);
        this.Fs.y((double)0.65f);
        this.Fs.y(1.0f);
        this.Xq.h(this.Fs, new Object[0]);
    }

    private void lambda$null$3() {
        this.Ff = false;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void a$src$V$18kn00v() {
        if (this.Ff) {
            return;
        }
        if (this.Xl.F() == OnlineStatus.OFFLINE) {
            return;
        }
        this.Ff = true;
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState != null) {
            if (!partyState.t()) {
                this.Ff = false;
                return;
            }
            if (partyState.c().contains(this.Xl)) {
                this.Ff = false;
                OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.ERROR, "Cannot send party invite to a party member"));
                return;
            }
            if (partyState.S().contains(this.Xl)) {
                this.Ff = false;
                OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.ERROR, "Cannot send party invite to an already invited person"));
                return;
            }
            ZeusConnectionManager.T().u().J(this.Xl.S(), this::lambda$onInvite$0, this::lambda$onInvite$1);
            return;
        }
        ZeusConnectionManager.T().u().w(this::lambda$onInvite$4, this::lambda$onInvite$5);
    }

    private void lambda$onInvite$4(GroupCreateResponsePacket groupCreateResponsePacket) {
        if (groupCreateResponsePacket.q$src$Lgg_vape_protocol_packet_GroupCreateStatus_$1c0kqtl() == GroupCreateStatus.SUCCESS) {
            this.Ff = true;
            ZeusConnectionManager.T().u().J(this.Xl.S(), this::lambda$null$2, this::lambda$null$3);
        }
    }

    private void e(OnlineFriend onlineFriend, GroupInviteResponsePacket groupInviteResponsePacket) {
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

    private void lambda$onInvite$0(GroupInviteResponsePacket groupInviteResponsePacket) {
        this.e(this.Xl, groupInviteResponsePacket);
    }

    private void lambda$onInvite$5() {
        this.Ff = false;
    }

    private void lambda$onInvite$1() {
        this.Ff = false;
    }

    private void lambda$null$2(GroupInviteResponsePacket groupInviteResponsePacket) {
        this.e(this.Xl, groupInviteResponsePacket);
    }
}

