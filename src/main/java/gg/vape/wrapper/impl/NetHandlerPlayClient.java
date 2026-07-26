package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MNetHandlerPlayClient;
import gg.vape.wrapper.impl.NetworkPacketHandle;
import gg.vape.wrapper.impl.SPacketEntity;

public class NetHandlerPlayClient
extends NetworkPacketHandle {
    public void sendPacket(SPacketEntity sPacketEntity) {
        MNetHandlerPlayClient.sendPacket(NetHandlerPlayClient.c.getMappingsMapperCompat().DY, this.I, sPacketEntity.getObject());
    }

    public NetHandlerPlayClient(Object object) {
        super(object);
    }
}

