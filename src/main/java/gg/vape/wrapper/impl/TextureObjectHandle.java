package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class TextureObjectHandle
extends Wrapper {
    public int G(int n) {
        return TextureObjectHandle.c.getMappingsMapperCompat().DS.H(this.I, n);
    }

    public int J() {
        return TextureObjectHandle.c.getMappingsMapperCompat().DS.r(this.I);
    }

    public TextureObjectHandle(Object object) {
        super(object);
    }
}

