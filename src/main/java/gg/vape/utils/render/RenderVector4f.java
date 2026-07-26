package gg.vape.utils.render;

public class RenderVector4f {
    public float w;
    public float N;
    public float J;
    public float Y;

    public RenderVector4f(float f, float f2, float f3, float f4) {
        this.N = f;
        this.w = f2;
        this.Y = f3;
        this.J = f4;
    }

    public RenderVector4f(double d, double d2, double d3, double d4) {
        this.N = (float)d;
        this.w = (float)d2;
        this.Y = (float)d3;
        this.J = (float)d4;
    }

    public String toString() {
        return "Vec4{x=" + this.N + ", y=" + this.w + ", z=" + this.Y + ", w=" + this.J + '}';
    }
}

