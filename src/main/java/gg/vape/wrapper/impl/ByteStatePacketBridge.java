package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.Packet;

public class ByteStatePacketBridge
extends Packet {
    public ByteStatePacketBridge(Object object) {
        super(object);
    }

    public byte r() {
        return ByteStatePacketBridge.c.getMappingsMapperCompat().hy.L(this.I);
    }
}

