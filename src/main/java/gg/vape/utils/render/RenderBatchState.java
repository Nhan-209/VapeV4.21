package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.module.blatant.invwalk.InvWalkKeyLayout;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.utils.render.RenderBatchShaderProgram;
import gg.vape.utils.render.RenderMatrix4f;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL33;

public class RenderBatchState {
    private FloatBuffer o = BufferUtils.createFloatBuffer((int)(this.B * 7));
    private int q;
    private int E;
    private static RenderBatchState y;
    private int G;
    private int m;
    private int b;
    private int J;
    private int w;
    private int B = 4096;
    private static final int u;
    private int L;
    private int A;
    private boolean H = false;
    private int Y;
    private static final int a;

    public void G() {
        if (!this.H) {
            return;
        }
        GL15.glDeleteBuffers((int)this.q);
        GL15.glDeleteBuffers((int)this.E);
        GL15.glDeleteBuffers((int)this.m);
        GL30.glDeleteVertexArrays((int)this.G);
        this.H = false;
        Vape.debugLog("InstancedBlockRenderer cleaned up");
    }

    private void b() {
        GL30.glBindVertexArray((int)this.A);
        GL20.glUseProgram((int)this.Y);
        GL15.glBindBuffer((int)34962, (int)this.L);
        GL15.glBindBuffer((int)34963, (int)this.w);
        GL30.glBindFramebuffer((int)36160, (int)this.b);
    }

    public void D(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        if (this.J >= this.B) {
            this.u();
        }
        this.o.put(f);
        this.o.put(f2);
        this.o.put(f3);
        this.o.put(f4);
        this.o.put(f5);
        this.o.put(f6);
        this.o.put(f7);
        ++this.J;
    }

    public void K(RenderMatrix4f renderMatrix4f, RenderMatrix4f renderMatrix4f2, RenderMatrix4f renderMatrix4f3) {
        boolean bl;
        if (this.J == 0 || !this.H) {
            return;
        }
        RenderBatchShaderProgram renderBatchShaderProgram = InvWalkKeyLayout.p;
        if (renderBatchShaderProgram == null) {
            return;
        }
        this.a();
        int n = RenderBatchManager.M().E();
        if (n != -1) {
            GL30.glBindFramebuffer((int)36160, (int)n);
        }
        if (bl = GL11.glIsEnabled((int)2884)) {
            GL11.glDisable((int)2884);
            GL30.glBindVertexArray((int)this.G);
            renderBatchShaderProgram.P();
            FloatBuffer floatBuffer = renderMatrix4f.J();
            FloatBuffer floatBuffer2 = renderMatrix4f2.J();
            FloatBuffer floatBuffer3 = renderMatrix4f3.J();
            gg.vape.wrapper.impl.GL20.w(renderBatchShaderProgram.m, false, floatBuffer);
            gg.vape.wrapper.impl.GL20.w(renderBatchShaderProgram.B, false, floatBuffer2);
            gg.vape.wrapper.impl.GL20.w(renderBatchShaderProgram.T, false, floatBuffer3);
            this.o.flip();
            GL15.glBindBuffer((int)34962, (int)this.E);
            GL15.glBufferSubData((int)34962, (long)0L, (FloatBuffer)this.o);
            GL31.glDrawElementsInstanced((int)1, (int)24, (int)5125, (long)0L, (int)this.J);
            GL11.glEnable((int)2884);
            this.b();
            return;
        }
        GL30.glBindVertexArray((int)this.G);
        renderBatchShaderProgram.P();
        FloatBuffer floatBuffer = renderMatrix4f.J();
        FloatBuffer floatBuffer4 = renderMatrix4f2.J();
        FloatBuffer floatBuffer5 = renderMatrix4f3.J();
        gg.vape.wrapper.impl.GL20.w(renderBatchShaderProgram.m, false, floatBuffer);
        gg.vape.wrapper.impl.GL20.w(renderBatchShaderProgram.B, false, floatBuffer4);
        gg.vape.wrapper.impl.GL20.w(renderBatchShaderProgram.T, false, floatBuffer5);
        this.o.flip();
        GL15.glBindBuffer((int)34962, (int)this.E);
        GL15.glBufferSubData((int)34962, (long)0L, (FloatBuffer)this.o);
        GL31.glDrawElementsInstanced((int)1, (int)24, (int)5125, (long)0L, (int)this.J);
        this.b();
    }

    public boolean G$src$Z$1js1tb7() {
        return this.H;
    }

    private void s() {
        if (this.H) {
            return;
        }
        if (InvWalkKeyLayout.p == null) {
            InvWalkKeyLayout.y();
        }
        this.G = GL30.glGenVertexArrays();
        GL30.glBindVertexArray((int)this.G);
        float[] fArray = new float[]{0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer((int)fArray.length);
        floatBuffer.put(fArray).flip();
        this.q = GL15.glGenBuffers();
        GL15.glBindBuffer((int)34962, (int)this.q);
        GL15.glBufferData((int)34962, (FloatBuffer)floatBuffer, (int)35044);
        GL20.glVertexAttribPointer((int)0, (int)3, (int)5126, (boolean)false, (int)12, (long)0L);
        GL20.glEnableVertexAttribArray((int)0);
        int[] nArray = new int[]{0, 1, 1, 2, 2, 3, 3, 0, 4, 5, 5, 6, 6, 7, 7, 4, 0, 4, 1, 5, 2, 6, 3, 7};
        IntBuffer intBuffer = BufferUtils.createIntBuffer((int)nArray.length);
        intBuffer.put(nArray).flip();
        this.m = GL15.glGenBuffers();
        GL15.glBindBuffer((int)34963, (int)this.m);
        GL15.glBufferData((int)34963, (IntBuffer)intBuffer, (int)35044);
        this.E = GL15.glGenBuffers();
        GL15.glBindBuffer((int)34962, (int)this.E);
        GL15.glBufferData((int)34962, (long)((long)this.B * 7L * 4L), (int)35048);
        GL20.glVertexAttribPointer((int)1, (int)3, (int)5126, (boolean)false, (int)28, (long)0L);
        GL20.glEnableVertexAttribArray((int)1);
        GL33.glVertexAttribDivisor((int)1, (int)1);
        GL20.glVertexAttribPointer((int)2, (int)4, (int)5126, (boolean)false, (int)28, (long)12L);
        GL20.glEnableVertexAttribArray((int)2);
        GL33.glVertexAttribDivisor((int)2, (int)1);
        GL30.glBindVertexArray((int)0);
        this.H = true;
        Vape.debugLog("InstancedBlockRenderer initialized (capacity: " + this.B + " blocks)");
    }

    private void u() {
        int n = this.B * 2;
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer((int)(n * 7));
        this.o.flip();
        floatBuffer.put(this.o);
        this.o = floatBuffer;
        GL15.glBindBuffer((int)34962, (int)this.E);
        GL15.glBufferData((int)34962, (long)((long)n * 7L * 4L), (int)35048);
        this.B = n;
    }

    private RenderBatchState() {
    }

    public static void r() {
        if (y != null) {
            y.G();
            y = null;
        }
    }

    public static RenderBatchState E() {
        if (y == null) {
            y = new RenderBatchState();
        }
        return y;
    }

    public int F() {
        return this.J;
    }

    public void f() {
        if (!this.H) {
            this.s();
        }
        this.o.clear();
        this.J = 0;
    }

    static {
        a = 24;
        u = 7;
    }


    private void a() {
        this.A = GL11.glGetInteger((int)34229);
        this.Y = GL11.glGetInteger((int)35725);
        this.L = GL11.glGetInteger((int)34964);
        this.w = GL11.glGetInteger((int)34965);
        this.b = GL11.glGetInteger((int)36006);
    }
}

