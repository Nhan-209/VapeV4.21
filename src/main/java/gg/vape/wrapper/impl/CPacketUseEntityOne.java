package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCPacketUseEntityOne;
import gg.vape.wrapper.impl.ModelPlayer;
import gg.vape.wrapper.impl.Packet;

public class CPacketUseEntityOne
extends Packet {
    public CPacketUseEntityOne(Object object) {
        super(object);
    }

    public static CPacketUseEntityOne g(ModelPlayer modelPlayer) {
        return new CPacketUseEntityOne(MCPacketUseEntityOne.e(CPacketUseEntityOne.c.getMappingsMapperCompat().RE, modelPlayer.getObject()));
    }
}

