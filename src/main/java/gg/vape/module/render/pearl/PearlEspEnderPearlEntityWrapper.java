package gg.vape.module.render.pearl;

import gg.vape.wrapper.impl.Entity;

public class PearlEspEnderPearlEntityWrapper
extends Entity {
    public PearlEspEnderPearlEntityWrapper(Object handle) {
        super(handle);
    }

    public int getAgeTicks() {
        return PearlEspEnderPearlEntityWrapper.c.getMappings().Dl.k(this.I);
    }
}
