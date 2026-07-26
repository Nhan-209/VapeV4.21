package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCPacketPlayer_Rotation;
import gg.vape.wrapper.impl.C03PacketPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;

public class CPacketPlayer_Rotation
extends C03PacketPlayer {
    private CPacketPlayer_Rotation(Object object) {
        super(object);
    }

    public static CPacketPlayer_Rotation create(float f, float f2, boolean bl) {
        if (ForgeVersion.MC_1_21_4.d()) {
            EntityPlayerSP entityPlayerSP = Minecraft.a_xH_J();
            return new CPacketPlayer_Rotation(MCPacketPlayer_Rotation.B(CPacketPlayer_Rotation.c.getMappingsMapperCompat().Cv, f, f2, bl, entityPlayerSP.boolean_r()));
        }
        return new CPacketPlayer_Rotation(MCPacketPlayer_Rotation.L(CPacketPlayer_Rotation.c.getMappingsMapperCompat().Cv, f, f2, bl));
    }
}

