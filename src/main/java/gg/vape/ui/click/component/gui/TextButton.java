package gg.vape.ui.click.component.gui;

import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class TextButton
extends TextLabel {
    private boolean IT;
    private boolean Il;
    public static final float IN = 2.0f;
    public static final float IQ = 1.0f;
    private Color IF;
    private boolean Iy;
    private Color Iw;
    private ColorAnimation Ig;
    private ColorAnimation IA;
    private float Io;
    @Nullable
    private String Iq;
    private float IX;
    private Color In;
    private float II;

    public void u(float f) {
        this.II = f;
    }

    public void m(float f) {
        this.IX = f;
    }

    public void i(float f) {
        this.Io = f;
    }

    public TextButton(String string, double d, Color color, Color color2) {
        this(string, d, color, color2, null, 2.0f, 1.0f, 0.0, 0.0);
    }

    public void p(boolean bl) {
        this.Il = bl;
    }

    @Override
    public double x() {
        return 0.0;
    }

    public void T(String string) {
        this.Iq = string;
    }

    public void F(boolean bl) {
        this.IT = bl;
    }

    public void m(boolean bl) {
        this.Iy = bl;
    }

    public TextButton(String string, double d, Color color, Color color2, double d2, double d3) {
        this(string, d, color, color2, null, 2.0f, 1.0f, d2, d3);
    }

    public TextButton R(Color color) {
        this.In = color;
        this.Ig = new ColorAnimation(0.0375, new Color(0, 0, 0, 0), this.Ig.getEndColor());
        return this;
    }

    public TextButton l(Color color, Color color2) {
        this.In = color;
        this.Ig = new ColorAnimation(0.0375, color2, this.Ig.getEndColor());
        return this;
    }

    public TextButton(String string, Color color, Color color2) {
        this(string, 0.9, color, color2, null, 2.0f, 1.0f, 0.0, 0.0);
    }

    public TextButton(String string, Color color) {
        this(string, 0.9, null, color, null, 2.0f, 1.0f, 0.0, 0.0);
    }

    public ColorAnimation L$src$Lgg_vape_ui_click_animation_ColorAnimation_$1j6vdwo() {
        return this.Ig;
    }

    public TextButton(String string, double d, Color color, Color color2, Color color3, float f, float f2, double d2, double d3) {
        super(string, d);
        this.Iw = TextButton.J.Z;
        this.IF = TextButton.J.Z;
        this.In = null;
        this.II = 1.0f;
        this.IX = 2.0f;
        this.IT = true;
        this.Io = 8.0f;
        this.Il = false;
        this.Iy = false;
        if (color != null) {
            this.T(color);
        }
        this.Ig = new ColorAnimation(0.0375, this.d(), color2);
        this.IA = new ColorAnimation(0.0375, this.Iw, this.IF);
        if (color3 != null && color != null) {
            this.l(color3, color);
        }
        this.IX = f;
        this.II = f2;
        if (d2 != 0.0) {
            this.o(d2);
        }
        if (d3 != 0.0) {
            this.Y(d3);
        }
    }

    public GuiComponent G(Color color, Color color2) {
        this.Ig = new ColorAnimation(0.0375, color, color2);
        return super.T(color);
    }

    public TextButton(String string, double d, Color color, Color color2, Color color3, float f, float f2) {
        this(string, d, color, color2, color3, f, f2, 0.0, 0.0);
    }

    @Override
    public void H() {
        Object object;
        if (this.Iy) {
            object = J.z();
            this.Ig.setStartColor((Color)object);
            this.Ig.setEndColor(((Color)object).brighter());
        }
        if (this.Z$src$Z$16e8vsp()) {
            GuiRenderPrimitives.B(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.Ig.getInterpolatedColor(), this.IX);
        } else if (this.w$src$Z$e457mb()) {
            GuiRenderPrimitives.B(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), new Color(100, 100, 100, 10), this.IX);
        }
        if (this.In != null) {
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.In, this.IX, this.II, 1.0f);
        }
        object = this.s$src$Z$8lhrly() ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.Yc) : this.O(this.Yc);
        double d = ((SmoothFontRenderer)object).d(this.Q);
        double d2 = this.G$src$D$1b2f02a() + this.A() / 2.0 + (double)(this.Iq != null ? this.Io / 2.0f : 0.0f);
        double d3 = this.n() + this.L() / 2.0 - d / 2.0 + 0.5;
        Color color = this.IT ? ColorUtil.r(this.Ig.getInterpolatedColor(), 70, 240) : this.G();
        ((SmoothFontRenderer)object).W(this.Q, d2, d3, color);
        if (this.Iq != null) {
            float f = this.Io;
            double d4 = this.G$src$D$1b2f02a() + this.A() / 2.0 - ((SmoothFontRenderer)object).N(this.Q) / 2.0 - (double)(f / 2.0f);
            GuiRenderPrimitives.F(this.Iq, d4, this.n() + this.L() / 2.0, (double)f, f, color);
        }
    }

    public TextButton h(Color color) {
        this.Iw = color;
        this.IA = new ColorAnimation(0.0375, color, this.IF);
        return this;
    }


    @Override
    public void n(boolean bl) {
        if (this.w$src$Z$e457mb() != bl) {
            this.Ig.J();
            this.IA.J();
        }
        super.n(bl);
    }

    @Override
    public Color G() {
        return this.Il ? this.IA.getInterpolatedColor() : this.Iw;
    }

    public void G(Color color) {
        this.IF = color;
        this.IA = new ColorAnimation(0.0375, this.Iw, color);
    }

    @Override
    public double C() {
        return 0.0;
    }

    public TextButton(String string, double d, Color color) {
        this(string, d, null, color, null, 2.0f, 1.0f, 0.0, 0.0);
    }
}

