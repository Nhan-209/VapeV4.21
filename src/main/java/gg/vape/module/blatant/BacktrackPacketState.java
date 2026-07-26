package gg.vape.module.blatant;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.datas.PlayerLocationSnapshot;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.NetworkPlayerInfo;
import gg.vape.wrapper.impl.SEntityPacket;
import gg.vape.wrapper.impl.SPacketEntity;
import gg.vape.wrapper.impl.Vec3;

public class BacktrackPacketState {
    private long r;
    private long z;
    private long k;

    public void K(SPacketEntity jv_02) {
        this.z = jv_02.H();
        this.k = jv_02.M();
        this.r = jv_02.B();
    }

    public void W(NetworkPlayerInfo jl_02) {
        Vec3 vec3 = jl_02.e().u();
        this.z = MathUtil.floor(vec3.getX() * BacktrackPacketState.K());
        this.k = MathUtil.floor(vec3.getY() * BacktrackPacketState.K());
        this.r = MathUtil.floor(vec3.getZ() * BacktrackPacketState.K());
    }

    public static BacktrackPacketState J(Entity entity) {
        return new BacktrackPacketState(MathUtil.floor(entity.double_z() * BacktrackPacketState.K()), MathUtil.floor(entity.double_N() * BacktrackPacketState.K()), MathUtil.floor(entity.double_h() * BacktrackPacketState.K()));
    }

    private static double K() {
        double d = ForgeVersion.MC_1_21_4.d() ? 4096.0 : 32.0;
        return d;
    }

    public PlayerLocationSnapshot g() {
        return new PlayerLocationSnapshot((double)this.z / BacktrackPacketState.K(), (double)this.k / BacktrackPacketState.K(), (double)this.r / BacktrackPacketState.K());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public void Z(SEntityPacket jJ) {
        this.z += (long)jJ.Y();
        this.k += (long)jJ.x();
        this.r += (long)jJ.E();
    }

    public BacktrackPacketState(int n, int n2, int n3) {
        this.z = n;
        this.k = n2;
        this.r = n3;
    }
}

