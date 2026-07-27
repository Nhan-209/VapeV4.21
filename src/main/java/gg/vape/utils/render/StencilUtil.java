package gg.vape.utils.render;

import gg.vape.unmap.Stencil;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.wrapper.impl.Framebuffer;
import gg.vape.wrapper.impl.Minecraft;
import java.util.HashMap;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public final class StencilUtil {
    private int g = 1;
    private final HashMap<Integer, Stencil> T = new HashMap();
    private static final StencilUtil j = new StencilUtil();
    private boolean A;

    public void S() {
        this.e(new Stencil(this, 517, this.g, this.I(), 7680, 7680, 7680));
    }

    public void N() {
        GL11.glClearStencil((int)0);
        GL11.glClear((int)1024);
        this.T.clear();
        this.g = 1;
    }

    public static void S(Framebuffer framebuffer) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT((int)framebuffer.getDepthBuffer());
        int n = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT((int)36161, (int)n);
        EXTFramebufferObject.glRenderbufferStorageEXT((int)36161, (int)34041, (int)Minecraft.J(), (int)Minecraft.h());
        EXTFramebufferObject.glFramebufferRenderbufferEXT((int)36160, (int)36128, (int)36161, (int)n);
        EXTFramebufferObject.glFramebufferRenderbufferEXT((int)36160, (int)36096, (int)36161, (int)n);
    }

    public int I() {
        return (int)(Math.pow(2.0, this.P()) - 1.0);
    }

    public static StencilUtil t() {
        return j;
    }

    public void Q(double d, double d2, double d3, double d4) {
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)d, (double)d4);
        GL11.glVertex2d((double)d3, (double)d4);
        GL11.glVertex2d((double)d3, (double)d2);
        GL11.glVertex2d((double)d, (double)d2);
        GL11.glEnd();
    }

    public static void d() {
        Framebuffer framebuffer = Minecraft.getFrameBuffer();
        if (framebuffer.isNotNull() && framebuffer.getDepthBuffer() > -1) {
            StencilUtil.S(framebuffer);
            framebuffer.setDepthBuffer(-1);
        }
    }

    public void e(Stencil stencil) {
        GL11.glStencilFunc((int)Stencil.Y, (int)Stencil.u, (int)Stencil.W);
        GL11.glStencilOp((int)Stencil.n, (int)Stencil.e, (int)Stencil.a);
        this.T.put(this.g, stencil);
    }

    public int e() {
        return this.g;
    }

    public void n() {
        this.e(new Stencil(this, this.A ? 519 : 512, this.g, this.I(), 7681, 7680, 7680));
    }

    public void B(boolean bl) {
        this.e(new Stencil(this, this.A ? 519 : 512, bl ? this.g : this.g - 1, this.I(), 7681, 7681, 7681));
    }

    public void x(boolean bl) {
        this.A = bl;
    }

    public void R() {
        this.e(new Stencil(this, 514, this.g, this.I(), 7680, 7680, 7680));
    }

    public void j() {
        if (this.g == 1) {
            GL11.glClearStencil((int)0);
            GL11.glClear((int)1024);
        }
        OpenGlBackendHolder.d.l(2960);
        ++this.g;
        if (this.g > this.I()) {
            System.out.println("StencilUtil: Reached maximum amount of layers!");
            this.g = 1;
        }
    }

    public Stencil J() {
        return this.T.get(this.g);
    }

    public void X() {
        if (this.g == 1) {
            System.out.println("StencilUtil: No layers found!");
            return;
        }
        --this.g;
        if (this.g == 1) {
            OpenGlBackendHolder.d.u$src$V$hntn98(2960);
        } else {
            Stencil stencil = this.T.remove(this.g);
            if (stencil != null) {
                stencil.apply();
            }
        }
    }


    public void T(double d, double d2, double d3) {
        GL11.glBegin((int)6);
        for (int i = 0; i <= 360; ++i) {
            double d4 = Math.sin((double)i * Math.PI / 180.0) * d3;
            double d5 = Math.cos((double)i * Math.PI / 180.0) * d3;
            GL11.glVertex2d((double)(d + d4), (double)(d2 + d5));
        }
        GL11.glEnd();
    }

    public int P() {
        return GL11.glGetInteger((int)3415);
    }
}

