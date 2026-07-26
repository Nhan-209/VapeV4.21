package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.PartyInvite;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.module.none.ClientSettings;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.GroupDeleteResponsePacket;
import gg.vape.protocol.packet.GroupInviteStateResponsePacket;
import gg.vape.protocol.packet.GroupInviteStateStatus;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.frame.DimmedCenteredPopupFrame;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.notification.NotificationType;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class PartyInviteRow
extends PanelComponent {
    SpacerComponent vp;
    SpacerComponent ve;
    PanelComponent vh;
    private final PartyInvite vQ;
    private TruncatedTextComponent vN;
    TextButton vL;
    PanelComponent vq = new PanelComponent(18.0, 14.0);
    private boolean v5;
    IconButtonComponent vB;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void lambda$acceptInvite$9() {
        this.v5 = false;
    }

    private void lambda$null$5() {
        this.a$src$V$1msuozy();
    }

    private void o$src$V$1n0jtb0() {
        if (this.v5) {
            return;
        }
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState != null) {
            if (partyState.r().equals(Vape.INSTANCE.getOnlineManager().r())) {
                this.D("Are you sure you want to disband the party?");
            } else {
                this.D("Are you sure you want to leave your current party?");
            }
            return;
        }
        this.v5 = true;
        this.a$src$V$1msuozy();
    }

    public PartyInviteRow(PartyInvite partyInvite) {
        super(100.0, 16.0);
        this.vh = new PanelComponent(14.0, 14.0);
        this.vp = new SpacerComponent(18.0, 16.0);
        this.ve = new SpacerComponent(2.0, 16.0);
        this.vL = new TextButton("JOIN", 0.6, PartyInviteRow.J.B, PartyInviteRow.J.O, 18.0, 8.0);
        this.vB = new SquareIconButtonComponent("newclose", 1.0, new Color(255, 255, 255, 0), new Color(255, 255, 255, 25), 8.0, 8.0);
        this.v5 = false;
        this.vQ = partyInvite;
        this.vN = new TruncatedTextComponent(partyInvite.x().C(), "...", 46.0, 0.8, PartyInviteRow.J.A, true);
        this.d(false);
        this.vq.d(false);
        this.vh.d(false);
        this.vq.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.vh.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.vB.w("Decline Invite");
        this.vL.r(this::lambda$new$0);
        this.vB.r(() -> this.lambda$new$3(partyInvite));
        this.vL.F(false);
        this.vL.h(Color.WHITE);
        this.vq.H(this.vL);
        this.vh.H(this.ve, this.vB);
        this.H(this.vp, this.vN, this.vq, this.vh);
    }

    public PartyInvite T() {
        return this.vQ;
    }

    private static void lambda$null$4(GroupDeleteResponsePacket groupDeleteResponsePacket) {
    }

    private void lambda$new$3(PartyInvite partyInvite) {
        if (this.v5) {
            return;
        }
        this.v5 = true;
        ZeusConnectionManager.T().u().c(partyInvite.x().S(), false, arg_0 -> PartyInviteRow.lambda$null$1(partyInvite, arg_0), this::lambda$null$2);
    }

    @Override
    public void H() {
        this.vL.F(false);
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L() - 2.0, PartyInviteRow.J.m.brighter());
        float f = (float)(this.G$src$D$1b2f02a() + 6.0);
        float f2 = (float)(this.n() + 4.0);
        ImageRenderer.E(PartyInviteRow.J.B, f, f2, "party1@2x", 7.0f, 6.3f, false);
        ImageRenderer.E(PartyInviteRow.J.B, (float)(this.G$src$D$1b2f02a() + this.A() - 22.0), (float)this.n() - 0.5f, "join party texture@2x", 14.5f, 14.5f, false);
        this.vN.S(this.n() + 5.0);
        this.vN.q(this.A() - 18.0 - this.vB.A() - this.vL.A() - 4.0);
        this.vN.D(this.A() - 18.0 - this.vB.A() - this.vL.A() - 6.0);
    }

    private void a$src$V$1msuozy() {
        ZeusConnectionManager.T().u().c(this.vQ.x().S(), true, this::lambda$acceptInvite$8, this::lambda$acceptInvite$9);
    }

    private void lambda$new$0() {
        this.o$src$V$1n0jtb0();
    }

    private void lambda$handleLeaveConfirmation$7(PopupFrame popupFrame) {
        ClientSettings.K(popupFrame);
        this.v5 = false;
    }

    private void lambda$handleLeaveConfirmation$6(PopupFrame popupFrame) {
        ClientSettings.K(popupFrame);
        ZeusConnectionManager.T().u().l(PartyInviteRow::lambda$null$4, this::lambda$null$5);
    }

    private static void lambda$null$1(PartyInvite partyInvite, GroupInviteStateResponsePacket groupInviteStateResponsePacket) {
        if (groupInviteStateResponsePacket.M() == GroupInviteStateStatus.SUCCESSFULLY_DECLINED) {
            Vape.INSTANCE.getOnlineManager().y().y(partyInvite);
        } else if (groupInviteStateResponsePacket.M() == GroupInviteStateStatus.FAILED) {
            OnlineFriendUiHelper.R(NotificationType.ERROR, "Error declining party invite");
        }
    }

    private void D(String string) {
        ConfirmationDialogComponent confirmationDialogComponent = new ConfirmationDialogComponent(string, "DISBAND", "disband confirm@2x");
        DimmedCenteredPopupFrame dimmedCenteredPopupFrame = ClientSettings.g(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), confirmationDialogComponent, DimmedCenteredPopupFrame.class);
        confirmationDialogComponent.T$src$Lgg_vape_ui_click_component_gui_TextButton_$17m2d4e().r(() -> this.lambda$handleLeaveConfirmation$6(dimmedCenteredPopupFrame));
        confirmationDialogComponent.E().r(() -> this.lambda$handleLeaveConfirmation$7(dimmedCenteredPopupFrame));
        dimmedCenteredPopupFrame.q(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), dimmedCenteredPopupFrame);
    }

    private void lambda$acceptInvite$8(GroupInviteStateResponsePacket groupInviteStateResponsePacket) {
        if (groupInviteStateResponsePacket.M() == GroupInviteStateStatus.SUCCESSFULLY_ACCEPTED) {
            Vape.INSTANCE.getOnlineManager().y().y(this.vQ);
        } else if (groupInviteStateResponsePacket.M() == GroupInviteStateStatus.GROUP_FULL) {
            OnlineFriendUiHelper.R(NotificationType.ERROR, "Party is full");
        } else if (groupInviteStateResponsePacket.M() == GroupInviteStateStatus.FAILED) {
            OnlineFriendUiHelper.R(NotificationType.ERROR, "Error accepting party invite");
        }
    }

    private void lambda$null$2() {
        this.v5 = false;
    }
}

