package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineStatus;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.OnlineFriendCardToggleDetailsMouseListener;
import gg.vape.friend.ui.OnlineFriendCardVisibleChildrenHeightComponent;
import gg.vape.friend.ui.OnlineFriendDetailsPanel;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.AnimatedPanelComponent;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.MarqueeTextRendererComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.notification.NotificationMessage;
import gg.vape.ui.notification.NotificationType;
import gg.vape.utils.ClipboardUtil;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RemoteImageTextureManager;
import java.awt.Color;

public class OnlineFriendCard
extends PanelComponent {
    private SpacerComponent uG;
    private final AnimatedPanelComponent ue = new AnimatedPanelComponent(99.0, 24.0);
    private final OnlineFriend um;
    private boolean uF = false;
    private final OnlineFriendDetailsPanel uk;
    private final DoubleAnimation uv;
    private TruncatedTextComponent uI;
    private final DoubleAnimation uq;
    private FlowLayoutComponent uH;
    private IconButtonComponent uW;
    private final PanelComponent ug = new PanelComponent(20.0, 15.0);
    private SpacerComponent uT;
    private boolean u2;
    private SpacerComponent uQ;
    private IconButtonComponent uL;
    private IconButtonComponent uC;
    private FlowLayoutComponent ul;
    private MarqueeTextRendererComponent marqueeTextRenderer = new MarqueeTextRendererComponent(this);

    @Override
    public void u() {
        this.uv.u(this.w$src$Z$e457mb() || this.uF);
        if (this.uF) {
            this.i$src$Lgg_vape_friend_OnlineFriend_$zehrml().J(false);
        }
    }

    public OnlineFriendCard(OnlineFriend onlineFriend) {
        super(99.0, 24.0);
        this.uq = new DoubleAnimation(0.15, 0.0, 180.0);
        this.uv = new DoubleAnimation(0.15, 0.0, 180.0);
        this.uW = new SquareIconButtonComponent("newclose", 1.0, new Color(0, 0, 0, 0), OnlineFriendCard.J.l, 10.0, 10.0);
        this.uL = new SquareIconButtonComponent("chat@2x", 0.5, new Color(0, 0, 0, 0), OnlineFriendCard.J.l, 8.0, 8.0);
        this.uC = new SquareIconButtonComponent("party hover@2x", 0.5, new Color(0, 0, 0, 0), OnlineFriendCard.J.l, 8.0, 8.0);
        this.uT = new SpacerComponent(0.0, 0.0);
        this.uQ = new SpacerComponent(10.0, 0.0);
        this.uG = new SpacerComponent(6.0, 2.0);
        this.uH = new FlowLayoutComponent(20.0);
        this.ul = new OnlineFriendCardVisibleChildrenHeightComponent(this, 20.0);
        this.um = onlineFriend;
        this.uk = new OnlineFriendDetailsPanel(this, this.um);
        this.uI = new TruncatedTextComponent(onlineFriend.C(), "...", "Right click to copy username to clipboard", 75.0, 0.8, OnlineFriendCard.J.A, false, false);
        this.ue.addMouseListener(new OnlineFriendCardToggleDetailsMouseListener(this));
        this.ue.setShowDisabledOverlay(false);
        this.ug.setShowDisabledOverlay(false);
        this.ug.h(this.uH, new Object[0]);
        this.ug.h(this.ul, new Object[0]);
        this.ug.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.uH.addChildren(this.uG, this.uW);
        this.ul.addChildren(this.uQ, this.uC, this.uT, this.uL);
        this.uC.w("Invite to party");
        this.uL.w("Open chat");
        this.uH.setVisible(false);
        this.ue.addChildren(new SpacerComponent(18.0, 0.0), this.uI);
        this.ue.h(this.ug, "alignright");
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.setShowDisabledOverlay(false);
        this.uk.setVisible(false);
        this.addChildren(this.ue, this.uk);
        this.uH.setShowDisabledOverlay(false);
        this.ul.setShowDisabledOverlay(false);
        this.ul.setVisible(false);
    }

    @Override
    public void z(boolean bl) {
    }

    static void a(OnlineFriendCard onlineFriendCard, MouseClickButton mouseClickButton) {
        onlineFriendCard.X(mouseClickButton);
    }

    public OnlineFriend i$src$Lgg_vape_friend_OnlineFriend_$zehrml() {
        return this.um;
    }

    private String W() {
        String string;
        OnlineStatus onlineStatus = this.um.F();
        if (onlineStatus == OnlineStatus.OFFLINE) {
            return onlineStatus.f();
        }
        String string2 = "";
        String string3 = null;
        String string4 = "";
        if (this.um.u()) {
            string3 = this.um.I();
        }
        if (string3 != null) {
            string2 = string3;
            string4 = " - ";
        }
        if ((string = this.um.v()) != null) {
            string2 = string2 + string4 + string;
        } else if (string3 == null) {
            string2 = string2 + string4 + onlineStatus.f();
        }
        return string2;
    }

    private void X(MouseClickButton mouseClickButton) {
        if (mouseClickButton.equals((Object)MouseClickButton.RIGHT_CLICK)) {
            ClipboardUtil.setText(this.i$src$Lgg_vape_friend_OnlineFriend_$zehrml().C());
            OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.SUCCESS, "Copied " + this.i$src$Lgg_vape_friend_OnlineFriend_$zehrml().C() + " to clipboard"));
            return;
        }
        if (this.uF) {
            return;
        }
        this.u2 = !this.u2;
        this.uk.setVisible(this.u2);
        if (this.u2) {
            this.setExplicitHeight(this.ue.L() + this.uk.L());
        } else {
            this.setExplicitHeight(24.0);
        }
    }

    public IconButtonComponent j$src$Lgg_vape_ui_click_component_IconButtonComponent_$1ooa9pe() {
        return this.uL;
    }

    static FlowLayoutComponent B(OnlineFriendCard onlineFriendCard) {
        return onlineFriendCard.ul;
    }

    @Override
    public void c() {
        boolean bl;
        if (this.uF) {
            GuiRenderPrimitives.C(this.G$src$D$1b2f02a() - 3.0, this.n() - 3.0, this.A() + 6.0, this.L() + 6.0, OnlineFriendCard.J.i);
        }
        if (bl = this.um.F().equals((Object)OnlineStatus.ONLINE)) {
            int n = Math.max(this.uq.getInterpolatedValue().intValue(), this.uv.getInterpolatedValue().intValue());
            double d = this.L() - 2.5;
            double d2 = this.A();
            double d3 = this.n();
            double d4 = this.G$src$D$1b2f02a();
            GuiRenderPrimitives.d(d4, d3, d2, d, OnlineFriendCard.J.m);
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L() - 2.5, new Color(40, 40, 40, n), 3.0f, 1.0f, 1.0f);
            GlImageTexture glImageTexture = RemoteImageTextureManager.getInstance().getTexture(this.um.u() && !this.um.F().equals((Object)OnlineStatus.OFFLINE) ? this.um.I() : "Steve", 32);
            if (glImageTexture != null) {
                GuiRenderPrimitives.m((float)this.G$src$D$1b2f02a() + 4.0f, (float)this.n() + 5.0f, 11.0f, 1.5f, 1.0f, this.um.F().P());
                float f = 1.0f;
                float f2 = 9.0f;
                float f3 = (float)this.n() + 6.0f;
                float f4 = (float)this.G$src$D$1b2f02a() + 5.0f;
                GuiRenderPrimitives.u(f4, f3, f2, f, Color.WHITE, glImageTexture);
            }
            this.uI.setText(this.um.C());
            this.uI.setMaxWidth(79.0 - this.ul.getVisibleChildrenWidth() - (this.uH.V$src$Z$1xhop3l() ? this.uH.getVisibleChildrenWidth() - this.uG.A() + 3.0 : 0.0));
            TruncatedTextComponent truncatedTextComponent = this.uI;
            truncatedTextComponent.setTextColor(OnlineFriendCard.J.A);
            this.uI.S(this.n() + 4.0);
            double d5 = 0.7;
            double d6 = this.A() - 18.0 - 4.0;
            double d7 = this.n() + 12.0;
            double d8 = this.G$src$D$1b2f02a() + 18.0;
            String string = this.W();
            MarqueeTextRendererComponent renderer = this.marqueeTextRenderer;
            renderer.render(string, d8, d7, d6, d5, OnlineFriendCard.J.h);
            super.c();
            this.d$src$V$ttzgw7();
            if (this.um.r()) {
                GuiRenderPrimitives.V(this.uL.G$src$D$1b2f02a() + 5.5, this.uL.n() + 0.5, 2.0, 1.0, OnlineFriendCard.J.d);
            }
            return;
        }
        int n = Math.max(this.uq.getInterpolatedValue().intValue(), this.uv.getInterpolatedValue().intValue());
        double d = this.L() - 2.5;
        double d9 = this.A();
        double d10 = this.n();
        double d11 = this.G$src$D$1b2f02a();
        GuiRenderPrimitives.d(d11, d10, d9, d, new Color(27, 27, 27));
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L() - 2.5, new Color(40, 40, 40, n), 3.0f, 1.0f, 1.0f);
        GlImageTexture glImageTexture = RemoteImageTextureManager.getInstance().getTexture(this.um.u() && !this.um.F().equals((Object)OnlineStatus.OFFLINE) ? this.um.I() : "Steve", 32);
        if (glImageTexture != null) {
            GuiRenderPrimitives.m((float)this.G$src$D$1b2f02a() + 4.0f, (float)this.n() + 5.0f, 11.0f, 1.5f, 1.0f, this.um.F().P());
            float f = 1.0f;
            float f5 = 9.0f;
            float f6 = (float)this.n() + 6.0f;
            float f7 = (float)this.G$src$D$1b2f02a() + 5.0f;
            GuiRenderPrimitives.u(f7, f6, f5, f, new Color(255, 255, 255, 150), glImageTexture);
        }
        this.uI.setText(this.um.C());
        this.uI.setMaxWidth(79.0 - this.ul.getVisibleChildrenWidth() - (this.uH.V$src$Z$1xhop3l() ? this.uH.getVisibleChildrenWidth() - this.uG.A() + 3.0 : 0.0));
        TruncatedTextComponent truncatedTextComponent = this.uI;
        truncatedTextComponent.setTextColor(new Color(110, 110, 110));
        this.uI.S(this.n() + 4.0);
        double d12 = 0.7;
        double d13 = this.A() - 18.0 - 4.0;
        double d14 = this.n() + 12.0;
        double d15 = this.G$src$D$1b2f02a() + 18.0;
        String string = this.W();
        MarqueeTextRendererComponent renderer = this.marqueeTextRenderer;
        renderer.render(string, d15, d14, d13, d12, new Color(68, 68, 68));
        super.c();
        this.d$src$V$ttzgw7();
        if (this.um.r()) {
            GuiRenderPrimitives.V(this.uL.G$src$D$1b2f02a() + 5.5, this.uL.n() + 0.5, 2.0, 1.0, OnlineFriendCard.J.d);
        }
    }

    public OnlineFriendDetailsPanel d$src$Lgg_vape_friend_ui_OnlineFriendDetailsPanel_$1i561zb() {
        return this.uk;
    }

    public IconButtonComponent b$src$Lgg_vape_ui_click_component_IconButtonComponent_$txugmi() {
        return this.uW;
    }

    public void x(boolean bl) {
        this.uF = bl;
        this.d$src$V$ttzgw7();
    }

    public IconButtonComponent A$src$Lgg_vape_ui_click_component_IconButtonComponent_$10cdcvf() {
        return this.uC;
    }


    public void d$src$V$ttzgw7() {
        if (this.uF) {
            this.d$src$Lgg_vape_friend_ui_OnlineFriendDetailsPanel_$1i561zb().N$src$V$16a20lf();
            this.uH.setVisible(true);
            this.ul.setVisible(false);
            this.uk.setVisible(true);
        } else {
            PartyState partyState;
            boolean bl;
            this.d$src$Lgg_vape_friend_ui_OnlineFriendDetailsPanel_$1i561zb().K$src$V$168emtc();
            this.uH.setVisible(false);
            this.ul.setVisible(true);
            boolean bl2 = Vape.INSTANCE.getOnlineManager().y().j() != null;
            boolean bl3 = bl2 && Vape.INSTANCE.getOnlineManager().y().j().c().contains(this.um);
            boolean bl4 = bl = bl2 && !bl3 && this.um.F().equals((Object)OnlineStatus.ONLINE);
            if (bl2 && (partyState = Vape.INSTANCE.getOnlineManager().y().j()).S().contains(this.um)) {
                bl = false;
            }
            boolean bl5 = this.um.B();
            this.uL.setVisible(bl5);
            this.uC.setVisible(bl);
            boolean bl6 = bl5 ^ bl;
            this.uQ.setVisible(bl6);
            this.uT.setVisible(!bl6);
            this.uk.setVisible(this.u2);
        }
    }

    @Override
    public double C() {
        return this.uF ? 24.0 + this.uk.L() : super.C();
    }
}

