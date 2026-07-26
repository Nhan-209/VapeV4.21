package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRenderTypeBufferBridge;
import gg.vape.wrapper.Wrapper;

public class RenderTypeBuffer
extends Wrapper {
    public RenderTypeBuffer(Object object) {
        super(object);
    }

    public void q() {
        MRenderTypeBufferBridge.d(RenderTypeBuffer.c.getMappingsMapperCompat().CU).F(this.getObject());
    }
}

