package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRenderItemContext;
import gg.vape.wrapper.Wrapper;

public class RenderItemContext
extends Wrapper {
    public static RenderItemContext d() {
        return new RenderItemContext(MRenderItemContext.r(RenderItemContext.vapeInstance.getMappingsMapperCompat().A));
    }

    public RenderItemContext(Object object) {
        super(object);
    }
}

