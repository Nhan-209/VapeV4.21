package gg.vape.utils.render.glu;

import gg.vape.runtime.ObfuscatedRuntimeException;
import org.lwjgl.opengl.GL11;

public class GluQuadric {
    protected boolean O;
    protected int g;
    protected int x;
    protected int P;

    public int A() {
        return this.g;
    }

    protected void J(float f, float f2, float f3) {
        float f4 = (float)Math.sqrt(f * f + f2 * f2 + f3 * f3);
        if (f4 > 1.0E-5f) {
            f /= f4;
            f2 /= f4;
            f3 /= f4;
        }
        GL11.glNormal3f((float)f, (float)f2, (float)f3);
    }

    public void g(int n) {
        this.g = n;
    }

    public GluQuadric() {
        this.g = 100012;
        this.P = 100020;
        this.O = false;
        this.x = 100000;
    }

    public void y(int n) {
        this.x = n;
    }

    public void b(int n) {
        this.P = n;
    }

    public void u(boolean bl) {
        this.O = bl;
    }


    protected float q(float f) {
        return (float)Math.cos(f);
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean j() {
        return this.O;
    }

    public int U() {
        return this.P;
    }

    public int g() {
        return this.x;
    }

    protected float Q(float f) {
        return (float)Math.sin(f);
    }

    protected void k(float f, float f2) {
        if (this.O) {
            GL11.glTexCoord2f((float)f, (float)f2);
        }
    }
}
