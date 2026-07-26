package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AtomicReferenceArrayBridge;

public class GlStateManager$BlendState
extends Wrapper {
    public AtomicReferenceArrayBridge X() {
        return new AtomicReferenceArrayBridge(GlStateManager$BlendState.c.getMappingsMapperCompat().Cw.v(this.I));
    }

    public GlStateManager$BlendState(Object object) {
        super(object);
    }
}

