package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.wrapper.Wrapper;

public class SetVisibility
extends Wrapper {
    public SetVisibility(Object object) {
        super(object);
    }

    public void n(boolean bl) {
        Vape.INSTANCE.getMappingsMapperCompat().qy.t(this.I, bl);
    }
}

