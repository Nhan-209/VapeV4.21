package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class GlStateManager$TextureState
extends Wrapper {
    public GlStateManager$TextureState(Object object) {
        super(object);
    }

    public int P() {
        return GlStateManager$TextureState.vapeInstance.getMappingsMapperCompat().hH.R(this.I);
    }

    public void H(int n) {
        GlStateManager$TextureState.vapeInstance.getMappingsMapperCompat().hH.g(this.I, n);
    }
}

