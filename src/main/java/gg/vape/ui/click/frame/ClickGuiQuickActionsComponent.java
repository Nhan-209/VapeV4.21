package gg.vape.ui.click.frame;

import gg.vape.friend.ui.OnlineCombatStatsSettingsFrame;
import gg.vape.friend.ui.OnlinePlayerPreviewSettingsFrame;
import gg.vape.friend.ui.OnlineRadarSettingsFrame;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.none.TextGuiSettingsFrame;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.frame.ClickGuiQuickActionActiveFrameIndicator;
import gg.vape.ui.click.frame.ClickGuiQuickActionsFrameButton;
import gg.vape.ui.click.frame.ClickGuiQuickActionsFrameToggleClickHandler;
import gg.vape.ui.click.frame.ClickGuiQuickActionsOnlineSettingsButton;
import gg.vape.ui.click.frame.ClickGuiQuickActionsVisibleModulesClickHandler;
import gg.vape.ui.click.frame.impl.VisibleModuleListFrame;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionSettingsFrame;
import gg.vape.ui.click.frame.impl.target.TargetInfoSettingsFrame;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.util.ArrayList;
import java.util.List;

public class ClickGuiQuickActionsComponent
extends GuiComponent {
    private IconButtonComponent O;
    private List<ClickGuiQuickActionActiveFrameIndicator> v;
    private IconButtonComponent Q;
    private IconButtonComponent i = new IconButtonComponent("newfavorites", 0.8);

    @Override
    public double C() {
        return 18.0;
    }

    @Override
    public double x() {
        return 110.0;
    }

    private static void lambda$new$0() {
        OnlineConnectionSettingsFrame.x4.e(false);
    }

    public ClickGuiQuickActionsComponent() {
        this.v = new ArrayList<ClickGuiQuickActionActiveFrameIndicator>();
        this.T(ClickGuiQuickActionsComponent.J.i);
        this.O = new ClickGuiQuickActionsOnlineSettingsButton(this, "vape_online", 0.75);
        this.Q = new ClickGuiQuickActionsFrameButton(this, "newoverlays", 0.6);
        this.Q.r(new ClickGuiQuickActionsFrameToggleClickHandler(this));
        this.Q.w("Open overlays menu");
        this.O.r(ClickGuiQuickActionsComponent::lambda$new$0);
        this.i.r(new ClickGuiQuickActionsVisibleModulesClickHandler(this));
        this.i.w("Favorites");
        this.H(this.O, this.i, this.Q);
        this.v.add(new ClickGuiQuickActionActiveFrameIndicator("newtextgui", ClientSettings.g(TextGuiSettingsFrame.class), 5));
        this.v.add(new ClickGuiQuickActionActiveFrameIndicator("newrearview", ClientSettings.g(OnlinePlayerPreviewSettingsFrame.class), 6));
        this.v.add(new ClickGuiQuickActionActiveFrameIndicator("newduelinfo", ClientSettings.g(OnlineCombatStatsSettingsFrame.class), 6));
        this.v.add(new ClickGuiQuickActionActiveFrameIndicator("newtargetinfo", ClientSettings.g(TargetInfoSettingsFrame.class), 7));
        this.v.add(new ClickGuiQuickActionActiveFrameIndicator("newradar", ClientSettings.g(OnlineRadarSettingsFrame.class), 7));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void H() {
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.d());
        this.O.K(this.G$src$D$1b2f02a() + 3.0);
        this.O.S(this.n());
        this.O.Y(this.L());
        this.i.K(this.G$src$D$1b2f02a() + this.A() - 32.0);
        this.i.S(this.n());
        this.i.Y(this.L());
        this.i.G(ClientSettings.g(VisibleModuleListFrame.class).V$src$Z$1xhop3l() ? (this.i.w$src$Z$e457mb() ? ClickGuiQuickActionsComponent.J.Y : ClickGuiQuickActionsComponent.J.I) : null);
        this.Q.K(this.G$src$D$1b2f02a() + this.A() - 18.0);
        this.Q.S(this.n());
        this.Q.Y(this.L());
        boolean bl = false;
        double d = this.n() + this.L() / 2.0;
        double d2 = (float)(this.G$src$D$1b2f02a() + this.A() - 17.5 - 8.0);
        for (ClickGuiQuickActionActiveFrameIndicator clickGuiQuickActionActiveFrameIndicator : this.v) {
            if (clickGuiQuickActionActiveFrameIndicator.F == null || !clickGuiQuickActionActiveFrameIndicator.F.V$src$Z$1xhop3l()) continue;
            GuiRenderPrimitives.F(clickGuiQuickActionActiveFrameIndicator.y, d2, d, (double)clickGuiQuickActionActiveFrameIndicator.W, clickGuiQuickActionActiveFrameIndicator.W, ClickGuiQuickActionsComponent.J.W);
            bl = true;
            d2 -= (double)(5.0f + (float)clickGuiQuickActionActiveFrameIndicator.W);
        }
        this.Q.G(bl ? (this.Q.w$src$Z$e457mb() ? ColorUtil.N(J.z(), 10.0) : J.z()) : null);
    }

    public IconButtonComponent b$src$Lgg_vape_ui_click_component_IconButtonComponent_$1sg98rj() {
        return this.Q;
    }

    @Override
    public void u() {
    }

    @Override
    public void I() {
    }

    @Override
    public void F() {
    }
}

