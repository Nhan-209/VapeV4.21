package gg.vape.utils.render;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GlFramebuffer;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GlScissorRect;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.PotionEffectIconRenderBackend;
import gg.vape.utils.render.RenderBatchBuilder;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.utils.render.VertexCoordinateMode;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Holder;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.ResourceLocation;
import gg.vape.wrapper.impl.ResourceLocationConstantPair;
import gg.vape.wrapper.impl.Screen;
import gg.vape.wrapper.impl.StatusEffectSpriteUploader;
import gg.vape.wrapper.impl.TextureAtlas;
import gg.vape.wrapper.impl.TextureAtlasSprite;
import gg.vape.wrapper.impl.TextureManager;
import gg.vape.wrapper.impl.TextureObject;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class PotionEffectIconTexture
implements PotionEffectIconRenderBackend {
    private GlFramebuffer v;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }


    @Override
    public void l(float f, float f2, int n, int n2, float f3, boolean bl) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(VertexCoordinateMode.DEFAULT, bl).o(new GlImageTexture(this.v.l)).e(f, f2, n, n2, 18.0f, 18.0f, 0.0f, 1.0f, 1.0f, 0.0f, new Color(1.0f, 1.0f, 1.0f, f3));
        if (bl) {
            RenderBatchManager.M().c(renderBatchBuilder);
        } else {
            RenderBatchManager.M().O(renderBatchBuilder);
        }
    }

    private TextureAtlasSprite f(PotionEffect potionEffect) {
        TextureAtlasSprite textureAtlasSprite;
        Holder holder = potionEffect.t();
        if (ForgeVersion.MC_1_21_10.d()) {
            TextureAtlas textureAtlas = Minecraft.x().q(ResourceLocationConstantPair.v());
            textureAtlasSprite = textureAtlas.a(Screen.k(holder));
        } else if (ForgeVersion.MC_1_21_6.d()) {
            textureAtlasSprite = Minecraft.T().t(Screen.k(holder));
        } else {
            StatusEffectSpriteUploader statusEffectSpriteUploader = StatusEffectSpriteUploader.c();
            textureAtlasSprite = statusEffectSpriteUploader.T(holder);
        }
        return textureAtlasSprite;
    }

    private static TextureObject l(TextureAtlasSprite textureAtlasSprite) {
        Wrapper wrapper;
        ResourceLocation resourceLocation;
        if (ForgeVersion.MC_1_20_6.d()) {
            resourceLocation = textureAtlasSprite.M();
        } else {
            wrapper = new TextureAtlas(textureAtlasSprite.e());
            resourceLocation = ((TextureAtlas)wrapper).K();
        }
        wrapper = Minecraft.Z();
        return ((TextureManager)wrapper).G(resourceLocation);
    }

    @Override
    public void B() {
        this.v.x();
        this.v = null;
    }

    @Override
    public void d(float f, float f2, int n, int n2, float f3) {
        this.l(f, f2, n, n2, f3, false);
    }

    @Override
    public void a(PotionEffect potionEffect) {
        RenderBatchManager.M().G(0.0f);
        int n = 18;
        int n2 = 18;
        int n3 = GL11.glGetInteger((int)36006);
        int n4 = GL11.glGetInteger((int)32873);
        boolean bl = GL11.glIsEnabled((int)3089);
        if (bl) {
            GL11.glDisable((int)3089);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(64);
            byteBuffer.order(ByteOrder.nativeOrder());
            IntBuffer intBuffer = byteBuffer.asIntBuffer();
            gg.vape.wrapper.impl.GL11.X(2978, intBuffer);
            this.v = new GlFramebuffer(n, n2, true);
            this.v.f(true);
            GlStateManager.enableDepth();
            GlStateManager.enableBlend();
            GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
            GL11.glClear((int)16384);
            GL11.glClear((int)256);
            OpenGlBackendHolder.d.m();
            OpenGlBackendHolder.d.I(0.0, -2.0, 0.0);
            OpenGlBackendHolder.d.G((double)Minecraft.J() / (double)((float)n * 2.0f), (double)Minecraft.h() / (double)((float)n * 2.0f), 0.0);
            TextureAtlasSprite textureAtlasSprite = this.f(potionEffect);
            TextureObject textureObject = PotionEffectIconTexture.l(textureAtlasSprite);
            float[] fArray = textureAtlasSprite.j();
            GlScissorRect glScissorRect = BufferedGuiRenderPrimitives.u;
            BufferedGuiRenderPrimitives.u = null;
            RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().o(new GlImageTexture(textureObject.h())).e(0.0f, -1.0f, n, n2, n, n2, fArray[0], fArray[2], fArray[1], fArray[3], Color.WHITE);
            RenderBatchManager renderBatchManager = RenderBatchManager.M();
            renderBatchManager.O(renderBatchBuilder);
            renderBatchManager.a(this.v.w);
            renderBatchManager.G(0.0f);
            renderBatchManager.j();
            BufferedGuiRenderPrimitives.u = glScissorRect;
            this.v.S();
            this.v.o();
            GL11.glViewport((int)intBuffer.get(0), (int)intBuffer.get(1), (int)intBuffer.get(2), (int)intBuffer.get(3));
            GL30.glBindFramebuffer((int)36160, (int)n3);
            GlStateManager.bindTexture(n4);
            GL11.glEnable((int)3089);
            OpenGlBackendHolder.d.F();
            return;
        }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(64);
        byteBuffer.order(ByteOrder.nativeOrder());
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        gg.vape.wrapper.impl.GL11.X(2978, intBuffer);
        this.v = new GlFramebuffer(n, n2, true);
        this.v.f(true);
        GlStateManager.enableDepth();
        GlStateManager.enableBlend();
        GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glClear((int)16384);
        GL11.glClear((int)256);
        OpenGlBackendHolder.d.m();
        OpenGlBackendHolder.d.I(0.0, -2.0, 0.0);
        OpenGlBackendHolder.d.G((double)Minecraft.J() / (double)((float)n * 2.0f), (double)Minecraft.h() / (double)((float)n * 2.0f), 0.0);
        TextureAtlasSprite textureAtlasSprite = this.f(potionEffect);
        TextureObject textureObject = PotionEffectIconTexture.l(textureAtlasSprite);
        float[] fArray = textureAtlasSprite.j();
        GlScissorRect glScissorRect = BufferedGuiRenderPrimitives.u;
        BufferedGuiRenderPrimitives.u = null;
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().o(new GlImageTexture(textureObject.h())).e(0.0f, -1.0f, n, n2, n, n2, fArray[0], fArray[2], fArray[1], fArray[3], Color.WHITE);
        RenderBatchManager renderBatchManager = RenderBatchManager.M();
        renderBatchManager.O(renderBatchBuilder);
        renderBatchManager.a(this.v.w);
        renderBatchManager.G(0.0f);
        renderBatchManager.j();
        BufferedGuiRenderPrimitives.u = glScissorRect;
        this.v.S();
        this.v.o();
        GL11.glViewport((int)intBuffer.get(0), (int)intBuffer.get(1), (int)intBuffer.get(2), (int)intBuffer.get(3));
        GL30.glBindFramebuffer((int)36160, (int)n3);
        GlStateManager.bindTexture(n4);
        OpenGlBackendHolder.d.F();
    }
}
