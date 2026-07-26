package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.SPacketChunkDataExtracted;
import java.util.ArrayList;
import java.util.List;

public class SPacketMapChunkBulk
extends Packet {
    public int[] c() {
        return SPacketMapChunkBulk.c.getMappingsMapperCompat().Rg.O(this.I);
    }

    public List<SPacketChunkDataExtracted> s() {
        Object[] objectArray = SPacketMapChunkBulk.c.getMappingsMapperCompat().Rg.Q(this.I);
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
        return SPacketMapChunkBulk.c.getMappingsMapperCompat().Rg.S(this.I);
    }
}

