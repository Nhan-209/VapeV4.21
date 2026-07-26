package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.Packet;

public class SPacketEntityTeleport
extends Packet {
    public SPacketEntityTeleport(Object object) {
        super(object);
    }

    public int d() {
        return SPacketEntityTeleport.c.getMappings().qH.a(this.I);
    }

    public byte Z() {
        return SPacketEntityTeleport.c.getMappings().qH.s(this.I);
    }

    public byte x() {
        return SPacketEntityTeleport.c.getMappings().qH.v(this.I);
    }

    public int I() {
        return SPacketEntityTeleport.c.getMappings().qH.z(this.I);
    }

    public int u() {
        return SPacketEntityTeleport.c.getMappings().qH.O(this.I);
    }

    public int m$src$I$1g30xfs() {
        return SPacketEntityTeleport.c.getMappings().qH.J(this.I);
    }
}

