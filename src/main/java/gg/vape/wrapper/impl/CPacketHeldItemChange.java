package gg.vape.wrapper.impl;

public class CPacketHeldItemChange
extends Packet {
    public static CPacketHeldItemChange create(int n) {
        return new CPacketHeldItemChange(CPacketHeldItemChange.vapeInstance.getMappingsMapperCompat().Ck.l(n));
    }

    public CPacketHeldItemChange(Object object) {
        super(object);
    }
}

