package gg.vape.ui.click.component;

import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.animation.ThemeColorAnimation;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class AnimatedIconButtonComponent
extends IconButtonComponent {
    private boolean j1;
    private ColorAnimation jd;
    private float jS = 1.5f;
    private float jW = 1.0f;
    @Nullable
    private Color jK;
    private boolean jG;

    public void X(@Nullable Color color) {
        this.jK = color;
    }

    @Override
    public void H() {
        double d = 1.0;
        if (this.jG && this.w$src$Z$e457mb()) {
            d = this.j1 ? 0.9 : 0.5;
        }
        if (this.w$src$Z$e457mb() || this.j1) {
            Color color = this.jd.getInterpolatedColor();
            color = ColorUtil.W(color, (int)(d * 255.0));
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 0.5, this.n() + 0.5, this.A() - 1.0, this.L() - 1.0, color);
        } else {
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 0.5, this.n() + 0.5, this.A() - 1.0, this.L() - 1.0, this.d());
        }
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.jK != null ? this.jK : AnimatedIconButtonComponent.J.l, this.jS, this.jW, 1.0f);
        super.H();
    }

    public void L(boolean bl) {
        this.j1 = bl;
    }


    public AnimatedIconButtonComponent(String string, Color color) {
        this(string, 1.0, color);
    }

    public float x$src$F$d1ko1n() {
        return this.jS;
    }

    public AnimatedIconButtonComponent(String string) {
        super(string);
        this.jd = new ThemeColorAnimation(0.15, this.d());
    }

    public void A(float f) {
        this.jW = f;
    }

    public AnimatedIconButtonComponent(String string, double d, Color color) {
        this(string, d, null, color);
    }

    public ColorAnimation d$src$Lgg_vape_ui_click_animation_ColorAnimation_$10kme50() {
        return this.jd;
    }

    public AnimatedIconButtonComponent(String string, double d, Color color, Color color2) {
        super(string, d);
        this.jd = new ColorAnimation(0.15, color == null ? this.d() : color, color2);
    }

    public void E(float f) {
        this.jS = f;
    }

    public void h(boolean bl) {
        this.jG = bl;
    }

    @Override
    public void n(boolean bl) {
        if (this.w$src$Z$e457mb() != bl && !this.j1) {
            this.jd.J();
        }
        super.n(bl);
    }

    public float L$src$F$cddpxr() {
        return this.jW;
    }

    @Nullable
    public Color m$src$Ljava_awt_Color_$sx1hy() {
        return this.jK;
    }
}

