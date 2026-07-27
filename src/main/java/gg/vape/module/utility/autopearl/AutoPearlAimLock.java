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
    private Vec3 landingPos = null;
    private final FixedRotationController rotationController;

    public Vec3 b() {
        return this.landingPos;
    }

    public FixedRotationController r() {
        return this.rotationController;
    }

    public AutoPearlAimLock(EntityEnderPearl entityEnderPearl, EntityPlayer entityPlayer, FixedRotationController fixedRotationController, Vec3 vec3, AutoPearlRotationController autoPearlRotationController) {
        this(entityEnderPearl, entityPlayer, fixedRotationController, vec3);
    }

    public static FixedRotationController E(AutoPearlAimLock autoPearlAimLock) {
        return autoPearlAimLock.rotationController;
    }

    public void b(Vec3 vec3) {
        this.landingPos = vec3;
    }

    private AutoPearlAimLock(EntityEnderPearl entityEnderPearl, EntityPlayer entityPlayer, FixedRotationController fixedRotationController, Vec3 vec3) {
        this(entityEnderPearl, entityPlayer, fixedRotationController);
        this.b(vec3);
    }

    private AutoPearlAimLock(EntityEnderPearl entityEnderPearl, EntityPlayer entityPlayer, FixedRotationController fixedRotationController) {
        super(entityEnderPearl, entityPlayer, null);
        this.rotationController = fixedRotationController;
    }
}

