package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MSPacketChunkDataExtracted
extends Mapping {
    private MappingField m;
    private MappingField B;

    public MSPacketChunkDataExtracted() {
        super(MappedClasses.uU);
        Class<byte[]> clazz = byte[].class;
        boolean bl = true;
        String string = "data";
        MSPacketChunkDataExtracted mSPacketChunkDataExtracted = this;
        this.B = this.J(string, bl, clazz);
        Class<Integer> clazz2 = Integer.TYPE;
        boolean bl2 = true;
        String string2 = "dataSize";
        MSPacketChunkDataExtracted mSPacketChunkDataExtracted2 = this;
        this.m = this.J(string2, bl2, clazz2);
    }

    public byte[] G(Object object) {
        return this.B.u(object);
    }

    public int S(Object object) {
        return this.m.getInt(object);
    }
}

