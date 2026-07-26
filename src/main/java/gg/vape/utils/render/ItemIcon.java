package gg.vape.utils.render;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.unmap.GLUtils;
import gg.vape.utils.render.GlFramebuffer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ItemIconRenderBackend;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import java.lang.invoke.MethodHandles;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ItemIcon
implements ItemIconRenderBackend {
    GlFramebuffer I;
    private static final long a;
    private GLUtils O = new GLUtils();

    @Override
    public void N(ItemStack itemStack, float f) {
        long l = a ^ 0x4425ADDD3E0L;
        int n = 32;
        int n2 = 32;
        boolean bl = GL11.glIsEnabled((int)3089);
        boolean bl2 = GL11.glIsEnabled((int)3553);
        boolean bl3 = GL11.glIsEnabled((int)2929);
        if (bl) {
            GL11.glDisable((int)3089);
        }
        if (!bl2) {
            GlStateManager.enableTexture2D();
        }
        if (!bl3) {
            GL11.glEnable((int)2929);
        }
        GlStateManager.depthMask(true);
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer((int)16);
        gg.vape.wrapper.impl.GL11.G(3106, floatBuffer);
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().B(1.0);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(64);
        byteBuffer.order(ByteOrder.nativeOrder());
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        gg.vape.wrapper.impl.GL11.X(2978, intBuffer);
        GL11.glPushMatrix();
        this.I = new GlFramebuffer(n, n2, true);
        this.I.f(true);
        this.I.S();
        GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glClear((int)16384);
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)16.0, (double)16.0, (double)0.0, (double)-1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
        GL11.glEnable((int)32826);
        GL11.glPushMatrix();
        GuiRenderPrimitives.g(itemStack, f, 0.0, 0.0, true);
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5888);
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5889);
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5888);
        GL11.glDisable((int)32826);
        this.I.M();
        this.I.o();
        GL11.glPopMatrix();
        GL11.glClearColor((float)floatBuffer.get(0), (float)floatBuffer.get(1), (float)floatBuffer.get(2), (float)floatBuffer.get(3));
        GL11.glViewport((int)intBuffer.get(0), (int)intBuffer.get(1), (int)intBuffer.get(2), (int)intBuffer.get(3));
        if (!bl3) {
            GL11.glDisable((int)2929);
        }
        if (bl) {
            GL11.glEnable((int)3089);
        }
        if (!bl2) {
            GlStateManager.disableTexture2D();
        }
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(1.0);
    }

    static {
        long l = a = ZkmLongKeyState.a(2504101573194017989L, -6455608136057261555L, MethodHandles.lookup().lookupClass()).a(39144605691681L);
    }

    public ItemIcon() {
        this.O.b(8, 7, 2);
        this.O.X();
    }

    @Override
    public void H(float f, float f2, int n, int n2, float f3) {
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
        this.I.S();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)f3);
        GL11.glPushMatrix();
        float f4 = 0.0f;
        float f5 = 1.0f;
        float f6 = 1.0f;
        float f7 = 0.0f;
        GL11.glBegin((int)7);
        GL11.glTexCoord2f((float)f6, (float)f5);
        GL11.glVertex2f((float)(f + (float)n), (float)f2);
        GL11.glTexCoord2f((float)f4, (float)f5);
        GL11.glVertex2f((float)f, (float)f2);
        GL11.glTexCoord2f((float)f4, (float)f7);
        GL11.glVertex2f((float)f, (float)(f2 + (float)n2));
        GL11.glTexCoord2f((float)f6, (float)f7);
        GL11.glVertex2f((float)(f + (float)n), (float)(f2 + (float)n2));
        GL11.glEnd();
        GL11.glPopMatrix();
        this.I.M();
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

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void e() {
        this.I.x();
        this.I = null;
    }
}
