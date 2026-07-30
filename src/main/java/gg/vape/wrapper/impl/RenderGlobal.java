package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRenderGlobal;
import gg.vape.wrapper.Wrapper;

public class RenderGlobal
extends Wrapper {
    public RenderGlobal(Object object) {
        super(object);
    }

    public void loadRenderers() {
        MRenderGlobal.d(RenderGlobal.vapeInstance.getMappingsMapperCompat().Df, this.I);
    }
}

