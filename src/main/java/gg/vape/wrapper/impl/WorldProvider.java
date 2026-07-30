package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class WorldProvider
extends Wrapper {
    public WorldProvider(Object object) {
        super(object);
    }

    public Object v(TickEvent_Phase wm_22) {
        return WorldProvider.vapeInstance.getMappingsMapperCompat().V.v(this.I, wm_22.getObject());
    }
}

