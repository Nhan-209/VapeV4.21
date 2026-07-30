package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class FluidState
extends Wrapper {
    public boolean hasNoSky() {
        return FluidState.vapeInstance.getMappingsMapperCompat().RG.hasNoSky(this.I);
    }

    public FluidState(Object wrappedObject) {
        super(wrappedObject);
    }
}
