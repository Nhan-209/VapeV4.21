package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class SPacketEntityStatus
extends Wrapper {
    public byte o() {
        return SPacketEntityStatus.vapeInstance.getMappingsMapperCompat().RO.n(this.I);
    }

    public int X() {
        return SPacketEntityStatus.vapeInstance.getMappingsMapperCompat().RO.e(this.I);
    }

    public SPacketEntityStatus(Object object) {
        super(object);
    }
}

