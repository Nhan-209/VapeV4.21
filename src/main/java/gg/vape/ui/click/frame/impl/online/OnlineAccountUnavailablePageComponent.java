package gg.vape.ui.click.frame.impl.online;

import gg.vape.manager.client.OnlineAccountState;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.ui.click.component.gui.UnderlinedTextLabel;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionSettingsPageComponent;
import java.awt.Color;

public class OnlineAccountUnavailablePageComponent
extends OnlineConnectionSettingsPageComponent {
    private final WrappingTextLabelComponent DX;
    private final UnderlinedTextLabel D2;


    public OnlineAccountUnavailablePageComponent() {
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        IconGlyphComponent iconGlyphComponent = new IconGlyphComponent("warning_48", 12.0f, 12.0f);
        this.h(new PaddedComponent(20.0, 0.0, 45.0, 45.0, iconGlyphComponent), new Object[0]);
        this.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        WrappingTextLabelComponent wrappingTextLabelComponent = new WrappingTextLabelComponent("Error establishing connection", 1.0);
        wrappingTextLabelComponent.l(true);
        wrappingTextLabelComponent.o(this.A() - 30.0);
        wrappingTextLabelComponent.Y(20.0);
        wrappingTextLabelComponent.T$src$V$1orl066(OnlineAccountUnavailablePageComponent.J.q);
        this.h(new PaddedComponent(0.0, 0.0, 15.0, 15.0, wrappingTextLabelComponent), new Object[0]);
        this.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        this.DX = new WrappingTextLabelComponent("Unknown", 0.8);
        this.DX.o(this.A() - 30.0);
        this.DX.Y(20.0);
        this.DX.T$src$V$1orl066(OnlineAccountUnavailablePageComponent.J.A);
        this.h(new PaddedComponent(0.0, 0.0, 15.0, 15.0, this.DX), new Object[0]);
        this.D2 = new UnderlinedTextLabel("Try again", (double)0.8f, OnlineAccountUnavailablePageComponent.J.A, new Color(255, 255, 255, 255));
        this.D2.r(OnlineAccountUnavailablePageComponent::lambda$new$0);
        this.D2.o(50.0);
        this.D2.Y(10.0);
        this.h(new PaddedComponent(40.0, 0.0, 26.0, 0.0, this.D2), new Object[0]);
    }

    private static void lambda$new$0() {
        OnlineConnectionManager.T.I();
    }

    @Override
    public void s() {
        if (OnlineConnectionManager.T.j().equals((Object)OnlineAccountState.BANNED)) {
            this.DX.G("You have been banned from Vape Online");
        } else if (OnlineConnectionManager.T.j().equals((Object)OnlineAccountState.REGISTRATION_OFFLINE)) {
            this.DX.G("Registration is currently offline");
        } else {
            this.DX.G("Unknown error");
        }
    }
}

