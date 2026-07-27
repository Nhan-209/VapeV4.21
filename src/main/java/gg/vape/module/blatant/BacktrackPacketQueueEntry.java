package gg.vape.module.blatant;

import gg.vape.wrapper.impl.Packet;
import java.util.ArrayList;
import java.util.List;

public class BacktrackPacketQueueEntry
extends Packet {
    public List<Packet> s() {
        ArrayList<Packet> arrayList = new ArrayList<Packet>();
        for (Object packetHandle : this.a()) {
            arrayList.add(new Packet(packetHandle));
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

