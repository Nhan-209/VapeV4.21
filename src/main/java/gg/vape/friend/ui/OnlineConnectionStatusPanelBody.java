package gg.vape.friend.ui;

import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionSettingsFrame;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class OnlineConnectionStatusPanelBody
extends PanelComponent {
    private static boolean dw;


    private static void lambda$new$0() {
        OnlineConnectionSettingsFrame.x4.e(true);
    }

    public static void v(boolean bl) {
        dw = bl;
    }

    public static boolean N$src$Z$hco3ln() {
        return dw;
    }

    public static boolean l$src$Z$ht5xeh() {
        boolean bl = OnlineConnectionStatusPanelBody.N$src$Z$hco3ln();
        return true;
    }

    static {
        OnlineConnectionStatusPanelBody.v(false);
    }

    @Override
    public void c() {
        super.c();
        GuiRenderPrimitives.V((float)((double)((float)this.G$src$D$1b2f02a()) + this.A() / 2.0 - 10.0), (float)(this.n() - 2.0), 20.0, 1.0, OnlineConnectionStatusPanelBody.J.l);
        ImageRenderer.drawResWithShadow(Color.WHITE, (float)((double)((float)this.G$src$D$1b2f02a()) + this.A() / 2.0 - 8.0), (float)this.n(), "avatar", 0.5f, false);
        this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.9).W("Sign in required", this.G$src$D$1b2f02a() + this.A() / 2.0, this.n() + this.L() - 35.0, Color.WHITE);
    }

    public OnlineConnectionStatusPanelBody() {
        super(64.0, 60.0);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        TextButton textButton = new TextButton("SIGN IN", 0.7, OnlineConnectionStatusPanelBody.J.B, OnlineConnectionStatusPanelBody.J.O, null, 2.0f, 0.0f, 50.0, 14.0);
        textButton.F(false);
        textButton.h(Color.WHITE);
        textButton.c(true);
        this.H(new SpacerComponent(this.A(), this.L() - textButton.L()), new PaddedComponent(7.0, 0.0, textButton));
        textButton.r(OnlineConnectionStatusPanelBody::lambda$new$0);
    }
}

