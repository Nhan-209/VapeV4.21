package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.layout.ComponentLayout;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class PanelComponent
extends FrameComponent {
    @Nullable
    private Color OK = null;
    private float Ob = 1.5f;
    private float Og = 1.0f;
    private static boolean OB;
    private boolean O5 = true;

    @Override
    public double C() {
        return 0.0;
    }

    public boolean y$src$Z$1y5ntlm() {
        return this.O5;
    }

    @Override
    public void v() {
    }

    public PanelComponent(double d, double d2) {
        this.o(d);
        this.Y(d2);
        ComponentLayout componentLayout = this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij();
        componentLayout.t(false);
        componentLayout.M(false);
        componentLayout.U(false);
        componentLayout.I(false);
        componentLayout.u(false);
    }

    public void R(float f) {
        this.Og = f;
    }

    public static boolean c$src$Z$1xtkcjo() {
        return OB;
    }

    @Override
    public double x() {
        return 0.0;
    }

    static {
        if (PanelComponent.e$src$Z$1xunxqe()) {
            PanelComponent.C(true);
        }
    }

    public float H$src$F$1xepw1p() {
        return this.Og;
    }

    public static void C(boolean bl) {
        OB = bl;
    }

    public float X$src$F$1xniljh() {
        return this.Ob;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Nullable
    public Color n$src$Ljava_awt_Color_$1yxivib() {
        return this.OK;
    }

    public void V(float f) {
        this.Ob = f;
    }

    @Override
    public void Y() {
    }

    public static boolean e$src$Z$1xunxqe() {
        boolean bl = PanelComponent.c$src$Z$1xtkcjo();
        return !bl;
    }

    public void z(@Nullable Color color) {
        this.OK = color;
    }

    @Override
    public void V() {
    }

    @Override
    public void z(boolean bl) {
        if (this.O5 && this.u$src$I$ikouxa() > 0) {
            double d;
            double d2;
            double d3;
            double d4;
            double d5 = this.A();
            double d6 = this.n();
            double d7 = this.G$src$D$1b2f02a();
            if (bl) {
                d4 = d7;
                d3 = d6;
                d2 = d5;
                d = this.K;
            } else {
                d4 = d7;
                d3 = d6;
                d2 = d5;
                d = this.L();
            }
            GuiRenderPrimitives.p(d4, d3, d2, d, this.d(), this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() != null, this.Ob, 1.0f, 8.0f, PanelComponent.J.u, this.u$src$I$ikouxa());
            if (this.n$src$Ljava_awt_Color_$1yxivib() != null) {
                double d8;
                double d9;
                double d10;
                double d11;
                double d12 = this.A();
                double d13 = this.n();
                double d14 = this.G$src$D$1b2f02a();
                if (bl) {
                    d11 = d14;
                    d10 = d13;
                    d9 = d12;
                    d8 = this.K;
                } else {
                    d11 = d14;
                    d10 = d13;
                    d9 = d12;
                    d8 = this.L();
                }
                GuiRenderPrimitives.P(d11, d10, d9, d8, this.n$src$Ljava_awt_Color_$1yxivib(), this.Ob, 0.75f, 1.0f);
            }
        } else if (this.n$src$Ljava_awt_Color_$1yxivib() != null) {
            double d;
            double d15;
            double d16;
            double d17;
            double d18 = this.A();
            double d19 = this.n();
            double d20 = this.G$src$D$1b2f02a();
            if (bl) {
                d17 = d20;
                d16 = d19;
                d15 = d18;
                d = this.K;
            } else {
                d17 = d20;
                d16 = d19;
                d15 = d18;
                d = this.L();
            }
            GuiRenderPrimitives.q(d17, d16, d15, d, this.H$src$F$1xepw1p(), this.d(), this.n$src$Ljava_awt_Color_$1yxivib());
        } else {
            double d;
            double d21;
            double d22;
            double d23;
            double d24 = this.A();
            double d25 = this.n();
            double d26 = this.G$src$D$1b2f02a();
            if (bl) {
                d23 = d26;
                d22 = d25;
                d21 = d24;
                d = this.K;
            } else {
                d23 = d26;
                d22 = d25;
                d21 = d24;
                d = this.L();
            }
            GuiRenderPrimitives.C(d23, d22, d21, d, this.d());
        }
    }

    private static ObfuscatedRuntimeException d(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void t(boolean bl) {
        this.O5 = bl;
    }
}

