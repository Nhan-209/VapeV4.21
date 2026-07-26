package gg.vape.utils.render;

import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import java.lang.invoke.MethodHandles;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL15;

public class IntBufferObject {
    private int z = GL15.glGenBuffers();
    private static final long a;

    public void S(IntBuffer intBuffer) {
        long l = a ^ 0x634E6ED6BD2BL;
        GL15.glBufferSubData((int)34963, (long)0L, (IntBuffer)intBuffer);
    }

    static {
        long l = a = ZkmLongKeyState.a(-8645315379294171195L, 2809248550571945896L, MethodHandles.lookup().lookupClass()).a(33057211162461L);
    }

    public void c() {
        long l = a ^ 0x22A82BFC5184L;
        GL15.glBindBuffer((int)34963, (int)this.z);
    }

    public void d() {
        GL15.glDeleteBuffers((int)this.z);
    }

    public void W() {
        long l = a ^ 0x4EDFAE70D466L;
        GL15.glBindBuffer((int)34963, (int)0);
    }
}
