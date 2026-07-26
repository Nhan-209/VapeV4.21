package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MSPacketMapChunkBulkBridge
extends Mapping {
    private MappingField c;
    private MappingField a;
    private MappingField x;

    public int[] S(Object object) {
        return this.c.getIntArray(object);
    }

    public int[] O(Object object) {
        return this.x.getIntArray(object);
    }

    public Object[] Q(Object object) {
        return this.a.getObjectArray(object);
    }

    public MSPacketMapChunkBulkBridge() {
        super(MappedClasses.zB);
        Class<int[]> clazz = int[].class;
        boolean bl = true;
        String string = "xPositions";
        MSPacketMapChunkBulkBridge mSPacketMapChunkBulkBridge = this;
        this.c = this.J(string, bl, clazz);
        Class<int[]> clazz2 = int[].class;
        boolean bl2 = true;
        String string2 = "zPositions";
        MSPacketMapChunkBulkBridge mSPacketMapChunkBulkBridge2 = this;
        this.x = this.J(string2, bl2, clazz2);
        Class clazz3 = MappedClasses.lW;
        boolean bl3 = true;
        String string3 = "chunksData";
        MSPacketMapChunkBulkBridge mSPacketMapChunkBulkBridge3 = this;
        this.a = this.J(string3, bl3, clazz3);
    }
}

