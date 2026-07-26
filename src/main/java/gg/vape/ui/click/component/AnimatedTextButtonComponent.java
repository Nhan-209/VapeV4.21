package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class AnimatedTextButtonComponent
extends InteractiveComponent {
    private final DoubleAnimation I;
    private final double b;
    private static final String cb = "expandarrow";
    private final float Hs;
    private final Color HF;
    private boolean HS;
    private boolean Q;
    private final String K;
    private boolean v;

    private void A$src$V$15bszb4() {
        if (this.v) {
            this.I.C();
        } else if (this.I.I$src$Z$c48gtw()) {
            this.I.O();
        }
    }

    public DoubleAnimation V$src$Lgg_vape_ui_click_animation_DoubleAnimation_$15ru9pe() {
        return this.I;
    }

    @Override
    public void F() {
        this.Q = true;
    }

    @Override
    public double x() {
        return 110.0;
    }

    @Override
    public double C() {
        return 18.0;
    }

    public void N(boolean bl) {
        this.HS = bl;
    }

    @Override
    public void I() {
    }

    public AnimatedTextButtonComponent(String string, double d) {
        this(string, d, false);
    }

    public AnimatedTextButtonComponent(String string) {
        this(string, 0.9);
    }

    public boolean G$src$Z$15f3qyq() {
        return this.HS;
    }

    public void X(boolean bl) {
        boolean bl2 = this.v != bl;
        this.v = bl;
        if (bl2) {
            if (this.I.l()) {
                this.A$src$V$15bszb4();
            } else {
                this.I.J();
            }
        }
    }

    public AnimatedTextButtonComponent(String string, double d, boolean bl) {
        this.getClass();
        this.I = new DoubleAnimation(0.15, 0.0, 3.0);
        this.Hs = 4.0f;
        this.HF = AnimatedTextButtonComponent.J.W;
        this.K = string;
        this.b = d;
        this.v = bl;
        this.r(this::lambda$new$0);
        this.A$src$V$15bszb4();
    }

    public boolean e() {
        return this.v;
    }

    private void lambda$new$0() {
        this.X(!this.v);
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void u() {
        if (this.Q && !this.w$src$Z$e457mb()) {
            this.Q = false;
        }
    }

    public String q$src$Ljava_lang_String_$txw8ee() {
        return this.K;
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.v && this.HS ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.b) : this.O(this.b);
        double d = smoothFontRenderer.d(this.K);
        double d2 = this.n() + this.L() / 2.0 - d / 2.0;
        double d3 = this.n() + this.L() / 2.0 - (double)(this.Hs / 2.0f);
        Color color = AnimatedTextButtonComponent.J.m;
        Color color2 = AnimatedTextButtonComponent.J.Z;
        if (this.Q) {
            color = AnimatedTextButtonComponent.J.a;
            color2 = AnimatedTextButtonComponent.J.A;
        }
        if (this.v) {
            color = AnimatedTextButtonComponent.J.a;
            color2 = AnimatedTextButtonComponent.J.A;
            if (this.Q) {
                color2 = ColorUtil.N(color2, 30.0);
            }
        }
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n() + 1.0, this.A() - 2.0, this.L() - 2.5, color);
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n() + 1.5, this.A(), this.L() - 3.0, color);
        Color color3 = this.v || this.Q ? color2 : this.HF;
        float f = (float)this.G$src$D$1b2f02a() + (float)this.A();
        this.getClass();
        ImageRenderer.E(color3, f - 5.0f - 5.0f + this.I.getInterpolatedValue().floatValue(), (float)d3, cb, this.Hs, this.Hs, false);
        double d4 = this.G$src$D$1b2f02a();
        this.getClass();
        smoothFontRenderer.d(this.K, d4 + (double)(5.0f * 2.0f), d2, color2);
    }
}
