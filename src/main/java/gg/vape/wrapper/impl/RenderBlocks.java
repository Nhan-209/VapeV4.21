package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.wrapper.Wrapper;

public class RenderBlocks
extends Wrapper {
    public void M(boolean bl) {
        Vape.INSTANCE.getMappingsMapperCompat().i.A(this.I, bl);
    }

    public RenderBlocks(Object object) {
        super(object);
    }
}

