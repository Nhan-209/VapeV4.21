package gg.vape.value;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.value.ColorValue;
import gg.vape.value.Value;
import java.text.DecimalFormat;

public class NumberValue
extends Value<Double, NumberValue> {
    private static final String d;
    private final String Q;
    private static int[] D;
    private final DecimalFormat Z;
    private double J = 0.01;
    private final String o;
    private final double G;
    private final double r;
    private final DecimalFormat f;
    private double A = 999999.0;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public String T() {
        return this.Q;
    }

    public NumberValue C(int n) {
        this.Z.setMaximumFractionDigits(n);
        return this;
    }

    public static NumberValue create(Object object, String string, String string2, String string3, String string4, double d, double d2, double d3) {
        return new NumberValue(object, string, d2, d, d3, string3, string4);
    }

    public static NumberValue create(Object object, String string, String string2, String string3, double d, double d2, double d3, double d4) {
        NumberValue numberValue = new NumberValue(object, string, d2, d, d3, string2, string3);
        numberValue.J = d4;
        return numberValue;
    }

    public DecimalFormat Q$src$Ljava_text_DecimalFormat_$j98hth() {
        return this.f;
    }

    public double S$src$D$10pa1t3() {
        return this.G;
    }

    @Override
    public void parse(String string) {
        if (string.isEmpty()) {
            return;
        }
        this.e(Double.parseDouble(string));
    }

    public static NumberValue create(Object object, String string, String string2, String string3, double d, double d2, double d3, double d4, String string4) {
        NumberValue numberValue = new NumberValue(object, string, d2, d, d3, string2, string3);
        numberValue.Z$src$Lgg_vape_value_Value_$16i62fx(string4);
        numberValue.J = d4;
        return numberValue;
    }

    public static NumberValue create(Object object, String string, String string2, String string3, double d, double d2, double d3) {
        return new NumberValue(object, string, d2, d, d3, string2, string3);
    }

    @Override
    public String c() {
        String string = String.valueOf(this.K());
        string = this.Z.format(this.K());
        return string;
    }

    @Override
    public void A(Double d) {
        if (d > this.A) {
            d = this.A;
        } else if (d < -this.A) {
            d = -this.A;
        }
        super.o(MathUtil.roundToIncrement(d, this.J));
        this.g$src$V$1akzyia();
        if (this.k$src$Ljava_lang_Object_$13p7u5q() instanceof ColorValue) {
            ((ColorValue)this.k$src$Ljava_lang_Object_$13p7u5q()).a();
        }
    }

    public static NumberValue E(Object object, String string, String string2, String string3, double d, double d2, double d3, String string4) {
        return (NumberValue)new NumberValue(object, string, d2, d, d3, string2, string3).Z$src$Lgg_vape_value_Value_$16i62fx(string4);
    }

    public void e(Double d) {
        super.o(d);
        this.g$src$V$1akzyia();
    }

    public double K$src$D$10kvp27() {
        return this.J;
    }

    public void P(Double d) {
        double d2 = (double)Math.round(d * 100.0) / 100.0;
        super.A(d2);
    }

    public static void Y(int[] nArray) {
        D = nArray;
    }

    public double Q$src$D$10o6gmd() {
        return this.r;
    }

    public void m(double d) {
        this.A = d;
    }

    static {
        NumberValue.Y(null);
        d = "#.##";
    }

    private DecimalFormat c$src$Ljava_text_DecimalFormat_$choocn() {
        DecimalFormat decimalFormat;
        try {
            decimalFormat = new DecimalFormat(this.o);
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
            decimalFormat = new DecimalFormat(d);
        }
        decimalFormat.setMinimumIntegerDigits(1);
        return decimalFormat;
    }

    public static int[] O$src$AI$1yesq9t() {
        return D;
    }

    public NumberValue A() {
        return new NumberValue(null, this.getName(), (Double)this.K(), this.G, this.r, this.o, this.Q);
    }

    @Override
    public NumberValue getALimit() {
        return this.A();
    }

    public NumberValue(Object object, String string, double d, double d2, double d3, String string2, String string3) {
        super(object, string, d);
        this.G = d2;
        this.r = d3;
        if (!string3.isEmpty()) {
            string3 = " " + string3;
        }
        this.Q = string3;
        this.o = string2;
        this.f = this.c$src$Ljava_text_DecimalFormat_$choocn();
        this.Z = this.c$src$Ljava_text_DecimalFormat_$choocn();
    }
}
