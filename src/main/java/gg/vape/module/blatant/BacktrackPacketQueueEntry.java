package gg.vape.module.blatant;

import gg.vape.wrapper.impl.Packet;
import java.util.ArrayList;
import java.util.List;

public class BacktrackPacketQueueEntry
extends Packet {
    public List<Packet> s() {
        ArrayList<Packet> arrayList = new ArrayList<Packet>();
        for (Object t : this.a()) {
            arrayList.add(new Packet(t));
        }
        return arrayList;
    }

    public Iterable a() {
        return BacktrackPacketQueueEntry.c.getMappingsMapperCompat().CS.A(this.I);
    }

    public BacktrackPacketQueueEntry(Object object) {
        super(object);
    }
}

