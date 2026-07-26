package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCPacketPlayer_PositionRotation;
import gg.vape.wrapper.impl.C03PacketPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;

public class C06PacketPlayerPositionLook
extends C03PacketPlayer {
    public static C06PacketPlayerPositionLook create(double d, double d2, double d3, double d4, float f, float f2, boolean bl) {
        return new C06PacketPlayerPositionLook(C06PacketPlayerPositionLook.c.getMappings().qq.L(d, d2, d3, d4, f, f2, bl));
    }

    private C06PacketPlayerPositionLook(Object object) {
        super(object);
    }

    public static C06PacketPlayerPositionLook create(double d, double d2, double d3, float f, float f2, boolean bl) {
        if (ForgeVersion.MC_1_21_4.d()) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            return new C06PacketPlayerPositionLook(MCPacketPlayer_PositionRotation.P(C06PacketPlayerPositionLook.c.getMappings().qq, d, d2, d3, f, f2, bl, entityPlayerSP.r()));
        }
        return new C06PacketPlayerPositionLook(MCPacketPlayer_PositionRotation.b(C06PacketPlayerPositionLook.c.getMappings().qq, d, d2, d3, f, f2, bl));
    }
}

