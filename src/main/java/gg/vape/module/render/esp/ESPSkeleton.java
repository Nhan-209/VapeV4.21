package gg.vape.module.render.esp;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventRenderPlayerPost;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.render.ESP;
import gg.vape.module.render.entity.RenderEntityContext;
import gg.vape.module.render.entity.RenderEntityContextCache;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.ModelBiped;
import gg.vape.wrapper.impl.ModelBipedSkeletonBridge;
import gg.vape.wrapper.impl.ModelRenderer;
import gg.vape.wrapper.impl.Render;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.RenderPlayer;
import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class ESPSkeleton
extends SubModule<ESP> {
    private final ESP o = (ESP)this.getParent();

    @EventHandler
    public void i(EventRenderPlayerPost eventRenderPlayerPost) {
        EntityPlayer entityPlayer = eventRenderPlayerPost.getEntityPlayer();
        float f = 57.29578f;
        GL11.glPushMatrix();
        OpenGlBackendHolder.d.u$src$V$hntn98(2929);
        eventRenderPlayerPost.getEntityRenderer().B(0.0);
        double d = RenderManager.getInterpolatedRenderPosX();
        double d2 = RenderManager.getInterpolatedRenderPosY();
        double d3 = RenderManager.getInterpolatedRenderPosZ();
        Color color = this.o.J(eventRenderPlayerPost.getThePlayer(), entityPlayer.getObject());
        if (color != null) {
            RenderPlayer renderPlayer;
            ModelBipedSkeletonBridge modelBipedSkeletonBridge;
            RenderEntityContext renderEntityContext = RenderEntityContextCache.V(entityPlayer, eventRenderPlayerPost.getThePlayer());
            if (renderEntityContext.R()) {
                color = Color.BLUE;
            }
            float f2 = Minecraft.getTimer().renderPartialTicks();
            double d4 = entityPlayer.M();
            double d5 = entityPlayer.W();
            double d6 = entityPlayer.m$src$D$fwnne5();
            double d7 = d4 + (entityPlayer.z() - d4) * (double)f2 - d;
            double d8 = d5 + (entityPlayer.N() - d5) * (double)f2 - d2;
            double d9 = d6 + (entityPlayer.h() - d6) * (double)f2 - d3;
            boolean bl = GL11.glIsEnabled((int)3042);
            RenderUtil.d();
            GL11.glBlendFunc((int)770, (int)771);
            if (!bl) {
                OpenGlBackendHolder.d.l(3042);
            }
            GL11.glBlendFunc((int)770, (int)771);
            RenderUtils.w(color);
            OpenGlBackendHolder.d.u$src$V$hntn98(2896);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
            GL11.glTranslated((double)d7, (double)d8, (double)d9);
            Render render = this.o.r.getEntityRenderObject(entityPlayer);
            if (render.isInstance(MappedClasses.D0) && (modelBipedSkeletonBridge = (renderPlayer = new RenderPlayer(render.getObject())).getMainModel()).isNotNull() && modelBipedSkeletonBridge.isInstance(MappedClasses.zV)) {
                float f3 = (float)renderEntityContext.e();
                float f4 = Math.max(4.0f * ((100.0f - Math.min(f3, 100.0f)) / 100.0f), 0.1f);
                GL11.glLineWidth((float)f4);
                ModelBiped modelBiped = new ModelBiped(modelBipedSkeletonBridge.getObject());
                boolean bl2 = renderEntityContext.A();
                float f5 = bl2 ? 0.6f : 0.75f;
                float f6 = entityPlayer.W$src$F$153nzpr();
                GL11.glRotatef((float)f6, (float)0.0f, (float)-999.0f, (float)0.0f);
                double d10 = bl2 ? -0.2 : 0.0;
                GL11.glTranslated((double)-0.15, (double)f5, (double)d10);
                ModelRenderer modelRenderer = modelBiped.getBipedRightLeg();
                float f7 = modelRenderer.getRotateAngleX() * f;
                float f8 = modelRenderer.getRotateAngleY() * f;
                float f9 = modelRenderer.getRotateAngleZ() * f;
                GL11.glRotatef((float)f7, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)(-f8), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f9), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glBegin((int)1);
                GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
                GL11.glVertex3d((double)0.0, (double)(-f5), (double)0.0);
                GL11.glEnd();
                GL11.glRotatef((float)f9, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)f8, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f7), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslated((double)0.3, (double)0.0, (double)0.0);
                ModelRenderer modelRenderer2 = modelBiped.getBipedLeftLeg();
                f7 = modelRenderer2.getRotateAngleX() * f;
                f8 = modelRenderer2.getRotateAngleY() * f;
                f9 = modelRenderer2.getRotateAngleZ() * f;
                GL11.glRotatef((float)f7, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)(-f8), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f9), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glBegin((int)1);
                GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
                GL11.glVertex3d((double)0.0, (double)(-f5), (double)0.0);
                GL11.glEnd();
                GL11.glRotatef((float)f9, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)f8, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f7), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslated((double)-0.15, (double)0.0, (double)0.0);
                GL11.glBegin((int)1);
                GL11.glVertex3d((double)0.15, (double)0.0, (double)0.0);
                GL11.glVertex3d((double)-0.15, (double)0.0, (double)0.0);
                GL11.glEnd();
                if (bl2) {
                    GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                }
                GL11.glBegin((int)1);
                GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
                GL11.glVertex3d((double)0.0, (double)0.65, (double)0.0);
                GL11.glEnd();
                GL11.glTranslated((double)0.0, (double)0.65, (double)0.0);
                GL11.glBegin((int)1);
                GL11.glVertex3d((double)0.35, (double)0.0, (double)0.0);
                GL11.glVertex3d((double)-0.35, (double)0.0, (double)0.0);
                GL11.glEnd();
                GL11.glTranslated((double)-0.35, (double)0.0, (double)0.0);
                ModelRenderer modelRenderer3 = modelBiped.getBipedRightArm();
                f7 = modelRenderer3.getRotateAngleX() * f;
                f8 = modelRenderer3.getRotateAngleY() * f;
                f9 = modelRenderer3.getRotateAngleZ() * f;
                GL11.glRotatef((float)f7, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)(-f8), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f9), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glBegin((int)1);
                GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
                GL11.glVertex3d((double)0.0, (double)-0.6, (double)0.0);
                GL11.glEnd();
                GL11.glRotatef((float)f9, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)f8, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f7), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslated((double)0.7, (double)0.0, (double)0.0);
                ModelRenderer modelRenderer4 = modelBiped.getBipedLeftArm();
                f7 = modelRenderer4.getRotateAngleX() * f;
                f8 = modelRenderer4.getRotateAngleY() * f;
                f9 = modelRenderer4.getRotateAngleZ() * f;
                GL11.glRotatef((float)f7, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)(-f8), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f9), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glBegin((int)1);
                GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
                GL11.glVertex3d((double)0.0, (double)-0.6, (double)0.0);
                GL11.glEnd();
                GL11.glRotatef((float)f9, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)f8, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f7), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslated((double)-0.35, (double)0.0, (double)0.0);
                GL11.glRotatef((float)(-f6), (float)0.0f, (float)-999.0f, (float)0.0f);
                float f10 = entityPlayer.J();
                float f11 = entityPlayer.V();
                double d11 = 0.4;
                GL11.glRotated((double)f10, (double)0.0, (double)-999.0, (double)0.0);
                GL11.glRotated((double)f11, (double)999.0, (double)0.0, (double)0.0);
                GL11.glBegin((int)1);
                GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
                GL11.glVertex3d((double)0.0, (double)d11, (double)0.0);
                GL11.glEnd();
                GL11.glBegin((int)1);
                GL11.glVertex3d((double)0.0, (double)d11, (double)0.0);
                GL11.glVertex3d((double)0.0, (double)d11, (double)0.25);
                GL11.glEnd();
                GL11.glRotated((double)f11, (double)999.0, (double)0.0, (double)0.0);
                GL11.glRotated((double)(-f10), (double)0.0, (double)999.0, (double)0.0);
            }
            if (!bl) {
                OpenGlBackendHolder.d.u$src$V$hntn98(3042);
            }
            OpenGlBackendHolder.d.l(3553);
            OpenGlBackendHolder.d.u$src$V$hntn98(2848);
            OpenGlBackendHolder.d.l(2896);
            RenderUtil.Y();
        }
        GL11.glColor3d((double)1.0, (double)1.0, (double)1.0);
        eventRenderPlayerPost.getEntityRenderer().O(0.0);
        OpenGlBackendHolder.d.l(2929);
        GL11.glPopMatrix();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ESPSkeleton(Mod mod, String string) {
        super(mod, string);
    }
}

