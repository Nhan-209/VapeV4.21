package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.CPacketUseEntityAction;
import gg.vape.wrapper.impl.Vec3;

public class CPacketUseEntityActionPacket
extends CPacketUseEntityAction {
    public CPacketUseEntityActionPacket(Object object) {
        super(object, null);
    }

    public Vec3 C() {
        return new Vec3(CPacketUseEntityActionPacket.c.getMappingsMapperCompat().h6.c(this.I));
    }
}

