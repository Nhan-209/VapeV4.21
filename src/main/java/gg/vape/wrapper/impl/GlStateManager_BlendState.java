package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.GlStateManager$BooleanState;

public class GlStateManager_BlendState
extends Wrapper {
    private GlStateManager$BooleanState f = null;


    public GlStateManager$BooleanState q() {
        if (this.f == null) {
            this.f = new GlStateManager$BooleanState(GlStateManager_BlendState.c.getMappingsMapperCompat().qG.F(this.I));
        }
        return this.f;
    }

    public GlStateManager_BlendState(Object object) {
        super(object);
    }
}

