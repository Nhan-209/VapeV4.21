package gg.vape.ui.click.frame.impl.target;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleFrameBase;
import gg.vape.utils.render.EntityModelRenderCache;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.EntityLivingBase;
import java.awt.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TargetInfoEntityPreviewComponent
extends GuiComponent {
    @NotNull
    private Color K = Color.WHITE;
    private EntityLivingBase G;
    private static final String o = "faceColor is marked non-null but is null";
    @Nullable
    private Color b = new Color(100, 100, 100, 70);
    @Nullable
    private HudModuleFrameBase O;

    public void n(@Nullable Color color) {
        this.b = color;
    }

    @NotNull
    public Color U$src$Ljava_awt_Color_$156e1hb() {
        return this.K;
    }

    public void Q(@NotNull Color color) {
        if (color == null) {
            throw new NullPointerException(o);
        }
        this.K = color;
    }

    @Nullable
    public Color P() {
        return this.b;
    }

    public EntityLivingBase s() {
        return this.G;
    }

    private static NullPointerException a(NullPointerException nullPointerException) {
        return nullPointerException;
    }

    public TargetInfoEntityPreviewComponent(double d, double d2) {
        this.o(d);
        this.Y(d2);
    }

    @Override
    public void H() {
        if (this.s() == null || this.s().isNull()) {
            return;
        }
        Color color = this.b;
        Color color2 = this.K;
        float f = 1.0f;
        if (this.O != null) {
            if (color != null) {
                color = this.O.l(color);
            }
            color2 = this.O.l(color2);
            f = this.O.r$src$F$35g3yx();
        }
        if (color != null) {
            GuiRenderPrimitives.g(this.G$src$D$1b2f02a(), this.n() + 1.0, this.A(), this.L(), 12.0f, 1.0f, color);
        }
        EntityModelRenderCache.d(this.s(), (float)this.G$src$D$1b2f02a(), (float)this.n(), (int)this.A(), (int)this.L(), color2, f);
    }

    public void m(EntityLivingBase entityLivingBase) {
        this.G = entityLivingBase;
    }

    public void M(@Nullable HudModuleFrameBase hudModuleFrameBase) {
        this.O = hudModuleFrameBase;
    }
}

