package gg.vape.wrapper.impl;

public class SPacketBlockChange
extends Packet {
    public SPacketBlockChange(Object object) {
        super(object);
    }

    public int L() {
        return SPacketBlockChange.vapeInstance.getMappings().CN.e(this.I);
    }

    public BlockState x() {
        return new BlockState(SPacketBlockChange.vapeInstance.getMappings().CN.h$src$Ljava_lang_Object_$1ir33a2(this.I));
    }

    public BlockPos B() {
        return new BlockPos(SPacketBlockChange.vapeInstance.getMappings().CN.j(this.I));
    }

    public int f() {
        return SPacketBlockChange.vapeInstance.getMappings().CN.e(this.I);
    }

    public int Q() {
        return SPacketBlockChange.vapeInstance.getMappings().CN.e(this.I);
    }
}

