package gg.vape.utils.math;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.wrapper.impl.Vec3;
import java.lang.invoke.MethodHandles;
import java.util.function.Consumer;

public class NumericMathUtil {
    private static final double g;
    private static final long a;
    private static final double[] P;
    private static final double[] x;
    private static final float[] E;

    public static boolean F(double d, double d2) {
        return Math.abs(d2 - d) < (double)1.0E-5f;
    }

    private static void lambda$static$0(float[] fArray) {
        for (int i = 0; i < fArray.length; ++i) {
            fArray[i] = (float)Math.sin((double)i * Math.PI * 2.0 / 65536.0);
        }
    }

    public static <T> T S(T t, Consumer<? super T> consumer) {
        consumer.accept(t);
        return t;
    }

    public static Vec3 y(double d, Vec3 vec3, Vec3 vec32) {
        return Vec3.create(NumericMathUtil.S(d, vec3.getX(), vec32.getX()), NumericMathUtil.S(d, vec3.getY(), vec32.getY()), NumericMathUtil.S(d, vec3.getZ(), vec32.getZ()));
    }

    public static int f(float f) {
        int n = (int)f;
        return f < (float)n ? n - 1 : n;
    }

    public static float S(float f) {
        return Math.abs(f);
    }

    public static double S(double d, double d2, double d3) {
        return d2 + d * (d3 - d2);
    }

    @Deprecated
    public static double E(double d) {
        double d2 = 0.5 * d;
        long l = Double.doubleToRawLongBits(d);
        l = 6910469410427058090L - (l >> 1);
        d = Double.longBitsToDouble(l);
        return d * (1.5 - d2 * d * d);
    }

    public static long D(long l) {
        return l * l;
    }

    public static double V(double d, double d2) {
        double d3;
        boolean bl;
        boolean bl2;
        boolean bl3;
        double d4 = d2 * d2 + d * d;
        if (Double.isNaN(d4)) {
            return Double.NaN;
        }
        boolean bl4 = bl3 = d < 0.0;
        if (bl3) {
            d = -d;
        }
        boolean bl5 = bl2 = d2 < 0.0;
        if (bl2) {
            d2 = -d2;
        }
        boolean bl6 = bl = d > d2;
        if (bl) {
            d3 = d2;
            d2 = d;
            d = d3;
        }
        d3 = NumericMathUtil.E(d4);
        d2 *= d3;
        double d5 = g + (d *= d3);
        int n = (int)Double.doubleToRawLongBits(d5);
        double d6 = P[n];
        double d7 = x[n];
        double d8 = d5 - g;
        double d9 = d * d7 - d2 * d8;
        double d10 = (6.0 + d9 * d9) * d9 * 0.16666666666666666;
        double d11 = d6 + d10;
        if (bl) {
            d11 = 1.5707963267948966 - d11;
        }
        if (bl2) {
            d11 = Math.PI - d11;
        }
        if (bl3) {
            d11 = -d11;
        }
        return d11;
    }

    public static float D(float f, float f2, float f3) {
        return f2 + f * (f3 - f2);
    }

    public static float w(float f, float f2, float f3) {
        return f < f2 ? f2 : Math.min(f, f3);
    }

    public static float k(long l) {
        float f = l % 360L;
        if (f >= 180.0f) {
            f -= 360.0f;
        }
        if (f < -180.0f) {
            f += 360.0f;
        }
        return f;
    }

    public static double X(double d) {
        return d * d;
    }

    public static int r(int n) {
        int n2 = n % 360;
        if (n2 >= 180) {
            n2 -= 360;
        }
        if (n2 < -180) {
            n2 += 360;
        }
        return n2;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static float A(float f) {
        return (float)Math.sqrt(f);
    }

    public static int d(float f) {
        int n = (int)f;
        return f > (float)n ? n + 1 : n;
    }

    public static int r(double d) {
        int n = (int)d;
        return d < (double)n ? n - 1 : n;
    }

    public static long m(double d) {
        long l = (long)d;
        return d < (double)l ? l - 1L : l;
    }

    public static float y(float f) {
        long l = a ^ 0x193C4105DAB3L;
        return E[(int)(f * 10430.378f + 16384.0f) & 0xFFFF];
    }

    public static int K(double d) {
        int n = (int)d;
        return d > (double)n ? n + 1 : n;
    }

    static {
        long l = a = ZkmLongKeyState.a(8424831522669036547L, 1107840836664613920L, MethodHandles.lookup().lookupClass()).a(259390716306152L);
        E = NumericMathUtil.S(new float[65536], NumericMathUtil::lambda$static$0);
        g = Double.longBitsToDouble(4805340802404319232L);
        P = new double[257];
        x = new double[257];
    }

    public static int t(int n, int n2, int n3) {
        return Math.min(Math.max(n, n2), n3);
    }

    public static double W(double d, double d2, double d3) {
        return d < d2 ? d2 : Math.min(d, d3);
    }

    public static float Q(float f) {
        return f * f;
    }

    public static boolean f(float f, float f2) {
        return Math.abs(f2 - f) < 1.0E-5f;
    }

    public static float X(float f) {
        long l = a ^ 0x462724DE8C6BL;
        return E[(int)(f * 10430.378f) & 0xFFFF];
    }

    public static int g(int n) {
        return Math.abs(n);
    }

    public static double S(double d) {
        double d2 = d % 360.0;
        if (d2 >= 180.0) {
            d2 -= 360.0;
        }
        if (d2 < -180.0) {
            d2 += 360.0;
        }
        return d2;
    }

    public static long c(long l, long l2, long l3) {
        return Math.min(Math.max(l, l2), l3);
    }

    public static int U(int n) {
        return n * n;
    }

    public static float F(float f) {
        float f2 = f % 360.0f;
        if (f2 >= 180.0f) {
            f2 -= 360.0f;
        }
        if (f2 < -180.0f) {
            f2 += 360.0f;
        }
        return f2;
    }
}
