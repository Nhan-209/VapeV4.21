package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.GlStateManager;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class GlFramebuffer {
    public int k;
    public static boolean E;
    public boolean L;
    public int J;
    public int e;
    public int l;
    public int q;
    private static String[] n;
    public int z;
    int j = -1;
    int O = -1;
    public int w;
    final boolean T = true;
    public int x;
    public float[] F;
    private static final String b;

    public void H() {
        this.f(true);
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer((int)16);
        gg.vape.wrapper.impl.GL11.G(3106, floatBuffer);
        GL11.glClearColor((float)this.F[0], (float)this.F[1], (float)this.F[2], (float)this.F[3]);
        int n = 16384;
        if (this.L) {
            GL11.glClearDepth((double)1.0);
            n |= 0x100;
        }
        GL11.glClear((int)n);
        GL11.glClearColor((float)floatBuffer.get(0), (float)floatBuffer.get(1), (float)floatBuffer.get(2), (float)floatBuffer.get(3));
        this.o();
    }

    public static void N(String[] stringArray) {
        n = stringArray;
    }

    static {
        GlFramebuffer.N(null);
        b = "Error creating fbo ";
        E = false;
    }

    public GlFramebuffer(int n, int n2, boolean bl) {
        this.L = bl;
        this.w = -1;
        this.l = -1;
        this.q = -1;
        this.F = new float[4];
        this.F[0] = 1.0f;
        this.F[1] = 1.0f;
        this.F[2] = 1.0f;
        this.F[3] = 0.0f;
        this.S(n, n2);
    }

    public void I(int n) {
        this.z = n;
        this.S();
        GL11.glTexParameterf((int)3553, (int)10241, (float)n);
        GL11.glTexParameterf((int)3553, (int)10240, (float)n);
        GL11.glTexParameteri((int)3553, (int)10242, (int)33071);
        GL11.glTexParameteri((int)3553, (int)10243, (int)33071);
        this.M();
    }

    public void o() {
        GL30.glBindFramebuffer((int)36160, (int)this.j);
    }

    public void x() {
        this.M();
        this.o();
        if (this.q > -1) {
            GL30.glDeleteRenderbuffers((int)this.q);
            this.q = -1;
        }
        if (this.l > -1) {
            GL11.glDeleteTextures((int)this.l);
            this.l = -1;
        }
        if (this.w > -1) {
            GL30.glDeleteFramebuffers((int)this.w);
            this.w = -1;
        }
    }

    public void v() {
        int n = GL30.glCheckFramebufferStatus((int)36160);
        if (n != 36053) {
            Vape.debugLog(b + n);
        }
    }

    public static String[] j() {
        return n;
    }

    public void f(boolean bl) {
        int n = GL11.glGetInteger((int)36006);
        if (n != this.w) {
            this.j = n;
        }
        GL30.glBindFramebuffer((int)36160, (int)this.w);
        if (bl) {
            GL11.glViewport((int)0, (int)0, (int)this.e, (int)this.k);
        }
    }

    public void S(int n, int n2) {
        boolean bl = GL11.glIsEnabled((int)2929);
        GlStateManager.enableDepth();
        this.u(n, n2);
        this.v();
        if (!bl) {
            GlStateManager.disableDepth();
        }
    }

    public void M() {
        if (!E) {
            GlStateManager.bindTexture(this.O);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void S() {
        int n;
        if (!E && (n = GL11.glGetInteger((int)32873)) != this.l) {
            this.O = n;
        }
        GlStateManager.bindTexture(this.l);
    }

    public void u(int n, int n2) {
        this.e = n;
        this.k = n2;
        this.x = n;
        this.J = n2;
        int n3 = GL11.glGetInteger((int)36007);
        this.w = GL30.glGenFramebuffers();
        this.l = GL11.glGenTextures();
        if (this.L) {
            this.q = GL30.glGenRenderbuffers();
        }
        this.I(9728);
        this.S();
        GL11.glTexImage2D((int)3553, (int)0, (int)32856, (int)this.x, (int)this.J, (int)0, (int)6408, (int)5121, (ByteBuffer)null);
        this.f(false);
        GL30.glFramebufferTexture2D((int)36160, (int)36064, (int)3553, (int)this.l, (int)0);
        if (this.L) {
            GL30.glBindRenderbuffer((int)36161, (int)this.q);
            GL30.glRenderbufferStorage((int)36161, (int)33190, (int)this.x, (int)this.J);
            GL30.glFramebufferRenderbuffer((int)36160, (int)36096, (int)36161, (int)this.q);
        }
        this.H();
        this.o();
        this.M();
        GL30.glBindRenderbuffer((int)36161, (int)n3);
    }
}

