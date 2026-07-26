package gg.vape.utils.render;

import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import java.lang.invoke.MethodHandles;
import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL15;

public class FloatBufferObject {
    private int I = GL15.glGenBuffers();
    private static final long a;

    public void G() {
        GL15.glDeleteBuffers((int)this.I);
    }

    public void U(long l) {
        long l2 = a ^ 0x3188572AE943L;
        GL15.glBufferData((int)34962, (long)l, (int)35048);
    }

    static {
        long l = a = ZkmLongKeyState.a(6724676921645458141L, 4423566862071600232L, MethodHandles.lookup().lookupClass()).a(165173321396133L);
    }

    public int T() {
        return this.I;
    }

    public void w() {
        long l = a ^ 0x7C1365E0ECCAL;
        GL15.glBindBuffer((int)34962, (int)this.I);
    }

    public void Q(FloatBuffer floatBuffer) {
        long l = a ^ 0x2166B41D7D7DL;
        GL15.glBufferSubData((int)34962, (long)0L, (FloatBuffer)floatBuffer);
    }

    public void I() {
        long l = a ^ 0x2B6E300D72AL;
        GL15.glBindBuffer((int)34962, (int)0);
    }
}
