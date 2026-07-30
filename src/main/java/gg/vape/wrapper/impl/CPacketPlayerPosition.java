package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCPacketPlayer_Position;

public class CPacketPlayerPosition
extends C03PacketPlayer {
    public static CPacketPlayerPosition newInstance(double d, double d2, double d3, boolean bl) {
        if (ForgeVersion.MC_1_21_4.d()) {
            return new CPacketPlayerPosition(CPacketPlayerPosition.vapeInstance.getMappingsMapperCompat().R5.R(d, d2, d3, bl, false));
        }
        return new CPacketPlayerPosition(MCPacketPlayer_Position.T(CPacketPlayerPosition.vapeInstance.getMappingsMapperCompat().R5, d, d2, d3, bl));
    }

    private CPacketPlayerPosition(Object object) {
        super(object);
    }

    public static CPacketPlayerPosition newInstance(double d, double d2, double d3, double d4, boolean bl) {
        return new CPacketPlayerPosition(CPacketPlayerPosition.vapeInstance.getMappingsMapperCompat().R5.U(d, d2, d3, d4, bl));
    }

}

