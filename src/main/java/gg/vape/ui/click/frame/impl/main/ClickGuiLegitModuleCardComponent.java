package gg.vape.ui.click.frame.impl.main;

import gg.vape.module.render.hud.HudModule;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.animation.ThemeColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class ClickGuiLegitModuleCardComponent
extends GuiComponent {
    private final float Q = 6.0f;
    private final String R;
    private final DoubleAnimation cX;
    private final IconButtonComponent G;
    private final float v = 1.0f;
    @Nullable
    private Runnable cG;
    private final HudModule cp;
    private boolean ci;
    private final DoubleAnimation b;
    private final Color K;
    private final ColorAnimation a;
    private boolean o;
    private final ThemeColorAnimation i;
    private final float cy = 6.0f;
    private final float I;
    private final String c_;
    private boolean O;
    private boolean c4;
    private final float c8 = 4.0f;

    private Color q(Color color) {
        if (color == null) {
            return null;
        }
        if (this.O && !this.ci) {
            double d = Math.min(1.0, Math.max(0.0, this.b.getInterpolatedValue()));
            float f = (float)(1.0 - 0.8 * d);
            int n = Math.max(0, Math.round((float)color.getAlpha() * f));
            return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
        }
        return color;
    }

    public void n(@Nullable Runnable runnable) {
        this.cG = runnable;
    }

    public boolean R() {
        return this.ci;
    }

    public ClickGuiLegitModuleCardComponent(HudModule hudModule) {
        this(hudModule, 0.9f);
    }

    public void N(boolean bl) {
        this.O = bl;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (guiMouseEvent.getAction().equals((Object)MouseButton.LEFT_CLICK)) {
            this.p();
        } else if (guiMouseEvent.getAction().equals((Object)MouseButton.RIGHT_CLICK) && this.cG != null) {
            this.cG.run();
        }
    }


    public boolean c$src$Z$11dn9wd() {
        return !this.cX.getInterpolatedValue().equals(this.cX.getStartValue()) && !this.cX.getInterpolatedValue().equals(this.cX.getEndValue());
    }

    public void p() {
        if (this.c4 == this.m$src$Z$11j57tz()) {
            this.c4 = !this.c4;
            this.i.J();
            this.cX.J();
        } else if (this.c4) {
            this.i.C();
            this.cX.C();
        } else {
            this.i.O();
            this.cX.O();
        }
        if (this.cp.r$src$Z$14eylz9() != this.c4) {
            this.cp.Y(this.c4);
        }
    }

    @Override
    public void H() {
        this.b.u(this.O && !this.ci);
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 2.0, this.n(), this.A() - 3.0, this.L() - 3.0, this.q(this.o || this.c4 ? ClickGuiLegitModuleCardComponent.J.l : ClickGuiLegitModuleCardComponent.J.m));
        GuiRenderPrimitives.F(this.c_, this.G$src$D$1b2f02a() + 10.0 + 3.0, this.n() + 10.0, (double)(8.0f * this.I), 8.0f * this.I, this.q(this.c4 ? J.z() : (this.o ? ClickGuiLegitModuleCardComponent.J.f : ClickGuiLegitModuleCardComponent.J.W)));
        this.G.K(this.G$src$D$1b2f02a() + this.A() - 15.0);
        this.G.S(this.n() + 10.0 - 4.5);
        this.G.Y(9.0);
        this.l$src$V$11ilf56();
        this.z();
    }

    private void l$src$V$11ilf56() {
        Color color;
        double d = this.G$src$D$1b2f02a() + this.A();
        this.getClass();
        double d2 = d - (double)(6.0f * 2.0f) - 17.5;
        double d3 = this.n() + 10.0 - 3.0;
        Color color2 = color = this.i.q() > 0.0 ? this.i.getInterpolatedColor() : this.a.getInterpolatedColor();
        if (this.o && this.i.q() > 0.0) {
            color = ColorUtil.N(color, 30.0);
        }
        Color color3 = this.q(color);
        float f = (float)d2;
        float f2 = (float)d3;
        this.getClass();
        this.getClass();
        ImageRenderer.E(color3, f, f2, "togglebg", 6.0f, 6.0f, false);
        ImageRenderer.E(this.q(this.K), (float)d2 + this.v + (float)this.cX.getInterpolatedValue().doubleValue(), (float)d3 + this.v, "toggledot", this.c8, this.c8, false);
    }

    public ClickGuiLegitModuleCardComponent(HudModule hudModule, float f) {
        this.a = new ColorAnimation(0.15, ClickGuiLegitModuleCardComponent.J.K, ClickGuiLegitModuleCardComponent.J.W);
        this.i = new ThemeColorAnimation(0.15, ClickGuiLegitModuleCardComponent.J.W);
        this.cX = new DoubleAnimation(0.15, 0.0, 5.0);
        this.K = ClickGuiLegitModuleCardComponent.J.r;
        this.b = new DoubleAnimation(0.15, 0.0, 1.0);
        this.cp = hudModule;
        this.R = hudModule.getName();
        this.c_ = hudModule.s$src$Ljava_lang_String_$pdppcm();
        this.c4 = hudModule.r$src$Z$14eylz9();
        this.I = f;
        if (hudModule.n() != null) {
            this.w(hudModule.n());
        }
        this.G = new IconButtonComponent("settingdots", 0.8);
        this.G.r(this::lambda$new$0);
        this.H(this.G);
    }

    public HudModule F$src$Lgg_vape_module_render_hud_HudModule_$mt0j3c() {
        return this.cp;
    }

    private void lambda$new$0() {
        if (this.cG != null) {
            this.cG.run();
        }
    }

    public boolean W() {
        return this.O;
    }

    private void K$src$V$110g7k9() {
        if (this.cp.r$src$Z$14eylz9() != this.c4 || this.c4 != this.m$src$Z$11j57tz() && !this.c$src$Z$11dn9wd()) {
            this.p();
        }
    }

    @Override
    public double x() {
        return 84.5;
    }

    @Override
    public void u() {
        if (this.o && !this.w$src$Z$e457mb()) {
            this.o = false;
        }
        this.K$src$V$110g7k9();
    }

    public boolean m$src$Z$11j57tz() {
        return this.cX.I$src$Z$c48gtw();
    }

    public void J(boolean bl) {
        this.ci = bl;
    }

    private void z() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        double d = smoothFontRenderer.d(this.R);
        double d2 = this.G$src$D$1b2f02a() + 10.0;
        double d3 = this.n() + (this.L() - 15.0) - d / 2.0;
        Color color = this.c4 ? Color.WHITE : ClickGuiLegitModuleCardComponent.J.Z;
        smoothFontRenderer.d(this.R, d2, d3, this.q(color));
        if (!this.c4 && (this.cp.t$src$Z$14g275z() || this.cp.Q())) {
            SmoothFontRenderer smoothFontRenderer2 = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.65f);
            String string = this.cp.Q() ? "INDEV" : "BETA";
            double d4 = smoothFontRenderer2.N(string) + 4.0;
            double d5 = smoothFontRenderer2.d(string) + 2.0;
            double d6 = d2 + smoothFontRenderer.N(this.R) + 4.0;
            double d7 = d3 + (d - d5) / 2.0;
            Color color2 = this.q(J.z());
            GuiRenderPrimitives.d(d6, d7, d4, d5, color2);
            smoothFontRenderer2.d(string, d6 + 2.0, d7 + 1.0, this.q(ColorUtil.r(J.z(), 35, 255)));
        }
    }

    @Override
    public double C() {
        return 60.0;
    }

    @Override
    public void F() {
        this.o = true;
    }
}

