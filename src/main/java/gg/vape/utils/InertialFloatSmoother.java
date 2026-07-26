package gg.vape.utils;

import gg.vape.runtime.ObfuscatedRuntimeException;

public class InertialFloatSmoother {
    private float v;
    private float C;
    private float P;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public float E(float f, float f2) {
        this.v += f;
        f = (this.v - this.C) * f2;
        this.P += (f - this.P) * 0.5f;
        if (f > 0.0f && f > this.P || f < 0.0f && f < this.P) {
            f = this.P;
        }
        this.C += f;
        return f;
    }
}

