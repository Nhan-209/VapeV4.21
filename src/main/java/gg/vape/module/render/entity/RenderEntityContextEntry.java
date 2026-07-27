package gg.vape.module.render.entity;

import gg.vape.module.render.entity.RenderEntityContext;
import gg.vape.module.render.entity.RenderEntityContextEntrySyntheticConstructorMarker;
import java.awt.Color;

public class RenderEntityContextEntry {
    private Color color;
    private final RenderEntityContext context;
    private double scale = 1.0;
    private boolean flag;

    private RenderEntityContextEntry(RenderEntityContext renderContext, Color color) {
        this.context = renderContext;
        this.color = color;
    }

    public double b() {
        return this.scale;
    }

    public boolean F() {
        return this.flag;
    }

    public RenderEntityContextEntry(RenderEntityContext renderContext, Color color, RenderEntityContextEntrySyntheticConstructorMarker uY) {
        this(renderContext, color);
    }

    public RenderEntityContext Y() {
        return this.context;
    }

    public void Q(Color color) {
        this.color = color;
    }

    public void S(double d) {
        this.scale = d;
    }

    public void I(boolean bl) {
        this.flag = bl;
    }

    public Color y() {
        return this.color;
    }
}

