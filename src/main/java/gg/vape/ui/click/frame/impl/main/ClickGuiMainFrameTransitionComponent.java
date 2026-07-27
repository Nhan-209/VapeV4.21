package gg.vape.ui.click.frame.impl.main;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class ClickGuiMainFrameTransitionComponent
extends GuiComponent {
    private final ClickGuiMainFrame v;
    private boolean Q = false;
    private static String K;
    private static final int I;
    private final DoubleAnimation R;

    public static void l(String string) {
        K = string;
    }

    public boolean y$src$Z$1yxqxvj() {
        return this.Q;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    static {
        if (ClickGuiMainFrameTransitionComponent.L$src$Ljava_lang_String_$yxjgps() == null) {
            ClickGuiMainFrameTransitionComponent.l("xzrYmc");
        }
        long l3 = -1000193941785175962L;
        I = (int)l3;
    }

    public ClickGuiMainFrameTransitionComponent(ClickGuiMainFrame clickGuiMainFrame) {
        this.v = clickGuiMainFrame;
        this.getClass();
        this.R = new DoubleAnimation(0.15, 0.0, 1.0);
        this.R.O();
        this.Z(false);
    }

    public void H(boolean bl) {
        if (this.Q != bl) {
            this.Q = bl;
            this.R.J();
            if (bl) {
                this.R.C();
                this.S(true);
            } else {
                this.R.O();
                this.S(false);
            }
            this.Z(bl);
            if (bl) {
                this.K(this.v.G$src$D$1b2f02a());
                this.S(this.v.n());
                this.o(this.v.A());
                this.Y(this.v.L());
            }
        }
    }

    @Override
    public void H() {
        this.R.u(this.Q);
        double d = Math.max(0.0, Math.min(1.0, this.R.getInterpolatedValue()));
        if (d <= 0.0) {
            return;
        }
        this.S(true);
        double d2 = this.v.G$src$D$1b2f02a();
        double d3 = this.v.n();
        double d4 = this.v.A();
        double d5 = this.v.L();
        int n = (int)(d * 140.0);
        Color color = new Color(0, 0, 0, Math.min(255, Math.max(0, n)));
        GuiRenderPrimitives.e(d2, d3, d4, d5, color, false, 2.0f, 1.0f);
    }

    public static String L$src$Ljava_lang_String_$yxjgps() {
        return K;
    }
}

