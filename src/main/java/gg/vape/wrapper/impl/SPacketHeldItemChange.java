package gg.vape.wrapper.impl;

public class SPacketHeldItemChange
extends Packet {
    public SPacketHeldItemChange(Object wrappedObject) {
        super(wrappedObject);
    }

    public static SPacketHeldItemChange create(int slot) {
        return new SPacketHeldItemChange(SPacketHeldItemChange.vapeInstance.getMappingsMapperCompat().C6.newInstance(slot));
    }
}
