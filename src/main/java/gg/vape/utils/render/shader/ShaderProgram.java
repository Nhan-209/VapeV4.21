package gg.vape.utils.render.shader;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderProgram {
    private static int D;
    int k;
    int A = 0;
    int h = 0;
    boolean j;
    int w = GL20.glCreateProgram();
    int l = 0;
    private static GuiComponent[] b;

    public void e(String string, String string2, String string3) {
        this.w = GL20.glCreateProgram();
        this.h = GL20.glCreateShader((int)35633);
        this.l = GL20.glCreateShader((int)35632);
        if (string3 != null) {
            this.A = GL20.glCreateShader((int)36313);
        }
        GL20.glShaderSource((int)this.h, (CharSequence)string);
        GL20.glShaderSource((int)this.l, (CharSequence)string2);
        if (string3 != null) {
            GL20.glShaderSource((int)this.A, (CharSequence)string3);
        }
        GL20.glCompileShader((int)this.h);
        GL20.glCompileShader((int)this.l);
        if (string3 != null) {
            GL20.glCompileShader((int)this.A);
        }
        GL20.glAttachShader((int)this.w, (int)this.h);
        GL20.glAttachShader((int)this.w, (int)this.l);
        if (string3 != null) {
            GL20.glAttachShader((int)this.w, (int)this.A);
        }
        GL20.glLinkProgram((int)this.w);
        IntBuffer intBuffer = BufferUtils.createIntBuffer((int)1);
        gg.vape.wrapper.impl.GL20.x(this.w, 35714, intBuffer);
        this.j = intBuffer.get(0) == 1;
    }

    public ShaderProgram(String string, String string2, String string3) {
        this.h = GL20.glCreateShader((int)35633);
        this.l = GL20.glCreateShader((int)35632);
        if (string3 != null) {
            this.A = GL20.glCreateShader((int)36313);
        }
        GL20.glShaderSource((int)this.h, (CharSequence)string);
        GL20.glShaderSource((int)this.l, (CharSequence)string2);
        if (this.A != 0) {
            GL20.glShaderSource((int)this.A, (CharSequence)string3);
        }
        GL20.glCompileShader((int)this.h);
        GL20.glCompileShader((int)this.l);
        if (this.A != 0) {
            GL20.glCompileShader((int)this.A);
        }
        GL20.glAttachShader((int)this.w, (int)this.h);
        GL20.glAttachShader((int)this.w, (int)this.l);
        if (this.A != 0) {
            GL20.glAttachShader((int)this.w, (int)this.A);
        }
        GL20.glLinkProgram((int)this.w);
        IntBuffer intBuffer = BufferUtils.createIntBuffer((int)1);
        gg.vape.wrapper.impl.GL20.x(this.w, 35714, intBuffer);
        this.j = intBuffer.get(0) == 1;
    }

    public static GuiComponent[] W() {
        return b;
    }

    public static int C() {
        if (D == -1) {
            D = GL11.glGetInteger((int)35725);
        }
        return D;
    }

    public boolean R() {
        return this.j;
    }

    public int o() {
        return this.w;
    }

    public void L() {
        ShaderProgram.m(this.k);
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void v(int n) {
        D = n;
    }

    public void C(String string, String string2) {
        this.e(string, string2, null);
    }

    public static void m(int n) {
        GL20.glUseProgram((int)n);
        ShaderProgram.v(n);
    }

    public ShaderProgram(String string, String string2) {
        this(string, string2, null);
    }

    static {
        ShaderProgram.b(new GuiComponent[4]);
        D = -1;
    }

    public boolean f() {
        if (!this.j) {
            return false;
        }
        this.k = ShaderProgram.C();
        ShaderProgram.m(this.o());
        return true;
    }

    public static void b(GuiComponent[] guiComponentArray) {
        b = guiComponentArray;
    }
}

