package gg.vape.utils.render;

import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.unmap.ImageParser;
import gg.vape.unmap.ImageParser$Format;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.GlStateManager;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import gg.vape.utils.render.GlPixelStoreState;

public class GlImageTexture {
    public int l;
    public int u;
    public float h;
    public float C;
    public final int v;
    public int N;
    public float w;
    public int F;
    public int r;
    private static final long a;
    public int D;
    public float p;
    private static int L;
    public int W;
    private final int n;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public GlImageTexture(InputStream inputStream, int n, int n2, ImageParser$Format imageParser$Format) throws IOException {
        long l = a ^ 0x2F4843B0D691L;
        this.v = 10497;
        this.n = 3553;
        this.C = 0.0f;
        this.w = 0.0f;
        this.h = 1.0f;
        this.p = 1.0f;
        this.u = 9729;
        this.D = 9728;
        this.W = 10496;
        this.r = 33071;
        try {
            int n3 = n;
            int n4 = n == 9987 ? 9729 : n;
            boolean bl = GL11.glIsEnabled((int)3553);
            int n5 = GlStateManager.p();
            if (!bl) {
                GlStateManager.enableTexture2D();
            }
            if (n == 9987) {
                int n6 = GL11.glGetInteger((int)33307);
                if (n6 < 3) {
                    n4 = 33071;
                    n3 = 33071;
                }
                if (MappedClasses.Y2 != null) {
                    ContextCapabilities contextCapabilities = GLContext.getCapabilities();
                    if (!contextCapabilities.GL_ARB_framebuffer_object) {
                        n4 = 33071;
                        n3 = 33071;
                    }
                }
            }
            ImageParser imageParser = new ImageParser(inputStream);
            this.l = imageParser.q();
            this.N = imageParser.k();
            int n7 = 4;
            ByteBuffer byteBuffer = BufferUtils.createByteBuffer((int)(4 * this.l * this.N));
            imageParser.i(byteBuffer, this.l * 4, imageParser$Format);
            byteBuffer.flip();
            this.F = GlStateManager.F();
            this.F();
            GlPixelStoreState.reset();
            GL11.glPixelStorei((int)3317, (int)1);
            GL11.glTexParameteri((int)3553, (int)10241, (int)n3);
            GL11.glTexParameteri((int)3553, (int)10240, (int)n4);
            GL11.glTexParameteri((int)3553, (int)10242, (int)n2);
            GL11.glTexParameteri((int)3553, (int)10243, (int)n2);
            if (n3 == 9987) {
                GL11.glTexParameteri((int)3553, (int)33084, (int)0);
                GL11.glTexParameteri((int)3553, (int)33085, (int)1);
            }
            GL11.glTexImage2D((int)3553, (int)0, (int)6408, (int)this.l, (int)this.N, (int)0, (int)6408, (int)5121, (ByteBuffer)byteBuffer);
            if (n3 == 9987) {
                GL30.glGenerateMipmap((int)3553);
            }
            GlStateManager.bindTexture(n5);
            if (!bl) {
                GlStateManager.disableTexture2D();
            }
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException iOException) {}
            }
        }
    }

    public void C() {
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public GlImageTexture(int n) {
        long l = a ^ 0x55C6E9E55603L;
        this.v = 10497;
        this.n = 3553;
        this.C = 0.0f;
        this.w = 0.0f;
        this.h = 1.0f;
        this.p = 1.0f;
        this.u = 9729;
        this.D = 9728;
        this.W = 10496;
        this.r = 33071;
        this.F = n;
    }

    public void U(int n, int n2) {
        GL11.glTexParameteri((int)3553, (int)n, (int)n2);
    }

    public static GlImageTexture J(int n, int n2, ByteBuffer byteBuffer, int n3, int n4, int n5) {
        boolean bl;
        long l = a ^ 0x49462E3BEECDL;
        int n6 = GlStateManager.p();
        GlImageTexture glImageTexture = new GlImageTexture();
        glImageTexture.N = n2;
        glImageTexture.l = n;
        GL11.glBindTexture((int)3553, (int)glImageTexture.F);
        GL11.glPixelStorei((int)3312, (int)0);
        GL11.glPixelStorei((int)3313, (int)0);
        GL11.glPixelStorei((int)3314, (int)0);
        GL11.glPixelStorei((int)32878, (int)0);
        GL11.glPixelStorei((int)3315, (int)0);
        GL11.glPixelStorei((int)3316, (int)0);
        GL11.glPixelStorei((int)32877, (int)0);
        GL11.glPixelStorei((int)3317, (int)4);
        boolean bl2 = bl = n4 == 9987 || n4 == 9985 || n4 == 9986 || n4 == 9984;
        if (bl) {
            glImageTexture.U(10240, 9729);
            glImageTexture.U(10241, n4);
            glImageTexture.U(10242, n5);
            glImageTexture.U(10243, n5);
            int n7 = (int)Math.floor(Math.log(Math.max(n, n2)) / Math.log(2.0));
            GL11.glTexParameteri((int)3553, (int)33084, (int)0);
            GL11.glTexParameteri((int)3553, (int)33085, (int)n7);
            GL11.glTexParameterf((int)3553, (int)34049, (float)-0.4f);
            GL11.glTexParameterf((int)3553, (int)33082, (float)0.0f);
            GL11.glTexParameterf((int)3553, (int)33083, (float)n7);
            glImageTexture.M(n3, n, n2, n3, byteBuffer);
            GL30.glGenerateMipmap((int)3553);
            GlStateManager.bindTexture(n6);
            return glImageTexture;
        }
        glImageTexture.U(10240, n4);
        glImageTexture.U(10241, n4);
        glImageTexture.U(10242, n5);
        glImageTexture.U(10243, n5);
        glImageTexture.M(n3, n, n2, n3, byteBuffer);
        GlStateManager.bindTexture(n6);
        return glImageTexture;
    }

    public GlImageTexture(int n, int n2, int n3, float f, float f2, float f3, float f4) {
        long l = a ^ 0x213B6F78746L;
        this.v = 10497;
        this.n = 3553;
        this.C = 0.0f;
        this.w = 0.0f;
        this.h = 1.0f;
        this.p = 1.0f;
        this.u = 9729;
        this.D = 9728;
        this.W = 10496;
        this.r = 33071;
        this.F = n;
        this.l = n2;
        this.N = n3;
        this.C = f;
        this.w = f2;
        this.h = f3;
        this.p = f4;
    }

    public void n(int n) {
        long l = a ^ 0x46F9925E43E8L;
        if (!GuiRenderPrimitives.d()) {
            this.F();
            return;
        }
        GL13.glActiveTexture((int)(33984 + n));
        GlStateManager.bindTexture(this.F);
    }

    public void F() {
        if (GuiRenderPrimitives.d()) {
            this.n(0);
        } else {
            GlStateManager.bindTexture(this.F);
            L = this.F;
        }
    }

    public GlImageTexture(InputStream inputStream) throws IOException {
        this(inputStream, 9729, ImageParser$Format.RGBA);
    }

    public GlImageTexture(InputStream inputStream, int n, ImageParser$Format imageParser$Format) throws IOException {
        this(inputStream, n, 33071, imageParser$Format);
    }

    public void O() {
        GL11.glDeleteTextures((int)this.F);
    }

    public void M(int n, int n2, int n3, int n4, ByteBuffer byteBuffer) {
        GL11.glTexImage2D((int)3553, (int)0, (int)n, (int)n2, (int)n3, (int)0, (int)n4, (int)5121, (ByteBuffer)byteBuffer);
    }

    static {
        long l = a = ZkmLongKeyState.a(842252555918399920L, -5543020354335729035L, MethodHandles.lookup().lookupClass()).a(30980163640429L);
        L = 0;
    }

    public GlImageTexture() {
        long l = a ^ 0x47CF30962C68L;
        this.v = 10497;
        this.n = 3553;
        this.C = 0.0f;
        this.w = 0.0f;
        this.h = 1.0f;
        this.p = 1.0f;
        this.u = 9729;
        this.D = 9728;
        this.W = 10496;
        this.r = 33071;
        this.F = GL11.glGenTextures();
    }
}
