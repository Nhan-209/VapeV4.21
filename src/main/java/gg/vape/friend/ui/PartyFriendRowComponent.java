package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.FriendRequest;
import gg.vape.friend.FriendRequestService;
import gg.vape.friend.IncomingFriendRequest;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineFriendColorUtil;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.OnlineFriendAvatarComponent;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.friend.ui.PartyFriendActionLabelComponent;
import gg.vape.friend.ui.PartyFriendCompactActionLabelComponent;
import gg.vape.friend.ui.PartyFriendFixedTextLabelComponent;
import gg.vape.friend.ui.PartyFriendNameLabelComponent;
import gg.vape.friend.ui.PartyFriendSecondaryActionLabelComponent;
import gg.vape.friend.ui.PartyFriendTertiaryActionLabelComponent;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.ClientGroupLeaderKickResponsePacket;
import gg.vape.protocol.packet.ClientGroupLeaderKickStatus;
import gg.vape.protocol.packet.ClientGroupLeaderPromoteResponsePacket;
import gg.vape.protocol.packet.ClientGroupLeaderPromoteStatus;
import gg.vape.protocol.packet.GroupUninviteResponsePacket;
import gg.vape.protocol.packet.GroupUninviteStatus;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TextLabelComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.AnimatedCenteredTextLabelComponent;
import gg.vape.ui.notification.NotificationMessage;
import gg.vape.ui.notification.NotificationType;
import gg.vape.utils.render.GuiRenderPrimitives;

public class PartyFriendRowComponent
extends PanelComponent {
    private boolean X2;
    protected final FlowLayoutComponent Xq;
    protected TextLabelComponent Xj;
    private final AnimatedCenteredTextLabelComponent X7;
    protected final AnimatedCenteredTextLabelComponent X5;
    private boolean XJ;
    protected final OnlineFriend Xl;
    private final AnimatedCenteredTextLabelComponent XL;
    private final AnimatedCenteredTextLabelComponent XQ;
    private final FlowLayoutComponent Xs = new FlowLayoutComponent(70.0);
    private final boolean X4;
    private final boolean XS;
    private static String XY;
    private TruncatedTextComponent Xz;

    private void lambda$onPromote$0(PartyState partyState, ClientGroupLeaderPromoteResponsePacket clientGroupLeaderPromoteResponsePacket) {
        if (clientGroupLeaderPromoteResponsePacket.M() == ClientGroupLeaderPromoteStatus.SUCCESS) {
            partyState.H(this.Xl);
        }
    }


    @Override
    public void H() {
        super.H();
    }

    public PartyFriendRowComponent(OnlineFriend onlineFriend, boolean bl, boolean bl2) {
        super(99.0, 20.0);
        this.XL = new PartyFriendCompactActionLabelComponent(this, "PROMOTE", PartyFriendRowComponent.J.l);
        this.X7 = new PartyFriendSecondaryActionLabelComponent(this, "KICK", PartyFriendRowComponent.J.l);
        this.X5 = new PartyFriendActionLabelComponent(this, "REVOKE", PartyFriendRowComponent.J.l);
        this.XQ = new PartyFriendTertiaryActionLabelComponent(this, "ADD", PartyFriendRowComponent.J.l);
        this.Xq = new FlowLayoutComponent(70.0);
        this.X2 = false;
        this.Xl = onlineFriend;
        this.XS = bl;
        this.X4 = bl2;
        this.Xz = new PartyFriendNameLabelComponent(this, onlineFriend.C(), "...", 72.0, 0.75, PartyFriendRowComponent.J.A, false);
        this.Xj = new PartyFriendFixedTextLabelComponent(this, onlineFriend.I(), 0.55, 0.75, 0.1, 72.0, false, false, PartyFriendRowComponent.J.h);
        this.Xs.h(this.Xz, new Object[0]);
        this.Xq.h(this.Xj, new Object[0]);
        if (bl) {
            this.Xq.addChildren(this.X5);
        } else {
            this.Xq.addChildren(this.XQ, this.XL, new SpacerComponent(2.0, 1.0), this.X7);
        }
        this.Xs.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.Xs.addChildren(new SpacerComponent(0.0, 9.0));
        this.Xs.addChildren(this.Xq);
        this.addChildren(new SpacerComponent(4.0, 1.0), new OnlineFriendAvatarComponent(onlineFriend, 8.0, 8.0), new SpacerComponent(4.0, 1.0), this.Xs);
        this.XL.addClickListener(this::e$src$V$hles3e);
        this.X7.addClickListener(this::d$src$V$hkuzi1);
        this.X5.addClickListener(this::Q$src$V$haew86);
        this.XQ.addClickListener(this::o$src$V$hqwq10);
    }

    public static String getName() {
        return XY;
    }

    private void lambda$onRevoke$4(PartyState partyState, GroupUninviteResponsePacket groupUninviteResponsePacket) {
        if (groupUninviteResponsePacket.H() == GroupUninviteStatus.SUCCESS) {
            partyState.Y(this.Xl);
        }
    }

    protected void b() {
        if (!this.XS) {
            GuiRenderPrimitives.V(this.Xj.G$src$D$1b2f02a(), this.Xj.n() + 2.0, 2.0, 1.0, OnlineFriendColorUtil.f(this.Xl.d()));
        }
    }

    static {
        PartyFriendRowComponent.d("yJCYxb");
    }

    private void e$src$V$hles3e() {
        if (this.X2) {
            return;
        }
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        this.X2 = true;
        ZeusConnectionManager.T().u().s(this.Xl.S(), arg_0 -> this.lambda$onPromote$0(partyState, arg_0), this::lambda$onPromote$1);
    }

    @Override
    public void c() {
        boolean bl;
        boolean bl2 = Vape.INSTANCE.getOnlineFriendManager().g().contains(this.Xl) || Vape.INSTANCE.getOnlineManager().D().J(this.Xl);
        boolean bl3 = Vape.INSTANCE.getOnlineManager().r().equals(this.Xl);
        boolean bl4 = bl = !bl2 && !bl3;
        if (this.w$src$Z$e457mb()) {
            if (this.X4) {
                this.XQ.setVisible(bl);
                this.XL.setVisible(false);
                this.X7.setVisible(false);
                this.X5.setVisible(false);
            } else {
                this.XQ.setVisible(bl);
                this.XL.setVisible(true);
                this.X7.setVisible(true);
                this.X5.setVisible(true);
            }
        } else {
            this.XQ.setVisible(false);
            this.XL.setVisible(false);
            this.X7.setVisible(false);
            this.X5.setVisible(false);
        }
        this.Xj.setVisible(!this.w$src$Z$e457mb() || this.X4 && !bl);
        this.l$src$V$1mibm4x();
        this.Xz.setText(this.Xl.C());
        if (this.XS) {
            this.Xj.setText(this.Xl.I());
        } else {
            this.Xj.setText("   " + this.Xl.I());
        }
        super.c();
        this.b();
        this.XL.setFontScale((double)0.65f);
        this.X7.setFontScale((double)0.65f);
        this.X5.setFontScale((double)0.65f);
        this.XQ.setFontScale((double)0.65f);
        this.XL.setBorderAlpha(1.0f);
        this.X7.setBorderAlpha(1.0f);
        this.X5.setBorderAlpha(1.0f);
        this.XQ.setBorderAlpha(1.0f);
        this.Xs.setShowDisabledOverlay(false);
        this.setShowDisabledOverlay(false);
    }

    private void lambda$onPromote$1() {
        this.X2 = false;
    }

    private void lambda$onKick$3() {
        this.X2 = false;
    }

    private void lambda$onRevoke$5() {
        this.X2 = false;
    }

    public OnlineFriend H$src$Lgg_vape_friend_OnlineFriend_$cx8nou() {
        return this.Xl;
    }

    private void d$src$V$hkuzi1() {
        if (this.X2) {
            return;
        }
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        this.X2 = true;
        ZeusConnectionManager.T().u().c(this.Xl.S(), arg_0 -> this.lambda$onKick$2(partyState, arg_0), this::lambda$onKick$3);
    }

    private void lambda$onKick$2(PartyState partyState, ClientGroupLeaderKickResponsePacket clientGroupLeaderKickResponsePacket) {
        if (clientGroupLeaderKickResponsePacket.P() == ClientGroupLeaderKickStatus.SUCCESS) {
            partyState.Y(this.Xl);
        }
    }

    private void o$src$V$hqwq10() {
        if (this.X2) {
            return;
        }
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        for (FriendRequest friendRequest : Vape.INSTANCE.getOnlineManager().D().I()) {
            if (!friendRequest.x().C().equals(this.Xl.C())) continue;
            OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.SUCCESS, "Added " + this.Xl.C() + " as a friend"));
            Vape.INSTANCE.getOnlineManager().D().N((IncomingFriendRequest)friendRequest);
            return;
        }
        this.X2 = true;
        FriendRequestService.Z(this.Xl.C());
        this.X2 = false;
    }

    public static void d(String string) {
        XY = string;
    }

    private void Q$src$V$haew86() {
        if (this.X2) {
            return;
        }
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        this.X2 = true;
        ZeusConnectionManager.T().u().V(this.Xl.S(), arg_0 -> this.lambda$onRevoke$4(partyState, arg_0), this::lambda$onRevoke$5);
    }
}

