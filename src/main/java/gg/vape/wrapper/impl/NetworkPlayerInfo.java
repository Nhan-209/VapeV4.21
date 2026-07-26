package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.PositionMoveRotation;

public class NetworkPlayerInfo
extends Packet {
    public PositionMoveRotation e() {
        return new PositionMoveRotation(NetworkPlayerInfo.c.getMappingsMapperCompat().RH.C(this.I));
    }

    public NetworkPlayerInfo(Object object) {
        super(object);
    }

    public int C() {
        return NetworkPlayerInfo.c.getMappingsMapperCompat().RH.s(this.I);
    }
}

