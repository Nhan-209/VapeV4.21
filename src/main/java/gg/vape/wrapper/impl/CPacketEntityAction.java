package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.CPacketEntityActionAction;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.Packet;

public class CPacketEntityAction
extends Packet {
    public static CPacketEntityAction create(Entity entity, CPacketEntityActionAction jT) {
        return new CPacketEntityAction(CPacketEntityAction.c.getMappingsMapperCompat().Y.k(entity.getObject(), jT.getObject()));
    }

    public CPacketEntityAction(Object object) {
        super(object);
    }

    public static CPacketEntityAction create(Entity entity, int n) {
        return new CPacketEntityAction(CPacketEntityAction.c.getMappingsMapperCompat().Y.u(entity.getObject(), n));
    }
}

