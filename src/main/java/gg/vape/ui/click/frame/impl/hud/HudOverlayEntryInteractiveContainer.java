package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

final class HudOverlayEntryInteractiveContainer
extends InteractiveComponent {
    private final IconGlyphComponent b;
    @Nullable
    private Runnable I;
    private final SimpleTextLabelComponent K;
    private static final float v = 5.0f;
    private static final double Q = 3.0;

    @Override
    public void H() {
        double d = this.L();
        double d2 = this.G$src$D$1b2f02a();
        double d3 = this.n();
        Color color = this.w$src$Z$e457mb() ? HudOverlayEntryInteractiveContainer.J.A : HudOverlayEntryInteractiveContainer.J.Z;
        this.b.S(color);
        this.b.K(d2);
        this.b.S(d3 + (d - 5.0) / 2.0);
        this.K.T$src$V$1orl066(color);
        this.K.K(this.b.G$src$D$1b2f02a() + 5.0 + 3.0);
        this.K.S(d3 - 0.5);
        this.K.o(this.K.h());
        this.K.Y(d);
        this.o(this.E());
    }

    double E() {
        return 8.0 + this.K.h();
    }


    private void lambda$new$0() {
        if (this.I != null) {
            this.I.run();
        }
    }

    HudOverlayEntryInteractiveContainer(@Nullable Runnable runnable) {
        this.b = new IconGlyphComponent("back_arrow", 5.0f, 5.0f, HudOverlayEntryInteractiveContainer.J.W);
        this.K = new SimpleTextLabelComponent("Back", 0.75, HudOverlayEntryInteractiveContainer.J.A);
        this.I = runnable;
        this.d(false);
        this.o(true);
        this.w("Back");
        this.b.r(true);
        this.b.o(5.0);
        this.b.Y(5.0);
        this.K.g(0.0f);
        this.K.c(0);
        this.K.z(0.0f);
        this.H(this.b, this.K);
        this.r(this::lambda$new$0);
    }

    public void G(@Nullable Runnable runnable) {
        this.I = runnable;
    }
}

