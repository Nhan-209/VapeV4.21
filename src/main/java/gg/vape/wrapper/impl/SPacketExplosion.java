package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MSPacketExplosion;
import gg.vape.wrapper.Wrapper;

public class SPacketExplosion
extends Wrapper {
    public void a(float f) {
        MSPacketExplosion.m(SPacketExplosion.c.getMappingsMapperCompat().hQ, this.I, f);
    }

    public void b(float f) {
        MSPacketExplosion.K(SPacketExplosion.c.getMappingsMapperCompat().hQ, this.I, f);
    }

    public float b() {
        return MSPacketExplosion.V(SPacketExplosion.c.getMappingsMapperCompat().hQ, this.I);
    }

    public float p() {
        return MSPacketExplosion.v(SPacketExplosion.c.getMappingsMapperCompat().hQ, this.I);
    }

    public SPacketExplosion(Object object) {
        super(object);
    }

    public void L(float f) {
        MSPacketExplosion.K(SPacketExplosion.c.getMappingsMapperCompat().hQ, this.I, f);
    }

    public float F() {
        return MSPacketExplosion.z(SPacketExplosion.c.getMappingsMapperCompat().hQ, this.I);
    }
}

