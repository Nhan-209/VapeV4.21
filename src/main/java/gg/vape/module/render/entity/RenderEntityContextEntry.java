package gg.vape.module.render.entity;

import gg.vape.module.render.entity.RenderEntityContext;
import gg.vape.module.render.entity.RenderEntityContextEntrySyntheticConstructorMarker;
import java.awt.Color;

public class RenderEntityContextEntry {
    private Color L;
    private final RenderEntityContext A;
    private double Q = 1.0;
    private boolean q;

    private RenderEntityContextEntry(RenderEntityContext i_02, Color color) {
        this.A = i_02;
        this.L = color;
    }

    public double b() {
        return this.Q;
    }

    public boolean F() {
        return this.q;
    }

    public RenderEntityContextEntry(RenderEntityContext i_02, Color color, RenderEntityContextEntrySyntheticConstructorMarker uY) {
        this(i_02, color);
    }

    public RenderEntityContext Y() {
        return this.A;
    }

    public void Q(Color color) {
        this.L = color;
    }

    public void S(double d) {
        this.Q = d;
    }

    public void I(boolean bl) {
        this.q = bl;
    }

    public Color y() {
        return this.L;
    }
}

