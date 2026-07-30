package gg.vape.wrapper.impl;

public class SPacketDestroyEntities
extends SPacketDestroyEntitiesBase {
    public int[] W() {
        return SPacketDestroyEntities.vapeInstance.getMappingsMapperCompat().C7.K(this.I);
    }

    public SPacketDestroyEntities(Object object) {
        super(object);
    }
}

