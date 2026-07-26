package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class SPacketChunkDataExtracted
extends Wrapper {
    public SPacketChunkDataExtracted(Object object) {
        super(object);
    }

    public int G() {
        return SPacketChunkDataExtracted.c.getMappings().b.S(this.I);
    }

    public byte[] m$src$AB$7svbyb() {
        return SPacketChunkDataExtracted.c.getMappings().b.G(this.I);
    }
}

