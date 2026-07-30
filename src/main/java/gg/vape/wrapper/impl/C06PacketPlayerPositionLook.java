package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCPacketPlayer_PositionRotation;

public class C06PacketPlayerPositionLook
extends C03PacketPlayer {
    public static C06PacketPlayerPositionLook create(double d, double d2, double d3, double d4, float f, float f2, boolean bl) {
        return new C06PacketPlayerPositionLook(C06PacketPlayerPositionLook.vapeInstance.getMappings().qq.L(d, d2, d3, d4, f, f2, bl));
    }

    private C06PacketPlayerPositionLook(Object object) {
        super(object);
    }

    public static C06PacketPlayerPositionLook create(double d, double d2, double d3, float f, float f2, boolean bl) {
        if (ForgeVersion.MC_1_21_4.d()) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            return new C06PacketPlayerPositionLook(MCPacketPlayer_PositionRotation.P(C06PacketPlayerPositionLook.vapeInstance.getMappings().qq, d, d2, d3, f, f2, bl, entityPlayerSP.r()));
        }
        return new C06PacketPlayerPositionLook(MCPacketPlayer_PositionRotation.b(C06PacketPlayerPositionLook.vapeInstance.getMappings().qq, d, d2, d3, f, f2, bl));
    }
}

