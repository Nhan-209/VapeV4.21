package gg.vape.module.render;

import gg.vape.module.render.TrajectoriesArrowProjectilePrimary;
import java.awt.Color;

public class TrajectoriesProjectileRenderState {
    public Color E;
    public float q;
    public double X;
    public double H;
    public double b;
    public double N;
    public double x;
    public double f;

    private TrajectoriesProjectileRenderState(double d, double d2, double d3) {
        this.X = d;
        this.N = d2;
        this.f = d3;
    }

    private TrajectoriesProjectileRenderState(double d, double d2, double d3, double d4, double d5, double d6, float f, Color color) {
        this.X = d;
        this.N = d2;
        this.f = d3;
        this.H = d4;
        this.x = d5;
        this.b = d6;
        this.q = f;
        this.E = color;
    }

    public TrajectoriesProjectileRenderState(double d, double d2, double d3, double d4, double d5, double d6, float f, TrajectoriesArrowProjectilePrimary rK) {
        this(d, d2, d3, d4, d5, d6, f);
    }

    public TrajectoriesProjectileRenderState(double d, double d2, double d3, TrajectoriesArrowProjectilePrimary rK) {
        this(d, d2, d3);
    }

    private TrajectoriesProjectileRenderState(double d, double d2, double d3, double d4, double d5, double d6, float f) {
        this.X = d;
        this.N = d2;
        this.f = d3;
        this.H = d4;
        this.x = d5;
        this.b = d6;
        this.q = f;
    }

    public TrajectoriesProjectileRenderState(double d, double d2, double d3, double d4, double d5, double d6, float f, Color color, TrajectoriesArrowProjectilePrimary rK) {
        this(d, d2, d3, d4, d5, d6, f, color);
    }
}

