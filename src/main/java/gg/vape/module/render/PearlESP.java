package gg.vape.module.render;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventRender3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.render.pearl.PearlEspEnderPearlEntityWrapper;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.glu.GluSphere;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderManager;
import org.lwjgl.opengl.GL11;

public class PearlESP
extends Mod {
    public BooleanValue P = BooleanValue.create(this, "Blast ring", true, "Shows the blast radius where blocks will be broken");

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        double d = RenderManager.getInterpolatedRenderPosX();
        double d2 = RenderManager.getInterpolatedRenderPosY();
        double d3 = RenderManager.getInterpolatedRenderPosZ();
        boolean bl = GL11.glIsEnabled((int)3042);
        double d4 = 4.0;
        double d5 = 8.0;
        for (Object e : Minecraft.theWorld().z()) {
            GluSphere gluSphere;
            if (!MappedClasses.qM.isAssignableFrom(e.getClass())) continue;
            PearlEspEnderPearlEntityWrapper pearlEspEnderPearlEntityWrapper = new PearlEspEnderPearlEntityWrapper(e);
            RenderUtil.d();
            OpenGlBackendHolder.d.l(3042);
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
            GL11.glBlendFunc((int)770, (int)771);
            float f = eventRender3D.getTicks();
            double d6 = pearlEspEnderPearlEntityWrapper.M() + (pearlEspEnderPearlEntityWrapper.z() - pearlEspEnderPearlEntityWrapper.M()) * (double)f;
            double d7 = pearlEspEnderPearlEntityWrapper.W() + (pearlEspEnderPearlEntityWrapper.N() - pearlEspEnderPearlEntityWrapper.W()) * (double)f;
            double d8 = pearlEspEnderPearlEntityWrapper.m$src$D$fwnne5() + (pearlEspEnderPearlEntityWrapper.h() - pearlEspEnderPearlEntityWrapper.m$src$D$fwnne5()) * (double)f;
            double d9 = Minecraft.thePlayer().i(d6, d7, d8);
            OpenGlBackendHolder.d.I(d6 - d, d7 - d2, d8 - d3);
            float f2 = ((float)(pearlEspEnderPearlEntityWrapper.v() % 5) - f + 1.0f) / 5.0f;
            float f3 = pearlEspEnderPearlEntityWrapper.v() / 5 % 2 == 0 ? 1.0f : f2 * f2;
            int n = Math.max(Math.min((int)(255.0f * f3), 255), 0);
            if (this.P.L().booleanValue()) {
                OpenGlBackendHolder.d.q(255.0f, 255.0f, 0.0f, (int)((float)n * 0.75f));
                gluSphere = new GluSphere();
                gluSphere.g(100011);
                gluSphere.K((float)d4, 24, 24);
            }
            OpenGlBackendHolder.d.q(255.0f, 0.0f, 0.0f, (int)((float)n * 0.4f));
            gluSphere = new GluSphere();
            gluSphere.g(100012);
            gluSphere.b(d9 < d5 ? 100021 : 100020);
            gluSphere.K((float)d5, 32, 32);
            OpenGlBackendHolder.d.l(3553);
            OpenGlBackendHolder.d.u$src$V$hntn98(3042);
            GL11.glPopMatrix();
        }
        if (bl) {
            OpenGlBackendHolder.d.l(3042);
        }
    }


    public PearlESP() {
        super("Explosions", 11534100, Category.k, "Shows the explosion radius of TNT");
        this.addValue(this.P);
    }
}

