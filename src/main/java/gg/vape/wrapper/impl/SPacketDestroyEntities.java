package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.SPacketDestroyEntitiesBase;

public class SPacketDestroyEntities
extends SPacketDestroyEntitiesBase {
    public int[] W() {
        return SPacketDestroyEntities.c.getMappingsMapperCompat().C7.K(this.I);
    }

    public SPacketDestroyEntities(Object object) {
        super(object);
    }
}

