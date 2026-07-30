package gg.vape.wrapper.impl;

public class ByteStatePacketBridge
extends Packet {
    public ByteStatePacketBridge(Object object) {
        super(object);
    }

    public byte r() {
        return ByteStatePacketBridge.vapeInstance.getMappingsMapperCompat().hy.L(this.I);
    }
}

