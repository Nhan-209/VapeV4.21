package gg.vape.rotation;

import gg.vape.event.impl.EventPreEntityRendererMouseUpdate;
import gg.vape.rotation.MouseRotationController;
import gg.vape.rotation.RotationAngles;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class FixedRotationController
extends MouseRotationController {
    public float I;
    private boolean a;
    public float L;
    private boolean k;
    private boolean w;
    private boolean p;
    private boolean J;
    private boolean G;
    public static final float Z = -999.0f;

    public boolean Y() {
        return this.k;
    }

    public void j(boolean bl) {
        this.w = bl;
    }

    public boolean e() {
        return this.p;
    }

    public void A(boolean bl) {
        this.G = bl;
    }

    public boolean w$src$Z$15qe9bc() {
        return this.J;
    }

    public float b() {
        return this.L;
    }

    public FixedRotationController(RotationAngles rotationAngles) {
        this.L = rotationAngles.z();
        this.I = rotationAngles.N();
    }

    public void z(boolean bl) {
        this.p = bl;
    }

    public float d() {
        return Minecraft.F().V();
    }

    public void k(boolean bl) {
        this.a = bl;
    }

    public boolean T() {
        return this.w;
    }

    @Override
    public boolean A() {
        float f;
        float f2;
        float f3;
        float f4;
        if (this.L == -999.0f) {
            return true;
        }
        float f5 = RotationManager.b.E();
        int n = (int)this.B;
        int n2 = (int)(-this.y);
        float f6 = f5 * 0.6f + 0.2f;
        float f7 = f6 * f6 * f6 * 8.0f;
        float f8 = (float)n * f7;
        float f9 = (float)n2 * f7;
        float f10 = (float)((double)this.k() + (double)f8 * 0.15);
        float f11 = (float)((double)this.d() - (double)f9 * 0.15);
        double d = MathUtil.wrapAngleTo180((double)((this.L - f10) % 360.0f));
        double d2 = MathUtil.wrapAngleTo180((double)((this.I - f11) % 360.0f));
        double d3 = Math.abs(d);
        double d4 = Math.abs(d2);
        double d5 = (double)this.O() * 0.25;
        double d6 = d3 / d4;
        if (this.k && d6 < 1.0) {
            d5 *= d6;
        }
        if (Math.round(d3 / (double)(f4 = (float)(0.0 + (double)(f3 = (f2 = (f = RotationManager.b.E()) * 0.6f + 0.2f) * f2 * f2 * 8.0f) * 0.15))) > (long)Math.max(Math.round(this.W / f4), 0)) {
            if (this.G) {
                d5 *= (225.0 + d3) / 180.0;
            } else if (this.J) {
                d5 += d3 * 0.05;
            } else if (this.p) {
                double d7 = d3 / 100.0;
                double d8 = 0.4;
                double d9 = 1.0;
                double d10 = -0.7;
                double d11 = d9 + 1.0;
                d5 *= Math.min(Math.max(1.0, d8 + d11 * Math.pow(d7 - d10, 3.0) + d9 * Math.pow(d7 - d10, 2.0)), 4.0);
            }
            this.B = this.a ? (d > 0.0 ? (float)((double)this.B + Math.min(d5, d / (double)f4)) : (float)((double)this.B - Math.min(d5, Math.abs(d / (double)f4)))) : (d > 0.0 ? (float)((double)this.B + d5) : (float)((double)this.B - d5));
            return false;
        }
        return true;
    }

    @Override
    public void B(EventPreEntityRendererMouseUpdate eventPreEntityRendererMouseUpdate) {
        if (this.T()) {
            EntityPlayerSP entityPlayerSP = eventPreEntityRendererMouseUpdate.getThePlayer();
            if (entityPlayerSP.J() != this.Q) {
                entityPlayerSP.H(this.Q);
            }
            if (entityPlayerSP.j() != this.s) {
                entityPlayerSP.D(this.s);
            }
            if (entityPlayerSP.V() != this.c) {
                entityPlayerSP.C(this.c);
            }
            if (entityPlayerSP.D() != this.S) {
                entityPlayerSP.l(this.S);
            }
        }
    }

    public void g(float f, float f2) {
        this.L = f;
        this.I = f2;
        this.u(false);
    }

    @Override
    public boolean m() {
        float f;
        float f2;
        float f3;
        float f4;
        if (this.I == -999.0f) {
            return true;
        }
        float f5 = this.d();
        if (f5 == -90.0f) {
            f5 = -89.99f;
        }
        float f6 = RotationManager.b.E();
        int n = (int)this.B;
        int n2 = (int)(-this.y);
        float f7 = f6 * 0.6f + 0.2f;
        float f8 = f7 * f7 * f7 * 8.0f;
        float f9 = (float)n * f8;
        float f10 = (float)n2 * f8;
        float f11 = (float)((double)this.k() + (double)f9 * 0.15);
        float f12 = (float)((double)f5 - (double)f10 * 0.15);
        double d = MathUtil.wrapAngleTo180((this.L - f11) % 360.0f);
        double d2 = MathUtil.wrapAngleTo180((this.I - f12) % 360.0f);
        double d3 = Math.abs(d);
        double d4 = Math.abs(d2);
        double d5 = (double)this.O() * 0.25;
        double d6 = d4 / d3;
        if (this.k && d6 < 1.0) {
            d5 *= d6;
        }
        if (Math.round(d4 / (double)(f4 = (float)(0.0 + (double)(f3 = (f2 = (f = RotationManager.b.E()) * 0.6f + 0.2f) * f2 * f2 * 8.0f) * 0.15))) > (long)Math.max(Math.round(this.W / f4), 0)) {
            if (this.G) {
                d5 *= (135.0 + d4) / 90.0;
            } else if (this.J) {
                d5 += d4 * 0.05;
            } else if (this.p) {
                double d7 = d4 / 75.0;
                double d8 = 0.4;
                double d9 = 1.0;
                double d10 = -0.7;
                double d11 = d9 + 1.0;
                d5 *= Math.max(1.0, d8 + d11 * Math.pow(d7 - d10, 3.0) + d9 * Math.pow(d7 - d10, 2.0));
            }
            this.y = this.a ? (d2 > 0.0 ? (float)((double)this.y + Math.min(d5, d2 / (double)f4)) : (float)((double)this.y - Math.min(d5, Math.abs(d2 / (double)f4)))) : (d2 > 0.0 ? (float)((double)this.y + d5) : (float)((double)this.y - d5));
            return false;
        }
        return true;
    }

    public void b(RotationAngles rotationAngles) {
        this.g(rotationAngles.z(), rotationAngles.N());
    }

    public FixedRotationController(float f, float f2) {
        this.L = f;
        this.I = f2;
    }

    public float s$src$F$15o72go() {
        return this.I;
    }

    public boolean K() {
        return this.a;
    }

    public float k() {
        return Minecraft.F().J();
    }

    public void s(boolean bl) {
        this.J = bl;
    }

    private static ObfuscatedRuntimeException c(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void U(boolean bl) {
        this.k = bl;
    }

    public boolean S() {
        return this.G;
    }
}

