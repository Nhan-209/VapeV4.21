package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MSPacketChunkData
extends Mapping {
    private MappingField k;
    private MappingField E;
    private MappingField e;

    public int J(Object object) {
        return this.e.getInt(object);
    }

    public int k(Object object) {
        return this.k.getInt(object);
    }

    public Object r(Object object) {
        return this.E.getObject(object);
    }

    public MSPacketChunkData() {
        super(MappedClasses.ZJ);
        Class clazz = MappedClasses.uU;
        boolean bl = true;
        String string = "extractedData";
        MSPacketChunkData mSPacketChunkData = this;
        this.E = this.J(string, bl, clazz);
        Class<Integer> clazz2 = Integer.TYPE;
        boolean bl2 = true;
        String string2 = "chunkX";
        MSPacketChunkData mSPacketChunkData2 = this;
        this.e = this.J(string2, bl2, clazz2);
        Class<Integer> clazz3 = Integer.TYPE;
        boolean bl3 = true;
        String string3 = "chunkZ";
        MSPacketChunkData mSPacketChunkData3 = this;
        this.k = this.J(string3, bl3, clazz3);
    }
}

