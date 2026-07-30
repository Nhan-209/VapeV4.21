package gg.vape.wrapper.impl;

public class CPacketUseEntityActionPacket
extends CPacketUseEntityAction {
    public CPacketUseEntityActionPacket(Object object) {
        super(object, null);
    }

    public Vec3 C() {
        return new Vec3(CPacketUseEntityActionPacket.vapeInstance.getMappingsMapperCompat().h6.c(this.I));
    }
}

