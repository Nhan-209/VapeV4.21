package gg.vape.module.utility;

import gg.vape.runtime.ObfuscatedRuntimeException;

public class MLGImpactState {
    public static final MLGImpactState D = new MLGImpactState(36, 44);
    private static int callCounter;
    public static final MLGImpactState S;
    public static final MLGImpactState i;
    private final int secondaryValue;
    private final int primaryValue;

    public MLGImpactState(int n, int n2) {
        this.primaryValue = n;
        this.secondaryValue = n2;
    }

    public static int computeThreshold() {
        int n = MLGImpactState.getCallCounter();
        return 23;
    }

    private static ObfuscatedRuntimeException passThrough(ObfuscatedRuntimeException error) {
        return error;
    }

    public static void setCallCounter(int n) {
        callCounter = n;
    }

    public int J() {
        return this.primaryValue;
    }

    public static int getCallCounter() {
        return callCounter;
    }

    public int u() {
        return this.secondaryValue;
    }

    static {
        MLGImpactState.setCallCounter(0);
        i = new MLGImpactState(9, 35);
        S = new MLGImpactState(9, 44);
    }
}

