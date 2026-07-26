package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRenderManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ActiveRenderInfo;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Quaternion;
import gg.vape.wrapper.impl.Render;

public class RenderManager
extends Wrapper {
    private static double W;
    private static double A;
    private static double U;

    public double getRenderPosZ() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.getActiveRenderInfo().o().getZ();
        }
        return MRenderManager.t(RenderManager.c.getMappings().CA, this.I);
    }

    public static RenderManager getInstance() {
        return new RenderManager(MRenderManager.T(RenderManager.c.getMappings().CA));
    }

    public Quaternion getCameraOrientation() {
        if (ForgeVersion.MC_1_21_10.d()) {
            return this.getActiveRenderInfo().G();
        }
        return new Quaternion(MRenderManager.q(RenderManager.c.getMappings().CA, this.I));
    }

    public static double getInterpolatedRenderPosX() {
        return W;
    }

    public double getRenderPosY() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.getActiveRenderInfo().o().getY();
        }
        return MRenderManager.N(RenderManager.c.getMappings().CA, this.I);
    }

    public float getPlayerViewX() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.getActiveRenderInfo().x();
        }
        return MRenderManager.o(RenderManager.c.getMappings().CA, this.I);
    }

    public double getRenderPosX() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.getActiveRenderInfo().o().getX();
        }
        return MRenderManager.M(RenderManager.c.getMappings().CA, this.I);
    }

    public RenderManager(Object object) {
        super(object);
    }

    public float getPlayerViewY() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.getActiveRenderInfo().Z();
        }
        return MRenderManager.r(RenderManager.c.getMappings().CA, this.I);
    }

    public ActiveRenderInfo getActiveRenderInfo() {
        return new ActiveRenderInfo(MRenderManager.e(RenderManager.c.getMappings().CA, this.I));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static double getInterpolatedRenderPosY() {
        return A;
    }

    public Render getEntityRenderObject(Entity entity) {
        return new Render(MRenderManager.O(RenderManager.c.getMappings().CA, this.I, entity.getObject()));
    }

    public static void updateInterpolatedRenderPosition(float f) {
        EntityLivingBase entityLivingBase = Minecraft.F();
        double d = entityLivingBase.M();
        double d2 = entityLivingBase.W();
        double d3 = entityLivingBase.m$src$D$fwnne5();
        W = d + (entityLivingBase.z() - d) * (double)f;
        A = d2 + (entityLivingBase.N() - d2) * (double)f;
        if (ForgeVersion.MC_1_14_4.d()) {
            A += (double)entityLivingBase.X();
            A += Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l().o().getY() - A;
        }
        U = d3 + (entityLivingBase.h() - d3) * (double)f;
    }

    public static double getInterpolatedRenderPosZ() {
        return U;
    }
}

