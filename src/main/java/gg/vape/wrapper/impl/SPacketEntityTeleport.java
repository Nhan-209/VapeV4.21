package gg.vape.wrapper.impl;

public class SPacketEntityTeleport
extends Packet {
    public SPacketEntityTeleport(Object object) {
        super(object);
    }

    public int d() {
        return SPacketEntityTeleport.vapeInstance.getMappings().qH.a(this.I);
    }

    public byte Z() {
        return SPacketEntityTeleport.vapeInstance.getMappings().qH.s(this.I);
    }

    public byte x() {
        return SPacketEntityTeleport.vapeInstance.getMappings().qH.v(this.I);
    }

    public int I() {
        return SPacketEntityTeleport.vapeInstance.getMappings().qH.z(this.I);
    }

    public int u() {
        return SPacketEntityTeleport.vapeInstance.getMappings().qH.O(this.I);
    }

    public int m$src$I$1g30xfs() {
        return SPacketEntityTeleport.vapeInstance.getMappings().qH.J(this.I);
    }
}

