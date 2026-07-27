package gg.vape.module.world.bedbreaker;

import func.skidline.RectData;
import gg.vape.module.render.entity.ProjectedEntityBounds;
import gg.vape.module.world.bedbreaker.BedTargetRenderPosition;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.Vec3d;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtil;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderWorldLastEvent;
import gg.vape.wrapper.impl.Vec3;
import java.awt.Color;

public class BedTargetRenderState {
    private ProjectedEntityBounds K;
    private final BedTargetRenderPosition G;
    private float V = 0.0f;
    private boolean O;
    private Vec3d D;
    private final TimerUtil w = new TimerUtil();

    private void H(boolean bl) {
        this.O = bl;
    }

    public void D(Vec3d vec3d) {
        this.D = vec3d;
    }

    public static void H(BedTargetRenderState bedTargetRenderState) {
        bedTargetRenderState.n();
    }

    public BedTargetRenderPosition q() {
        return this.G;
    }

    public void j(RectData rectData, boolean bl, float f) {
        float f2;
        Vec3 vec3 = Minecraft.F().O(1.0f);
        double d = vec3.distanceTo(new Vec3d((double)this.G.N() + 0.5, (double)this.G.h() + 0.5, (double)this.G.D$src$I$nuyd86() + 0.5).n());
        float f3 = 1.0f;
        float f4 = 1.0f;
        float f5 = RenderWorldLastEvent.getPartialTicks();
        float f6 = Minecraft.h();
        ProjectedEntityBounds projectedEntityBounds = this.K;
        double d2 = projectedEntityBounds.r / (double)f4 / (double)f3 / (double)f5;
        double d3 = projectedEntityBounds.V / (double)f4 / (double)f3 / (double)f5;
        double d4 = ((double)f6 - projectedEntityBounds.g / (double)f5) / (double)f4 / (double)f3;
        double d5 = ((double)f6 - projectedEntityBounds.I / (double)f5) / (double)f4 / (double)f3;
        double d6 = d3 - d2;
        double d7 = d5 - d4;
        float f7 = (float)Math.min(d6, d7);
        boolean bl2 = rectData.z(d2 + d6 / 2.0, d4 + d7 / 2.0, f7 / 2.0f);
        boolean bl3 = false;
        if (this.D != null) {
            double d8 = vec3.distanceTo(this.D.n());
            if (d8 < 4.5) {
                bl3 = true;
            }
            if (!bl3) {
                bl2 = false;
            }
        }
        float f8 = 1.0f;
        if (d < 20.0) {
            f2 = d > 10.0 ? (float)((20.0 - d) / 10.0) : 1.0f;
            f8 = f2;
        }
        this.H(bl2);
        f2 = this.i();
        int n = (int)((float)(bl3 ? 160 : 150) * f8);
        int n2 = (int)((float)(bl3 ? 250 : 170) * f8);
        float f9 = (float)(d2 + d6 / 2.0 - (double)((f7 += 20.0f * f2) / 2.0f));
        float f10 = (float)(d4 + d7 / 2.0 - (double)(f7 / 2.0f));
        GuiRenderPrimitives.V(f9, f10, f7, 1.0, new Color(10, 10, 10, n));
        int n3 = (int)(75.0f * f2);
        if (bl3) {
            GuiRenderPrimitives.m(f9, f10, f7, f7 * 0.12f, 1.0f, new Color(10, 100 + n3, 10, 255));
        }
        GuiRenderPrimitives.m(f9, f10, f7, f7 * 0.1f, 1.0f, new Color(10, 10, 10, n2));
        if (bl) {
            float f11 = 100.0f * f;
            float f12 = 360.0f * (f11 / 100.0f);
            if (f11 < 100.0f && f11 > 0.0f) {
                GuiRenderPrimitives.p(f9, f10, f7, f7 * 0.12f, 1.0f, 270.0f, -f12, new Color(10, 100 + n3, 10, 255));
            }
        }
    }

    public float i() {
        return this.V;
    }

    public BedTargetRenderState(BedTargetRenderPosition bedTargetRenderPosition) {
        this.G = bedTargetRenderPosition;
    }

    private void n() {
        if (this.w.hasTimeElapsed(10L)) {
            this.V = this.O ? (float)((double)this.V + 0.05) : (float)((double)this.V - 0.05);
            this.V = Math.min(1.0f, Math.max(0.0f, this.V));
            this.w.reset();
        }
    }

    public static boolean f(BedTargetRenderState bedTargetRenderState) {
        return bedTargetRenderState.O;
    }

    public void h() {
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(0.2, 0.0, 0.2, 0.8, 0.8, 0.8);
        RenderUtil.d();
        this.K = new ProjectedEntityBounds(this.G.G(), this.G.D() + 0.4, this.G.b(), axisAlignedBB, null, null, null);
        RenderUtil.Y();
    }

}

