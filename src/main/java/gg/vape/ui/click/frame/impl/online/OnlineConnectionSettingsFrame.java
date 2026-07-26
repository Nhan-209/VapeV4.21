package gg.vape.ui.click.frame.impl.online;

import gg.vape.Vape;
import gg.vape.manager.client.OnlineAccountState;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.manager.client.OnlineConnectionState;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.SettingsSubpageFrame;
import gg.vape.ui.click.frame.impl.ThemeComponentGroupFactory;
import gg.vape.ui.click.frame.impl.online.OnlineAccountConnectedPageComponent;
import gg.vape.ui.click.frame.impl.online.OnlineAccountLinkCodePageComponent;
import gg.vape.ui.click.frame.impl.online.OnlineAccountSettingsPageComponent;
import gg.vape.ui.click.frame.impl.online.OnlineAccountUnavailablePageComponent;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionBackdropFrame;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionBackdropMouseListener;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionConnectingPageComponent;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionRetryPageComponent;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionSettingsPageComponent;
import gg.vape.ui.click.layout.ComponentLayout;

public class OnlineConnectionSettingsFrame
extends SettingsSubpageFrame {
    private FlowLayoutComponent xN = new FlowLayoutComponent(104.0);
    private boolean x0 = false;
    private static OnlineConnectionBackdropFrame xp;
    private final OnlineConnectionRetryPageComponent xq;
    private final OnlineAccountSettingsPageComponent xS = new OnlineAccountSettingsPageComponent();
    private final OnlineAccountConnectedPageComponent x2 = new OnlineAccountConnectedPageComponent();
    private final OnlineAccountUnavailablePageComponent xb;
    private boolean xy = false;
    private final OnlineConnectionConnectingPageComponent xE = new OnlineConnectionConnectingPageComponent();
    private final OnlineAccountLinkCodePageComponent xK;
    public static OnlineConnectionSettingsFrame x4;
    private OnlineConnectionState xx = null;

    public void F(OnlineAccountState onlineAccountState, OnlineConnectionState onlineConnectionState) {
        if (onlineConnectionState == OnlineConnectionState.OFFLINE) {
            this.x(onlineConnectionState);
        }
    }

    public void x(OnlineConnectionState onlineConnectionState) {
        OnlineConnectionSettingsPageComponent onlineConnectionSettingsPageComponent = null;
        if (onlineConnectionState == OnlineConnectionState.ONLINE) {
            onlineConnectionSettingsPageComponent = this.xS;
            if (this.x0) {
                this.d$src$V$bbt7r8();
            }
        }
        if (onlineConnectionState == OnlineConnectionState.OFFLINE) {
            onlineConnectionSettingsPageComponent = OnlineConnectionManager.T.b() == -1L ? (OnlineConnectionManager.T.j() == OnlineAccountState.REGISTERED ? this.x2 : (OnlineConnectionManager.T.j() == OnlineAccountState.UNREGISTERED ? this.xK : this.xb)) : this.xq;
        }
        if (onlineConnectionState == OnlineConnectionState.CONNECTING) {
            onlineConnectionSettingsPageComponent = OnlineConnectionManager.T.b() == -1L ? this.xE : this.xq;
        }
        if (onlineConnectionSettingsPageComponent != null) {
            ((OnlineConnectionSettingsPageComponent)onlineConnectionSettingsPageComponent).s();
            this.xN.S();
            this.xN.h(onlineConnectionSettingsPageComponent, new Object[0]);
        }
    }

    static {
        x4 = new OnlineConnectionSettingsFrame();
        xp = new OnlineConnectionBackdropFrame();
    }

    @Override
    protected void T(double d, double d2) {
    }

    @Override
    public void H() {
        super.H();
        ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().v(xp);
        ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().v(this);
        if (this.H$src$Lgg_vape_ui_click_frame_CenteredPopupFrame_$1qmombx() != null) {
            ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().R(this, this.H$src$Lgg_vape_ui_click_frame_CenteredPopupFrame_$1qmombx());
        }
        this.H(true);
        this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().D$src$V$1njh5lz();
    }

    public void e(boolean bl) {
        this.x0 = bl;
        this.xy = true;
        ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().q(xp);
        ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().q(this);
        ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().v(xp);
        ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().v(this);
    }

    public OnlineConnectionSettingsFrame() {
        super("vape_online", "Vape Online", false, false);
        this.xq = new OnlineConnectionRetryPageComponent();
        this.xK = new OnlineAccountLinkCodePageComponent();
        this.xb = new OnlineAccountUnavailablePageComponent();
        try {
            this.o(104.0);
            this.Y(160.0);
            this.xN.d(false);
            ComponentLayout componentLayout = this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij();
            componentLayout.t(false);
            componentLayout.M(false);
            componentLayout.U(false);
            componentLayout.I(false);
            componentLayout.u(false);
            this.g(true);
            this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().V(true);
            this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().g(104.0);
            this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().x(0.75f);
            this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().w$src$Lgg_vape_ui_click_component_SquareIconButtonComp$1a3t2u0().r(this::d$src$V$bbt7r8);
            GuiComponent[] guiComponentArray = ThemeComponentGroupFactory.k(J);
            this.n(guiComponentArray);
            this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().P(true);
            this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().o(104.0);
            this.Z(new OnlineConnectionBackdropMouseListener(this));
            this.h(this.xN, new Object[0]);
            this.x(OnlineConnectionState.OFFLINE);
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    public void d$src$V$bbt7r8() {
        this.xy = false;
        if (this.H$src$Lgg_vape_ui_click_frame_CenteredPopupFrame_$1qmombx() != null) {
            this.p();
        }
        ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().m(xp);
        ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().m(this);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

