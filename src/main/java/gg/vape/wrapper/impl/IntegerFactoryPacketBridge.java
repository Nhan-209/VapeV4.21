package gg.vape.wrapper.impl;

public class IntegerFactoryPacketBridge
extends Packet {
    public static IntegerFactoryPacketBridge w(int n) {
        return new IntegerFactoryPacketBridge(IntegerFactoryPacketBridge.vapeInstance.getMappingsMapperCompat().hp.F(n));
    }

    public IntegerFactoryPacketBridge(Object object) {
        super(object);
    }
}

