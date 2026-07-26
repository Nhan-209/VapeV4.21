package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.Packet;

public class CPacketHeldItemChange
extends Packet {
    public static CPacketHeldItemChange create(int n) {
        return new CPacketHeldItemChange(CPacketHeldItemChange.c.getMappingsMapperCompat().Ck.l(n));
    }

    public CPacketHeldItemChange(Object object) {
        super(object);
    }
}

