package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTextureObject;
import gg.vape.wrapper.Wrapper;

public class TextureObject
extends Wrapper {
    public void f(boolean bl, boolean bl2) {
        MTextureObject.k(TextureObject.vapeInstance.getMappingsMapperCompat().hG, this.I, bl, bl2);
    }

    public TextureObject(Object object) {
        super(object);
    }

    public int h() {
        if (ForgeVersion.MC_1_21_6.d()) {
            TextureObjectHandle b5 = new TextureObjectHandle(MTextureObject.B(TextureObject.vapeInstance.getMappingsMapperCompat().hG, this.I));
            return b5.J();
        }
        return MTextureObject.m(TextureObject.vapeInstance.getMappingsMapperCompat().hG, this.I);
    }
}

