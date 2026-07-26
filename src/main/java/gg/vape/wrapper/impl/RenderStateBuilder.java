package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRenderStateBuilder;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.RenderState;

public class RenderStateBuilder
extends Wrapper {
    public RenderStateBuilder(Object object) {
        super(object);
    }

    public static void t(RenderState renderState) {
        MRenderStateBuilder.E(RenderStateBuilder.c.getMappingsMapperCompat().C1, renderState.getObject());
    }
}

