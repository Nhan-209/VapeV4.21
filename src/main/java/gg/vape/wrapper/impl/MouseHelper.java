package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MMouseHelper;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;

public class MouseHelper
extends Wrapper {
    public void D(boolean bl) {
        MMouseHelper.w(MouseHelper.c.getMappings().qI, this.I, bl);
    }

    public int getDeltaX() {
        return MMouseHelper.N(MouseHelper.c.getMappings().qI, this.I);
    }

    public int getDeltaY() {
        return MMouseHelper.J(MouseHelper.c.getMappings().qI, this.I);
    }

    public MouseHelper(Object object) {
        super(object);
    }

    public int z() {
        return MMouseHelper.x(MouseHelper.c.getMappings().qI, this.I);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void H(int n) {
        MMouseHelper.r(MouseHelper.c.getMappings().qI, this.I, n);
    }

    public void Q() {
        this.D(!this.x());
    }

    public boolean x() {
        return MMouseHelper.H$src$Z$1w8ze45(MouseHelper.c.getMappings().qI, this.I);
    }

    public int e() {
        return MMouseHelper.H(MouseHelper.c.getMappings().qI, this.I);
    }

    public boolean z$src$Z$14t0goe() {
        return MMouseHelper.E(MouseHelper.c.getMappings().qI, this.I);
    }

    public int R() {
        return MMouseHelper.t(MouseHelper.c.getMappings().qI, this.I);
    }

    public void a(int n) {
        MMouseHelper.i(MouseHelper.c.getMappings().qI, this.I, n);
    }

    public long e$src$J$14hgru1() {
        return MMouseHelper.T(MouseHelper.c.getMappings().qI, this.I);
    }

    public int I() {
        return MMouseHelper.z(MouseHelper.c.getMappings().qI, this.I);
    }

    public int k(int n, boolean bl) {
        return MMouseHelper.m(MouseHelper.c.getMappings().qI, this.I, n, bl);
    }

    public int P() {
        return MMouseHelper.M(MouseHelper.c.getMappings().qI, this.I);
    }
}

