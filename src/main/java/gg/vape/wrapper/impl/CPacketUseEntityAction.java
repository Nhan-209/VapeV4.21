package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.CPacketUseEntity;
import gg.vape.wrapper.impl.CPacketUseEntityActionConstructorMarker;

public class CPacketUseEntityAction
extends Wrapper {
    private CPacketUseEntityAction(Object object) {
        super(object);
    }

    public CPacketUseEntityAction(Object object, CPacketUseEntityActionConstructorMarker fv_02) {
        this(object);
    }

    public CPacketUseEntity w() {
        return new CPacketUseEntity(CPacketUseEntityAction.c.getMappingsMapperCompat().qz.f(this.I), null);
    }
}

