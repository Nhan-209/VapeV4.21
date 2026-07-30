package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class SPacketChunkDataExtracted
extends Wrapper {
    public SPacketChunkDataExtracted(Object object) {
        super(object);
    }

    public int G() {
        return SPacketChunkDataExtracted.vapeInstance.getMappings().b.S(this.I);
    }

    public byte[] m$src$AB$7svbyb() {
        return SPacketChunkDataExtracted.vapeInstance.getMappings().b.G(this.I);
    }
}

