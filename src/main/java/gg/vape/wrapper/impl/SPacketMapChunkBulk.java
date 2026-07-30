package gg.vape.wrapper.impl;

import java.util.ArrayList;
import java.util.List;

public class SPacketMapChunkBulk
extends Packet {
    public int[] c() {
        return SPacketMapChunkBulk.vapeInstance.getMappingsMapperCompat().Rg.O(this.I);
    }

    public List<SPacketChunkDataExtracted> s() {
        Object[] objectArray = SPacketMapChunkBulk.vapeInstance.getMappingsMapperCompat().Rg.Q(this.I);
        ArrayList<SPacketChunkDataExtracted> arrayList = new ArrayList<SPacketChunkDataExtracted>();
        for (Object object : objectArray) {
            arrayList.add(new SPacketChunkDataExtracted(object));
        }
        return arrayList;
    }

    public SPacketMapChunkBulk(Object object) {
        super(object);
    }

    public int[] y() {
        return SPacketMapChunkBulk.vapeInstance.getMappingsMapperCompat().Rg.S(this.I);
    }
}

