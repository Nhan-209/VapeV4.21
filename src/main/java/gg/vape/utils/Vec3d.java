package gg.vape.utils;

import gg.vape.wrapper.impl.Vec3;

public class Vec3d {
    public double i;
    private static String o;
    public double B;
    public double H;

    public void u(double d) {
        this.i = d;
    }

    public Vec3d(double d, double d2, double d3) {
        this.H = d;
        this.B = d2;
        this.i = d3;
    }

    public Vec3 n() {
        return Vec3.create(this.H, this.B, this.i);
    }

    public double o() {
        return this.i;
    }

    public void Y(float f) {
        float f2 = (float)Math.cos(f);
        float f3 = (float)Math.sin(f);
        double d = this.H * (double)f2 + this.i * (double)f3;
        double d2 = this.B;
        double d3 = this.i * (double)f2 - this.H * (double)f3;
        this.H = d;
        this.B = d2;
        this.i = d3;
    }

    public void N(double d) {
        this.H = d;
    }

    public double T() {
        return Math.sqrt(this.H * this.H + this.B * this.B + this.i * this.i);
    }

    public static void w(String string) {
        o = string;
    }

    public void B(double d) {
        this.H *= d;
        this.B *= d;
        this.i *= d;
    }

    public void k(float f) {
        float f2 = (float)Math.cos(f);
        float f3 = (float)Math.sin(f);
        double d = this.H;
        double d2 = this.B * (double)f2 + this.i * (double)f3;
        double d3 = this.i * (double)f2 - this.B * (double)f3;
        this.H = d;
        this.B = d2;
        this.i = d3;
    }

    public Vec3d(Vec3 vec3) {
        this.H = vec3.getX();
        this.B = vec3.getY();
        this.i = vec3.getZ();
    }

    public static String x() {
        return o;
    }

    public double t() {
        return this.B;
    }

    public void u(double d, double d2, double d3) {
        this.H += d;
        this.B += d2;
        this.i += d3;
    }

    public void u(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d.B * vec3d2.i - vec3d.i * vec3d2.B;
        double d2 = vec3d2.H * vec3d.i - vec3d2.i * vec3d.H;
        this.i = vec3d.H * vec3d2.B - vec3d.B * vec3d2.H;
        this.H = d;
        this.B = d2;
    }

    public void j(double d, double d2, double d3) {
        this.H -= d;
        this.B -= d2;
        this.i -= d3;
    }

    public void x(Vec3d vec3d) {
        this.H += vec3d.H;
        this.B += vec3d.B;
        this.i += vec3d.i;
    }

    public double e(Vec3d vec3d) {
        double d = this.H - vec3d.H;
        double d2 = this.B - vec3d.B;
        double d3 = this.i - vec3d.i;
        return Math.sqrt(d * d + d2 * d2 + d3 * d3);
    }

    public void P(Vec3d vec3d) {
        this.H -= vec3d.H;
        this.B -= vec3d.B;
        this.i -= vec3d.i;
    }

    public void j(double d) {
        this.B = d;
    }

    public void B(double d, double d2, double d3) {
        this.H = d;
        this.B = d2;
        this.i = d3;
    }

    public void l() {
        double d = 1.0 / this.T();
        this.H *= d;
        this.B *= d;
        this.i *= d;
    }

    public Vec3d() {
        this.H = 0.0;
        this.B = 0.0;
        this.i = 0.0;
    }

    public double z(Vec3d vec3d) {
        return this.H * vec3d.H + this.B * vec3d.B + this.i * vec3d.i;
    }

    public double Y() {
        return this.H;
    }

    static {
        if (Vec3d.x() == null) {
            Vec3d.w("hgFcEc");
        }
    }
}

