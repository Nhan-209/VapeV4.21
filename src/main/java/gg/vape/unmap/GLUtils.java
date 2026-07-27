package gg.vape.unmap;

import gg.vape.runtime.ObfuscatedRuntimeException;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class GLUtils {
    private FloatBuffer D;
    private FloatBuffer U;
    private int C;
    private int R;
    private int P;
    private float[] g;
    private int N;
    private float[] o;
    private int b;

    public void initializeVertexBuffer(int n, int n2) {
        this.b(n, n2, 3);
    }

    public void addVertex2D(float f, float f2) {
        this.g[this.N++] = f;
        this.g[this.N++] = f2;
    }

    public void draw() {
        GL11.glEnableClientState((int)32884);
        if (this.o != null) {
            GL11.glEnableClientState((int)32888);
        }
        gg.vape.wrapper.impl.GL11.f(this.b, 0, this.D);
        if (this.o != null) {
            GL11.glTexCoordPointer((int)2, (int)0, (FloatBuffer)this.U);
        }
        GL11.glDrawArrays((int)this.C, (int)0, (int)this.R);
        if (this.o != null) {
            GL11.glDisableClientState((int)32888);
        }
        GL11.glDisableClientState((int)32884);
        this.N = 0;
        this.P = 0;
    }


    public void addVertex(double d, double d2, double d3) {
        this.g[this.N++] = (float)d;
        this.g[this.N++] = (float)d2;
        this.g[this.N++] = (float)d3;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void X() {
        this.o = new float[this.R * 2];
        this.U = BufferUtils.createFloatBuffer((int)(this.R * 2));
    }

    public void uploadAndDraw() {
        this.D.put(this.g);
        this.D.flip();
        if (this.o != null) {
            this.U.put(this.o);
            this.U.flip();
        }
        this.draw();
    }

    public void reset() {
        this.N = 0;
        this.P = 0;
        this.D.clear();
    }

    public void a(float f, float f2) {
        this.o[this.P++] = f;
        this.o[this.P++] = f2;
    }

    public GLUtils() {
        this.b = 2;
        this.C = 7;
        this.N = -1;
        this.P = -1;
    }

    public void b(int n, int n2, int n3) {
        this.R = n;
        this.C = n2;
        this.b = n3;
        this.g = new float[n * this.b];
        this.D = BufferUtils.createFloatBuffer((int)(n * this.b));
    }
}
