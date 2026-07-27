package gg.vape.module.other.rotation;

import gg.vape.mapping.MappedClasses;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.RotationAngles;
import gg.vape.rotation.RotationManager;
import gg.vape.utils.MathUtil;
import gg.vape.utils.Vec3d;
import gg.vape.wrapper.impl.C03PacketPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;

public class RotationDebugSample {
    private final int id;
    private boolean flagF = false;
    private RotationAngles rotationDelta;
    private RotationAngles rotation;
    private boolean flagN = false;
    private Vec3d target;
    private RotationAngles controllerRotation;
    private Vec3d position;

    public static Vec3d w(RotationDebugSample rotationDebugSample) {
        return rotationDebugSample.position;
    }

    public static boolean s(RotationDebugSample rotationDebugSample, boolean bl) {
        rotationDebugSample.flagF = bl;
        return rotationDebugSample.flagF;
    }

    public static boolean G(RotationDebugSample rotationDebugSample, boolean bl) {
        rotationDebugSample.flagN = bl;
        return rotationDebugSample.flagN;
    }

    public static RotationAngles p(RotationDebugSample rotationDebugSample) {
        return rotationDebugSample.rotation;
    }

    public RotationDebugSample(int n) {
        this.id = n;
    }

    public void D(C03PacketPlayer c03PacketPlayer, EntityPlayerSP entityPlayerSP) {
        this.position = c03PacketPlayer.isInstance(MappedClasses.ul) || c03PacketPlayer.isInstance(MappedClasses.FK) ? new Vec3d(c03PacketPlayer.getX(), c03PacketPlayer.getY(), c03PacketPlayer.getZ()) : new Vec3d(entityPlayerSP.o$src$D$1u5n7bh(), entityPlayerSP.Q$src$D$1tp5din(), entityPlayerSP.X$src$D$1tszxo6());
        this.rotation = c03PacketPlayer.isInstance(MappedClasses.qw) || c03PacketPlayer.isInstance(MappedClasses.FK) ? new RotationAngles(c03PacketPlayer.getYaw(), c03PacketPlayer.getPitch()) : new RotationAngles(entityPlayerSP.g(), entityPlayerSP.a$src$F$1txy325());
        if (RotationManager.b.w() instanceof AdaptiveRotationController) {
            AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)RotationManager.b.w();
            this.controllerRotation = new RotationAngles(adaptiveRotationController.J(), adaptiveRotationController.X());
            if (this.target != null) {
                RotationAngles rotationAngles = adaptiveRotationController.j(this.target.n().addVector(0.0, entityPlayerSP.X(), 0.0));
                double d = MathUtil.wrapAngleTo180((double)((rotationAngles.z() - this.rotation.z()) % 360.0f));
                double d2 = MathUtil.wrapAngleTo180((double)((rotationAngles.N() - this.rotation.N()) % 360.0f));
                this.rotationDelta = this.rotation.w(new RotationAngles(d, d2));
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.format("%d,%f,%f,%f,%f,%f,%b,%b,", this.id, this.position.Y(), this.position.t(), this.position.o(), Float.valueOf(this.rotation.z() % 360.0f), Float.valueOf(this.rotation.N() % 360.0f), this.flagN, this.flagF));
        if (this.target != null) {
            stringBuilder.append(this.target.Y());
            stringBuilder.append(",");
            stringBuilder.append(this.target.t());
            stringBuilder.append(",");
            stringBuilder.append(this.target.o());
            stringBuilder.append(",");
        } else {
            stringBuilder.append(",,,");
        }
        if (this.controllerRotation != null) {
            stringBuilder.append(this.controllerRotation.z() % 360.0f);
            stringBuilder.append(", ");
            stringBuilder.append(this.controllerRotation.N() % 360.0f);
            stringBuilder.append(",");
        } else {
            stringBuilder.append(",,");
        }
        if (this.rotationDelta != null) {
            stringBuilder.append(this.rotationDelta.z() % 360.0f);
            stringBuilder.append(", ");
            stringBuilder.append(this.rotationDelta.N() % 360.0f);
        } else {
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }


    public static Vec3d q(RotationDebugSample rotationDebugSample, Vec3d vec3d) {
        rotationDebugSample.target = vec3d;
        return rotationDebugSample.target;
    }
}

