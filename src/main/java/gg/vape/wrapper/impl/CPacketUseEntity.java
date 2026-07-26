package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCPacketUseEntity;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.CPacketUseEntityActionConstructorMarker;

public class CPacketUseEntity
extends Wrapper {
    private CPacketUseEntity(Object object) {
        super(object);
    }

    public static CPacketUseEntity T() {
        return new CPacketUseEntity(MCPacketUseEntity.L(CPacketUseEntity.c.getMappingsMapperCompat().Ra));
    }

    public static CPacketUseEntity o() {
        return new CPacketUseEntity(MCPacketUseEntity.x(CPacketUseEntity.c.getMappingsMapperCompat().Ra));
    }

    public static CPacketUseEntity M() {
        return new CPacketUseEntity(MCPacketUseEntity.W(CPacketUseEntity.c.getMappingsMapperCompat().Ra));
    }

    public CPacketUseEntity(Object object, CPacketUseEntityActionConstructorMarker fv_02) {
        this(object);
    }
}

