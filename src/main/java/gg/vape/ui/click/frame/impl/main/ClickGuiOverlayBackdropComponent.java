package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class ClickGuiOverlayBackdropComponent
extends GuiComponent {
    private static final int a;
    private final DoubleSupplier K;
    private final Runnable o;
    private final Supplier<ClickGuiOverlayPlacement> Q;
    private boolean I = true;
    private final ClickGuiMainFrame G;

    public ClickGuiOverlayBackdropComponent(ClickGuiMainFrame clickGuiMainFrame, DoubleSupplier doubleSupplier, Supplier<ClickGuiOverlayPlacement> supplier, Runnable runnable) {
        this.G = clickGuiMainFrame;
        this.K = doubleSupplier;
        this.Q = supplier;
        this.o = runnable;
        this.Z(false);
    }


    public boolean P() {
        return this.I;
    }

    public void G(boolean bl) {
        this.I = bl;
    }

    @Override
    public void H() {
        ClickGuiOverlayPlacement clickGuiOverlayPlacement;
        double d = Math.max(0.0, Math.min(1.0, this.K.getAsDouble()));
        if (d <= 0.0) {
            return;
        }
        ClickGuiOverlayPlacement clickGuiOverlayPlacement2 = clickGuiOverlayPlacement = this.Q != null ? this.Q.get() : ClickGuiOverlayPlacement.OVERLAY;
        if (clickGuiOverlayPlacement == null) {
            clickGuiOverlayPlacement = ClickGuiOverlayPlacement.OVERLAY;
        }
        double d2 = this.G.G$src$D$1b2f02a();
        double d3 = this.G.n();
        double d4 = this.G.A();
        double d5 = this.G.L();
        double d6 = d3;
        double d7 = d5;
        boolean bl = true;
        if (clickGuiOverlayPlacement == ClickGuiOverlayPlacement.DOCKED || clickGuiOverlayPlacement == ClickGuiOverlayPlacement.DOCKED_SHIFT) {
            bl = false;
            double d8 = this.G.f$src$D$17a38m();
            d6 = d3 + d8;
            d7 = Math.max(0.0, d5 - d8);
        }
        if (d7 <= 0.0) {
            return;
        }
        this.K(d2);
        this.S(d6);
        this.o(d4);
        this.Y(d7);
        this.S(this.I);
        int n = (int)(d * 140.0);
        Color color = new Color(0, 0, 0, Math.min(255, Math.max(0, n)));
        if (bl) {
            GuiRenderPrimitives.e(d2, d6, d4, d7, color, false, 2.0f, 1.0f);
        } else {
            GuiRenderPrimitives.p(d2, d6, d4, d7, color, false, 2.0f, 1.0f, 0.0f, ClickGuiOverlayBackdropComponent.J.B, 12);
        }
    }

    static {
        long l2 = -2320619047542800124L;
        a = (int)l2;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (guiMouseEvent.getAction() == MouseButton.LEFT_CLICK && this.w$src$Z$e457mb() && this.o != null) {
            this.o.run();
        }
    }
}

