package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class WorldClientBridge
extends Wrapper {
    public void R(long l) {
        WorldClientBridge.c.getMappingsMapperCompat().q0.G(this.I, l);
    }

    public WorldClientBridge(Object object) {
        super(object);
    }

    public void z(long l) {
        WorldClientBridge.c.getMappingsMapperCompat().q0.E(this.I, l);
    }
}

