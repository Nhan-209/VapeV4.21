package gg.vape.utils.render;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GlScissorRect;
import gg.vape.utils.render.OpenGlBackend;
import org.lwjgl.opengl.GL11;

public class BufferedOpenGlBackend
implements OpenGlBackend {
    private int[] J = new int[]{3008, 3553, 2896};

    @Override
    public void M() {
    }

    @Override
    public void F() {
        BufferedGuiRenderPrimitives.X.z();
    }

    @Override
    public void H(float f, float f2, float f3) {
        BufferedGuiRenderPrimitives.X.g(f, f2, f3);
    }

    @Override
    public void m() {
        BufferedGuiRenderPrimitives.X.D();
    }

    @Override
    public void k(int n, float f) {
    }

    @Override
    public void l(int n) {
        for (int n2 : this.J) {
            if (n != n2) continue;
            return;
        }
        BufferedGuiRenderPrimitives.b.D(n);
        GL11.glEnable((int)n);
    }

    @Override
    public void void_u(int n) {
        for (int n2 : this.J) {
            if (n != n2) continue;
            return;
        }
        BufferedGuiRenderPrimitives.b.Z(n);
        GL11.glDisable((int)n);
    }

    @Override
    public void q(float f, float f2, float f3, float f4) {
    }

    @Override
    public void r(float f) {
    }

    @Override
    public boolean L(int n) {
        for (int i = 0; i < this.J.length; ++i) {
            if (n != this.J[i]) continue;
            return false;
        }
        return GL11.glIsEnabled((int)n);
    }

    @Override
    public void E(float f, float f2, float f3) {
    }

    @Override
    public void S() {
    }

    @Override
    public void G(double d, double d2, double d3) {
        this.H((float)d, (float)d2, (float)d3);
    }

    @Override
    public void b(double d, float f, float f2) {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void m(double d, double d2, double d3) {
    }

    @Override
    public void F(float f, float f2, float f3) {
    }

    @Override
    public void U(boolean bl) {
        BufferedGuiRenderPrimitives.b.a(bl);
    }

    @Override
    public void P(float f, float f2, float f3) {
        BufferedGuiRenderPrimitives.X.K(f, f2, f3);
    }

    @Override
    public void w(float f, double d, double d2, double d3) {
        this.X(f, (float)d, (float)d2, (float)d3);
    }

    @Override
    public void C(int n) {
    }

    @Override
    public float float_u(int n) {
        if (n == 3010) {
            return 0.0f;
        }
        return GL11.glGetFloat((int)n);
    }

    @Override
    public void k(double d, double d2, double d3, double d4) {
    }

    @Override
    public void X(float f, float f2, float f3, float f4) {
        BufferedGuiRenderPrimitives.X.e(f, f2, f3, f4);
    }

    @Override
    public void I(double d, double d2, double d3) {
        this.P((float)d, (float)d2, (float)d3);
    }

    @Override
    public int K(int n) {
        if (n == 3009) {
            return 0;
        }
        return GL11.glGetInteger((int)n);
    }

    @Override
    public void n(double d, double d2, double d3) {
        this.F((float)d, (float)d2, (float)d3);
    }

    @Override
    public void e(int n, int n2, int n3, int n4) {
        BufferedGuiRenderPrimitives.u = new GlScissorRect(n, n2, n3, n4);
        GL11.glScissor((int)n, (int)n2, (int)n3, (int)n4);
    }
}

