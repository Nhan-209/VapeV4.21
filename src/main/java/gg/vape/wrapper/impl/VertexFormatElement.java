package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MVertexFormatElement;
import gg.vape.wrapper.Wrapper;

public class VertexFormatElement
extends Wrapper {
    public int b() {
        return MVertexFormatElement.w(VertexFormatElement.vapeInstance.getMappings().Dc, this.I);
    }

    public VertexFormatElement(Object object) {
        super(object);
    }

    public Object b$src$Ljava_lang_Object_$triqpo() {
        return MVertexFormatElement.d(VertexFormatElement.vapeInstance.getMappings().Dc, this.I);
    }
}

