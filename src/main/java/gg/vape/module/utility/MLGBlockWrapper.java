package gg.vape.module.utility;

import gg.vape.mapping.mappings.MMLGBlockWrapper;
import gg.vape.wrapper.Wrapper;

public class MLGBlockWrapper
extends Wrapper {
    public MLGBlockWrapper(Object object) {
        super(object);
    }

    public static Object t() {
        return MMLGBlockWrapper.m(MLGBlockWrapper.c.getMappingsMapperCompat().Rj);
    }

    public static Object f() {
        return MMLGBlockWrapper.a(MLGBlockWrapper.c.getMappingsMapperCompat().Rj);
    }
}

