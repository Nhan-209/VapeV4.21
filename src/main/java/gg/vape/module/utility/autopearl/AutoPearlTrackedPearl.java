package gg.vape.module.utility.autopearl;

import gg.vape.mapping.MappedClasses;
import gg.vape.module.utility.autopearl.AutoPearlRotationController;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.BlockUtil;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.EntityEnderPearl;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.WorldClient;
import org.jetbrains.annotations.Nullable;

public class AutoPearlTrackedPearl {
    private final EntityPlayer owner;
    private final EntityEnderPearl pearl;

    private AutoPearlTrackedPearl(EntityEnderPearl entityEnderPearl, EntityPlayer entityPlayer) {
        this.pearl = entityEnderPearl;
        this.owner = entityPlayer;
    }

    public EntityPlayer A() {
        return this.owner;
    }

    public EntityEnderPearl P() {
        return this.pearl;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public AutoPearlTrackedPearl(EntityEnderPearl entityEnderPearl, EntityPlayer entityPlayer, AutoPearlRotationController autoPearlRotationController) {
        this(entityEnderPearl, entityPlayer);
    }

    @Nullable
    public Vec3 a() {
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return null;
        }
        double posX = this.pearl.z();
        double posY = this.pearl.N();
        double posZ = this.pearl.h();
        double motionX = this.pearl.t();
        double motionY = this.pearl.q();
        double motionZ = this.pearl.T();
        RayTraceResult rayTraceResult = null;
        while (true) {
            double dragZ;
            double motionZDrag;
            double dragY;
            double motionYDrag;
            double dragX;
            double motionXDrag;
            boolean inWater;
            Vec3 vec3 = Vec3.create(posX, posY, posZ);
            Vec3 vec32 = Vec3.create(posX + motionX, posY + motionY, posZ + motionZ);
            rayTraceResult = worldClient.K(vec3, vec32, false, this.pearl.isInstance(MappedClasses.F), false, this.pearl);
            posX += motionX;
            posY += motionY;
            posZ += motionZ;
            if (rayTraceResult.isNotNull() && !rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.miss())) {
                inWater = false;
                Block block = rayTraceResult.Z$src$Lgg_vape_wrapper_impl_Block_$6x2c9a();
                if (block.isNotNull() && BlockUtil.p(block)) {
                    inWater = true;
                }
                if (!inWater) {
                    posX = rayTraceResult.getHitVec().getX();
                    posY = rayTraceResult.getHitVec().getY();
                    posZ = rayTraceResult.getHitVec().getZ();
                    return Vec3.create(posX, posY, posZ);
                }
            }
            if (posY < -128.0) break;
            inWater = this.pearl.h$src$Z$ftwoya();
            double curMotionX = motionX;
            if (inWater) {
                motionXDrag = curMotionX;
                dragX = 0.8;
            } else {
                motionXDrag = curMotionX;
                dragX = 0.99;
            }
            motionX = motionXDrag * dragX;
            double curMotionY = motionY;
            if (inWater) {
                motionYDrag = curMotionY;
                dragY = 0.8;
            } else {
                motionYDrag = curMotionY;
                dragY = 0.99;
            }
            motionY = motionYDrag * dragY;
            double curMotionZ = motionZ;
            if (inWater) {
                motionZDrag = curMotionZ;
                dragZ = 0.8;
            } else {
                motionZDrag = curMotionZ;
                dragZ = 0.99;
            }
            motionZ = motionZDrag * dragZ;
            motionY -= 0.03;
        }
        return null;
    }
}

