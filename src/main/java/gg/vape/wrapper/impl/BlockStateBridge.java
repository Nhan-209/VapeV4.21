package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class BlockStateBridge
extends Wrapper {

    public int v() {
        Object object = this.d();
        if (object == null) {
            return -1;
        }
        TextureObjectHandle b5 = new TextureObjectHandle(object);
        return b5.J();
    }

    public int D() {
        return BlockStateBridge.vapeInstance.getMappingsMapperCompat().qj.E(this.I);
    }

    public boolean n() {
        return BlockStateBridge.vapeInstance.getMappingsMapperCompat().qj.Z(this.I);
    }

    public BlockStateBridge(Object object) {
        super(object);
    }

    public int Z() {
        return BlockStateBridge.vapeInstance.getMappingsMapperCompat().qj.a(this.I);
    }

    public Object d() {
        return BlockStateBridge.vapeInstance.getMappingsMapperCompat().qj.p(this.I);
    }

    public int h(int n) {
        return BlockStateBridge.vapeInstance.getMappingsMapperCompat().qj.H(this.I, n);
    }

    public int s(int n) {
        return BlockStateBridge.vapeInstance.getMappingsMapperCompat().qj.v(this.I, n);
    }
}

