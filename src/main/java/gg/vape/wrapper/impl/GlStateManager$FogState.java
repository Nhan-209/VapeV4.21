package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.GlStateManagerFogStateBridge;

public class GlStateManager$FogState
extends Wrapper {
    public GlStateManager$FogState(Object object) {
        super(object);
    }

    public void Q() {
        GlStateManager$FogState.c.getMappingsMapperCompat().R.m(this.I);
    }

    public GlStateManagerFogStateBridge getFogMode() {
        return new GlStateManagerFogStateBridge(GlStateManager$FogState.c.getMappingsMapperCompat().R.t(this.I));
    }
}

