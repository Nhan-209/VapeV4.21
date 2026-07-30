package gg.vape.wrapper.impl;

public class SPacketHeldItemChange
extends Packet {
    public SPacketHeldItemChange(Object object) {
        super(object);
    }

    public static SPacketHeldItemChange x(int n) {
        return new SPacketHeldItemChange(SPacketHeldItemChange.vapeInstance.getMappingsMapperCompat().C6.h(n));
    }
}

