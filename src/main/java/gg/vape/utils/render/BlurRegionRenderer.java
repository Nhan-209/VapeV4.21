package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderBatchBuilder;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import java.lang.invoke.MethodHandles;
import java.nio.ByteBuffer;
import java.util.function.Supplier;
import org.lwjgl.opengl.GL11;

public class BlurRegionRenderer {
    private int Z = -1;
    private static final long a = ZkmLongKeyState.a(-2872469218236996901L, -3211630272103781112L, MethodHandles.lookup().lookupClass()).a(109364196820520L);
    private int P;
    private boolean h = false;
    private static boolean e;
    private int k;

    public BlurRegionRenderer(int n, int n2) {
        this.P = n;
        this.k = n2;
    }

    private Void lambda$renderBlur$0(int n, float f, int n2) {
        GL11.glBindTexture((int)3553, (int)this.Z);
        GL11.glCopyTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)((int)((float)n * f)), (int)n2, (int)this.P, (int)this.k);
        return null;
    }

    public void L(int n, int n2) {
        double d = Vape.INSTANCE.getClientSettings().s();
        n = (int)((double)n * d);
        n2 = (int)((double)n2 * d);
        if (this.P == n && this.k == n2) {
            return;
        }
        this.P = n;
        this.k = n2;
        this.h = false;
    }

    public static boolean w() {
        return e;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void k(boolean bl) {
        e = bl;
    }

    public void t(int n, int n2, float f, float f2) {
        RenderBatchBuilder renderBatchBuilder;
        Supplier<Void> supplier;
        if (!Minecraft.gameSettings().Y$src$Z$1rxemad()) {
            return;
        }
        float f3 = 2.0f;
        double d = Vape.INSTANCE.getClientSettings().s();
        n = (int)((double)n * d);
        n2 = (int)((double)n2 * d);
        int n4 = Minecraft.h();
        int n5 = (int)((float)n4 - (float)n2 * f3 - (float)this.k);
        if (!this.h) {
            this.B();
        }
        float f4 = 0.5f;
        f4 = (float)((double)f4 / d);
        OpenGlBackendHolder.d.m();
        if (!GuiRenderPrimitives.d()) {
            OpenGlBackendHolder.d.G(f4, f4, f4);
        }
        if (!GuiRenderPrimitives.d()) {
            GL11.glBindTexture((int)3553, (int)this.Z);
        }
        if (GuiRenderPrimitives.d()) {
            final int capturedX = n;
            supplier = () -> this.lambda$renderBlur$0(capturedX, f3, n5);
            if (this.P == 0 || this.k == 0) {
                return;
            }
            renderBatchBuilder = new RenderBatchBuilder().V(supplier).o(new GlImageTexture(this.Z)).A(n, n2, (float)this.P / 2.0f, (float)this.k / 2.0f, f, f2, 1.0f, 0.0f);
            RenderBatchManager.M().O(renderBatchBuilder);
        } else {
            GL11.glCopyTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)((int)((float)n * f3)), (int)n5, (int)this.P, (int)this.k);
            GuiRenderPrimitives.v((float)n * f3, (float)n2 * f3, this.P, this.k, f, f2, 1.0f, 0);
        }
        if (GuiRenderPrimitives.d()) {
            final int capturedX = n;
            supplier = () -> this.lambda$renderBlur$1(capturedX, f3, n5);
            if (this.P == 0 || this.k == 0) {
                return;
            }
            renderBatchBuilder = new RenderBatchBuilder().V(supplier).o(new GlImageTexture(this.Z)).A(n, n2, (float)this.P / 2.0f, (float)this.k / 2.0f, f, f2, 1.0f, 1.0f);
            RenderBatchManager.M().O(renderBatchBuilder);
        } else {
            GL11.glCopyTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)((int)((float)n * f3)), (int)n5, (int)this.P, (int)this.k);
            GuiRenderPrimitives.v((float)n * f3, (float)n2 * f3, this.P, this.k, f, f2, 1.0f, 1);
        }
        OpenGlBackendHolder.d.F();
        GlStateManager.bindTexture(0);
    }

    public static boolean d() {
        boolean bl = BlurRegionRenderer.w();
        return true;
    }

    private Void lambda$renderBlur$1(int n, float f, int n2) {
        GL11.glBindTexture((int)3553, (int)this.Z);
        GL11.glCopyTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)((int)((float)n * f)), (int)n2, (int)this.P, (int)this.k);
        return null;
    }

    private void B() {
        long l = a ^ 0x48BCEDB25080L;
        this.Z = GL11.glGenTextures();
        GL11.glBindTexture((int)3553, (int)this.Z);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
        GL11.glTexParameteri((int)3553, (int)10242, (int)33071);
        GL11.glTexParameteri((int)3553, (int)10243, (int)33071);
        GL11.glTexImage2D((int)3553, (int)0, (int)6407, (int)this.P, (int)this.k, (int)0, (int)6407, (int)5121, (ByteBuffer)null);
        this.h = true;
    }

    static {
        long l = a ^ 0x650B55E23470L;
        BlurRegionRenderer.k(false);
    }
}
