package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.SPacketChunkDataExtracted;

public class SPacketChunkData
extends Packet {
    public SPacketChunkData(Object object) {
        super(object);
    }

    public int N() {
        return SPacketChunkData.c.getMappingsMapperCompat().h8.k(this.I);
    }

    public SPacketChunkDataExtracted J() {
        return new SPacketChunkDataExtracted(SPacketChunkData.c.getMappingsMapperCompat().h8.r(this.I));
    }

    public int b() {
        return SPacketChunkData.c.getMappingsMapperCompat().h8.J(this.I);
    }
}

