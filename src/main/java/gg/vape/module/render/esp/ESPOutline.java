package gg.vape.module.render.esp;

import com.google.common.collect.Lists;
import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventPreRenderEntity;
import gg.vape.event.impl.EventPreRenderLiving;
import gg.vape.event.impl.EventPreRenderPlayerSpec;
import gg.vape.event.impl.EventRender3D;
import gg.vape.event.impl.EventSetArmorModel;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.render.ESP;
import gg.vape.render.OffscreenRenderContext;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtils;
import gg.vape.utils.render.StencilUtil;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderLivingBase;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class ESPOutline
extends SubModule<ESP> {
    private boolean I;
    private boolean V;
    private boolean p = false;
    private int v = 0;
    private boolean t;

    @EventHandler
    public void e(EventPreRenderLiving eventPreRenderLiving) {
        if (OffscreenRenderContext.W()) {
            return;
        }
        if (Vape.INSTANCE.getClientSettings().J(eventPreRenderLiving.getEntity()) && ((ESP)this.getParent()).Z.L().booleanValue()) {
            return;
        }
        if (((ESP)this.getParent()).S.L().booleanValue() && (((ESP)this.getParent()).o.L() != false ? !Vape.INSTANCE.getEnemyManager().q(eventPreRenderLiving.getEntity().getName()) : !Vape.INSTANCE.getClientSettings().g(eventPreRenderLiving.getEntity(), false))) {
            return;
        }
        if (eventPreRenderLiving.getWorld().isNull()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = eventPreRenderLiving.getThePlayer();
        Entity entity = eventPreRenderLiving.getEntity();
        if (entity.equals(entityPlayerSP)) {
            return;
        }
        if (this.I) {
            eventPreRenderLiving.setCancelled(true);
        }
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (Minecraft.gameSettings().M()) {
            ((ESP)this.getParent()).O.setValue(((ESP)this.getParent()).L);
            Vape.INSTANCE.getNotificationManager().k("ESP reverted to 3D mode", "Disable fast render to use outline", 5000L);
        }
    }

    public ESPOutline(Mod mod, String string) {
        super(mod, string);
    }


    @EventHandler
    public void V(EventSetArmorModel eventSetArmorModel) {
        if (OffscreenRenderContext.W()) {
            return;
        }
        if (Vape.INSTANCE.getClientSettings().J(eventSetArmorModel.getEntity()) && ((ESP)this.getParent()).Z.L().booleanValue()) {
            return;
        }
        if (((ESP)this.getParent()).S.L().booleanValue() && (((ESP)this.getParent()).o.L() != false ? !Vape.INSTANCE.getEnemyManager().q(eventSetArmorModel.getEntity().getName()) : !Vape.INSTANCE.getClientSettings().g(eventSetArmorModel.getEntity(), false))) {
            return;
        }
        if (eventSetArmorModel.getWorld().isNull()) {
            return;
        }
        if (this.I) {
            eventSetArmorModel.setResult(0);
            eventSetArmorModel.setCancelled(true);
        }
    }

    public boolean K() {
        return this.I;
    }

    @EventHandler
    public void H(EventPreRenderEntity eventPreRenderEntity) {
        if (OffscreenRenderContext.W()) {
            return;
        }
        if (((ESP)this.getParent()).J(eventPreRenderEntity.getThePlayer(), eventPreRenderEntity.getEntity()) == null) {
            return;
        }
        if (this.I && ForgeVersion.MC_1_7_10.L()) {
            RenderUtils.g();
            RenderUtils.w(((ESP)this.getParent()).H.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            RenderUtils.f();
            return;
        }
        if (!eventPreRenderEntity.getEntity().isInstance(MappedClasses.z5) && eventPreRenderEntity.getEntity().isInstance(MappedClasses.Yl)) {
            this.p = true;
            ++this.v;
        }
    }

    @EventHandler
    public void x(EventPreRenderPlayerSpec eventPreRenderPlayerSpec) {
        if (OffscreenRenderContext.W()) {
            return;
        }
        if (Vape.INSTANCE.getClientSettings().J(eventPreRenderPlayerSpec.getClientPlayer()) && ((ESP)this.getParent()).Z.L().booleanValue()) {
            return;
        }
        if (((ESP)this.getParent()).S.L().booleanValue() && (((ESP)this.getParent()).o.L() != false ? !Vape.INSTANCE.getEnemyManager().q(eventPreRenderPlayerSpec.getClientPlayer().getName()) : !Vape.INSTANCE.getClientSettings().g(eventPreRenderPlayerSpec.getClientPlayer(), false))) {
            return;
        }
        if (eventPreRenderPlayerSpec.getWorld().isNull()) {
            return;
        }
        if (this.I) {
            eventPreRenderPlayerSpec.setCancelled(true);
        }
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (OffscreenRenderContext.W()) {
            return;
        }
        if (ForgeVersion.MC_1_7_10.B() && (!this.p || this.v < 10)) {
            return;
        }
        EntityPlayerSP entityPlayerSP = eventRender3D.getThePlayer();
        WorldClient worldClient = eventRender3D.getWorld();
        StencilUtil.d();
        int n = GL11.glGenLists((int)1);
        StencilUtil.t().j();
        GL11.glPushMatrix();
        RenderUtils.g();
        boolean bl = GL11.glIsEnabled((int)2929);
        boolean bl2 = GL11.glIsEnabled((int)3042);
        boolean bl3 = GL11.glIsEnabled((int)3553);
        boolean bl4 = GL11.glIsEnabled((int)2896);
        boolean bl5 = GL11.glIsEnabled((int)2848);
        boolean bl6 = GL11.glIsEnabled((int)3008);
        GlStateManager.disableDepth();
        StencilUtil.t().B(true);
        GL11.glNewList((int)n, (int)4864);
        boolean bl7 = false;
        for (Object e : worldClient.z()) {
            Entity entity = new Entity(e);
            MutableColor mutableColor = ((ESP)this.getParent()).J(eventRender3D.getThePlayer(), entity);
            if (mutableColor == null || ((ESP)this.getParent()).S.L().booleanValue() && (((ESP)this.getParent()).o.L() == false ? !Vape.INSTANCE.getClientSettings().g(entity, false) : !Vape.INSTANCE.getEnemyManager().q(entity.getName()))) continue;
            if (entity.equals(entityPlayerSP) || !entity.isInstance(MappedClasses.Yl)) continue;
            double d = entity.M();
            double d2 = entity.W();
            double d3 = entity.m$src$D$fwnne5();
            double d4 = d + (entity.z() - d) * (double)eventRender3D.getTicks() - RenderManager.getInterpolatedRenderPosX();
            double d5 = d2 + (entity.N() - d2) * (double)eventRender3D.getTicks() - RenderManager.getInterpolatedRenderPosY();
            double d6 = d3 + (entity.h() - d3) * (double)eventRender3D.getTicks() - RenderManager.getInterpolatedRenderPosZ();
            boolean bl8 = entity.J$src$Z$fdev5g();
            entity.q(false);
            GL11.glPushMatrix();
            GL11.glLineWidth((float)3.0f);
            OpenGlBackendHolder.d.l(2848);
            GlStateManager.enableAlpha();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            eventRender3D.getEntityRenderer().B(0.0);
            RenderLivingBase renderLivingBase = new RenderLivingBase(Minecraft.D().getEntityRenderObject(entity).getObject());
            if (renderLivingBase.isNotNull()) {
                this.I = true;
                ArrayList arrayList = null;
                if (ForgeVersion.MC_1_7_10.Y()) {
                    List<Object> list = renderLivingBase.getLayerRenderers();
                    arrayList = Lists.newArrayList(list);
                    list.clear();
                }
                float[] fArray = RenderUtils.d(mutableColor.l());
                OpenGlBackendHolder.d.q(fArray[0], fArray[1], fArray[2], fArray[3]);
                renderLivingBase.doRender(entity, d4, d5, d6, eventRender3D.getTicks(), eventRender3D.getTicks());
                if (ForgeVersion.MC_1_7_10.Y()) {
                    renderLivingBase.setLayerRenderers(arrayList);
                }
                this.I = false;
            }
            entity.q(bl8);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            eventRender3D.getEntityRenderer().O(1.0);
            GL11.glPopMatrix();
        }
        GlStateManager.enableAlpha();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        eventRender3D.getEntityRenderer().B(0.0);
        GL11.glEndList();
        GL11.glPolygonMode((int)1032, (int)6913);
        GL11.glCallList((int)n);
        GL11.glPolygonMode((int)1032, (int)6912);
        GL11.glCallList((int)n);
        StencilUtil.t().B(false);
        GL11.glPolygonMode((int)1032, (int)6914);
        GL11.glCallList((int)n);
        StencilUtil.t().R();
        GL11.glPolygonMode((int)1032, (int)6913);
        GL11.glCallList((int)n);
        GL11.glPolygonMode((int)1032, (int)6912);
        GL11.glCallList((int)n);
        GL11.glPolygonMode((int)1032, (int)6914);
        StencilUtil.t().X();
        GlStateManager.r(2929, bl);
        GL11.glDeleteLists((int)n, (int)1);
        GlStateManager.r(2896, bl4);
        GlStateManager.r(3042, bl2);
        GlStateManager.r(3553, bl3);
        if (bl5) {
            OpenGlBackendHolder.d.l(2848);
        } else {
            OpenGlBackendHolder.d.u$src$V$hntn98(2848);
        }
        GlStateManager.r(3008, bl6);
        eventRender3D.getEntityRenderer().O(1.0);
        GL11.glPopMatrix();
        RenderUtils.f();
    }
}
