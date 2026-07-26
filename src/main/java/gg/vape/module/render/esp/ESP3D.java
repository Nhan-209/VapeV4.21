package gg.vape.module.render.esp;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventRender3D;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.blatant.HitBoxes;
import gg.vape.module.render.ESP;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.RenderManager;
import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class ESP3D
extends SubModule<ESP> {
    private final ESP I = (ESP)this.getParent();
    private HitBoxes Z;

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        eventRender3D.getEntityRenderer().B(1.0);
        RenderUtil.d();
        boolean bl = OpenGlBackendHolder.d.L(3042);
        if (bl) {
            GL11.glBlendFunc((int)770, (int)771);
            OpenGlBackendHolder.d.r(1.5f);
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.u$src$V$hntn98(2929);
            OpenGlBackendHolder.d.U(false);
            double d = RenderManager.getInterpolatedRenderPosX();
            double d2 = RenderManager.getInterpolatedRenderPosY();
            double d3 = RenderManager.getInterpolatedRenderPosZ();
            if (this.Z == null) {
                this.Z = Vape.INSTANCE.getModManager().getMod(HitBoxes.class);
            }
            for (Object e : eventRender3D.getWorld().z()) {
                MutableColor mutableColor;
                Entity entity = new Entity(e);
                if (Vape.INSTANCE.getClientSettings().J(entity) && this.I.Z.L().booleanValue() || (mutableColor = this.I.J(eventRender3D.getThePlayer(), e)) == null) continue;
                double d4 = entity.M();
                double d5 = entity.W();
                double d6 = entity.m$src$D$fwnne5();
                double d7 = d4 + (entity.z() - d4) * (double)eventRender3D.getTicks();
                double d8 = d5 + (entity.N() - d5) * (double)eventRender3D.getTicks();
                double d9 = d6 + (entity.h() - d6) * (double)eventRender3D.getTicks();
                float f = entity.b() + (this.I.A.L() != false ? this.Z.z() : 0.0f);
                AxisAlignedBB axisAlignedBB = entity.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
                AxisAlignedBB axisAlignedBB2 = axisAlignedBB.expand(f, f, f);
                RenderUtil.u(d7 - (axisAlignedBB2.getMinX() - axisAlignedBB2.getMaxX()) / 2.0, d8 + (axisAlignedBB2.getMinY() - entity.N()), d9 - (axisAlignedBB2.getMinZ() - axisAlignedBB2.getMaxZ()) / 2.0, axisAlignedBB2.getMinZ() - axisAlignedBB2.getMaxZ(), axisAlignedBB2.getMaxY() - axisAlignedBB2.getMinY(), axisAlignedBB2.getMinX() - axisAlignedBB2.getMaxX(), 0.1, mutableColor, null, d, d2, d3);
                if (!(this.Z.z() > 0.0f) || !this.I.A.L().booleanValue() || !this.I.K.L().booleanValue()) continue;
                axisAlignedBB2 = axisAlignedBB.expand(entity.b(), entity.b(), entity.b());
                Color color = new Color(255 - ((Color)mutableColor).getRed(), 255 - ((Color)mutableColor).getGreen(), 255 - ((Color)mutableColor).getBlue(), 70);
                RenderUtil.u(d7 - (axisAlignedBB2.getMinX() - axisAlignedBB2.getMaxX()) / 2.0, d8 - (axisAlignedBB2.getMinY() - entity.N()), d9 - (axisAlignedBB2.getMinZ() - axisAlignedBB2.getMaxZ()) / 2.0, axisAlignedBB2.getMinZ() - axisAlignedBB2.getMaxZ(), axisAlignedBB2.getMaxY() - axisAlignedBB2.getMinY(), axisAlignedBB2.getMinX() - axisAlignedBB2.getMaxX(), 0.1, color, null, d, d2, d3);
            }
            OpenGlBackendHolder.d.U(true);
            OpenGlBackendHolder.d.l(2929);
            OpenGlBackendHolder.d.l(3553);
            OpenGlBackendHolder.d.u$src$V$hntn98(2848);
            RenderUtil.Y();
            eventRender3D.getEntityRenderer().O(1.0);
            return;
        }
        OpenGlBackendHolder.d.l(3042);
        GL11.glBlendFunc((int)770, (int)771);
        OpenGlBackendHolder.d.r(1.5f);
        OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        OpenGlBackendHolder.d.l(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(2929);
        OpenGlBackendHolder.d.U(false);
        double d = RenderManager.getInterpolatedRenderPosX();
        double d10 = RenderManager.getInterpolatedRenderPosY();
        double d11 = RenderManager.getInterpolatedRenderPosZ();
        if (this.Z == null) {
            this.Z = Vape.INSTANCE.getModManager().getMod(HitBoxes.class);
        }
        for (Object e : eventRender3D.getWorld().z()) {
            MutableColor mutableColor;
            Entity entity = new Entity(e);
            if (Vape.INSTANCE.getClientSettings().J(entity) && this.I.Z.L().booleanValue() || (mutableColor = this.I.J(eventRender3D.getThePlayer(), e)) == null) continue;
            double d12 = entity.M();
            double d13 = entity.W();
            double d14 = entity.m$src$D$fwnne5();
            double d15 = d12 + (entity.z() - d12) * (double)eventRender3D.getTicks();
            double d16 = d13 + (entity.N() - d13) * (double)eventRender3D.getTicks();
            double d17 = d14 + (entity.h() - d14) * (double)eventRender3D.getTicks();
            float f = entity.b() + (this.I.A.L() != false ? this.Z.z() : 0.0f);
            AxisAlignedBB axisAlignedBB = entity.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
            AxisAlignedBB axisAlignedBB3 = axisAlignedBB.expand(f, f, f);
            RenderUtil.u(d15 - (axisAlignedBB3.getMinX() - axisAlignedBB3.getMaxX()) / 2.0, d16 + (axisAlignedBB3.getMinY() - entity.N()), d17 - (axisAlignedBB3.getMinZ() - axisAlignedBB3.getMaxZ()) / 2.0, axisAlignedBB3.getMinZ() - axisAlignedBB3.getMaxZ(), axisAlignedBB3.getMaxY() - axisAlignedBB3.getMinY(), axisAlignedBB3.getMinX() - axisAlignedBB3.getMaxX(), 0.1, mutableColor, null, d, d10, d11);
            if (!(this.Z.z() > 0.0f) || !this.I.A.L().booleanValue() || !this.I.K.L().booleanValue()) continue;
            axisAlignedBB3 = axisAlignedBB.expand(entity.b(), entity.b(), entity.b());
            Color color = new Color(255 - ((Color)mutableColor).getRed(), 255 - ((Color)mutableColor).getGreen(), 255 - ((Color)mutableColor).getBlue(), 70);
            RenderUtil.u(d15 - (axisAlignedBB3.getMinX() - axisAlignedBB3.getMaxX()) / 2.0, d16 - (axisAlignedBB3.getMinY() - entity.N()), d17 - (axisAlignedBB3.getMinZ() - axisAlignedBB3.getMaxZ()) / 2.0, axisAlignedBB3.getMinZ() - axisAlignedBB3.getMaxZ(), axisAlignedBB3.getMaxY() - axisAlignedBB3.getMinY(), axisAlignedBB3.getMinX() - axisAlignedBB3.getMaxX(), 0.1, color, null, d, d10, d11);
        }
        OpenGlBackendHolder.d.U(true);
        OpenGlBackendHolder.d.l(2929);
        OpenGlBackendHolder.d.l(3553);
        OpenGlBackendHolder.d.u$src$V$hntn98(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        RenderUtil.Y();
        eventRender3D.getEntityRenderer().O(1.0);
    }

    public ESP3D(Mod mod, String string) {
        super(mod, string);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

