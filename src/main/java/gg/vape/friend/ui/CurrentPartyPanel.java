package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.CurrentPartyLeaveDeleteClickHandler;
import gg.vape.friend.ui.CurrentPartyNameOpenDetailsMouseListener;
import gg.vape.friend.ui.CurrentPartyPanelOpenDetailsMouseListener;
import gg.vape.friend.ui.PartyDetailsAndChatPanel;
import gg.vape.friend.ui.PartyDetailsPopupCloseClickHandler;
import gg.vape.module.none.ClientSettings;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.GroupDeleteResponsePacket;
import gg.vape.protocol.packet.GroupLeaveResponsePacket;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.AnimatedPanelComponent;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.frame.DimmedCenteredPopupFrame;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;
import java.awt.Point;
import org.jetbrains.annotations.Nullable;

public class CurrentPartyPanel
extends AnimatedPanelComponent {
    private PartyDetailsAndChatPanel D8;
    private final TextButton Dq;
    private String D_;
    private final ColorAnimation D9;
    private final SpacerComponent DT;
    private static GuiComponent[] Dp;
    private PartyState Dd;
    private PopupFrame D1;
    private boolean Ds;
    private TruncatedTextComponent DU;
    private final PanelComponent Dg = new PanelComponent(23.0, 14.0);

    private void lambda$null$1() {
        this.Ds = false;
    }

    private void lambda$leaveAction$5() {
        this.Ds = false;
    }

    private static void lambda$leaveAction$4(GroupLeaveResponsePacket groupLeaveResponsePacket) {
    }

    public static PartyDetailsAndChatPanel U(CurrentPartyPanel currentPartyPanel, PartyDetailsAndChatPanel partyDetailsAndChatPanel) {
        currentPartyPanel.D8 = partyDetailsAndChatPanel;
        return currentPartyPanel.D8;
    }

    public static void W(CurrentPartyPanel currentPartyPanel, Point point, MouseClickButton mouseClickButton) {
        currentPartyPanel.q(point, mouseClickButton);
    }

    public static PopupFrame n(CurrentPartyPanel currentPartyPanel) {
        return currentPartyPanel.D1;
    }

    private void lambda$leaveAction$2(PopupFrame popupFrame) {
        ClientSettings.K(popupFrame);
        ZeusConnectionManager.T().u().l(CurrentPartyPanel::lambda$null$0, this::lambda$null$1);
    }

    @Override
    public void u() {
        if (Vape.INSTANCE.getOnlineManager() == null) {
            return;
        }
        this.D9.u(this.w$src$Z$e457mb());
        this.Dd = Vape.INSTANCE.getOnlineManager().y().j();
        this.Z(this.Dd != null);
        if (this.Dd != null) {
            if (this.Dd.r().equals(Vape.INSTANCE.getOnlineManager().r())) {
                this.Dq.d("DISBAND");
                this.Dq.w("Disband party");
                this.Dq.q(23.0);
                this.Dq.o(23.0);
                this.w("Disband party");
                this.D_ = "My party";
            } else {
                this.Dq.d("LEAVE");
                this.Dq.w("Leave party");
                this.w("Leave party");
                this.Dq.q(18.0);
                this.Dq.o(18.0);
                this.D_ = this.Dd.r().C() + "'s party";
            }
            this.DU.q(this.A() - 18.0 - this.Dq.A() - 4.0);
            this.DU.D(this.A() - 18.0 - this.Dq.A() - 6.0);
            this.DU.O(this.D_);
        } else {
            this.w("Open party");
            if (this.D1 != null) {
                ClientSettings.K(this.D1);
                this.D8 = null;
                this.D1 = null;
            }
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static PopupFrame f(CurrentPartyPanel currentPartyPanel, PopupFrame popupFrame) {
        currentPartyPanel.D1 = popupFrame;
        return currentPartyPanel.D1;
    }

    public static GuiComponent[] V$src$ALgg_vape_ui_click_component_GuiComponent_$uexm5r() {
        return Dp;
    }

    private void q(Point point, MouseClickButton mouseClickButton) {
        this.D8 = new PartyDetailsAndChatPanel(this.Dd);
        this.D1 = ClientSettings.g(this, this.D8, PopupFrame.class);
        this.D8.e$src$Lgg_vape_ui_click_component_IconButtonComponent_$pbqe5z().r(new PartyDetailsPopupCloseClickHandler(this));
    }

    private static void lambda$null$0(GroupDeleteResponsePacket groupDeleteResponsePacket) {
    }

    public static void x(GuiComponent[] guiComponentArray) {
        Dp = guiComponentArray;
    }

    private void lambda$leaveAction$3(PopupFrame popupFrame) {
        ClientSettings.K(popupFrame);
        this.Ds = false;
    }

    public CurrentPartyPanel() {
        super(100.0, 16.0);
        this.DT = new SpacerComponent(18.0, 16.0);
        this.Dq = new TextButton("LEAVE", 0.6, CurrentPartyPanel.J.d, CurrentPartyPanel.J.c, 18.0, 8.0);
        this.D9 = new ColorAnimation(0.15, new Color(150, 150, 150, 0), new Color(150, 150, 150, 20));
        this.Ds = false;
        this.d(false);
        this.Dg.d(false);
        this.Dg.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.DU = new TruncatedTextComponent("", "...", 48.0, 0.8, CurrentPartyPanel.J.A, true);
        this.Dq.r(new CurrentPartyLeaveDeleteClickHandler(this));
        this.DU.j(new CurrentPartyNameOpenDetailsMouseListener(this));
        this.j(new CurrentPartyPanelOpenDetailsMouseListener(this));
        this.w("Open party");
        this.Dq.F(false);
        this.Dq.h(Color.WHITE);
        this.Dg.H(this.Dq);
        this.H(this.DT, this.DU, this.Dg);
    }

    @Override
    public void H() {
        if (this.D1 != null) {
            Frame frame = this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa();
            this.D1.K(this.G$src$D$1b2f02a());
            this.D1.S(frame.n() + frame.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L());
            double d = frame.L() - frame.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L() - 45.0;
            this.D8.e$src$Lgg_vape_friend_ui_OnlineChatPanel_$1fym7va().z().u(d);
            this.D8.e$src$Lgg_vape_friend_ui_OnlineChatPanel_$1fym7va().z().t(d);
            this.D8.e$src$Lgg_vape_friend_ui_OnlineChatPanel_$1fym7va().z().l$src$V$1mibm4x();
            this.D1.l$src$V$1mibm4x();
        }
        this.DU.S(this.n() + 5.0);
    }

    public static void M(CurrentPartyPanel currentPartyPanel) {
        currentPartyPanel.e$src$V$1pzycwa();
    }

    @Override
    public void c() {
        if (this.Dd == null) {
            return;
        }
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L() - 2.0, CurrentPartyPanel.J.m.brighter());
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L() - 2.0, this.D9.getInterpolatedColor());
        float f = (float)(this.G$src$D$1b2f02a() + 6.0);
        float f2 = (float)(this.n() + 4.0);
        ImageRenderer.E(CurrentPartyPanel.J.B, f, f2, "party1@2x", 7.0f, 6.3f, false);
        ImageRenderer.E(CurrentPartyPanel.J.B, (float)(this.G$src$D$1b2f02a() + this.A() - 22.0), (float)this.n() - 0.5f, "join party texture@2x", 14.5f, 14.5f, false);
        super.c();
    }

    static {
        CurrentPartyPanel.x(null);
    }

    @Nullable
    public PartyDetailsAndChatPanel v$src$Lgg_vape_friend_ui_PartyDetailsAndChatPanel_$1pxu2wh() {
        return this.D8;
    }

    private void e$src$V$1pzycwa() {
        if (this.Ds) {
            return;
        }
        this.Ds = true;
        if (this.Dd != null) {
            if (this.Dd.r().equals(Vape.INSTANCE.getOnlineManager().r())) {
                ConfirmationDialogComponent confirmationDialogComponent = new ConfirmationDialogComponent("Are you sure you want to disband the party?", "DISBAND", "disband confirm@2x");
                DimmedCenteredPopupFrame dimmedCenteredPopupFrame = ClientSettings.g(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), confirmationDialogComponent, DimmedCenteredPopupFrame.class);
                confirmationDialogComponent.T$src$Lgg_vape_ui_click_component_gui_TextButton_$17m2d4e().r(() -> this.lambda$leaveAction$2(dimmedCenteredPopupFrame));
                confirmationDialogComponent.E().r(() -> this.lambda$leaveAction$3(dimmedCenteredPopupFrame));
                dimmedCenteredPopupFrame.q(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), dimmedCenteredPopupFrame);
            } else {
                ZeusConnectionManager.T().u().u(CurrentPartyPanel::lambda$leaveAction$4, this::lambda$leaveAction$5);
            }
        }
    }
}

