package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MSPacketExplosion;
import gg.vape.wrapper.Wrapper;

public class SPacketExplosion
extends Wrapper {
    public void a(float f) {
        MSPacketExplosion.m(SPacketExplosion.vapeInstance.getMappingsMapperCompat().hQ, this.I, f);
    }

    public void b(float f) {
        MSPacketExplosion.K(SPacketExplosion.vapeInstance.getMappingsMapperCompat().hQ, this.I, f);
    }

    public float b() {
        return MSPacketExplosion.V(SPacketExplosion.vapeInstance.getMappingsMapperCompat().hQ, this.I);
    }

    public float p() {
        return MSPacketExplosion.v(SPacketExplosion.vapeInstance.getMappingsMapperCompat().hQ, this.I);
    }

    public SPacketExplosion(Object object) {
        super(object);
    }

    public void L(float f) {
        MSPacketExplosion.K(SPacketExplosion.vapeInstance.getMappingsMapperCompat().hQ, this.I, f);
    }

    public float F() {
        return MSPacketExplosion.z(SPacketExplosion.vapeInstance.getMappingsMapperCompat().hQ, this.I);
    }
}

