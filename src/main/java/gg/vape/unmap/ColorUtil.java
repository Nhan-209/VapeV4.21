package gg.vape.unmap;

import gg.vape.Vape;
import gg.vape.unmap.ColorContrastUtil;
import gg.vape.utils.MathUtil;
import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Random;

public class ColorUtil {
    private static final HashMap<Integer, Color> D = new LinkedHashMap<Integer, Color>();

    public static int U(int n) {
        return ColorUtil.n(n, n, n, 255);
    }

    public static int M(int n, int n2, boolean bl, double d) {
        int n3;
        int n4 = bl ? n : n2;
        int n5 = n3 = bl ? n2 : n;
        if (ColorContrastUtil.V(n4, n3) >= d) {
            return n;
        }
        double[] dArray = new double[3];
        ColorContrastUtil.M(bl ? n4 : n3, dArray);
        double d2 = 0.0;
        double d3 = dArray[0];
        double d4 = dArray[1];
        double d5 = dArray[2];
        for (int i = 0; i < 15 && d3 - d2 > 1.0E-5; ++i) {
            double d6 = (d2 + d3) / 2.0;
            if (bl) {
                n4 = ColorContrastUtil.A(d6, d4, d5);
            } else {
                n3 = ColorContrastUtil.A(d6, d4, d5);
            }
            if (ColorContrastUtil.V(n4, n3) > d) {
                d2 = d6;
                continue;
            }
            d3 = d6;
        }
        return ColorContrastUtil.A(d2, d4, d5);
    }

    public static int n(int n, int n2, int n3, int n4) {
        int n5 = 0;
        n5 |= MathUtil.clamp(n4, 0, 255) << 24;
        n5 |= MathUtil.clamp(n, 0, 255) << 16;
        n5 |= MathUtil.clamp(n2, 0, 255) << 8;
        return n5 |= MathUtil.clamp(n3, 0, 255);
    }

    public static Color W(Color color, int n) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
    }

    public static Color r(Color color, int n, int n2) {
        return ColorUtil.s(color, n, n2, false);
    }

    public static int reAlpha(int n, int n2) {
        return ColorUtil.n(n, n, n, n2);
    }

    public static int M(int n, int n2, int n3) {
        return ColorUtil.n(n, n2, n3, 255);
    }

    public static int G(Color color) {
        return ColorUtil.n(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public static Color j() {
        return ColorUtil.r(Vape.INSTANCE.getClientSettings().w.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), 45, 240);
    }

    public static Color l(float f, float f2, float f3, int n) {
        int n2 = Objects.hash(Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), n);
        Color color = D.get(n2);
        if (color != null) {
            return color;
        }
        Color color2 = Color.getHSBColor(f, f2, f3);
        Color color3 = new Color(ColorUtil.p(color2.getRGB(), new Color(45, 45, 45).getRGB(), true, n));
        D.put(n2, color3);
        return color3;
    }

    public static Color f() {
        return ColorUtil.Y(MathUtil.random(new Random(), 0.0f, 1.0f), 0.9f, 0.9f);
    }

    public static Color X(float f, float f2, float f3) {
        float f4;
        float f5;
        float f6;
        if (f2 == 0.0f) {
            f5 = f6 = f3;
            f4 = f6;
        } else {
            float f7 = (double)f3 < 0.5 ? f3 * (1.0f + f2) : f3 + f2 - f3 * f2;
            float f8 = 2.0f * f3 - f7;
            f4 = ColorUtil.L(f8, f7, f + 0.33333334f);
            f5 = ColorUtil.L(f8, f7, f);
            f6 = ColorUtil.L(f8, f7, f - 0.33333334f);
        }
        return new Color(Math.round(f4 * 255.0f), Math.round(f5 * 255.0f), Math.round(f6 * 255.0f));
    }

    public static Color s(Color color, int n, int n2, boolean bl) {
        double d;
        int n3 = ColorUtil.B(color);
        double d2 = d = !bl && Vape.INSTANCE.getClientSettings().w.g() ? 0.0 : 130.0;
        if ((double)n3 > d) {
            return new Color(n, n, n);
        }
        return new Color(n2, n2, n2);
    }

    public static boolean G$src$Z$10d3l6y(Color color) {
        double d;
        int n = ColorUtil.B(color);
        return !((double)n > (d = 130.0));
    }

    public static Color N(Color color, double d) {
        double d2 = Math.max(0.0, Math.min(255.0, (double)color.getRed() + d));
        double d3 = Math.max(0.0, Math.min(255.0, (double)color.getGreen() + d));
        double d4 = Math.max(0.0, Math.min(255.0, (double)color.getBlue() + d));
        return new Color((int)d2, (int)d3, (int)d4, color.getAlpha());
    }

    public static int p(int n, int n2, boolean bl, double d) {
        int n3;
        int n4 = bl ? n : n2;
        int n5 = n3 = bl ? n2 : n;
        if (ColorContrastUtil.V(n4, n3) >= d) {
            return n;
        }
        float[] fArray = new float[3];
        ColorContrastUtil.F(bl ? n4 : n3, fArray);
        float f = fArray[2];
        float f2 = 1.0f;
        for (int i = 0; i < 15 && (double)(f2 - f) > 1.0E-5; ++i) {
            float f3;
            fArray[2] = f3 = (f + f2) / 2.0f;
            if (bl) {
                n4 = ColorContrastUtil.g(fArray);
            } else {
                n3 = ColorContrastUtil.g(fArray);
            }
            if (ColorContrastUtil.V(n4, n3) > d) {
                f2 = f3;
                continue;
            }
            f = f3;
        }
        return bl ? n4 : n3;
    }

    public static int B(Color color) {
        double d = (double)(color.getRed() * color.getRed()) * 0.241;
        double d2 = (double)(color.getGreen() * color.getGreen()) * 0.691;
        double d3 = (double)(color.getBlue() * color.getBlue()) * 0.068;
        double d4 = Math.sqrt(d + d2 + d3);
        return (int)d4;
    }


    private static float L(float f, float f2, float f3) {
        if (f3 < 0.0f) {
            f3 += 1.0f;
        }
        if (f3 > 1.0f) {
            f3 -= 1.0f;
        }
        if (6.0f * f3 < 1.0f) {
            return f + (f2 - f) * 6.0f * f3;
        }
        if (2.0f * f3 < 1.0f) {
            return f2;
        }
        if (3.0f * f3 < 2.0f) {
            return f + (f2 - f) * 6.0f * (0.6666667f - f3);
        }
        return f;
    }

    public static Color Y(float f, float f2, float f3) {
        return ColorUtil.l(f, f2, f3, 4);
    }
}

