package gg.vape.utils.render;

import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.utils.render.EntityModelRenderBackend;
import gg.vape.utils.render.EntityModelRenderCache;
import gg.vape.utils.render.GlFramebuffer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.AbstractClientPlayer;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Render;
import gg.vape.wrapper.impl.RenderHelper;
import gg.vape.wrapper.impl.ResourceLocation;
import java.awt.Color;
import java.lang.invoke.MethodHandles;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class EntityModelFramebufferRenderer
implements EntityModelRenderBackend {
    private static final long a;
    GlFramebuffer b;

    @Override
    public void F() {
        this.b.x();
        this.b = null;
    }

    private void E(ResourceLocation resourceLocation) {
        long l = a ^ 0x5075DCF48357L;
        int n = 32;
        int n2 = 32;
        int n3 = ForgeVersion.MC_1_7_10.L() ? 32 : 0;
        int n4 = GL11.glGetInteger((int)36006);
        int n5 = GL11.glGetInteger((int)32873);
        boolean bl = GL11.glIsEnabled((int)3089);
        boolean bl2 = GL11.glIsEnabled((int)3553);
        if (bl) {
            GL11.glDisable((int)3089);
        }
        if (!bl2) {
            GlStateManager.enableTexture2D();
        }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(64);
        byteBuffer.order(ByteOrder.nativeOrder());
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        gg.vape.wrapper.impl.GL11.X(2978, intBuffer);
        OpenGlBackendHolder.d.m();
        this.b = new GlFramebuffer(n, n2, true);
        this.b.f(true);
        GlStateManager.enableDepth();
        GlStateManager.enableBlend();
        GL11.glEnable((int)2929);
        GlStateManager.enableBlend();
        GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glClear((int)16384);
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        OpenGlBackendHolder.d.m();
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)32.0, (double)(32 + n3), (double)0.0, (double)-1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        OpenGlBackendHolder.d.m();
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
        RenderHelper.e();
        GlStateManager.disableLighting();
        GL11.glEnable((int)32826);
        GL11.glPushMatrix();
        GlStateManager.disableBlend();
        int n6 = 32;
        int n7 = 32 + n3;
        Minecraft.Z().G(resourceLocation);
        Minecraft.Z().g(resourceLocation);
        RenderUtils.R(0, 0, n6, n7, 32, 32 + n3);
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5888);
        GL11.glPopMatrix();
        this.b.S();
        GL11.glMatrixMode((int)5889);
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5888);
        RenderHelper.s();
        GL11.glDisable((int)32826);
        this.b.S();
        this.b.o();
        GL11.glPopMatrix();
        GL11.glViewport((int)intBuffer.get(0), (int)intBuffer.get(1), (int)intBuffer.get(2), (int)intBuffer.get(3));
        GL30.glBindFramebuffer((int)36160, (int)n4);
        GlStateManager.bindTexture(n5);
        if (bl) {
            GL11.glEnable((int)3089);
        }
        if (!bl2) {
            GlStateManager.disableTexture2D();
        }
    }

    @Override
    public void y(ResourceLocation resourceLocation) {
        this.E(resourceLocation);
    }

    @Override
    public void g(float f, float f2, int n, int n2, Color color, float f3) {
        long l = a ^ 0x77DE43D73630L;
        GL11.glEnable((int)2903);
        boolean bl = GL11.glIsEnabled((int)3553);
        boolean bl2 = GL11.glIsEnabled((int)2896);
        boolean bl3 = GL11.glIsEnabled((int)3008);
        boolean bl4 = GL11.glIsEnabled((int)3042);
        if (!bl) {
            GlStateManager.enableTexture2D();
        }
        if (bl2) {
            GlStateManager.disableLighting();
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
        int n3 = GL11.glGetInteger((int)32873);
        this.b.S();
        RenderUtils.w(color);
        GuiRenderPrimitives.a(f, f2, n, n2, f3, 1.0f);
        this.b.M();
        GlStateManager.bindTexture(n3);
        if (!bl) {
            GlStateManager.disableTexture2D();
        }
        if (bl2) {
            GlStateManager.enableLighting();
        }
        if (bl3) {
            GlStateManager.enableAlpha();
        }
        if (bl4) {
            GlStateManager.enableBlend();
        }
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
        this.E(resourceLocation);
    }

    static {
        long l = a = ZkmLongKeyState.a(-3299161649916191848L, -7083752019901308553L, MethodHandles.lookup().lookupClass()).a(131746295237392L);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
