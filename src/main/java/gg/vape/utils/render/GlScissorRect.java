package gg.vape.utils.render;

import gg.vape.runtime.ObfuscatedRuntimeException;

public class GlScissorRect {
    public int I;
    public int F;
    public int f;
    public int v;

    public GlScissorRect(int n, int n2, int n3, int n4) {
        this.v = n;
        this.F = n2;
        this.I = n3;
        this.f = n4;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        GlScissorRect glScissorRect = (GlScissorRect)object;
        return this.v == glScissorRect.v && this.F == glScissorRect.F && this.I == glScissorRect.I && this.f == glScissorRect.f;
    }

    public String toString() {
        return "ScissorCoords{x=" + this.v + ", y=" + this.F + ", w=" + this.I + ", h=" + this.f + '}';
    }
}

