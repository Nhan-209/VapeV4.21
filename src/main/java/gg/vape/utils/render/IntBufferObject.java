package gg.vape.utils.render;

import java.nio.IntBuffer;
import org.lwjgl.opengl.GL15;

public class IntBufferObject {
    private int z = GL15.glGenBuffers();

    public void S(IntBuffer intBuffer) {
        GL15.glBufferSubData((int)34963, (long)0L, (IntBuffer)intBuffer);
    }


    public void c() {
        GL15.glBindBuffer((int)34963, (int)this.z);
    }

    public void d() {
        GL15.glDeleteBuffers((int)this.z);
    }

    public void W() {
        GL15.glBindBuffer((int)34963, (int)0);
    }
}
