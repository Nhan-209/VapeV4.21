package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCPacketPlayer;
import gg.vape.wrapper.impl.CPacketPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;

public class C03PacketPlayer
extends Packet {
    public double getX() {
        return C03PacketPlayer.c.getMappings().Dq.i(this.I);
    }

    public float getPitch() {
        return C03PacketPlayer.c.getMappings().Dq.j(this.I);
    }

    public double getY() {
        return C03PacketPlayer.c.getMappings().Dq.x(this.I);
    }

    public float getYaw() {
        return C03PacketPlayer.c.getMappings().Dq.A(this.I);
    }

    public C03PacketPlayer(Object object) {
        super(object);
    }

    public static C03PacketPlayer newInstance(boolean bl) {
        if (ForgeVersion.MC_1_21_4.d()) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            return CPacketPlayer.R(bl, entityPlayerSP.r());
        }
        if (ForgeVersion.MC_1_20_6.d()) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            return new C03PacketPlayer(MCPacketPlayer.s(C03PacketPlayer.c.getMappings().Dq, entityPlayerSP.z(), entityPlayerSP.N(), entityPlayerSP.h(), entityPlayerSP.J(), entityPlayerSP.V(), bl, false, false));
        }
        return new C03PacketPlayer(MCPacketPlayer.T(C03PacketPlayer.c.getMappings().Dq, bl));
    }

    public double getZ() {
        return C03PacketPlayer.c.getMappings().Dq.b(this.I);
    }

    public boolean isOnGround() {
        return C03PacketPlayer.c.getMappings().Dq.g(this.I);
    }
}

