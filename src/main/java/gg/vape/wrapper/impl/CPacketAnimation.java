package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCPacketAnimation;

public class CPacketAnimation
extends Packet {
    public static CPacketAnimation create() {
        if (ForgeVersion.MC_1_12_2.d()) {
            return new CPacketAnimation(MCPacketAnimation.A(CPacketAnimation.vapeInstance.getMappingsMapperCompat().Q3, EnumHand.M().getObject()));
        }
        return new CPacketAnimation(CPacketAnimation.vapeInstance.getMappingsMapperCompat().Q3.H());
    }

    public CPacketAnimation(Object object) {
        super(object);
    }

}

