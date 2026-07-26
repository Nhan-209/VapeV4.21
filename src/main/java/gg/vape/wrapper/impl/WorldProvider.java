package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.TickEvent_Phase;

public class WorldProvider
extends Wrapper {
    public WorldProvider(Object object) {
        super(object);
    }

    public Object v(TickEvent_Phase wm_22) {
        return WorldProvider.c.getMappingsMapperCompat().V.v(this.I, wm_22.getObject());
    }
}

