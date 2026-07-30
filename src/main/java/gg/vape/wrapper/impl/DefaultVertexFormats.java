package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MDefaultVertexFormats;
import gg.vape.wrapper.Wrapper;

public class DefaultVertexFormats
extends Wrapper {
    public static DefaultVertexFormatBridge p() {
        return new DefaultVertexFormatBridge(MDefaultVertexFormats.T(DefaultVertexFormats.vapeInstance.getMappingsMapperCompat().DC));
    }

    public DefaultVertexFormats(Object object) {
        super(object);
    }
}

