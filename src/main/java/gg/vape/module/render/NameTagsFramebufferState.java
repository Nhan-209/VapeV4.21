package gg.vape.module.render;

import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class NameTagsFramebufferState {
    private int D;
    private final int M;
    private final IntBuffer Y = BufferUtils.createIntBuffer((int)16);
    private final int E;
    private int x;
    private final int s;
    private final int O;
    private int p;
    private int R;


    public NameTagsFramebufferState(int n, int n2, int n3, int n4) {
        this.E = n;
        this.O = n2;
        this.s = n3;
        this.M = n4;
        this.o();
    }

    public void V() {
        if (GuiRenderPrimitives.d()) {
            BufferedGuiRenderPrimitives.k.b();
            BufferedGuiRenderPrimitives.X.z();
        } else {
            GL11.glMatrixMode((int)5888);
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5889);
            GL11.glPopMatrix();
        }
        GL30.glBindFramebuffer((int)36160, (int)this.x);
        this.Y.rewind();
        GL11.glViewport((int)this.Y.get(0), (int)this.Y.get(1), (int)this.Y.get(2), (int)this.Y.get(3));
    }

    public boolean R() {
        if (this.p == 0) {
            return false;
        }
        try {
            return GL11.glIsTexture((int)this.p);
        }
        catch (Exception exception) {
            return false;
        }
    }

    public void N() {
        this.Y.clear();
        gg.vape.wrapper.impl.GL11.X(2978, this.Y);
        IntBuffer intBuffer = BufferUtils.createIntBuffer((int)16);
        gg.vape.wrapper.impl.GL11.X(36006, intBuffer);
        this.x = intBuffer.get(0);
        GL11.glViewport((int)0, (int)0, (int)(this.s * 4), (int)(this.M * 4));
        GL30.glBindFramebuffer((int)36160, (int)this.R);
        GL11.glClear((int)256);
        GL11.glClear((int)16384);
        if (GuiRenderPrimitives.d()) {
            BufferedGuiRenderPrimitives.k.b();
            BufferedGuiRenderPrimitives.k.e(0.0f, this.s, this.M, 0.0f, -2000.0f, 1000.0f);
            BufferedGuiRenderPrimitives.X.D();
            BufferedGuiRenderPrimitives.X.W();
            BufferedGuiRenderPrimitives.X.K(-this.E, -this.O, 0.0f);
        } else {
            GL11.glMatrixMode((int)5889);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GL11.glOrtho((double)0.0, (double)this.s, (double)this.M, (double)0.0, (double)-2000.0, (double)1000.0);
            GL11.glMatrixMode((int)5888);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GL11.glTranslatef((float)(-this.E), (float)(-this.O), (float)0.0f);
        }
    }

    public int B() {
        return this.O;
    }

    private void o() {
        IntBuffer intBuffer = BufferUtils.createIntBuffer((int)16);
        gg.vape.wrapper.impl.GL11.X(36006, intBuffer);
        int n = intBuffer.get(0);
        IntBuffer intBuffer2 = BufferUtils.createIntBuffer((int)16);
        gg.vape.wrapper.impl.GL11.X(32873, intBuffer2);
        int n2 = intBuffer2.get(0);
        IntBuffer intBuffer3 = BufferUtils.createIntBuffer((int)16);
        gg.vape.wrapper.impl.GL11.X(36007, intBuffer3);
        int n3 = intBuffer3.get(0);
        this.R = GL30.glGenFramebuffers();
        GL30.glBindFramebuffer((int)36160, (int)this.R);
        this.p = GL11.glGenTextures();
        GL11.glBindTexture((int)3553, (int)this.p);
        GL11.glTexImage2D((int)3553, (int)0, (int)6408, (int)(this.s * 4), (int)(this.M * 4), (int)0, (int)6408, (int)5121, (ByteBuffer)null);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
        GL30.glFramebufferTexture2D((int)36160, (int)36064, (int)3553, (int)this.p, (int)0);
        this.D = GL30.glGenRenderbuffers();
        GL30.glBindRenderbuffer((int)36161, (int)this.D);
        GL30.glRenderbufferStorage((int)36161, (int)6402, (int)(this.s * 4), (int)(this.M * 4));
        GL30.glFramebufferRenderbuffer((int)36160, (int)36096, (int)36161, (int)this.D);
        if (GL30.glCheckFramebufferStatus((int)36160) != 36053) {
            // empty if block
        }
        GL30.glBindFramebuffer((int)36160, (int)n);
        GL11.glBindTexture((int)3553, (int)n2);
        GL30.glBindRenderbuffer((int)36161, (int)n3);
    }

    public void n() {
        GL30.glDeleteFramebuffers((int)this.R);
        GL11.glDeleteTextures((int)this.p);
        GL30.glDeleteRenderbuffers((int)this.D);
    }

    public int j() {
        return this.E;
    }

    public int X() {
        return this.M;
    }

    public int k() {
        return this.s;
    }

    public int m() {
        return this.p;
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}
