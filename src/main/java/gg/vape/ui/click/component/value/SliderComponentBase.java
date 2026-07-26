package gg.vape.ui.click.component.value;

import func.skidline.RectData;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.RenderUtils;

public abstract class SliderComponentBase
extends GuiComponent {
    protected MousePosition o;
    protected final double G = 2.0;
    private boolean i;
    private String b;
    private static int[] Q;
    protected boolean I;

    public SliderComponentBase(String string) {
        this.b = string;
    }

    public double double_u() {
        return RenderUtils.h().H - this.o.H;
    }

    public double double_P() {
        return RenderUtils.h().O - this.o.O;
    }

    public String java_lang_String_W() {
        return this.b;
    }

    public double Y(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d6 / d3 * 100.0;
        if (d7 <= 0.0) {
            return d;
        }
        if (d7 >= 100.0) {
            return d2;
        }
        d7 = Math.min(d7, 100.0);
        d7 = Math.max(d7, 0.0);
        double d8 = d5 * d7 + d5;
        double d9 = d8 % d4;
        double d10 = d8 - d9;
        return d10 + d;
    }

    public static void c(int[] nArray) {
        Q = nArray;
    }

    public void c(String string) {
        this.b = string;
    }

    public static int[] int_arr_L() {
        return Q;
    }

    public boolean boolean_W() {
        return this.I;
    }

    public double h(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = (d2 - d) / d5;
        double d8 = (d4 - d3) / d7;
        double d9 = d6 / d8;
        double d10 = d9 * d5;
        return Math.min(Math.max(d10 + d, d), d2);
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public RectData L(double d, double d2, double d3) {
        return new RectData(d - d3, d2 - d3, d3 * 2.0, d3 * 2.0);
    }

    static {
        if (SliderComponentBase.int_arr_L() == null) {
            SliderComponentBase.c(new int[2]);
        }
    }

    public /* synthetic */ double P$src$D$34o7qt() {
        return this.double_P();
    }

    public /* synthetic */ String W$src$Ljava_lang_String_$24bvf0() {
        return this.java_lang_String_W();
    }

    public /* synthetic */ boolean W$src$Z$38isfa() {
        return this.boolean_W();
    }
}

