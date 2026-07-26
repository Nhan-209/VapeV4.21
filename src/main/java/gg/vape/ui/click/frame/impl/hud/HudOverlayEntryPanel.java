package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.frame.impl.hud.HudOverlayEntryInteractiveContainer;
import org.jetbrains.annotations.Nullable;

public final class HudOverlayEntryPanel
extends GuiComponent {
    private static final double v = 4.0;
    private final HudOverlayEntryInteractiveContainer K;
    private final IconGlyphComponent o;
    private static final double i = 6.0;
    private final SimpleTextLabelComponent G;

    @Override
    public void H() {
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        double d3 = this.L();
        this.o.K(d);
        this.o.S(d2 + (d3 - 6.0) / 2.0);
        double d4 = this.o.G$src$D$1b2f02a() + 6.0 + 4.0;
        this.G.K(d4);
        this.G.S(d2);
        this.G.o(Math.max(0.0, this.A() / 2.0));
        this.G.Y(d3);
        double d5 = this.K.E();
        this.K.Y(d3);
        this.K.o(d5);
        this.K.K(d + this.A() - d5);
        this.K.S(d2);
    }

    public HudOverlayEntryPanel(@Nullable Runnable runnable) {
        this.o = new IconGlyphComponent("newoverlays_2x", 6.0f, 6.0f, HudOverlayEntryPanel.J.A);
        this.G = new SimpleTextLabelComponent("Members", 0.75, HudOverlayEntryPanel.J.A, true);
        this.d(false);
        this.o.r(true);
        this.o.o(6.0);
        this.o.Y(6.0);
        this.G.g(0.0f);
        this.G.Y(18.0);
        this.G.c(0);
        this.K = new HudOverlayEntryInteractiveContainer(runnable);
        this.H(this.o, this.G, this.K);
    }

    public void t(@Nullable Runnable runnable) {
        this.K.G(runnable);
    }

    public void q(String string) {
        this.G.G(string);
    }
}

