package gg.vape.wrapper.impl;

public class SPacketChunkData
extends Packet {
    public SPacketChunkData(Object object) {
        super(object);
    }

    public int N() {
        return SPacketChunkData.vapeInstance.getMappingsMapperCompat().h8.k(this.I);
    }

    public SPacketChunkDataExtracted J() {
        return new SPacketChunkDataExtracted(SPacketChunkData.vapeInstance.getMappingsMapperCompat().h8.r(this.I));
    }

    public int b() {
        return SPacketChunkData.vapeInstance.getMappingsMapperCompat().h8.J(this.I);
    }
}

