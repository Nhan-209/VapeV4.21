package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.wrapper.impl.Packet;

public class ShortStatePacketBridge
extends Packet {
    public short D() {
        return Vape.INSTANCE.getMappingsMapperCompat().F.P(this.I);
    }

    public ShortStatePacketBridge(Object object) {
        super(object);
    }
}

