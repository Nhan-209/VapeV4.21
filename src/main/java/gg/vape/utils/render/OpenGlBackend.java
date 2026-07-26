package gg.vape.utils.render;

public interface OpenGlBackend {
    public void m();

    public void P(float var1, float var2, float var3);

    public void w(float var1, double var2, double var4, double var6);

    public void b(double var1, float var3, float var4);

    public void e(int var1, int var2, int var3, int var4);

    public void S();

    public float float_u(int var1);

    public void F();

    public void r(float var1);

    public void k(int var1, float var2);

    public void H(float var1, float var2, float var3);

    public void X(float var1, float var2, float var3, float var4);

    public void E(float var1, float var2, float var3);

    public void F(float var1, float var2, float var3);

    public void l(int var1);

    public int K(int var1);

    public void I(double var1, double var3, double var5);

    public void M();

    public void C(int var1);

    public void void_u(int var1);

    public boolean L(int var1);

    public void m(double var1, double var3, double var5);

    public void n(double var1, double var3, double var5);

    public void G(double var1, double var3, double var5);

    public void k(double var1, double var3, double var5, double var7);

    public void q(float var1, float var2, float var3, float var4);

    public void U(boolean var1);

    default public /* synthetic */ float u(int n) {
        return this.float_u(n);
    }

    default public /* synthetic */ void u$src$V$hntn98(int n) {
        this.void_u(n);
    }
}

