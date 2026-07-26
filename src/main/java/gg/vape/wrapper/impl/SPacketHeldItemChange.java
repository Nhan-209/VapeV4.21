package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.Packet;

public class SPacketHeldItemChange
extends Packet {
    public SPacketHeldItemChange(Object object) {
        super(object);
    }

    public static SPacketHeldItemChange x(int n) {
        return new SPacketHeldItemChange(SPacketHeldItemChange.c.getMappingsMapperCompat().C6.h(n));
    }
}

