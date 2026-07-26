package gg.vape.ui.click.component;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class ImageTextureComponent
extends GuiComponent {
    private float a;
    @Nullable
    private Color o;
    private GlImageTexture i;
    private boolean I;

    public GlImageTexture i$src$Lgg_vape_utils_render_GlImageTexture_$y3vvix() {
        return this.i;
    }

    public void u(@Nullable Color color) {
        this.o = color;
    }

    public void J(GlImageTexture glImageTexture) {
        this.i = glImageTexture;
    }

    @Override
    public void H() {
        this.i.F();
        if (this.I) {
            double d = 4.0;
            GuiRenderPrimitives.V((double)((float)this.G$src$D$1b2f02a() + this.a) - d / 2.0, (double)((float)this.n() + this.a) - d / 2.0, (double)((float)this.L() - this.a) + d, 1.0, ImageTextureComponent.J.l);
        }
        GuiRenderPrimitives.u((float)this.G$src$D$1b2f02a() + this.a, (float)this.n() + this.a, (float)this.L() - this.a, 1.0f, Color.WHITE, this.i);
    }

    @Override
    public double C() {
        return 8.0;
    }

    @Override
    public double x() {
        return 8.0;
    }

    public void q(boolean bl) {
        this.I = bl;
    }

    @Nullable
    public Color o$src$Ljava_awt_Color_$1xd4vnq() {
        return this.o;
    }

    public float d$src$F$12tg6yd() {
        return this.a;
    }

    public void X(float f) {
        this.a = f;
    }
}

