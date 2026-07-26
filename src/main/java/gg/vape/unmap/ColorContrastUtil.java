package gg.vape.unmap;

import java.awt.Color;

class ColorContrastUtil {
    private static final int D = 1;
    private static final double U = 100.0;
    private static final ThreadLocal<double[]> Y;
    private static final double n = 108.883;
    private static final double G = 95.047;
    private static final double W = 903.3;
    private static final int P;
    private static final double k = 0.008856;

    static {
        long l = 8524902361867485194L;
        P = (int)l;
        Y = new ThreadLocal();
    }

    private static double V(double d) {
        return d > 0.008856 ? Math.pow(d, 0.3333333333333333) : (903.3 * d + 16.0) / 116.0;
    }

    public static void M(int n, double[] dArray) {
        Color color = new Color(n);
        ColorContrastUtil.h(color.getRed(), color.getGreen(), color.getBlue(), dArray);
    }

    public static int t(double d, double d2, double d3) {
        double d4 = (d * 3.2406 + d2 * -1.5372 + d3 * -0.4986) / 100.0;
        double d5 = (d * -0.9689 + d2 * 1.8758 + d3 * 0.0415) / 100.0;
        double d6 = (d * 0.0557 + d2 * -0.204 + d3 * 1.057) / 100.0;
        d4 = d4 > 0.0031308 ? 1.055 * Math.pow(d4, 0.4166666666666667) - 0.055 : 12.92 * d4;
        d5 = d5 > 0.0031308 ? 1.055 * Math.pow(d5, 0.4166666666666667) - 0.055 : 12.92 * d5;
        d6 = d6 > 0.0031308 ? 1.055 * Math.pow(d6, 0.4166666666666667) - 0.055 : 12.92 * d6;
        return new Color(ColorContrastUtil.w((int)Math.round(d4 * 255.0), 0, 255), ColorContrastUtil.w((int)Math.round(d5 * 255.0), 0, 255), ColorContrastUtil.w((int)Math.round(d6 * 255.0), 0, 255)).getRGB();
    }

    public static int g(float[] fArray) {
        float f = fArray[0];
        float f2 = fArray[1];
        float f3 = fArray[2];
        float f4 = (1.0f - Math.abs(2.0f * f3 - 1.0f)) * f2;
        float f5 = f3 - 0.5f * f4;
        float f6 = f4 * (1.0f - Math.abs(f / 60.0f % 2.0f - 1.0f));
        int n = (int)f / 60;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        switch (n) {
            case 0: {
                n2 = Math.round(255.0f * (f4 + f5));
                n3 = Math.round(255.0f * (f6 + f5));
                n4 = Math.round(255.0f * f5);
                break;
            }
            case 1: {
                n2 = Math.round(255.0f * (f6 + f5));
                n3 = Math.round(255.0f * (f4 + f5));
                n4 = Math.round(255.0f * f5);
                break;
            }
            case 2: {
                n2 = Math.round(255.0f * f5);
                n3 = Math.round(255.0f * (f4 + f5));
                n4 = Math.round(255.0f * (f6 + f5));
                break;
            }
            case 3: {
                n2 = Math.round(255.0f * f5);
                n3 = Math.round(255.0f * (f6 + f5));
                n4 = Math.round(255.0f * (f4 + f5));
                break;
            }
            case 4: {
                n2 = Math.round(255.0f * (f6 + f5));
                n3 = Math.round(255.0f * f5);
                n4 = Math.round(255.0f * (f4 + f5));
                break;
            }
            case 5: 
            case 6: {
                n2 = Math.round(255.0f * (f4 + f5));
                n3 = Math.round(255.0f * f5);
                n4 = Math.round(255.0f * (f6 + f5));
            }
        }
        n2 = ColorContrastUtil.w(n2, 0, 255);
        n3 = ColorContrastUtil.w(n3, 0, 255);
        n4 = ColorContrastUtil.w(n4, 0, 255);
        return new Color(n2, n3, n4).getRGB();
    }

    public static void H(int n, int n2, int n3, double[] dArray) {
        if (dArray.length != 3) {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        double d = (double)n / 255.0;
        d = d < 0.04045 ? d / 12.92 : Math.pow((d + 0.055) / 1.055, 2.4);
        double d2 = (double)n2 / 255.0;
        d2 = d2 < 0.04045 ? d2 / 12.92 : Math.pow((d2 + 0.055) / 1.055, 2.4);
        double d3 = (double)n3 / 255.0;
        d3 = d3 < 0.04045 ? d3 / 12.92 : Math.pow((d3 + 0.055) / 1.055, 2.4);
        dArray[0] = 100.0 * (d * 0.4124 + d2 * 0.3576 + d3 * 0.1805);
        dArray[1] = 100.0 * (d * 0.2126 + d2 * 0.7152 + d3 * 0.0722);
        dArray[2] = 100.0 * (d * 0.0193 + d2 * 0.1192 + d3 * 0.9505);
    }

    private static float S(float f, float f2, float f3) {
        return f < f2 ? f2 : (f > f3 ? f3 : f);
    }

    public static void p(int n, double[] dArray) {
        Color color = new Color(n);
        ColorContrastUtil.H(color.getRed(), color.getGreen(), color.getBlue(), dArray);
    }

    private ColorContrastUtil() {
    }

    public static double V(int n, int n2) {
        n = ColorContrastUtil.k(n, n2);
        double d = ColorContrastUtil.K(n) + 0.05;
        double d2 = ColorContrastUtil.K(n2) + 0.05;
        return Math.max(d, d2) / Math.min(d, d2);
    }

    private static int w(int n, int n2, int n3) {
        return n < n2 ? n2 : (n > n3 ? n3 : n);
    }

    public static void j(double d, double d2, double d3, double[] dArray) {
        if (dArray.length != 3) {
            throw new IllegalArgumentException("outLab must have a length of 3.");
        }
        d = ColorContrastUtil.V(d / 95.047);
        d2 = ColorContrastUtil.V(d2 / 100.0);
        d3 = ColorContrastUtil.V(d3 / 108.883);
        dArray[0] = Math.max(0.0, 116.0 * d2 - 16.0);
        dArray[1] = 500.0 * (d - d2);
        dArray[2] = 200.0 * (d2 - d3);
    }

    public static void h(int n, int n2, int n3, double[] dArray) {
        ColorContrastUtil.H(n, n2, n3, dArray);
        ColorContrastUtil.j(dArray[0], dArray[1], dArray[2], dArray);
    }

    public static void F(int n, float[] fArray) {
        Color color = new Color(n);
        ColorContrastUtil.P(color.getRed(), color.getGreen(), color.getBlue(), fArray);
    }

    public static void P(int n, int n2, int n3, float[] fArray) {
        float f;
        float f2;
        float f3 = (float)n / 255.0f;
        float f4 = (float)n2 / 255.0f;
        float f5 = (float)n3 / 255.0f;
        float f6 = Math.max(f3, Math.max(f4, f5));
        float f7 = Math.min(f3, Math.min(f4, f5));
        float f8 = f6 - f7;
        float f9 = (f6 + f7) / 2.0f;
        if (f6 == f7) {
            f2 = 0.0f;
            f = 0.0f;
        } else {
            f = f6 == f3 ? (f4 - f5) / f8 % 6.0f : (f6 == f4 ? (f5 - f3) / f8 + 2.0f : (f3 - f4) / f8 + 4.0f);
            f2 = f8 / (1.0f - Math.abs(2.0f * f9 - 1.0f));
        }
        f = f * 60.0f % 360.0f;
        if (f < 0.0f) {
            f += 360.0f;
        }
        fArray[0] = ColorContrastUtil.S(f, 0.0f, 360.0f);
        fArray[1] = ColorContrastUtil.S(f2, 0.0f, 1.0f);
        fArray[2] = ColorContrastUtil.S(f9, 0.0f, 1.0f);
    }

    public static void G(double d, double d2, double d3, double[] dArray) {
        double d4 = (d + 16.0) / 116.0;
        double d5 = d2 / 500.0 + d4;
        double d6 = d4 - d3 / 200.0;
        double d7 = Math.pow(d5, 3.0);
        double d8 = d7 > 0.008856 ? d7 : (116.0 * d5 - 16.0) / 903.3;
        double d9 = d > 7.9996247999999985 ? Math.pow(d4, 3.0) : d / 903.3;
        d7 = Math.pow(d6, 3.0);
        double d10 = d7 > 0.008856 ? d7 : (116.0 * d6 - 16.0) / 903.3;
        dArray[0] = d8 * 95.047;
        dArray[1] = d9 * 100.0;
        dArray[2] = d10 * 108.883;
    }

    public static int A(double d, double d2, double d3) {
        double[] dArray = ColorContrastUtil.q();
        ColorContrastUtil.G(d, d2, d3, dArray);
        return ColorContrastUtil.t(dArray[0], dArray[1], dArray[2]);
    }

    private static IllegalArgumentException a(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException;
    }

    public static double[] q() {
        double[] dArray = Y.get();
        if (dArray == null) {
            dArray = new double[3];
            Y.set(dArray);
        }
        return dArray;
    }

    public static int k(int n, int n2) {
        int n3 = 255;
        int n4 = 255;
        int n5 = ColorContrastUtil.Z(n4, n3);
        Color color = new Color(n);
        Color color2 = new Color(n2);
        int n6 = ColorContrastUtil.G(color.getRed(), n4, color2.getRed(), n3, n5);
        int n7 = ColorContrastUtil.G(color.getGreen(), n4, color2.getGreen(), n3, n5);
        int n8 = ColorContrastUtil.G(color.getBlue(), n4, color2.getBlue(), n3, n5);
        return new Color(n6, n7, n8).getRGB();
    }

    private static int G(int n, int n2, int n3, int n4, int n5) {
        if (n5 == 0) {
            return 0;
        }
        return (255 * n * n2 + n3 * n4 * (255 - n2)) / (n5 * 255);
    }

    private static int Z(int n, int n2) {
        return 255 - (255 - n2) * (255 - n) / 255;
    }

    public static double K(int n) {
        double[] dArray = ColorContrastUtil.q();
        ColorContrastUtil.p(n, dArray);
        return dArray[1] / 100.0;
    }
}

