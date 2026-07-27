package gg.vape.utils.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class VertexArrayObject {
    private int W = GL30.glGenVertexArrays();
    private int y;
    private static final long b;

    public int L() {
        return this.W;
    }

    public void c() {
        GL30.glDeleteVertexArrays((int)this.W);
    }

    public void m() {
        this.y = GL11.glGetInteger((int)((int)b));
        GL30.glBindVertexArray((int)this.W);
    }

    public void X() {
        GL30.glBindVertexArray((int)this.y);
    }

    static {
        b = -614517057353906763L;
    }
}

