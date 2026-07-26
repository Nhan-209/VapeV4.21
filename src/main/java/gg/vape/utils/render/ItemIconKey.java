package gg.vape.utils.render;

import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.Objects;

public class ItemIconKey {
    private int u;
    private int l;
    private int F;
    private float Q = 1.0f;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public boolean equals(Object object) {
        if (object instanceof ItemIconKey) {
            boolean bl = ((ItemIconKey)object).A() == this.A() && ((ItemIconKey)object).E() == this.E() && ((ItemIconKey)object).w() == this.w() && ((ItemIconKey)object).q() == this.q();
            return bl;
        }
        return false;
    }

    public int A() {
        return this.l;
    }

    public ItemIconKey(int n, int n2, float f) {
        this.l = n;
        this.F = n2;
        this.Q = f;
    }

    public int hashCode() {
        return Objects.hash(this.A(), this.E(), this.q());
    }

    public float w() {
        return this.Q;
    }

    public int q() {
        return this.u;
    }

    public int E() {
        return this.F;
    }

    public void a(int n) {
        this.u = n;
    }
}

