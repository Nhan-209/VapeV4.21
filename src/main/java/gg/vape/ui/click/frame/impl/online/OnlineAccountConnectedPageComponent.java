package gg.vape.ui.click.frame.impl.online;

import gg.vape.Vape;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionSettingsPageComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileUserAvatarComponent;
import java.awt.Color;

public class OnlineAccountConnectedPageComponent
extends OnlineConnectionSettingsPageComponent {
    private final SimpleTextLabelComponent Gj;
    private TextButton Ge = new TextButton("Login", Color.RED);
    private PanelComponent Gc = new PanelComponent(104.0, 65.0);
    private PanelComponent Gz = new PanelComponent(104.0, 20.0);
    private PublicProfileUserAvatarComponent Gm;

    @Override
    public void s() {
        this.Gm.W(Vape.INSTANCE.getAccountInfo().i());
    }

    public OnlineAccountConnectedPageComponent() {
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.Gc.d(false);
        this.Gc.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.Gm = new PublicProfileUserAvatarComponent(null, 16.0, 16.0);
        this.Gm.q(true);
        this.Gj = new WrappingTextLabelComponent("Welcome back,\n" + Vape.INSTANCE.getAccountInfo().h(), 1.0, OnlineAccountConnectedPageComponent.J.A);
        this.Gj.o(104.0);
        this.Gj.Y(12.0);
        this.Gj.l(true);
        this.Gc.h(new PaddedComponent(6.0, 6.0, this.A() / 2.0 - this.Gm.A() / 2.0, 0.0, this.Gm), new Object[0]);
        this.Gc.h(new PaddedComponent(2.0, 0.0, this.Gj), new Object[0]);
        this.Gc.h(new SpacerComponent(0.0, 4.0), new Object[0]);
        this.Gz.d(false);
        this.Ge = new TextButton("Login", OnlineAccountConnectedPageComponent.J.B, OnlineAccountConnectedPageComponent.J.O);
        this.Ge.o(50.0);
        this.Ge.Y(14.0);
        this.Ge.F(false);
        this.Ge.h(OnlineAccountConnectedPageComponent.J.A);
        this.Ge.c(true);
        this.Gz.h(new SpacerComponent(27.0, 0.0), new Object[0]);
        this.Gz.h(this.Ge, new Object[0]);
        this.H(new SpacerComponent(0.0, 10.0), this.Gc, this.Gz);
        this.Ge.r(OnlineConnectionManager.T::I);
    }
}

