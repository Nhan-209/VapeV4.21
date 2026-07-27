package gg.vape.utils.render;

import gg.vape.utils.render.RenderVector4f;
import gg.vape.wrapper.impl.Matrix4f;
import gg.vape.wrapper.impl.Quaternion;
import java.nio.FloatBuffer;
import java.util.Arrays;
import org.lwjgl.BufferUtils;

public class RenderMatrix4f {
    private static boolean P;
    public float[] e;
    private static final String b;

    public void Z(RenderVector4f renderVector4f, RenderVector4f renderVector4f2) {
        float f = renderVector4f.N;
        float f2 = renderVector4f.w;
        float f3 = renderVector4f.Y;
        float f4 = renderVector4f.J;
        renderVector4f2.N = this.e[0] * f + this.e[4] * f2 + this.e[8] * f3 + this.e[12] * f4;
        renderVector4f2.w = this.e[1] * f + this.e[5] * f2 + this.e[9] * f3 + this.e[13] * f4;
        renderVector4f2.Y = this.e[2] * f + this.e[6] * f2 + this.e[10] * f3 + this.e[14] * f4;
        renderVector4f2.J = this.e[3] * f + this.e[7] * f2 + this.e[11] * f3 + this.e[15] * f4;
    }

    public RenderMatrix4f y(float f, float f2, float f3, float f4) {
        float f5 = (float)Math.tan(Math.toRadians(f) / 2.0);
        this.e[0] = 1.0f / (f2 * f5);
        this.e[5] = 1.0f / f5;
        this.e[10] = -((f4 + f3) / (f4 - f3));
        this.e[11] = -(2.0f * f4 * f3 / (f4 - f3));
        this.e[14] = -1.0f;
        return this;
    }

    public RenderMatrix4f m() {
        return new RenderMatrix4f(this);
    }

    public RenderMatrix4f b() {
        for (int i = 0; i < 16; ++i) {
            this.e[i] = 0.0f;
        }
        this.e[0] = 1.0f;
        this.e[5] = 1.0f;
        this.e[10] = 1.0f;
        this.e[15] = 1.0f;
        return this;
    }

    public static boolean i() {
        return P;
    }

    public RenderMatrix4f u(RenderMatrix4f renderMatrix4f) {
        float[] fArray = new float[16];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                float f = 0.0f;
                for (int k = 0; k < 4; ++k) {
                    f += this.e[j + k * 4] * renderMatrix4f.e[k + i * 4];
                }
                fArray[j + i * 4] = f;
            }
        }
        this.e = fArray;
        return this;
    }

    public FloatBuffer J() {
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer((int)16);
        floatBuffer.put(this.e).flip();
        return floatBuffer;
    }

    public RenderMatrix4f(Quaternion quaternion) {
        this();
        float f = quaternion.Q();
        float f2 = quaternion.N();
        float f3 = quaternion.P();
        float f4 = quaternion.q();
        float f5 = 2.0f * f * f;
        float f6 = 2.0f * f2 * f2;
        float f7 = 2.0f * f3 * f3;
        this.e[0] = 1.0f - f6 - f7;
        this.e[5] = 1.0f - f7 - f5;
        this.e[10] = 1.0f - f5 - f6;
        this.e[15] = 1.0f;
        float f8 = f * f2;
        float f9 = f2 * f3;
        float f10 = f3 * f;
        float f11 = f * f4;
        float f12 = f2 * f4;
        float f13 = f3 * f4;
        this.e[4] = 2.0f * (f8 + f13);
        this.e[1] = 2.0f * (f8 - f13);
        this.e[8] = 2.0f * (f10 - f12);
        this.e[2] = 2.0f * (f10 + f12);
        this.e[9] = 2.0f * (f9 + f11);
        this.e[6] = 2.0f * (f9 - f11);
    }

    static {
        RenderMatrix4f.Q(true);
        b = "Matrix4f{elements=";
    }

    public static boolean h() {
        boolean bl = RenderMatrix4f.i();
        return false;
    }

    public RenderMatrix4f O(float f, float f2, float f3) {
        RenderMatrix4f renderMatrix4f = new RenderMatrix4f().b();
        renderMatrix4f.e[0] = f;
        renderMatrix4f.e[5] = f2;
        renderMatrix4f.e[10] = f3;
        return this.u(renderMatrix4f);
    }

    public RenderMatrix4f I(float f, float f2, float f3) {
        RenderMatrix4f renderMatrix4f = new RenderMatrix4f().b();
        renderMatrix4f.e[12] = f;
        renderMatrix4f.e[13] = f2;
        renderMatrix4f.e[14] = f3;
        return this.u(renderMatrix4f);
    }

    public String toString() {
        return b + Arrays.toString(this.e) + '}';
    }

    public static void Q(boolean bl) {
        P = bl;
    }

    public RenderMatrix4f(RenderVector4f renderVector4f) {
        this();
        this.e[0] = renderVector4f.N;
        this.e[5] = renderVector4f.w;
        this.e[10] = renderVector4f.Y;
        this.e[15] = renderVector4f.J;
    }


    public RenderMatrix4f() {
        this.e = new float[16];
    }

    public boolean Z(RenderMatrix4f renderMatrix4f) {
        if (this == renderMatrix4f) {
            return true;
        }
        if (renderMatrix4f != null) {
            for (int i = 0; i < 16; ++i) {
                if (Float.compare(this.e[i], renderMatrix4f.e[i]) == 0) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public RenderMatrix4f v(float f) {
        this.b();
        float f2 = (float)Math.toRadians(f);
        this.e[5] = (float)Math.cos(f2);
        this.e[6] = -((float)Math.sin(f2));
        this.e[9] = (float)Math.sin(f2);
        this.e[10] = (float)Math.cos(f2);
        return this;
    }

    public RenderMatrix4f(RenderMatrix4f renderMatrix4f) {
        this.e = renderMatrix4f.e;
    }

    public RenderMatrix4f e(float f, float f2, float f3, float f4, float f5, float f6) {
        this.e[0] = 2.0f / (f2 - f);
        this.e[5] = 2.0f / (f4 - f3);
        this.e[10] = -2.0f / (f6 - f5);
        this.e[12] = -((f2 + f) / (f2 - f));
        this.e[13] = -((f4 + f3) / (f4 - f3));
        this.e[14] = -((f6 + f5) / (f6 - f5));
        return this;
    }

    public Matrix4f u() {
        Matrix4f matrix4f = Matrix4f.G();
        Matrix4f matrix4f2 = matrix4f.c(this.J());
        return matrix4f2 == null ? matrix4f : matrix4f2;
    }

    public RenderMatrix4f d(float f, float f2, float f3, float f4) {
        float f5 = (float)Math.toRadians(f);
        float f6 = (float)Math.cos(f5);
        float f7 = (float)Math.sin(f5);
        float f8 = 1.0f - f6;
        RenderMatrix4f renderMatrix4f = new RenderMatrix4f().b();
        renderMatrix4f.e[0] = f2 * f2 * f8 + f6;
        renderMatrix4f.e[1] = f3 * f2 * f8 + f4 * f7;
        renderMatrix4f.e[2] = f2 * f4 * f8 - f3 * f7;
        renderMatrix4f.e[4] = f2 * f3 * f8 - f4 * f7;
        renderMatrix4f.e[5] = f3 * f3 * f8 + f6;
        renderMatrix4f.e[6] = f3 * f4 * f8 + f2 * f7;
        renderMatrix4f.e[8] = f2 * f4 * f8 + f3 * f7;
        renderMatrix4f.e[9] = f3 * f4 * f8 - f2 * f7;
        renderMatrix4f.e[10] = f4 * f4 * f8 + f6;
        return this.u(renderMatrix4f);
    }

    public RenderMatrix4f(float[] fArray) {
        this.e = fArray;
    }
}

