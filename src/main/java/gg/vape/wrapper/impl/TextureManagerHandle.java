package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.TextureObjectHandle;

public class TextureManagerHandle
extends Wrapper {
    public TextureObjectHandle e() {
        return new TextureObjectHandle(TextureManagerHandle.c.getMappingsMapperCompat().hL.A(this.I));
    }

    public int k() {
        TextureObjectHandle b5 = this.e();
        if (b5.isNull()) {
            return -1;
        }
        return b5.J();
    }

    public TextureObjectHandle x() {
        return new TextureObjectHandle(TextureManagerHandle.c.getMappingsMapperCompat().hL.C(this.I));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public TextureManagerHandle(Object object) {
        super(object);
    }

    public int L() {
        TextureObjectHandle b5 = this.x();
        if (b5.isNull()) {
            return -1;
        }
        return b5.J();
    }
}

