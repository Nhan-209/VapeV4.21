package gg.vape.wrapper.impl;

public class NetworkPlayerInfo
extends Packet {
    public PositionMoveRotation e() {
        return new PositionMoveRotation(NetworkPlayerInfo.vapeInstance.getMappingsMapperCompat().RH.C(this.I));
    }

    public NetworkPlayerInfo(Object object) {
        super(object);
    }

    public int C() {
        return NetworkPlayerInfo.vapeInstance.getMappingsMapperCompat().RH.s(this.I);
    }
}

