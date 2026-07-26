package gg.vape.utils.render;

import gg.vape.Vape;
import java.nio.charset.StandardCharsets;
import org.lwjgl.opengl.GL20;

public class RenderBatchShaderProgram {
    public int S;
    public final int m;
    private static int[] g;
    public final int T;
    public final int B;

    private int b(String string) {
        int n;
        if (string.endsWith(".frag")) {
            n = 35632;
        } else if (string.endsWith(".vert")) {
            n = 35633;
        } else {
            throw new RuntimeException("Unable to set type");
        }
        String string2 = string;
        byte[] byArray = Vape.readResource(string2);
        int n2 = GL20.glCreateShader((int)n);
        String string3 = new String(byArray, StandardCharsets.UTF_8);
        GL20.glShaderSource((int)n2, (CharSequence)string3);
        GL20.glCompileShader((int)n2);
        String string4 = null;
        if (GL20.glGetShaderi((int)n2, (int)35713) == 0) {
            string4 = GL20.glGetShaderInfoLog((int)n2, (int)512);
        }
        if (string4 != null) {
            throw new RuntimeException("Unable to compile shader: " + n + " - " + string4);
        }
        return n2;
    }

    public static int[] s() {
        return g;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static void Z(int[] nArray) {
        g = nArray;
    }

    static {
        RenderBatchShaderProgram.Z(new int[3]);
    }

    private void d(int n, int n2) {
        this.S = GL20.glCreateProgram();
        GL20.glAttachShader((int)this.S, (int)n);
        GL20.glAttachShader((int)this.S, (int)n2);
        GL20.glLinkProgram((int)this.S);
        if (GL20.glGetProgrami((int)this.S, (int)35714) == 0) {
            String string = GL20.glGetProgramInfoLog((int)this.S, (int)8224);
            throw new RuntimeException("Unable to link shader: " + string);
        }
        GL20.glDeleteShader((int)n);
        GL20.glDeleteShader((int)n2);
    }

    public RenderBatchShaderProgram(String string, String string2) {
        int n = this.b(string);
        int n2 = this.b(string2);
        this.d(n, n2);
        this.m = GL20.glGetUniformLocation((int)this.S, (CharSequence)"u_Projection");
        this.T = GL20.glGetUniformLocation((int)this.S, (CharSequence)"u_Model");
        this.B = GL20.glGetUniformLocation((int)this.S, (CharSequence)"u_View");
    }

    public void P() {
        GL20.glUseProgram((int)this.S);
    }
}

