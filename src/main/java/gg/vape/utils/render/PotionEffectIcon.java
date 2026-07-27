package gg.vape.utils.render;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.GlFramebuffer;
import gg.vape.utils.render.PotionEffectIconRenderBackend;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.GuiContainer;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Potion;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.RenderHelper;
import gg.vape.wrapper.impl.RenderItemTextBridge;
import gg.vape.wrapper.impl.StatusEffect;
import gg.vape.wrapper.impl.StatusEffectSpriteUploader;
import gg.vape.wrapper.impl.TextureAtlas;
import gg.vape.wrapper.impl.TextureAtlasSprite;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class PotionEffectIcon
implements PotionEffectIconRenderBackend {
    GlFramebuffer n;

    @Override
    public void d(float f, float f2, int n, int n2, float f3) {
        GL11.glEnable(2903);
        boolean bl = GL11.glIsEnabled(3553);
        boolean bl2 = GL11.glIsEnabled(2896);
        boolean bl3 = GL11.glIsEnabled(3008);
        boolean bl4 = GL11.glIsEnabled(3042);
        if (!bl) {
            GlStateManager.enableTexture2D();
        }
        if (bl2) {
            GlStateManager.disableLighting();
        }
        if (!bl3) {
            GlStateManager.enableAlpha();
        }
        if (!bl4) {
            GlStateManager.enableBlend();
        }
        int n3 = GL11.glGetInteger(32873);
        this.n.S();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, f3);
        GL11.glPushMatrix();
        float f4 = 0.0f;
        float f5 = 1.0f;
        float f6 = 1.0f;
        float f7 = 0.0f;
        GL11.glBegin(7);
        GL11.glTexCoord2f(f6, f5);
        GL11.glVertex2f(f + (float)n, f2);
        GL11.glTexCoord2f(f4, f5);
        GL11.glVertex2f(f, f2);
        GL11.glTexCoord2f(f4, f7);
        GL11.glVertex2f(f, f2 + (float)n2);
        GL11.glTexCoord2f(f6, f7);
        GL11.glVertex2f(f + (float)n, f2 + (float)n2);
        GL11.glEnd();
        GL11.glPopMatrix();
        this.n.M();
        GlStateManager.bindTexture(n3);
        if (!bl) {
            GlStateManager.disableTexture2D();
        }
        if (bl2) {
            GlStateManager.enableLighting();
        }
        if (!bl3) {
            GlStateManager.disableAlpha();
        }
        if (!bl4) {
            GlStateManager.disableBlend();
        }
    }

    private void T(PotionEffect potionEffect, int n, int n2) {
        if (ForgeVersion.MC_1_16_5.d()) {
            StatusEffectSpriteUploader statusEffectSpriteUploader = StatusEffectSpriteUploader.c();
            StatusEffect statusEffect = potionEffect.i();
            TextureAtlasSprite textureAtlasSprite = statusEffectSpriteUploader.l(statusEffect);
            TextureAtlas textureAtlas = new TextureAtlas(textureAtlasSprite.e());
            Minecraft.Z().g(textureAtlas.K());
            GL11.glPushMatrix();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.disableDepth();
            RenderItemTextBridge.x(MatrixStack.A(), 0, 0, 0, n, n2, textureAtlasSprite);
            GL11.glPopMatrix();
            return;
        }
        Minecraft.Z().g(GuiContainer.m$src$Lgg_vape_wrapper_impl_ResourceLocation_$1fc62cj());
        Potion potion = Potion.getPotionById(potionEffect.C());
        if (potion.isBadEffect()) {
            int n3 = potion.y();
            GL11.glPushMatrix();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.disableDepth();
            RenderUtils.R(0, 0, n3 % 8 * 18, 198 + n3 / 8 * 18, n, n2);
            GL11.glPopMatrix();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void a(PotionEffect potionEffect) {
        int n = 18;
        int n2 = 18;
        int n3 = GL11.glGetInteger(36006);
        int n4 = GL11.glGetInteger(32873);
        boolean bl = GL11.glIsEnabled(3089);
        boolean bl2 = GL11.glIsEnabled(3553);
        if (bl) {
            GL11.glDisable(3089);
        }
        if (!bl2) {
            GlStateManager.enableTexture2D();
        }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(64);
        byteBuffer.order(ByteOrder.nativeOrder());
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        gg.vape.wrapper.impl.GL11.X(2978, intBuffer);
        GL11.glPushMatrix();
        this.n = new GlFramebuffer(n, n2, true);
        this.n.f(true);
        GlStateManager.enableDepth();
        GlStateManager.enableBlend();
        GL11.glEnable(2929);
        GlStateManager.enableBlend();
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glClear(16384);
        GL11.glClear(256);
        GL11.glMatrixMode(5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, 18.0, 18.0, 0.0, -1000.0, 3000.0);
        GL11.glMatrixMode(5888);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0f, 0.0f, -2000.0f);
        RenderHelper.e();
        GlStateManager.disableLighting();
        GL11.glEnable(32826);
        GL11.glPushMatrix();
        GlStateManager.disableBlend();
        this.T(potionEffect, n, n2);
        GL11.glPopMatrix();
        GL11.glMatrixMode(5888);
        GL11.glPopMatrix();
        this.n.S();
        GL11.glMatrixMode(5889);
        GL11.glPopMatrix();
        GL11.glMatrixMode(5888);
        RenderHelper.s();
        GL11.glDisable(32826);
        this.n.S();
        this.n.o();
        GL11.glPopMatrix();
        GL11.glViewport(intBuffer.get(0), intBuffer.get(1), intBuffer.get(2), intBuffer.get(3));
        GL30.glBindFramebuffer(36160, n3);
        GlStateManager.bindTexture(n4);
        if (bl) {
            GL11.glEnable(3089);
        }
        if (!bl2) {
            GlStateManager.disableTexture2D();
        }
    }

    @Override
    public void B() {
        this.n.x();
        this.n = null;
    }
}
