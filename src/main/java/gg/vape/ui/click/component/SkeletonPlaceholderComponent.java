package gg.vape.ui.click.component;

import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class SkeletonPlaceholderComponent
extends GuiComponent {
    private final ColorAnimation b;
    private static boolean Q;

    public static boolean g$src$Z$1cp21ye() {
        boolean bl = SkeletonPlaceholderComponent.y$src$Z$1cyycmw();
        return !bl;
    }

    public static boolean y$src$Z$1cyycmw() {
        return Q;
    }

    public SkeletonPlaceholderComponent(double d, double d2) {
        this.getClass();
        this.b = new ColorAnimation(0.15 * 4.0, new Color(32, 32, 32, 32), new Color(128, 128, 128, 64));
        this.o(d);
        this.Y(d2);
    }

    public static void k(boolean bl) {
        Q = bl;
    }


    @Override
    public void H() {
        if (!this.b.l()) {
            if (this.b.N()) {
                this.b.Z();
            } else {
                this.b.c();
            }
        }
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.b.getInterpolatedColor());
    }

    static {
        if (!SkeletonPlaceholderComponent.g$src$Z$1cp21ye()) {
            SkeletonPlaceholderComponent.k(true);
        }
    }
}

