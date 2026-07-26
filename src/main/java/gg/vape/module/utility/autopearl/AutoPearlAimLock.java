package gg.vape.module.utility.autopearl;

import gg.vape.module.utility.autopearl.AutoPearlRotationController;
import gg.vape.module.utility.autopearl.AutoPearlTrackedPearl;
import gg.vape.rotation.FixedRotationController;
import gg.vape.wrapper.impl.EntityEnderPearl;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.Vec3;
import org.jetbrains.annotations.Nullable;

public class AutoPearlAimLock
extends AutoPearlTrackedPearl {
    @Nullable
    private Vec3 C = null;
    private final FixedRotationController s;

    public Vec3 b() {
        return this.C;
    }

    public FixedRotationController r() {
        return this.s;
    }

    public AutoPearlAimLock(EntityEnderPearl entityEnderPearl, EntityPlayer entityPlayer, FixedRotationController fixedRotationController, Vec3 vec3, AutoPearlRotationController autoPearlRotationController) {
        this(entityEnderPearl, entityPlayer, fixedRotationController, vec3);
    }

    public static FixedRotationController E(AutoPearlAimLock autoPearlAimLock) {
        return autoPearlAimLock.s;
    }

    public void b(Vec3 vec3) {
        this.C = vec3;
    }

    private AutoPearlAimLock(EntityEnderPearl entityEnderPearl, EntityPlayer entityPlayer, FixedRotationController fixedRotationController, Vec3 vec3) {
        this(entityEnderPearl, entityPlayer, fixedRotationController);
        this.b(vec3);
    }

    private AutoPearlAimLock(EntityEnderPearl entityEnderPearl, EntityPlayer entityPlayer, FixedRotationController fixedRotationController) {
        super(entityEnderPearl, entityPlayer, null);
        this.s = fixedRotationController;
    }
}

