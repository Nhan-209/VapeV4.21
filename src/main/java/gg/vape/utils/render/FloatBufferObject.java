package gg.vape.utils.render;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL15;

public class FloatBufferObject {
    private int I = GL15.glGenBuffers();

    public void G() {
        GL15.glDeleteBuffers((int)this.I);
    }

    public void U(long l) {
        GL15.glBufferData((int)34962, (long)l, (int)35048);
    }


    public int T() {
        return this.I;
    }

    public void w() {
        GL15.glBindBuffer((int)34962, (int)this.I);
    }

    public void Q(FloatBuffer floatBuffer) {
        GL15.glBufferSubData((int)34962, (long)0L, (FloatBuffer)floatBuffer);
    }

    public void I() {
        GL15.glBindBuffer((int)34962, (int)0);
    }
}
