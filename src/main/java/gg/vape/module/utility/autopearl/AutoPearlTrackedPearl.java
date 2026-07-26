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
    private final EntityPlayer E;
    private final EntityEnderPearl W;

    private AutoPearlTrackedPearl(EntityEnderPearl entityEnderPearl, EntityPlayer entityPlayer) {
        this.W = entityEnderPearl;
        this.E = entityPlayer;
    }

    public EntityPlayer A() {
        return this.E;
    }

    public EntityEnderPearl P() {
        return this.W;
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
        double d = this.W.z();
        double d2 = this.W.N();
        double d3 = this.W.h();
        double d4 = this.W.t();
        double d5 = this.W.q();
        double d6 = this.W.T();
        RayTraceResult rayTraceResult = null;
        while (true) {
            double d7;
            double d8;
            double d9;
            double d10;
            double d11;
            double d12;
            boolean bl;
            Vec3 vec3 = Vec3.create(d, d2, d3);
            Vec3 vec32 = Vec3.create(d + d4, d2 + d5, d3 + d6);
            rayTraceResult = worldClient.K(vec3, vec32, false, this.W.isInstance(MappedClasses.F), false, this.W);
            d += d4;
            d2 += d5;
            d3 += d6;
            if (rayTraceResult.isNotNull() && !rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.miss())) {
                bl = false;
                Block block = rayTraceResult.Z$src$Lgg_vape_wrapper_impl_Block_$6x2c9a();
                if (block.isNotNull() && BlockUtil.p(block)) {
                    bl = true;
                }
                if (!bl) {
                    d = rayTraceResult.getHitVec().getX();
                    d2 = rayTraceResult.getHitVec().getY();
                    d3 = rayTraceResult.getHitVec().getZ();
                    return Vec3.create(d, d2, d3);
                }
            }
            if (d2 < -128.0) break;
            bl = this.W.h$src$Z$ftwoya();
            double d13 = d4;
            if (bl) {
                d12 = d13;
                d11 = 0.8;
            } else {
                d12 = d13;
                d11 = 0.99;
            }
            d4 = d12 * d11;
            double d14 = d5;
            if (bl) {
                d10 = d14;
                d9 = 0.8;
            } else {
                d10 = d14;
                d9 = 0.99;
            }
            d5 = d10 * d9;
            double d15 = d6;
            if (bl) {
                d8 = d15;
                d7 = 0.8;
            } else {
                d8 = d15;
                d7 = 0.99;
            }
            d6 = d8 * d7;
            d5 -= 0.03;
        }
        return null;
    }
}

