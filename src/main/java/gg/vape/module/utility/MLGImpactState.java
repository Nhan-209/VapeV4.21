package gg.vape.module.utility;

import gg.vape.runtime.ObfuscatedRuntimeException;

public class MLGImpactState {
    public static final MLGImpactState D = new MLGImpactState(36, 44);
    private static int N;
    public static final MLGImpactState S;
    public static final MLGImpactState i;
    private final int u;
    private final int p;

    public MLGImpactState(int n, int n2) {
        this.p = n;
        this.u = n2;
    }

    public static int L() {
        int n = MLGImpactState.j();
        return 23;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public static void m(int n) {
        N = n;
    }

    public int J() {
        return this.p;
    }

    public static int j() {
        return N;
    }

    public int u() {
        return this.u;
    }

    static {
        MLGImpactState.m(0);
        i = new MLGImpactState(9, 35);
        S = new MLGImpactState(9, 44);
    }
}

