package gg.vape.rotation;

import gg.vape.utils.MathUtil;

public class RotationAngles {
    private float L;
    public static final double y = Math.PI / 180;
    private static String p;
    public static final double x = 57.29577951308232;
    private float b;

    public boolean U(RotationAngles rotationAngles) {
        boolean bl = this.t(rotationAngles) && (double)Math.abs(this.L - rotationAngles.L) < 0.1;
        return bl;
    }


    public boolean h(RotationAngles rotationAngles, float f) {
        float f2 = Math.abs(MathUtil.wrapAngleTo180(this.b) - MathUtil.wrapAngleTo180(rotationAngles.b));
        float f3 = Math.abs(this.L - rotationAngles.L);
        boolean bl = (f2 < f || f2 > 360.0f - f) && f3 < f;
        return bl;
    }

    public boolean t(RotationAngles rotationAngles) {
        float f = Math.abs(MathUtil.wrapAngleTo180(this.b) - MathUtil.wrapAngleTo180(rotationAngles.b));
        boolean bl = (double)f < 0.1 || (double)f > 359.9;
        return bl;
    }

    public static String v() {
        return p;
    }

    public float N() {
        return this.L;
    }

    public float z() {
        return this.b;
    }

    public RotationAngles I(RotationAngles rotationAngles) {
        return new RotationAngles(this.b - rotationAngles.b, this.L - rotationAngles.L);
    }

    public RotationAngles X() {
        return new RotationAngles(MathUtil.wrapAngleTo180(this.b), this.L);
    }

    public double C() {
        return Math.abs(this.b) + Math.abs(this.L);
    }

    public RotationAngles(float f, float f2) {
        this.b = f;
        this.L = f2;
    }

    public RotationAngles(double d, double d2) {
        this((float)d, (float)d2);
    }

    public static void c(String string) {
        p = string;
    }

    public RotationAngles w(RotationAngles rotationAngles) {
        return new RotationAngles(this.b + rotationAngles.b, this.L + rotationAngles.L);
    }

    static {
        if (RotationAngles.v() != null) {
            RotationAngles.c("LPXgA");
        }
    }
}

