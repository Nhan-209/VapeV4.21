package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.Packet;

public class IntegerFactoryPacketBridge
extends Packet {
    public static IntegerFactoryPacketBridge w(int n) {
        return new IntegerFactoryPacketBridge(IntegerFactoryPacketBridge.c.getMappingsMapperCompat().hp.F(n));
    }

    public IntegerFactoryPacketBridge(Object object) {
        super(object);
    }
}

