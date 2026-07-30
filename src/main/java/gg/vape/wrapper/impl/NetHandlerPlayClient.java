package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MNetHandlerPlayClient;

public class NetHandlerPlayClient
extends NetworkPacketHandle {
    public void sendPacket(SPacketEntity sPacketEntity) {
        MNetHandlerPlayClient.sendPacket(NetHandlerPlayClient.vapeInstance.getMappingsMapperCompat().DY, this.I, sPacketEntity.getObject());
    }

    public NetHandlerPlayClient(Object object) {
        super(object);
    }
}

