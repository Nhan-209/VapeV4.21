package gg.vape.module.render.hud;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPostAttack;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.rotation.RotationManager;
import gg.vape.ui.click.frame.impl.hud.ReachDisplayHudFrame;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.Vec3;

public class ReachDisplayHudModule
extends HudModule {
    float k;
    private long K;
    float p;
    private float b;

    public float p() {
        if (System.currentTimeMillis() - this.K >= 5000L) {
            this.b = 0.0f;
            this.K = 0L;
        }
        return this.b;
    }

    private void O$src$V$12sgcml() {
        RotationManager rotationManager = RotationManager.b;
        if (rotationManager.u()) {
            EntityLivingBase entityLivingBase = Minecraft.F();
            entityLivingBase.H(this.p);
            entityLivingBase.C(this.k);
        }
    }

    @EventHandler
    public void M(EventPostAttack eventPostAttack) {
        if (eventPostAttack.getTarget().isInstance(MappedClasses.zm) && !eventPostAttack.getTarget().isInstance(MappedClasses.FT) && Minecraft.p$src$Lgg_vape_wrapper_impl_RayTraceResult_$5rw6n0().isNotNull()) {
            Entity entity = Minecraft.p$src$Lgg_vape_wrapper_impl_RayTraceResult_$5rw6n0().getEntity();
            if (entity.isNull()) {
                return;
            }
            EntityLivingBase entityLivingBase = Minecraft.F();
            double d = Minecraft.playerController().N();
            this.K();
            Vec3 vec3 = entityLivingBase.O(1.0f);
            Vec3 vec32 = entityLivingBase.J(1.0f);
            this.O$src$V$12sgcml();
            Vec3 vec33 = vec3.addVector(vec32.getX() * d, vec32.getY() * d, vec32.getZ() * d);
            float f = entity.b();
            AxisAlignedBB axisAlignedBB = entity.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().expand(f, f, f);
            RayTraceResult rayTraceResult = axisAlignedBB.calculateIntercept(vec3, vec33);
            if (rayTraceResult.isNull()) {
                return;
            }
            double d2 = vec3.distanceTo(rayTraceResult.getHitVec());
            this.b = (float)d2;
            this.K = System.currentTimeMillis();
        }
    }

    public ReachDisplayHudModule() {
        super("Reach Display", HudModuleGroup.f, "reach_display", ReachDisplayHudFrame.class);
        this.setSuffix("Shows how far away your last attack was");
    }


    private void K() {
        RotationManager rotationManager = RotationManager.b;
        if (rotationManager.u()) {
            EntityLivingBase entityLivingBase = Minecraft.F();
            this.p = entityLivingBase.J();
            this.k = entityLivingBase.V();
            entityLivingBase.H(rotationManager.V());
            entityLivingBase.C(rotationManager.x());
        }
    }
}

