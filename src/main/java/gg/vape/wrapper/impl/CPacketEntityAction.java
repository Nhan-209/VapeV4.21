package gg.vape.wrapper.impl;

public class CPacketEntityAction
extends Packet {
    public static CPacketEntityAction create(Entity entity, CPacketEntityActionAction jT) {
        return new CPacketEntityAction(CPacketEntityAction.vapeInstance.getMappingsMapperCompat().Y.k(entity.getObject(), jT.getObject()));
    }

    public CPacketEntityAction(Object object) {
        super(object);
    }

    public static CPacketEntityAction create(Entity entity, int n) {
        return new CPacketEntityAction(CPacketEntityAction.vapeInstance.getMappingsMapperCompat().Y.u(entity.getObject(), n));
    }
}

