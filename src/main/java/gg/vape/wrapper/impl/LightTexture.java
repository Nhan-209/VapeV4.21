package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MLightTexture;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class LightTexture
extends Wrapper {
    public void Z(float f) {
        MLightTexture.B(LightTexture.c.getMappingsMapperCompat().hF, this.I, f);
    }

    public void V() {
        if (ForgeVersion.MC_1_21_11.d()) {
            return;
        }
        MLightTexture.f(LightTexture.c.getMappingsMapperCompat().hF, this.I);
    }

    public LightTexture(Object object) {
        super(object);
    }


    public void X() {
        if (ForgeVersion.MC_1_21_11.d()) {
            return;
        }
        MLightTexture.z(LightTexture.c.getMappingsMapperCompat().hF, this.I);
    }
}

