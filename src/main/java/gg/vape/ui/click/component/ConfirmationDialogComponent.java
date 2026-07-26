package gg.vape.ui.click.component;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.frame.DimmedCenteredPopupFrame;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;

public class ConfirmationDialogComponent
extends GuiComponent {
    private IconGlyphComponent Q;
    private final WrappingTextLabelComponent a;
    private double I = 80.0;
    private final IconButtonComponent v = new SquareIconButtonComponent("newclose", 1.0, new Color(255, 255, 255, 0), new Color(255, 255, 255, 25), 10.0, 10.0);
    private Color o;
    private final boolean i;
    private final TextButton b;
    private double O = 0.0;
    private boolean R;
    private final TextButton G;

    public void c(double d) {
        this.I = d;
    }

    public TextButton T$src$Lgg_vape_ui_click_component_gui_TextButton_$17m2d4e() {
        return this.G;
    }

    public static CompletableFuture<Void> x(Frame frame, String string, String string2, String string3, Runnable runnable, double d, @Nullable String string4, @Nullable Runnable runnable2) {
        CompletableFuture<Void> completableFuture = new CompletableFuture<Void>();
        ConfirmationDialogComponent confirmationDialogComponent = new ConfirmationDialogComponent(string, string2, string3);
        if (d != -1.0) {
            confirmationDialogComponent.c(d);
        }
        if (string4 != null) {
            confirmationDialogComponent.b.Z(true);
            confirmationDialogComponent.b.d(string4);
            confirmationDialogComponent.b.h(ConfirmationDialogComponent.J.Z);
            confirmationDialogComponent.b.G(Color.WHITE);
            confirmationDialogComponent.b.c(true);
            confirmationDialogComponent.b.p(true);
            confirmationDialogComponent.G.G(ConfirmationDialogComponent.J.B, ConfirmationDialogComponent.J.O);
        }
        DimmedCenteredPopupFrame dimmedCenteredPopupFrame = ClientSettings.g(frame, confirmationDialogComponent, DimmedCenteredPopupFrame.class);
        confirmationDialogComponent.T$src$Lgg_vape_ui_click_component_gui_TextButton_$17m2d4e().r(() -> ConfirmationDialogComponent.lambda$createStandard$0(dimmedCenteredPopupFrame, runnable, completableFuture));
        confirmationDialogComponent.E().r(() -> ConfirmationDialogComponent.lambda$createStandard$1(dimmedCenteredPopupFrame, completableFuture));
        confirmationDialogComponent.u$src$Lgg_vape_ui_click_component_gui_TextButton_$1ht3xvz().r(() -> ConfirmationDialogComponent.lambda$createStandard$2(dimmedCenteredPopupFrame, runnable2, completableFuture));
        dimmedCenteredPopupFrame.q(frame, dimmedCenteredPopupFrame);
        return completableFuture;
    }

    private static void lambda$createStandard$1(PopupFrame popupFrame, CompletableFuture completableFuture) {
        ClientSettings.K(popupFrame);
        completableFuture.complete(null);
    }

    public ConfirmationDialogComponent(String string, String string2, @Nullable String string3) {
        this.o = ConfirmationDialogComponent.J.y;
        this.R = true;
        this.a = new WrappingTextLabelComponent(string, 0.9f, ConfirmationDialogComponent.J.Z);
        this.G = new TextButton(string2, 0.7, ConfirmationDialogComponent.J.d, ConfirmationDialogComponent.J.c, 36.0, 14.0);
        this.b = new TextButton("Cancel", 0.7, ConfirmationDialogComponent.J.d, ConfirmationDialogComponent.J.c, 36.0, 14.0);
        this.b.Z(false);
        this.d(false);
        this.T(ConfirmationDialogComponent.J.m.brighter());
        this.G.F(false);
        this.G.h(Color.WHITE);
        this.G.G(Color.WHITE);
        boolean bl = this.i = string3 != null;
        if (this.i) {
            this.Q = new IconGlyphComponent(string3, 12.0f, 12.0f, Color.white);
            this.H(this.Q);
        }
        this.H(this.G, this.v, this.a, this.b);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public double x() {
        return 100.0;
    }

    @Override
    public void H() {
        double d = this.I;
        double d2 = 0.0;
        if (!this.i) {
            d -= 20.0;
            d2 += 20.0;
        }
        this.O = d;
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), 100.0, d, this.d());
        if (this.R) {
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), 100.0, d, this.o, 2.0f, 1.0f, 1.0f);
        }
        if (this.i) {
            this.Q.K(this.G$src$D$1b2f02a() + (100.0 - this.Q.A()) / 2.0);
            this.Q.S(this.n() + 10.0);
        }
        this.v.K(this.G$src$D$1b2f02a() + 100.0 - this.v.A() - 2.0);
        this.v.S(this.n() + 2.0);
        this.a.K(this.G$src$D$1b2f02a() + 5.0);
        this.a.q(this.A() - 10.0);
        this.a.S(this.n() + 32.0 - d2);
        double d3 = this.a.w$src$D$x8xgh3();
        this.G.K(this.G$src$D$1b2f02a() + (100.0 - this.G.A()) / 2.0);
        this.G.S(this.a.n() + d3 + 10.0);
        if (this.b.V$src$Z$1xhop3l()) {
            this.b.o(36.0);
            this.b.G(ConfirmationDialogComponent.J.t, ConfirmationDialogComponent.J.t);
            this.b.F(false);
            double d4 = this.b.A() + this.G.A() + 5.0;
            double d5 = this.G$src$D$1b2f02a() + this.A() / 2.0 - d4 / 2.0;
            this.G.K(d5);
            this.b.K(this.G.G$src$D$1b2f02a() + this.b.A() + 2.5);
            this.b.S(this.G.n());
        }
    }

    @Override
    public double C() {
        return this.O;
    }

    public void G(boolean bl) {
        this.R = bl;
    }

    private static void lambda$createStandard$0(PopupFrame popupFrame, Runnable runnable, CompletableFuture completableFuture) {
        ClientSettings.K(popupFrame);
        runnable.run();
        completableFuture.complete(null);
    }

    private static void lambda$createStandard$2(PopupFrame popupFrame, Runnable runnable, CompletableFuture completableFuture) {
        ClientSettings.K(popupFrame);
        if (runnable != null) {
            runnable.run();
        }
        completableFuture.complete(null);
    }

    public TextButton u$src$Lgg_vape_ui_click_component_gui_TextButton_$1ht3xvz() {
        return this.b;
    }

    public static CompletableFuture<Void> U(Frame frame, String string, String string2, String string3, Runnable runnable) {
        return ConfirmationDialogComponent.x(frame, string, string2, string3, runnable, -1.0, null, null);
    }

    public boolean m$src$Z$2x2rjh() {
        return this.R;
    }

    public Color g$src$Ljava_awt_Color_$bfdv6z() {
        return this.o;
    }

    public void N(Color color) {
        this.o = color;
    }

    public IconButtonComponent E() {
        return this.v;
    }
}

