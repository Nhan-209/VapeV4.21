package gg.vape.utils.render.glu;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.glu.GluQuadric;
import org.lwjgl.opengl.GL11;

public class GluSphere
extends GluQuadric {
    public void K(float f, int n, int n2) {
        boolean bl;
        boolean bl2 = bl = this.x != 100002;
        if (bl) {
            float f2 = this.P == 100021 ? -1.0f : 1.0f;
            float f3 = (float)Math.PI / (float)n2;
            float f4 = (float)Math.PI * 2 / (float)n;
            if (this.g == 100012) {
                float f5;
                float f6;
                int n3;
                int n4;
                float f7;
                float f8;
                float f9;
                float f10;
                int n5;
                if (!this.O) {
                    GL11.glBegin((int)6);
                    GL11.glNormal3f((float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glVertex3f((float)0.0f, (float)0.0f, (float)(f2 * f));
                    for (n5 = 0; n5 <= n; ++n5) {
                        f10 = n5 == n ? 0.0f : (float)n5 * f4;
                        f9 = -this.Q(f10) * this.Q(f3);
                        f8 = this.q(f10) * this.Q(f3);
                        f7 = f2 * this.q(f3);
                        GL11.glNormal3f((float)(f9 * f2), (float)(f8 * f2), (float)(f7 * f2));
                        GL11.glVertex3f((float)(f9 * f), (float)(f8 * f), (float)(f7 * f));
                    }
                    GL11.glEnd();
                }
                float f11 = 1.0f / (float)n;
                float f12 = 1.0f / (float)n2;
                float f13 = 1.0f;
                if (this.O) {
                    n4 = 0;
                    n3 = n2;
                } else {
                    n4 = 1;
                    n3 = n2 - 1;
                }
                for (int i = n4; i < n3; ++i) {
                    f6 = (float)i * f3;
                    GL11.glBegin((int)8);
                    f5 = 0.0f;
                    for (n5 = 0; n5 <= n; ++n5) {
                        f10 = n5 == n ? 0.0f : (float)n5 * f4;
                        f9 = -this.Q(f10) * this.Q(f6);
                        f8 = this.q(f10) * this.Q(f6);
                        f7 = f2 * this.q(f6);
                        GL11.glNormal3f((float)(f9 * f2), (float)(f8 * f2), (float)(f7 * f2));
                        this.k(f5, f13);
                        GL11.glVertex3f((float)(f9 * f), (float)(f8 * f), (float)(f7 * f));
                        f9 = -this.Q(f10) * this.Q(f6 + f3);
                        f8 = this.q(f10) * this.Q(f6 + f3);
                        f7 = f2 * this.q(f6 + f3);
                        GL11.glNormal3f((float)(f9 * f2), (float)(f8 * f2), (float)(f7 * f2));
                        this.k(f5, f13 - f12);
                        f5 += f11;
                        GL11.glVertex3f((float)(f9 * f), (float)(f8 * f), (float)(f7 * f));
                    }
                    GL11.glEnd();
                    f13 -= f12;
                }
                if (!this.O) {
                    GL11.glBegin((int)6);
                    GL11.glNormal3f((float)0.0f, (float)0.0f, (float)-1.0f);
                    GL11.glVertex3f((float)0.0f, (float)0.0f, (float)(-f * f2));
                    f6 = (float)Math.PI - f3;
                    f5 = 1.0f;
                    for (n5 = n; n5 >= 0; --n5) {
                        f10 = n5 == n ? 0.0f : (float)n5 * f4;
                        f9 = -this.Q(f10) * this.Q(f6);
                        f8 = this.q(f10) * this.Q(f6);
                        f7 = f2 * this.q(f6);
                        GL11.glNormal3f((float)(f9 * f2), (float)(f8 * f2), (float)(f7 * f2));
                        f5 -= f11;
                        GL11.glVertex3f((float)(f9 * f), (float)(f8 * f), (float)(f7 * f));
                    }
                    GL11.glEnd();
                }
            } else if (this.g != 100011 && this.g != 100013) {
                if (this.g == 100010) {
                    GL11.glBegin((int)0);
                    GL11.glNormal3f((float)0.0f, (float)0.0f, (float)f2);
                    GL11.glVertex3f((float)0.0f, (float)0.0f, (float)f);
                    GL11.glNormal3f((float)0.0f, (float)0.0f, (float)(-f2));
                    GL11.glVertex3f((float)0.0f, (float)0.0f, (float)(-f));
                    for (int i = 1; i < n2 - 1; ++i) {
                        float f14 = (float)i * f3;
                        for (int j = 0; j < n; ++j) {
                            float f15 = (float)j * f4;
                            float f16 = this.q(f15) * this.Q(f14);
                            float f17 = this.Q(f15) * this.Q(f14);
                            float f18 = this.q(f14);
                            GL11.glNormal3f((float)(f16 * f2), (float)(f17 * f2), (float)(f18 * f2));
                            GL11.glVertex3f((float)(f16 * f), (float)(f17 * f), (float)(f18 * f));
                        }
                    }
                    GL11.glEnd();
                }
            } else {
                float f19;
                float f20;
                float f21;
                float f22;
                int n6;
                float f23;
                int n7;
                for (n7 = 1; n7 < n2; ++n7) {
                    f23 = (float)n7 * f3;
                    GL11.glBegin((int)2);
                    for (n6 = 0; n6 < n; ++n6) {
                        f22 = (float)n6 * f4;
                        f21 = this.q(f22) * this.Q(f23);
                        f20 = this.Q(f22) * this.Q(f23);
                        f19 = this.q(f23);
                        GL11.glNormal3f((float)(f21 * f2), (float)(f20 * f2), (float)(f19 * f2));
                        GL11.glVertex3f((float)(f21 * f), (float)(f20 * f), (float)(f19 * f));
                    }
                    GL11.glEnd();
                }
                for (n6 = 0; n6 < n; ++n6) {
                    f22 = (float)n6 * f4;
                    GL11.glBegin((int)3);
                    for (n7 = 0; n7 <= n2; ++n7) {
                        f23 = (float)n7 * f3;
                        f21 = this.q(f22) * this.Q(f23);
                        f20 = this.Q(f22) * this.Q(f23);
                        f19 = this.q(f23);
                        GL11.glNormal3f((float)(f21 * f2), (float)(f20 * f2), (float)(f19 * f2));
                        GL11.glVertex3f((float)(f21 * f), (float)(f20 * f), (float)(f19 * f));
                    }
                    GL11.glEnd();
                }
            }
            return;
        }
        float f24 = this.P == 100021 ? -1.0f : 1.0f;
        float f25 = (float)Math.PI / (float)n2;
        float f26 = (float)Math.PI * 2 / (float)n;
        if (this.g == 100012) {
            float f27;
            float f28;
            int n8;
            int n9;
            float f29;
            float f30;
            float f31;
            float f32;
            int n10;
            if (!this.O) {
                GL11.glBegin((int)6);
                GL11.glNormal3f((float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glVertex3f((float)0.0f, (float)0.0f, (float)(f24 * f));
                for (n10 = 0; n10 <= n; ++n10) {
                    f32 = n10 == n ? 0.0f : (float)n10 * f26;
                    f31 = -this.Q(f32) * this.Q(f25);
                    f30 = this.q(f32) * this.Q(f25);
                    f29 = f24 * this.q(f25);
                    GL11.glVertex3f((float)(f31 * f), (float)(f30 * f), (float)(f29 * f));
                }
                GL11.glEnd();
            }
            float f33 = 1.0f / (float)n;
            float f34 = 1.0f / (float)n2;
            float f35 = 1.0f;
            if (this.O) {
                n9 = 0;
                n8 = n2;
            } else {
                n9 = 1;
                n8 = n2 - 1;
            }
            for (int i = n9; i < n8; ++i) {
                f28 = (float)i * f25;
                GL11.glBegin((int)8);
                f27 = 0.0f;
                for (n10 = 0; n10 <= n; ++n10) {
                    f32 = n10 == n ? 0.0f : (float)n10 * f26;
                    f31 = -this.Q(f32) * this.Q(f28);
                    f30 = this.q(f32) * this.Q(f28);
                    f29 = f24 * this.q(f28);
                    this.k(f27, f35);
                    GL11.glVertex3f((float)(f31 * f), (float)(f30 * f), (float)(f29 * f));
                    f31 = -this.Q(f32) * this.Q(f28 + f25);
                    f30 = this.q(f32) * this.Q(f28 + f25);
                    f29 = f24 * this.q(f28 + f25);
                    this.k(f27, f35 - f34);
                    f27 += f33;
                    GL11.glVertex3f((float)(f31 * f), (float)(f30 * f), (float)(f29 * f));
                }
                GL11.glEnd();
                f35 -= f34;
            }
            if (!this.O) {
                GL11.glBegin((int)6);
                GL11.glNormal3f((float)0.0f, (float)0.0f, (float)-1.0f);
                GL11.glVertex3f((float)0.0f, (float)0.0f, (float)(-f * f24));
                f28 = (float)Math.PI - f25;
                f27 = 1.0f;
                for (n10 = n; n10 >= 0; --n10) {
                    f32 = n10 == n ? 0.0f : (float)n10 * f26;
                    f31 = -this.Q(f32) * this.Q(f28);
                    f30 = this.q(f32) * this.Q(f28);
                    f29 = f24 * this.q(f28);
                    f27 -= f33;
                    GL11.glVertex3f((float)(f31 * f), (float)(f30 * f), (float)(f29 * f));
                }
                GL11.glEnd();
            }
        } else if (this.g != 100011 && this.g != 100013) {
            if (this.g == 100010) {
                GL11.glBegin((int)0);
                GL11.glVertex3f((float)0.0f, (float)0.0f, (float)f);
                GL11.glVertex3f((float)0.0f, (float)0.0f, (float)(-f));
                for (int i = 1; i < n2 - 1; ++i) {
                    float f36 = (float)i * f25;
                    for (int j = 0; j < n; ++j) {
                        float f37 = (float)j * f26;
                        float f38 = this.q(f37) * this.Q(f36);
                        float f39 = this.Q(f37) * this.Q(f36);
                        float f40 = this.q(f36);
                        GL11.glVertex3f((float)(f38 * f), (float)(f39 * f), (float)(f40 * f));
                    }
                }
                GL11.glEnd();
            }
        } else {
            float f41;
            float f42;
            float f43;
            float f44;
            int n11;
            float f45;
            int n12;
            for (n12 = 1; n12 < n2; ++n12) {
                f45 = (float)n12 * f25;
                GL11.glBegin((int)2);
                for (n11 = 0; n11 < n; ++n11) {
                    f44 = (float)n11 * f26;
                    f43 = this.q(f44) * this.Q(f45);
                    f42 = this.Q(f44) * this.Q(f45);
                    f41 = this.q(f45);
                    GL11.glVertex3f((float)(f43 * f), (float)(f42 * f), (float)(f41 * f));
                }
                GL11.glEnd();
            }
            for (n11 = 0; n11 < n; ++n11) {
                f44 = (float)n11 * f26;
                GL11.glBegin((int)3);
                for (n12 = 0; n12 <= n2; ++n12) {
                    f45 = (float)n12 * f25;
                    f43 = this.q(f44) * this.Q(f45);
                    f42 = this.Q(f44) * this.Q(f45);
                    f41 = this.q(f45);
                    GL11.glVertex3f((float)(f43 * f), (float)(f42 * f), (float)(f41 * f));
                }
                GL11.glEnd();
            }
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

