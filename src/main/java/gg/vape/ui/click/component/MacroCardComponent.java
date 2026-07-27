package gg.vape.ui.click.component;

import func.skidline.RectData;
import gg.vape.module.Macro;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.input.BindableInputComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class MacroCardComponent
extends GuiComponent {
    private static final double R = 10.0;
    @Nullable
    private Runnable G;
    private final DoubleAnimation Cq;
    private final TruncatedTextComponent Cc;
    private static final double C2 = 8.0;
    private final ColorAnimation Cx;
    private static final double CZ = 160.0;
    private static final double K = 6.0;
    @Nullable
    private Runnable I;
    private boolean CO;
    private final RectData Cz;
    private boolean Q;
    private final IconGlyphComponent b;
    private final DoubleAnimation v;
    private final ColorAnimation o;
    private static final double Cb = 22.0;
    private final TruncatedTextComponent CW;
    private final BindableInputComponent C5;
    private final IconGlyphComponent i;
    private static final double CC = 10.0;
    private static final float CF = 3.0f;
    @Nullable
    private Runnable a;
    private final ColorAnimation Co;
    private static final double Ca = 8.0;
    private final Macro O;
    private final ColorAnimation CE;
    private static final double Ct = 6.0;

    public void k(@Nullable Runnable runnable) {
        this.G = runnable;
    }

    public void I(boolean bl) {
        this.Q = bl;
    }

    public boolean Q$src$Z$jxpu9m() {
        return this.Q;
    }

    @Override
    public double C() {
        return 22.0;
    }

    private Color W(Color color) {
        if (color == null) {
            return null;
        }
        if (this.CO && !this.Q) {
            double d = Math.min(1.0, Math.max(0.0, this.Cq.getInterpolatedValue()));
            float f = (float)(1.0 - 0.8 * d);
            int n = Math.max(0, Math.round((float)color.getAlpha() * f));
            return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
        }
        return color;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (guiMouseEvent.getAction() != MouseButton.LEFT_CLICK && guiMouseEvent.getAction() != MouseButton.RIGHT_CLICK) {
            return;
        }
        if (this.Cz.J(guiMouseEvent.getX(), guiMouseEvent.getY())) {
            if (this.G != null) {
                this.G.run();
            }
            return;
        }
        if (this.C5.V$src$Z$1xhop3l() && this.C5.i(guiMouseEvent.getX(), guiMouseEvent.getY())) {
            return;
        }
        if (this.a != null) {
            this.a.run();
        }
    }

    private String R() {
        String string = this.O.h();
        if (string != null && !string.isEmpty()) {
            return string;
        }
        return "Set bind";
    }

    public MacroCardComponent(Macro macro, double d) {
        this.getClass();
        this.o = new ColorAnimation(0.15, MacroCardComponent.J.t, MacroCardComponent.J.z);
        this.getClass();
        this.Co = new ColorAnimation(0.15, MacroCardComponent.J.t, MacroCardComponent.J.E);
        this.getClass();
        this.CE = new ColorAnimation(0.15 * 1.5, MacroCardComponent.J.m, MacroCardComponent.J.H);
        this.getClass();
        this.Cx = new ColorAnimation(0.15, MacroCardComponent.J.R, MacroCardComponent.J.o);
        this.getClass();
        this.v = new DoubleAnimation(0.15, 0.0, 2.0);
        this.getClass();
        this.Cq = new DoubleAnimation(0.15, 0.0, 1.0);
        this.Cz = new RectData(0.0, 0.0, 0.0, 0.0);
        this.O = macro;
        this.o(true);
        this.i = new IconGlyphComponent("standalone_macro", 6.0f, 6.0f);
        this.i.S(MacroCardComponent.J.W);
        this.b = new IconGlyphComponent("settingdots", 6.0f, 6.0f);
        this.b.S(MacroCardComponent.J.W);
        this.CW = new TruncatedTextComponent(macro.getName(), 50.0, 0.75);
        this.CW.C(0.0);
        this.Cc = new TruncatedTextComponent(this.getName(), 50.0, 0.625);
        this.Cc.C(0.0);
        this.Cc.P(true);
        this.C5 = new BindableInputComponent(this.O, MacroCardComponent.J.A);
        this.C5.Z(false);
        this.C5.Y(10.0);
        this.H(this.i, this.CW, this.Cc, this.b);
        this.H(this.C5);
    }

    private String getName() {
        int n;
        int n2 = this.O.getDelay().s$src$I$vi2lk8();
        boolean bl = n2 != (n = this.O.getDelay().y());
        StringBuilder stringBuilder = new StringBuilder(bl ? n2 + "-" + n : String.valueOf(n));
        stringBuilder.append("ms delay");
        if (this.O.getDoubleClick().L().booleanValue()) {
            stringBuilder.append(" \u2022 double click ");
            stringBuilder.append(this.O.getDoubleClickDelay().y());
            stringBuilder.append("ms");
        }
        return stringBuilder.toString();
    }

    public Macro j$src$Lgg_vape_module_Macro_$1ed9en7() {
        return this.O;
    }

    public MacroCardComponent(Macro macro) {
        this(macro, 22.0);
    }

    public void Z(@Nullable Runnable runnable) {
        this.a = runnable;
    }

    public void l(boolean bl) {
        this.CO = bl;
    }

    @Override
    public void H() {
        Color color;
        this.CE.u(this.Q);
        this.Cx.u(this.Q);
        this.v.u(this.Q);
        this.Cq.u(this.CO && !this.Q);
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        double d3 = this.A();
        double d4 = this.L();
        double d5 = d2 + d4 / 2.0;
        double d6 = this.v.getInterpolatedValue();
        d += d6;
        double d7 = d3 / 2.0;
        boolean bl = this.O.y$src$Z$r0tfl8();
        if (bl) {
            Color color2;
            String string = this.getName();
            if (!string.equals(this.Cc.S$src$Ljava_lang_String_$1bp7ddx())) {
                this.Cc.O(string);
            }
            double d8 = d + d3 - 6.0;
            double d9 = this.b.A();
            double d10 = this.b.L();
            double d11 = d8 - d9;
            double d12 = d5 - d10 / 2.0;
            this.b.K(d11);
            this.b.S(d12);
            this.Cz.M(d11 - 6.0);
            this.Cz.O(d12 - 8.0);
            this.Cz.A(d9 + 10.0);
            this.Cz.U(d10 + 16.0);
            MousePosition mousePosition = RenderUtils.h();
            boolean bl2 = this.Cz.Z(mousePosition);
            if (bl2) {
                Color color3;
                if (this.w$src$Z$e457mb()) {
                    // empty if block
                }
                boolean bl3 = false;
                this.o.u(bl3);
                this.Co.u(bl2);
                Color color4 = this.CE.getInterpolatedColor();
                GuiRenderPrimitives.B(d, d2, d3, d4, this.W(color4), 3.0f);
                Color color5 = this.o.getInterpolatedColor();
                if (color5.getAlpha() > 0 && !this.Q) {
                    GuiRenderPrimitives.B(d, d2, d3, d4, this.W(color5), 3.0f);
                }
                if ((color3 = this.Co.getInterpolatedColor()).getAlpha() > 0) {
                    GuiRenderPrimitives.p(this.Cz.o(), this.Cz.W(), this.Cz.e(), this.Cz.R(), this.W(color3), false, 2.0f, 1.0f, 0.0f, MacroCardComponent.J.u, 6);
                }
                this.b.S(this.W(MacroCardComponent.J.f));
                d8 = d11 - 8.0;
                double d13 = d8 - this.C5.A();
                double d14 = d5 - 5.0;
                boolean bl4 = this.C5.u$src$Lgg_vape_input_BindCaptureTask_$1o4th8o().V$src$Z$xc25df();
                boolean bl5 = true;
                this.C5.K(d13);
                this.C5.S(d14);
                this.C5.o(this.C5.Q$src$Lgg_vape_ui_click_component_TruncatedTextCompone$6s53nl().u$src$D$ivbecn());
                this.C5.Y(10.0);
                this.C5.Z(bl5);
                GuiRenderPrimitives.p(d, d2, d7, d4, this.W(MacroCardComponent.J.E), false, 3.0f, 1.0f, 0.0f, this.W(MacroCardComponent.J.u), 9);
                GuiRenderPrimitives.C(d + d7 - 1.0, d2 - 0.5, 1.0, d4, this.W(MacroCardComponent.J.z));
                double d15 = d + 8.0;
                this.i.S(this.W(ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1()));
                this.i.K(d15);
                this.i.S(d5 - this.i.L() / 2.0);
                this.CW.p(this.Q);
                this.CW.R(this.Q ? this.W(Color.WHITE) : this.W(MacroCardComponent.J.A));
                this.CW.K(d15 += this.i.A() + 6.0);
                this.CW.S(d2);
                this.CW.o(d7 - this.i.A() - 8.0 - 6.0 - 5.0);
                this.CW.D(this.CW.A());
                this.CW.Y(d4);
                this.Cc.R(this.Q ? this.W(MacroCardComponent.J.A) : this.W(MacroCardComponent.J.h));
                this.Cc.K(d + d7 + 5.0);
                this.Cc.S(d2);
                this.Cc.o(d7 - (this.C5.V$src$Z$1xhop3l() ? this.C5.A() : 0.0) - this.Cz.e() - 8.0 - 6.0 - 8.0);
                this.Cc.D(this.Cc.A());
                this.Cc.Y(d4);
                return;
            }
            boolean bl6 = this.w$src$Z$e457mb();
            this.o.u(bl6);
            this.Co.u(bl2);
            Color color6 = this.CE.getInterpolatedColor();
            GuiRenderPrimitives.B(d, d2, d3, d4, this.W(color6), 3.0f);
            Color color7 = this.o.getInterpolatedColor();
            if (color7.getAlpha() > 0 && !this.Q) {
                GuiRenderPrimitives.B(d, d2, d3, d4, this.W(color7), 3.0f);
            }
            if ((color2 = this.Co.getInterpolatedColor()).getAlpha() > 0) {
                GuiRenderPrimitives.p(this.Cz.o(), this.Cz.W(), this.Cz.e(), this.Cz.R(), this.W(color2), false, 2.0f, 1.0f, 0.0f, MacroCardComponent.J.u, 6);
            }
            this.b.S(this.W(MacroCardComponent.J.W));
            d8 = d11 - 8.0;
            double d16 = d8 - this.C5.A();
            double d17 = d5 - 5.0;
            boolean bl7 = this.C5.u$src$Lgg_vape_input_BindCaptureTask_$1o4th8o().V$src$Z$xc25df();
            boolean bl8 = true;
            this.C5.K(d16);
            this.C5.S(d17);
            this.C5.o(this.C5.Q$src$Lgg_vape_ui_click_component_TruncatedTextCompone$6s53nl().u$src$D$ivbecn());
            this.C5.Y(10.0);
            this.C5.Z(bl8);
            GuiRenderPrimitives.p(d, d2, d7, d4, this.W(MacroCardComponent.J.E), false, 3.0f, 1.0f, 0.0f, this.W(MacroCardComponent.J.u), 9);
            GuiRenderPrimitives.C(d + d7 - 1.0, d2 - 0.5, 1.0, d4, this.W(MacroCardComponent.J.z));
            double d18 = d + 8.0;
            this.i.S(this.W(ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1()));
            this.i.K(d18);
            this.i.S(d5 - this.i.L() / 2.0);
            this.CW.p(this.Q);
            this.CW.R(this.Q ? this.W(Color.WHITE) : this.W(MacroCardComponent.J.A));
            this.CW.K(d18 += this.i.A() + 6.0);
            this.CW.S(d2);
            this.CW.o(d7 - this.i.A() - 8.0 - 6.0 - 5.0);
            this.CW.D(this.CW.A());
            this.CW.Y(d4);
            this.Cc.R(this.Q ? this.W(MacroCardComponent.J.A) : this.W(MacroCardComponent.J.h));
            this.Cc.K(d + d7 + 5.0);
            this.Cc.S(d2);
            this.Cc.o(d7 - (this.C5.V$src$Z$1xhop3l() ? this.C5.A() : 0.0) - this.Cz.e() - 8.0 - 6.0 - 8.0);
            this.Cc.D(this.Cc.A());
            this.Cc.Y(d4);
            return;
        }
        String string = this.getName();
        if (!string.equals(this.Cc.S$src$Ljava_lang_String_$1bp7ddx())) {
            this.Cc.O(string);
        }
        double d19 = d + d3 - 6.0;
        double d20 = this.b.A();
        double d21 = this.b.L();
        double d22 = d19 - d20;
        double d23 = d5 - d21 / 2.0;
        this.b.K(d22);
        this.b.S(d23);
        this.Cz.M(d22 - 6.0);
        this.Cz.O(d23 - 8.0);
        this.Cz.A(d20 + 10.0);
        this.Cz.U(d21 + 16.0);
        MousePosition mousePosition = RenderUtils.h();
        boolean bl9 = this.Cz.Z(mousePosition);
        if (bl9) {
            Color color8;
            if (this.w$src$Z$e457mb()) {
                // empty if block
            }
            boolean bl10 = false;
            this.o.u(bl10);
            this.Co.u(bl9);
            Color color9 = this.CE.getInterpolatedColor();
            GuiRenderPrimitives.B(d, d2, d3, d4, this.W(color9), 3.0f);
            Color color10 = this.o.getInterpolatedColor();
            if (color10.getAlpha() > 0 && !this.Q) {
                GuiRenderPrimitives.B(d, d2, d3, d4, this.W(color10), 3.0f);
            }
            if ((color8 = this.Co.getInterpolatedColor()).getAlpha() > 0) {
                GuiRenderPrimitives.p(this.Cz.o(), this.Cz.W(), this.Cz.e(), this.Cz.R(), this.W(color8), false, 2.0f, 1.0f, 0.0f, MacroCardComponent.J.u, 6);
            }
            this.b.S(this.W(MacroCardComponent.J.f));
            d19 = d22 - 8.0;
            double d24 = d19 - this.C5.A();
            double d25 = d5 - 5.0;
            boolean bl11 = this.C5.u$src$Lgg_vape_input_BindCaptureTask_$1o4th8o().V$src$Z$xc25df();
            boolean bl12 = bl11;
            this.C5.K(d24);
            this.C5.S(d25);
            this.C5.o(this.C5.Q$src$Lgg_vape_ui_click_component_TruncatedTextCompone$6s53nl().u$src$D$ivbecn());
            this.C5.Y(10.0);
            this.C5.Z(bl12);
            GuiRenderPrimitives.p(d, d2, d7, d4, this.W(MacroCardComponent.J.E), false, 3.0f, 1.0f, 0.0f, this.W(MacroCardComponent.J.u), 9);
            GuiRenderPrimitives.C(d + d7 - 1.0, d2 - 0.5, 1.0, d4, this.W(MacroCardComponent.J.z));
            double d26 = d + 8.0;
            this.i.S(this.W(ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1()));
            this.i.K(d26);
            this.i.S(d5 - this.i.L() / 2.0);
            this.CW.p(this.Q);
            this.CW.R(this.Q ? this.W(Color.WHITE) : this.W(MacroCardComponent.J.A));
            this.CW.K(d26 += this.i.A() + 6.0);
            this.CW.S(d2);
            this.CW.o(d7 - this.i.A() - 8.0 - 6.0 - 5.0);
            this.CW.D(this.CW.A());
            this.CW.Y(d4);
            this.Cc.R(this.Q ? this.W(MacroCardComponent.J.A) : this.W(MacroCardComponent.J.h));
            this.Cc.K(d + d7 + 5.0);
            this.Cc.S(d2);
            this.Cc.o(d7 - (this.C5.V$src$Z$1xhop3l() ? this.C5.A() : 0.0) - this.Cz.e() - 8.0 - 6.0 - 8.0);
            this.Cc.D(this.Cc.A());
            this.Cc.Y(d4);
            return;
        }
        boolean bl13 = this.w$src$Z$e457mb();
        this.o.u(bl13);
        this.Co.u(bl9);
        Color color11 = this.CE.getInterpolatedColor();
        GuiRenderPrimitives.B(d, d2, d3, d4, this.W(color11), 3.0f);
        Color color12 = this.o.getInterpolatedColor();
        if (color12.getAlpha() > 0 && !this.Q) {
            GuiRenderPrimitives.B(d, d2, d3, d4, this.W(color12), 3.0f);
        }
        if ((color = this.Co.getInterpolatedColor()).getAlpha() > 0) {
            GuiRenderPrimitives.p(this.Cz.o(), this.Cz.W(), this.Cz.e(), this.Cz.R(), this.W(color), false, 2.0f, 1.0f, 0.0f, MacroCardComponent.J.u, 6);
        }
        this.b.S(this.W(MacroCardComponent.J.W));
        d19 = d22 - 8.0;
        double d27 = d19 - this.C5.A();
        double d28 = d5 - 5.0;
        boolean bl14 = this.C5.u$src$Lgg_vape_input_BindCaptureTask_$1o4th8o().V$src$Z$xc25df();
        boolean bl15 = bl14 || bl13;
        this.C5.K(d27);
        this.C5.S(d28);
        this.C5.o(this.C5.Q$src$Lgg_vape_ui_click_component_TruncatedTextCompone$6s53nl().u$src$D$ivbecn());
        this.C5.Y(10.0);
        this.C5.Z(bl15);
        GuiRenderPrimitives.p(d, d2, d7, d4, this.W(MacroCardComponent.J.E), false, 3.0f, 1.0f, 0.0f, this.W(MacroCardComponent.J.u), 9);
        GuiRenderPrimitives.C(d + d7 - 1.0, d2 - 0.5, 1.0, d4, this.W(MacroCardComponent.J.z));
        double d29 = d + 8.0;
        this.i.S(this.W(ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1()));
        this.i.K(d29);
        this.i.S(d5 - this.i.L() / 2.0);
        this.CW.p(this.Q);
        this.CW.R(this.Q ? this.W(Color.WHITE) : this.W(MacroCardComponent.J.A));
        this.CW.K(d29 += this.i.A() + 6.0);
        this.CW.S(d2);
        this.CW.o(d7 - this.i.A() - 8.0 - 6.0 - 5.0);
        this.CW.D(this.CW.A());
        this.CW.Y(d4);
        this.Cc.R(this.Q ? this.W(MacroCardComponent.J.A) : this.W(MacroCardComponent.J.h));
        this.Cc.K(d + d7 + 5.0);
        this.Cc.S(d2);
        this.Cc.o(d7 - (this.C5.V$src$Z$1xhop3l() ? this.C5.A() : 0.0) - this.Cz.e() - 8.0 - 6.0 - 8.0);
        this.Cc.D(this.Cc.A());
        this.Cc.Y(d4);
    }


    public boolean r$src$Z$kfv1uj() {
        return this.CO;
    }

    public void S(@Nullable Runnable runnable) {
        this.I = runnable;
    }
}

