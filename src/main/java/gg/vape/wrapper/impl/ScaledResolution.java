package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.wrapper.impl.Minecraft;

public class ScaledResolution {
    private final double p;
    private static int G;
    private int v = 1;
    private int O = Minecraft.J();
    private int q = Minecraft.h();
    private final double N;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public double X() {
        return this.p;
    }

    public int T() {
        return this.O;
    }

    static {
        if (ScaledResolution.q() != 0) {
            ScaledResolution.r(123);
        }
    }

    public static void r(int n) {
        G = n;
    }

    public int e() {
        return this.v;
    }

    public int G() {
        return this.q;
    }

    public static int q() {
        return G;
    }

    public double U() {
        return this.N;
    }

    public ScaledResolution() {
        boolean bl = false;
        int n = Minecraft.gameSettings().T();
        if (n == 0) {
            n = 1000;
        }
        while (this.v < n && this.O / (this.v + 1) >= 320 && this.q / (this.v + 1) >= 240) {
            ++this.v;
        }
        this.N = (double)this.O / (double)this.v;
        this.p = (double)this.q / (double)this.v;
        this.O = MathUtil.ceil(this.N);
        this.q = MathUtil.ceil(this.p);
    }

    public static int W() {
        int n = ScaledResolution.q();
        if (n == 0) {
            return 80;
        }
        return 0;
    }
}

