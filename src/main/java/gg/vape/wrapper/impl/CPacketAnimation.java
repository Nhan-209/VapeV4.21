package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCPacketAnimation;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.EnumHand;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Packet;

public class CPacketAnimation
extends Packet {
    public static CPacketAnimation create() {
        if (ForgeVersion.MC_1_12_2.d()) {
            return new CPacketAnimation(MCPacketAnimation.A(CPacketAnimation.c.getMappingsMapperCompat().Q3, EnumHand.M().getObject()));
        }
        return new CPacketAnimation(CPacketAnimation.c.getMappingsMapperCompat().Q3.H());
    }

    public CPacketAnimation(Object object) {
        super(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }
}

