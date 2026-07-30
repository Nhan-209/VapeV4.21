package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class GlStateManager$FogState
extends Wrapper {
    public GlStateManager$FogState(Object object) {
        super(object);
    }

    public void Q() {
        GlStateManager$FogState.vapeInstance.getMappingsMapperCompat().R.m(this.I);
    }

    public GlStateManagerFogStateBridge getFogMode() {
        return new GlStateManagerFogStateBridge(GlStateManager$FogState.vapeInstance.getMappingsMapperCompat().R.t(this.I));
    }
}

