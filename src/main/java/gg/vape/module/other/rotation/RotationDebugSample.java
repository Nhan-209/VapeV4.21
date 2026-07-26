package gg.vape.module.other.rotation;

import gg.vape.mapping.MappedClasses;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.RotationAngles;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.Vec3d;
import gg.vape.wrapper.impl.C03PacketPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;

public class RotationDebugSample {
    private final int z;
    private boolean f = false;
    private RotationAngles M;
    private RotationAngles m;
    private boolean N = false;
    private Vec3d T;
    private RotationAngles t;
    private Vec3d w;

    public static Vec3d w(RotationDebugSample rotationDebugSample) {
        return rotationDebugSample.w;
    }

    public static boolean s(RotationDebugSample rotationDebugSample, boolean bl) {
        rotationDebugSample.f = bl;
        return rotationDebugSample.f;
    }

    public static boolean G(RotationDebugSample rotationDebugSample, boolean bl) {
        rotationDebugSample.N = bl;
        return rotationDebugSample.N;
    }

    public static RotationAngles p(RotationDebugSample rotationDebugSample) {
        return rotationDebugSample.m;
    }

    public RotationDebugSample(int n) {
        this.z = n;
    }

    public void D(C03PacketPlayer c03PacketPlayer, EntityPlayerSP entityPlayerSP) {
        this.w = c03PacketPlayer.isInstance(MappedClasses.ul) || c03PacketPlayer.isInstance(MappedClasses.FK) ? new Vec3d(c03PacketPlayer.getX(), c03PacketPlayer.getY(), c03PacketPlayer.getZ()) : new Vec3d(entityPlayerSP.o$src$D$1u5n7bh(), entityPlayerSP.Q$src$D$1tp5din(), entityPlayerSP.X$src$D$1tszxo6());
        this.m = c03PacketPlayer.isInstance(MappedClasses.qw) || c03PacketPlayer.isInstance(MappedClasses.FK) ? new RotationAngles(c03PacketPlayer.getYaw(), c03PacketPlayer.getPitch()) : new RotationAngles(entityPlayerSP.g(), entityPlayerSP.a$src$F$1txy325());
        if (RotationManager.b.w() instanceof AdaptiveRotationController) {
            AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)RotationManager.b.w();
            this.t = new RotationAngles(adaptiveRotationController.J(), adaptiveRotationController.X());
            if (this.T != null) {
                RotationAngles rotationAngles = adaptiveRotationController.j(this.T.n().addVector(0.0, entityPlayerSP.X(), 0.0));
                double d = MathUtil.wrapAngleTo180((double)((rotationAngles.z() - this.m.z()) % 360.0f));
                double d2 = MathUtil.wrapAngleTo180((double)((rotationAngles.N() - this.m.N()) % 360.0f));
                this.M = this.m.w(new RotationAngles(d, d2));
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.format("%d,%f,%f,%f,%f,%f,%b,%b,", this.z, this.w.Y(), this.w.t(), this.w.o(), Float.valueOf(this.m.z() % 360.0f), Float.valueOf(this.m.N() % 360.0f), this.N, this.f));
        if (this.T != null) {
            stringBuilder.append(this.T.Y());
            stringBuilder.append(",");
            stringBuilder.append(this.T.t());
            stringBuilder.append(",");
            stringBuilder.append(this.T.o());
            stringBuilder.append(",");
        } else {
            stringBuilder.append(",,,");
        }
        if (this.t != null) {
            stringBuilder.append(this.t.z() % 360.0f);
            stringBuilder.append(", ");
            stringBuilder.append(this.t.N() % 360.0f);
            stringBuilder.append(",");
        } else {
            stringBuilder.append(",,");
        }
        if (this.M != null) {
            stringBuilder.append(this.M.z() % 360.0f);
            stringBuilder.append(", ");
            stringBuilder.append(this.M.N() % 360.0f);
        } else {
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static Vec3d q(RotationDebugSample rotationDebugSample, Vec3d vec3d) {
        rotationDebugSample.T = vec3d;
        return rotationDebugSample.T;
    }
}

