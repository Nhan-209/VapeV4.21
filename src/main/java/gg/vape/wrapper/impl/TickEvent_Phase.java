package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTickEvent_Phase;
import gg.vape.wrapper.Wrapper;

public class TickEvent_Phase
extends Wrapper {
    public static TickEvent_Phase F() {
        return new TickEvent_Phase(MTickEvent_Phase.i(TickEvent_Phase.vapeInstance.getMappingsMapperCompat().hz));
    }

    public TickEvent_Phase(Object object) {
        super(object);
    }

    public static TickEvent_Phase r() {
        return new TickEvent_Phase(MTickEvent_Phase.a(TickEvent_Phase.vapeInstance.getMappingsMapperCompat().hz));
    }
}

