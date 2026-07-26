package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.TextureObjectHandle;

public class BlockStateBridge
extends Wrapper {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public int v() {
        Object object = this.d();
        if (object == null) {
            return -1;
        }
        TextureObjectHandle b5 = new TextureObjectHandle(object);
        return b5.J();
    }

    public int D() {
        return BlockStateBridge.c.getMappingsMapperCompat().qj.E(this.I);
    }

    public boolean n() {
        return BlockStateBridge.c.getMappingsMapperCompat().qj.Z(this.I);
    }

    public BlockStateBridge(Object object) {
        super(object);
    }

    public int Z() {
        return BlockStateBridge.c.getMappingsMapperCompat().qj.a(this.I);
    }

    public Object d() {
        return BlockStateBridge.c.getMappingsMapperCompat().qj.p(this.I);
    }

    public int h(int n) {
        return BlockStateBridge.c.getMappingsMapperCompat().qj.H(this.I, n);
    }

    public int s(int n) {
        return BlockStateBridge.c.getMappingsMapperCompat().qj.v(this.I, n);
    }
}

