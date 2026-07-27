package gg.vape.module.render.esp;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreRenderLiving;
import gg.vape.event.impl.EventRender2D;
import gg.vape.friend.FriendEntry;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.render.ESP;
import gg.vape.module.render.entity.ProjectedEntityBounds;
import gg.vape.module.render.entity.RenderEntityContext;
import gg.vape.module.render.entity.RenderEntityContextCache;
import gg.vape.render.OffscreenRenderContext;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.RenderWorldLastEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class ESP2D
extends SubModule<ESP> {
    private final List<ProjectedEntityBounds> pendingBounds;
    private final ESP parentEsp = (ESP)this.getParent();
    private static final String NON_ASCII_PATTERN = "[^\u00a7^\\x00-\\x7F]";

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        if (OffscreenRenderContext.W()) {
            return;
        }
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().W(0.9, true);
        OpenGlBackendHolder.d.m();
        float f = 1.0f;
        float f2 = 2.0f;
        OpenGlBackendHolder.d.H(f, f, f);
        GlStateManager.enableAlpha();
        float f3 = RenderWorldLastEvent.getPartialTicks();
        boolean bl = GL11.glIsEnabled((int)3042);
        RenderUtils.g();
        for (ProjectedEntityBounds projectedEntityBounds : this.pendingBounds) {
            double d;
            boolean bl2;
            float f4;
            if (ForgeVersion.MC_1_16_5.d()) {
                f4 = eventRender2D.getRenderManager().getPlayerViewX();
                float f5 = eventRender2D.getRenderManager().getPlayerViewY();
            }
            f4 = eventRender2D.getDisplayHeight();
            double d2 = projectedEntityBounds.r / (double)f2 / (double)f / (double)f3;
            double d3 = projectedEntityBounds.V / (double)f2 / (double)f / (double)f3;
            double d4 = ((double)f4 - projectedEntityBounds.g / (double)f3) / (double)f2 / (double)f;
            double d5 = ((double)f4 - projectedEntityBounds.I / (double)f3) / (double)f2 / (double)f;
            GlStateManager.disableTexture2D();
            OpenGlBackendHolder.d.r(1.0f);
            GlStateManager.enableBlend();
            String string = projectedEntityBounds.c.k();
            boolean bl3 = projectedEntityBounds.c.K$src$Z$1xmao67();
            boolean bl4 = projectedEntityBounds.c.f();
            boolean bl5 = bl2 = bl4 || bl3;
            if (this.parentEsp.c.L().booleanValue() && (!this.parentEsp.D.L().booleanValue() || bl2)) {
                float f6 = (float)projectedEntityBounds.U.getAlpha() / 255.0f;
                if (GuiRenderPrimitives.d()) {
                    double d6 = d5 - d4;
                    d = d3 - d2;
                    double d7 = 1.0;
                    BufferedGuiRenderPrimitives.V(d2, d4, d, d7, new Color(0.0f, 0.0f, 0.0f, 0.4f * f6));
                    BufferedGuiRenderPrimitives.V(d2, d4, d7, d6, new Color(0.0f, 0.0f, 0.0f, 0.4f * f6));
                    BufferedGuiRenderPrimitives.V(d3, d5, -d7, -d6, new Color(0.0f, 0.0f, 0.0f, 0.4f * f6));
                    BufferedGuiRenderPrimitives.V(d3, d5, -d, -d7, new Color(0.0f, 0.0f, 0.0f, 0.4f * f6));
                    d6 = (d5 -= 1.0) - (d4 += 1.0);
                    d = (d3 -= 1.0) - (d2 += 1.0);
                    BufferedGuiRenderPrimitives.V(d2, d4, d, d7, new Color(0.0f, 0.0f, 0.0f, 0.4f * f6));
                    BufferedGuiRenderPrimitives.V(d2, d4, d7, d6, new Color(0.0f, 0.0f, 0.0f, 0.4f * f6));
                    BufferedGuiRenderPrimitives.V(d3, d5, -d7, -d6, new Color(0.0f, 0.0f, 0.0f, 0.4f * f6));
                    BufferedGuiRenderPrimitives.V(d3, d5, -d, -d7, new Color(0.0f, 0.0f, 0.0f, 0.4f * f6));
                    d6 = (d5 += 0.5) - (d4 -= 0.5);
                    d = (d3 += 0.5) - (d2 -= 0.5);
                    BufferedGuiRenderPrimitives.V(d2, d4, d, d7, projectedEntityBounds.U);
                    BufferedGuiRenderPrimitives.V(d2, d4, d7, d6, projectedEntityBounds.U);
                    BufferedGuiRenderPrimitives.V(d3, d5, -d7, -d6, projectedEntityBounds.U);
                    BufferedGuiRenderPrimitives.V(d3, d5, -d, -d7, projectedEntityBounds.U);
                } else {
                    OpenGlBackendHolder.d.k(0.0, 0.0, 0.0, 0.4 * (double)f6);
                    GL11.glBegin((int)2);
                    GL11.glVertex2d((double)d2, (double)d4);
                    GL11.glVertex2d((double)d3, (double)d4);
                    GL11.glVertex2d((double)d3, (double)d5);
                    GL11.glVertex2d((double)d2, (double)d5);
                    GL11.glEnd();
                    GL11.glBegin((int)2);
                    GL11.glVertex2d((double)(d2 + 1.0), (double)(d4 + 1.0));
                    GL11.glVertex2d((double)(d3 - 1.0), (double)(d4 + 1.0));
                    GL11.glVertex2d((double)(d3 - 1.0), (double)(d5 - 1.0));
                    GL11.glVertex2d((double)(d2 + 1.0), (double)(d5 - 1.0));
                    GL11.glEnd();
                    GlStateManager.enableBlend();
                    RenderUtils.w(projectedEntityBounds.U);
                    GL11.glBegin((int)2);
                    GL11.glVertex2d((double)(d2 + 0.5), (double)(d4 + 0.5));
                    GL11.glVertex2d((double)(d3 - 0.5), (double)(d4 + 0.5));
                    GL11.glVertex2d((double)(d3 - 0.5), (double)(d5 - 0.5));
                    GL11.glVertex2d((double)(d2 + 0.5), (double)(d5 - 0.5));
                    GL11.glEnd();
                    GlStateManager.disableBlend();
                }
            }
            if (projectedEntityBounds.y.isInstance(MappedClasses.zm)) {
                EntityLivingBase entityLivingBase = new EntityLivingBase(projectedEntityBounds.y.getObject());
                float f7 = projectedEntityBounds.c.t();
                if (this.parentEsp.J.L().booleanValue() && f7 >= 0.0f && projectedEntityBounds.c.y() >= 0.0f) {
                    double d8 = Math.min(1.0f, f7 / projectedEntityBounds.c.y());
                    if (GuiRenderPrimitives.d()) {
                        BufferedGuiRenderPrimitives.r(d2 - 2.0, d5 - 0.5, d2 - 2.0, d4 + 0.5, d2 - 4.0, d4 + 0.5, d2 - 4.0, d5 - 0.5, new Color(0.0f, 0.0f, 0.0f, 0.4f));
                    } else {
                        GlStateManager.enableBlend();
                        OpenGlBackendHolder.d.k(0.0, 0.0, 0.0, 0.4);
                        GL11.glBegin((int)7);
                        GL11.glVertex2d((double)(d2 - 2.0), (double)(d5 - 0.5));
                        GL11.glVertex2d((double)(d2 - 2.0), (double)(d4 + 0.5));
                        GL11.glVertex2d((double)(d2 - 4.0), (double)(d4 + 0.5));
                        GL11.glVertex2d((double)(d2 - 4.0), (double)(d5 - 0.5));
                        GL11.glEnd();
                    }
                    double d9 = d5 - d4 - 1.0;
                    double d10 = d4 + d9 * d8;
                    double d11 = 0.0;
                    double d12 = 0.0;
                    double d13 = 0.0;
                    double d14 = 0.0;
                    if (d8 >= 0.9) {
                        d11 = 0.0;
                        d12 = 1.0;
                        d13 = 0.0;
                        d14 = 1.0;
                    } else if (d8 >= 0.75) {
                        d11 = 0.9;
                        d12 = 1.0;
                        d13 = 0.0;
                        d14 = 1.0;
                    } else if (d8 >= 0.5) {
                        d11 = 1.0;
                        d12 = 1.0;
                        d13 = 0.0;
                        d14 = 1.0;
                    } else if (d8 >= 0.25) {
                        d11 = 1.0;
                        d12 = 0.5;
                        d13 = 0.0;
                        d14 = 1.0;
                    } else if (d8 >= 0.0) {
                        d11 = 1.0;
                        d12 = 0.0;
                        d13 = 0.0;
                        d14 = 1.0;
                    }
                    if (GuiRenderPrimitives.d()) {
                        BufferedGuiRenderPrimitives.r(d2 - 2.5, d10, d2 - 2.5, d4 + 1.0, d2 - 3.5, d4 + 1.0, d2 - 3.5, d10, new Color((int)(d11 * 255.0), (int)(d12 * 255.0), (int)(d13 * 255.0), (int)(d14 * 255.0)));
                    } else {
                        GL11.glColor4d((double)d11, (double)d12, (double)d13, (double)d14);
                        GL11.glBegin((int)7);
                        GL11.glVertex2d((double)(d2 - 2.5), (double)d10);
                        GL11.glVertex2d((double)(d2 - 2.5), (double)(d4 + 1.0));
                        GL11.glVertex2d((double)(d2 - 3.5), (double)(d4 + 1.0));
                        GL11.glVertex2d((double)(d2 - 3.5), (double)d10);
                        GL11.glEnd();
                    }
                }
                if (this.parentEsp.U.L().booleanValue()) {
                    FriendEntry friendEntry;
                    String string2;
                    String string3 = string2 = this.parentEsp.I.L() == false || bl2 ? projectedEntityBounds.c.k() : projectedEntityBounds.c.o();
                    if (this.parentEsp.I.L().booleanValue()) {
                        string2 = string2.replaceAll(NON_ASCII_PATTERN, "");
                    }
                    if (bl3 && (friendEntry = Vape.INSTANCE.getFriendManager().O(projectedEntityBounds.c.k())) != null) {
                        string2 = friendEntry.o();
                    }
                    d = smoothFontRenderer.N(string2);
                    if (this.parentEsp.a.L().booleanValue()) {
                        Color color = bl2 ? projectedEntityBounds.U : new Color(0, 0, 0, 95);
                        GlStateManager.disableTexture2D();
                        boolean bl6 = entityLivingBase.P();
                        double d15 = bl6 ? 1.5 : 0.5;
                        Color color2 = bl6 ? new Color(255, 0, 0, 200) : new Color(0, 0, 0, 102);
                        float f8 = (float)(d3 + (d2 - d3) / 2.0 - d / 2.0 - 1.5);
                        float f9 = (float)(d4 - 10.0);
                        float f10 = (float)(d3 + (d2 - d3) / 2.0 + d / 2.0 + 1.5);
                        float f11 = (float)(d4 - 1.0);
                        if (GuiRenderPrimitives.d()) {
                            BufferedGuiRenderPrimitives.t(f8, f9 + 1.0f, f10 - f8, f11 - f9 + 1.0f, d15, color, color2);
                        } else {
                            RenderUtils.M(f8, f9, f10, f11, d15, color, color2);
                        }
                        GlStateManager.enableTexture2D();
                    }
                    smoothFontRenderer.g(string2, d3 + (d2 - d3) / 2.0 - d / 2.0, d4 - 8.0, bl2 ? -1 : projectedEntityBounds.U.getRGB());
                }
            }
            OpenGlBackendHolder.d.q(1.0f, 1.0f, 1.0f, 1.0f);
            OpenGlBackendHolder.d.u$src$V$hntn98(2848);
            GlStateManager.enableTexture2D();
        }
        RenderUtils.f();
        if (bl) {
            GlStateManager.enableBlend();
        } else {
            GlStateManager.disableBlend();
        }
        RenderUtil.Y();
        this.pendingBounds.clear();
    }


    @EventHandler
    public void S(EventPreRenderLiving eventPreRenderLiving) {
        if (eventPreRenderLiving.getWorld().isNull()) {
            return;
        }
        Entity entity = eventPreRenderLiving.getEntity();
        double d = eventPreRenderLiving.getX();
        double d2 = eventPreRenderLiving.getY();
        double d3 = eventPreRenderLiving.getZ();
        MutableColor mutableColor = this.parentEsp.J(eventPreRenderLiving.getThePlayer(), entity.getObject());
        if (mutableColor == null) {
            return;
        }
        EntityLivingBase entityLivingBase = new EntityLivingBase(entity.getObject());
        RenderUtil.d();
        float f = entity.b();
        AxisAlignedBB axisAlignedBB = entity.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().expand(f, f, f);
        AxisAlignedBB axisAlignedBB2 = AxisAlignedBB.create(axisAlignedBB.getMinX() - entity.z(), axisAlignedBB.getMinY() - entity.N(), axisAlignedBB.getMinZ() - entity.h(), axisAlignedBB.getMaxX() - entity.z(), axisAlignedBB.getMaxY() - entity.N(), axisAlignedBB.getMaxZ() - entity.h());
        RenderEntityContext renderEntityContext = RenderEntityContextCache.V(entityLivingBase, eventPreRenderLiving.getThePlayer());
        ProjectedEntityBounds projectedEntityBounds = new ProjectedEntityBounds(d, d2, d3, axisAlignedBB2, entity, renderEntityContext, mutableColor);
        if (projectedEntityBounds.L) {
            this.pendingBounds.add(projectedEntityBounds);
            if (this.parentEsp.U.L().booleanValue()) {
                eventPreRenderLiving.setCancelled(true);
            }
        }
        RenderUtil.Y();
    }

    public ESP2D(Mod mod, String string) {
        super(mod, string);
        this.pendingBounds = new ArrayList<ProjectedEntityBounds>();
    }
}

