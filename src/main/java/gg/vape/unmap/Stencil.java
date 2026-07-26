package gg.vape.unmap;

import gg.vape.utils.render.StencilUtil;
import org.lwjgl.opengl.GL11;

public class Stencil {
    public static int e;
    public static int u;
    public static int n;
    public static int a;
    public static int Y;
    public static int W;

    public Stencil(StencilUtil stencilUtil, int n, int n2, int n3, int n4, int n5, int n6) {
        Y = n;
        u = n2;
        W = n3;
        Stencil.n = n4;
        e = n5;
        a = n6;
    }

    public void apply() {
        GL11.glStencilFunc((int)Y, (int)u, (int)W);
        GL11.glStencilOp((int)n, (int)e, (int)a);
    }
}

