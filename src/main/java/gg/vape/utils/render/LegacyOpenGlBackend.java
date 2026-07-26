package gg.vape.utils.render;

import gg.vape.utils.render.OpenGlBackend;
import org.lwjgl.opengl.GL11;

public class LegacyOpenGlBackend
implements OpenGlBackend {
    @Override
    public void X(float f, float f2, float f3, float f4) {
        GL11.glRotatef((float)f, (float)f2, (float)f3, (float)f4);
    }

    @Override
    public void n(double d, double d2, double d3) {
        GL11.glNormal3d((double)d, (double)d2, (double)d3);
    }

    @Override
    public void M() {
        GL11.glEnd();
    }

    @Override
    public void k(int n, float f) {
        GL11.glAlphaFunc((int)n, (float)f);
    }

    @Override
    public void U(boolean bl) {
        GL11.glDepthMask((boolean)bl);
    }

    @Override
    public void void_u(int n) {
        GL11.glDisable((int)n);
    }

    @Override
    public void S() {
        GL11.glLoadIdentity();
    }

    @Override
    public void l(int n) {
        GL11.glEnable((int)n);
    }

    @Override
    public void F() {
        GL11.glPopMatrix();
    }

    @Override
    public void m(double d, double d2, double d3) {
        GL11.glVertex3d((double)d, (double)d2, (double)d3);
    }

    @Override
    public float float_u(int n) {
        return GL11.glGetFloat((int)n);
    }

    @Override
    public void P(float f, float f2, float f3) {
        GL11.glTranslatef((float)f, (float)f2, (float)f3);
    }

    @Override
    public boolean L(int n) {
        return GL11.glIsEnabled((int)n);
    }

    @Override
    public void q(float f, float f2, float f3, float f4) {
        GL11.glColor4f((float)f, (float)f2, (float)f3, (float)f4);
    }

    @Override
    public int K(int n) {
        return GL11.glGetInteger((int)n);
    }

    @Override
    public void b(double d, float f, float f2) {
        GL11.glColor3d((double)d, (double)f, (double)f2);
    }

    @Override
    public void E(float f, float f2, float f3) {
        GL11.glColor3f((float)f, (float)f2, (float)f3);
    }

    @Override
    public void w(float f, double d, double d2, double d3) {
        GL11.glRotated((double)f, (double)d, (double)d2, (double)d3);
    }

    @Override
    public void k(double d, double d2, double d3, double d4) {
        GL11.glColor4d((double)d, (double)d2, (double)d3, (double)d4);
    }

    @Override
    public void H(float f, float f2, float f3) {
        GL11.glScalef((float)f, (float)f2, (float)f3);
    }

    @Override
    public void G(double d, double d2, double d3) {
        GL11.glScaled((double)d, (double)d2, (double)d3);
    }

    @Override
    public void e(int n, int n2, int n3, int n4) {
        GL11.glScissor((int)n, (int)n2, (int)n3, (int)n4);
    }

    @Override
    public void r(float f) {
        GL11.glLineWidth((float)f);
    }

    @Override
    public void m() {
        GL11.glPushMatrix();
    }

    @Override
    public void F(float f, float f2, float f3) {
        GL11.glNormal3f((float)f, (float)f2, (float)f3);
    }

    @Override
    public void I(double d, double d2, double d3) {
        GL11.glTranslated((double)d, (double)d2, (double)d3);
    }

    @Override
    public void C(int n) {
        GL11.glBegin((int)n);
    }
}

