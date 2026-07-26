package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockState;
import gg.vape.wrapper.impl.Packet;

public class SPacketBlockChange
extends Packet {
    public SPacketBlockChange(Object object) {
        super(object);
    }

    public int L() {
        return SPacketBlockChange.c.getMappings().CN.e(this.I);
    }

    public BlockState x() {
        return new BlockState(SPacketBlockChange.c.getMappings().CN.h$src$Ljava_lang_Object_$1ir33a2(this.I));
    }

    public BlockPos B() {
        return new BlockPos(SPacketBlockChange.c.getMappings().CN.j(this.I));
    }

    public int f() {
        return SPacketBlockChange.c.getMappings().CN.e(this.I);
    }

    public int Q() {
        return SPacketBlockChange.c.getMappings().CN.e(this.I);
    }
}

