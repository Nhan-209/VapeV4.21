package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCPacketUseEntityOne;

public class CPacketUseEntityOne
extends Packet {
    public CPacketUseEntityOne(Object object) {
        super(object);
    }

    public static CPacketUseEntityOne g(ModelPlayer modelPlayer) {
        return new CPacketUseEntityOne(MCPacketUseEntityOne.e(CPacketUseEntityOne.vapeInstance.getMappingsMapperCompat().RE, modelPlayer.getObject()));
    }
}

