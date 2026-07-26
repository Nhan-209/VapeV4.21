package gg.vape.ui.click.component;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class ColorDividerComponent
extends GuiComponent {
    private double K;
    private static int[] G;
    private Color v;
    double I = 110.0;

    @Override
    public void I() {
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.v);
    }

    public ColorDividerComponent(Color color) {
        this(color, 0.5);
    }

    @Override
    public void H() {
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.v);
    }

    public ColorDividerComponent(Color color, double d, double d2) {
        this.v = color;
        this.K = d;
        this.I = d2;
    }

    @Override
    public double x() {
        return this.I;
    }

    public static void y(int[] nArray) {
        G = nArray;
    }

    @Override
    public double L() {
        return this.C();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    static {
        if (ColorDividerComponent.j$src$AI$1b2e8rh() != null) {
            ColorDividerComponent.y(new int[5]);
        }
    }

    @Override
    public void F() {
    }

    public static int[] j$src$AI$1b2e8rh() {
        return G;
    }

    public ColorDividerComponent(Color color, double d) {
        this.v = color;
        this.K = d;
    }

    @Override
    public void u() {
    }

    @Override
    public double C() {
        return this.K;
    }
}

