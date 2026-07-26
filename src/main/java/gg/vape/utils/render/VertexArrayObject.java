package gg.vape.utils.render;

import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import java.lang.invoke.MethodHandles;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class VertexArrayObject {
    private int W = GL30.glGenVertexArrays();
    private int y;
    private static final long a = ZkmLongKeyState.a(5150667607278920873L, 7655150126317714313L, MethodHandles.lookup().lookupClass()).a(254581193807856L);
    private static final long b;

    public int L() {
        return this.W;
    }

    public void c() {
        GL30.glDeleteVertexArrays((int)this.W);
    }

    public void m() {
        long l = a ^ 0x7A358256F47CL;
        this.y = GL11.glGetInteger((int)((int)b));
        GL30.glBindVertexArray((int)this.W);
    }

    public void X() {
        GL30.glBindVertexArray((int)this.y);
    }

    static {
        long l = a ^ 0x3B20C5BE06DBL;
        b = -614517057353906763L;
    }
}

