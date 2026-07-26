package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.EntityModelRenderBackend;
import gg.vape.utils.render.EntityModelRenderCache;
import gg.vape.utils.render.GlFramebuffer;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.RenderBatch;
import gg.vape.utils.render.RenderBatchBuffer;
import gg.vape.utils.render.RenderBatchBuilder;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.utils.render.RenderMatrix4f;
import gg.vape.utils.render.RenderMatrixStack;
import gg.vape.wrapper.impl.AbstractClientPlayer;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Render;
import gg.vape.wrapper.impl.ResourceLocation;
import gg.vape.wrapper.impl.TextureManager;
import gg.vape.wrapper.impl.TextureObject;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Post117EntityModelFramebufferRenderer
implements EntityModelRenderBackend {
    private static final String b = "[CachedFace] Exception during capture: ";
    GlFramebuffer G;
    private boolean X = false;

    private void w() {
        if (this.G != null && this.G.l > 0 || this.X) {
            return;
        }
        this.X = true;
        this.R(EntityModelRenderCache.M());
    }

    private void R(ResourceLocation resourceLocation) {
        int n = 32;
        int n2 = 32;
        try {
            RenderBatchManager renderBatchManager = RenderBatchManager.M();
            renderBatchManager.G(0.0f);
            int n3 = GL11.glGetInteger((int)34229);
            int n4 = GL11.glGetInteger((int)35725);
            int n5 = GL11.glGetInteger((int)32873);
            int n6 = GL11.glGetInteger((int)36006);
            int n7 = GL11.glGetInteger((int)34964);
            int n8 = GL11.glGetInteger((int)34965);
            boolean bl = GL11.glIsEnabled((int)3089);
            if (bl) {
                GL11.glDisable((int)3089);
            }
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(64);
            byteBuffer.order(ByteOrder.nativeOrder());
            IntBuffer intBuffer = byteBuffer.asIntBuffer();
            gg.vape.wrapper.impl.GL11.X(2978, intBuffer);
            this.G = new GlFramebuffer(n, n2, true);
            this.G.f(true);
            GlStateManager.enableBlend();
            GlStateManager.Y(770, 771);
            GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
            GL11.glClear((int)16640);
            TextureManager textureManager = Minecraft.Z();
            TextureObject textureObject = textureManager.G(resourceLocation);
            if (textureObject == null || textureObject.isNull() || textureObject.h() <= 0) {
                this.G.o();
                GL11.glViewport((int)intBuffer.get(0), (int)intBuffer.get(1), (int)intBuffer.get(2), (int)intBuffer.get(3));
                GL30.glBindFramebuffer((int)36160, (int)n6);
                GlStateManager.bindTexture(n5);
                if (bl) {
                    GL11.glEnable((int)3089);
                }
                this.w();
                return;
            }
            float f = 0.00390625f;
            float f2 = 0.00390625f;
            RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().o(new GlImageTexture(textureObject.h())).e(0.0f, 0.0f, 32.0f, 32.0f, n, n2, 32.0f * f, (float)(32 + n2) * f2, (float)(32 + n) * f, 32.0f * f2, Color.WHITE);
            RenderMatrix4f renderMatrix4f = BufferedGuiRenderPrimitives.k;
            RenderMatrix4f renderMatrix4f2 = BufferedGuiRenderPrimitives.l;
            RenderMatrixStack renderMatrixStack = BufferedGuiRenderPrimitives.X;
            BufferedGuiRenderPrimitives.k = new RenderMatrix4f().b().e(0.0f, n, n2, 0.0f, -21000.0f, 21000.0f);
            BufferedGuiRenderPrimitives.l = new RenderMatrix4f().b();
            BufferedGuiRenderPrimitives.X = new RenderMatrixStack();
            int n9 = textureObject.h();
            GL11.glBindTexture((int)3553, (int)n9);
            int n10 = GL11.glGetTexParameteri((int)3553, (int)10241);
            int n11 = GL11.glGetTexParameteri((int)3553, (int)10240);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
            RenderBatchBuffer renderBatchBuffer = renderBatchManager.s();
            RenderBatch renderBatch = new RenderBatch(renderBatchBuilder);
            renderBatchBuffer.z();
            renderBatchBuffer.m(renderBatch);
            renderBatchBuffer.i();
            GL30.glBindVertexArray((int)0);
            GL11.glBindTexture((int)3553, (int)n9);
            GL11.glTexParameteri((int)3553, (int)10241, (int)n10);
            GL11.glTexParameteri((int)3553, (int)10240, (int)n11);
            BufferedGuiRenderPrimitives.k = renderMatrix4f;
            BufferedGuiRenderPrimitives.l = renderMatrix4f2;
            BufferedGuiRenderPrimitives.X = renderMatrixStack;
            this.G.o();
            GL11.glViewport((int)intBuffer.get(0), (int)intBuffer.get(1), (int)intBuffer.get(2), (int)intBuffer.get(3));
            GL30.glBindVertexArray((int)n3);
            GL20.glUseProgram((int)n4);
            GL11.glBindTexture((int)3553, (int)n5);
            GL30.glBindFramebuffer((int)36160, (int)n6);
            GL15.glBindBuffer((int)34962, (int)n7);
            GL15.glBindBuffer((int)34963, (int)n8);
            if (bl) {
                GL11.glEnable((int)3089);
            }
        }
        catch (Exception exception) {
            Vape.debugLog(b + exception.getMessage());
            Vape.logThrowable(exception);
        }
    }

    @Override
    public void g(float f, float f2, int n, int n2, Color color, float f3) {
        if (this.G == null || this.G.l <= 0) {
            return;
        }
        BufferedGuiRenderPrimitives.c(f, f2, (float)n, (float)n2, f3, 1.0f, color, new GlImageTexture(this.G.l));
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @Override
    public void y(ResourceLocation resourceLocation) {
        this.R(resourceLocation);
    }

    @Override
    public void F() {
        this.G.x();
        this.G = null;
    }

    @Override
    public void y(EntityLivingBase entityLivingBase) {
        ResourceLocation resourceLocation = EntityModelRenderCache.M();
        if (entityLivingBase != null) {
            if (entityLivingBase.isInstance(MappedClasses.zt)) {
                AbstractClientPlayer abstractClientPlayer = new AbstractClientPlayer(entityLivingBase);
                resourceLocation = abstractClientPlayer.O();
            } else {
                Render render = Minecraft.D().getEntityRenderObject(entityLivingBase);
                resourceLocation = render.getEntityTexture(entityLivingBase);
            }
        }
        this.R(resourceLocation);
    }
}

