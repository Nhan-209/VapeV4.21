package gg.vape.ui.click.component;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;

public class SpacerComponent
extends GuiComponent {
    private double I;
    private double G;
    private static int Q;

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    public static int S$src$I$17xdg2d() {
        int n = SpacerComponent.w$src$I$18h61fd();
        if (n == 0) {
            return 41;
        }
        return 0;
    }

    @Override
    public double C() {
        return this.I;
    }

    @Override
    public void o(double d) {
        super.o(d);
    }

    static {
        if (SpacerComponent.S$src$I$17xdg2d() != 0) {
            SpacerComponent.M(56);
        }
    }

    @Override
    public void I() {
    }

    public static int w$src$I$18h61fd() {
        return Q;
    }

    public SpacerComponent(double d, double d2) {
        this.G = d;
        this.I = d2;
        this.o(d);
        this.Y(d2);
    }

    @Override
    public void u() {
    }

    @Override
    public double x() {
        return this.G;
    }

    @Override
    public void H() {
    }


    public static void M(int n) {
        Q = n;
    }

    @Override
    public void Y(double d) {
        super.Y(d);
        this.u(d);
        this.I = d;
    }

    @Override
    public void F() {
    }

    public /* synthetic */ void void_H() {
        this.H();
    }
}

