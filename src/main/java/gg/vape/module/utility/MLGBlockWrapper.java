package gg.vape.module.utility;

import gg.vape.mapping.mappings.MMLGBlockWrapper;
import gg.vape.wrapper.Wrapper;

public class MLGBlockWrapper
extends Wrapper {
    public MLGBlockWrapper(Object handle) {
        super(handle);
    }

    public static Object getWaterBlock() {
        return MMLGBlockWrapper.m(MLGBlockWrapper.vapeInstance.getMappingsMapperCompat().Rj);
    }

    public static Object getLavaBlock() {
        return MMLGBlockWrapper.a(MLGBlockWrapper.vapeInstance.getMappingsMapperCompat().Rj);
    }
}
