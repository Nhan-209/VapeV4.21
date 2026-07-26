package gg.vape.rotation;

import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationAngles;
import gg.vape.rotation.WorldPointRotationTarget;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.RotationVectorMath;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Vec3;

public class PointRotationController
extends FixedRotationController
implements WorldPointRotationTarget {
    private boolean X = true;
    private Vec3 O;

    public RotationAngles N(Vec3 vec3) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d = ForgeVersion.MC_1_7_10.Y() ? (double)entityPlayerSP.X() : 0.0;
        Vec3 vec32 = Vec3.create(vec3.getX(), vec3.getY(), vec3.getZ());
        Vec3 vec33 = Vec3.create(entityPlayerSP.c(), entityPlayerSP.A() + d, entityPlayerSP.Z());
        return RotationVectorMath.H(vec33, vec32, this.k(), this.d$src$Z$1lil4j5());
    }

    public boolean d$src$Z$1lil4j5() {
        return this.X;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void V$src$V$1law04n() {
        this.b(this.N(this.O));
    }

    public void E(boolean bl) {
        this.X = bl;
    }

    public PointRotationController(double d, double d2, double d3) {
        this(Vec3.create(d, d2, d3));
    }

    @Override
    public Vec3 w() {
        return this.O;
    }

    @Override
    public void z(double d, double d2, double d3) {
        this.J(Vec3.create(d, d2, d3));
    }

    public PointRotationController(Vec3 vec3) {
        super(Minecraft.F().J(), Minecraft.F().V());
        this.O = vec3;
        this.b(this.N(vec3));
    }

    @Override
    public void J(Vec3 vec3) {
        this.O = vec3;
    }

    @Override
    public void J(EntityPlayerSP entityPlayerSP, GuiScreen guiScreen) {
        if (entityPlayerSP.isNotNull() && guiScreen.isNull()) {
            this.V$src$V$1law04n();
        }
        super.J(entityPlayerSP, guiScreen);
    }
}

