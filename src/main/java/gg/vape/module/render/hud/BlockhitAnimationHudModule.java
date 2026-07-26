package gg.vape.module.render.hud;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRenderItemInFirstPerson;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.ItemRenderer;
import gg.vape.wrapper.impl.ItemRendererBridge;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.RenderHelper;
import org.lwjgl.opengl.GL11;

public class BlockhitAnimationHudModule
extends HudModule {
    private TimerUtil H = new TimerUtil();
    private boolean A;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public BlockhitAnimationHudModule() {
        super("Blockhit Animation", HudModuleGroup.T, "animations");
        this.setSuffix("Shows 1.7 style blockhit animation constantly when blockhitting");
    }

    private void A(float f, float f2) {
        GL11.glPushMatrix();
        GlStateManager.d(f, 1.0f, 0.0f, 0.0f);
        GlStateManager.d(f2, 0.0f, 1.0f, 0.0f);
        RenderHelper.e();
        GL11.glPopMatrix();
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (this.G() && ClientSettings.V()) {
            if (Minecraft.thePlayer().o$src$Z$1iprrmi() && Minecraft.thePlayer().j$src$I$1in0s92() > 0) {
                this.H.reset();
            }
            if (!this.H.hasTimeElapsed(200L)) {
                this.A = true;
                return;
            }
        }
        this.A = this.R();
    }

    private void y(float f, float f2) {
        GL11.glTranslatef((float)0.56f, (float)-0.52f, (float)-0.71999997f);
        GL11.glTranslatef((float)0.0f, (float)(f * -0.6f), (float)0.0f);
        GlStateManager.d(45.0f, 0.0f, 1.0f, 0.0f);
        float f3 = MathUtil.sin(f2 * f2 * (float)Math.PI);
        float f4 = MathUtil.sin(MathUtil.sqrt(f2) * (float)Math.PI);
        GlStateManager.d(f3 * -20.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.d(f4 * -20.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.d(f4 * -80.0f, 1.0f, 0.0f, 0.0f);
        GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
    }

    private void B(ItemRenderer itemRenderer, float f) {
        float f2 = 1.0f - (itemRenderer.e() + (itemRenderer.R() - itemRenderer.e()) * f);
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        float f3 = entityPlayerSP.L(f);
        float f4 = entityPlayerSP.D() + (entityPlayerSP.V() - entityPlayerSP.D()) * f;
        float f5 = entityPlayerSP.j() + (entityPlayerSP.J() - entityPlayerSP.j()) * f;
        this.A(f4, f5);
        itemRenderer.X(entityPlayerSP);
        this.K(entityPlayerSP, f);
        OpenGlBackendHolder.d.l(32826);
        GL11.glPushMatrix();
        this.y(f2, f3);
        this.U();
        itemRenderer.g(entityPlayerSP, itemRenderer.k(), ItemRendererBridge.G());
        GL11.glPopMatrix();
        OpenGlBackendHolder.d.u$src$V$hntn98(32826);
        RenderHelper.s();
    }

    @EventHandler
    public void n(EventRenderItemInFirstPerson eventRenderItemInFirstPerson) {
        if (this.A) {
            this.B(eventRenderItemInFirstPerson.getItemRenderer(), eventRenderItemInFirstPerson.H);
            eventRenderItemInFirstPerson.setCancelled(true);
        }
    }

    private void K(EntityPlayerSP entityPlayerSP, float f) {
        float f2 = entityPlayerSP.n$src$F$1u53eru() + (entityPlayerSP.t$src$F$1u8e6c0() - entityPlayerSP.n$src$F$1u53eru()) * f;
        float f3 = entityPlayerSP.x$src$F$1ualcpg() + (entityPlayerSP.q$src$F$1u6qsjx() - entityPlayerSP.x$src$F$1ualcpg()) * f;
        GlStateManager.d((entityPlayerSP.V() - f2) * 0.1f, 1.0f, 0.0f, 0.0f);
        GlStateManager.d((entityPlayerSP.J() - f3) * 0.1f, 0.0f, 1.0f, 0.0f);
    }

    private void B(float f) {
        float f2 = -0.4f * MathUtil.sin(MathUtil.sqrt(f) * (float)Math.PI);
        float f3 = 0.2f * MathUtil.sin(MathUtil.sqrt(f) * (float)Math.PI * 2.0f);
        float f4 = -0.2f * MathUtil.sin(f * (float)Math.PI);
        GL11.glTranslatef((float)f2, (float)f3, (float)f4);
    }

    private boolean R() {
        if (Minecraft.thePlayer().isNull()) {
            return false;
        }
        RayTraceResult rayTraceResult = RotationManager.b.n();
        if (rayTraceResult.isNotNull() && rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
            return false;
        }
        if (this.G()) {
            if (Minecraft.thePlayer().o$src$Z$1iprrmi() && Minecraft.thePlayer().j$src$I$1in0s92() > 0) {
                this.H.reset();
            }
            return !this.H.hasTimeElapsed(200L);
        }
        return false;
    }

    private void U() {
        GL11.glTranslatef((float)-0.5f, (float)0.2f, (float)0.0f);
        GlStateManager.d(30.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.d(-80.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.d(60.0f, 0.0f, 1.0f, 0.0f);
    }

    public boolean G() {
        if (Minecraft.currentScreen().isNotNull()) {
            return false;
        }
        return Minecraft.thePlayer().getHeldItemHand().isNotNull() && ItemStackScoreUtil.h(Minecraft.thePlayer().getHeldItemHand().getItem());
    }
}

