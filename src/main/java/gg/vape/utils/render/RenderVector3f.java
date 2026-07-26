package gg.vape.utils.render;

public class RenderVector3f {
    public float n;
    public float x;
    public float t;

    public RenderVector3f R(RenderVector3f renderVector3f) {
        float f = this.n * renderVector3f.x - this.x * renderVector3f.n;
        float f2 = this.x * renderVector3f.t - this.t * renderVector3f.x;
        float f3 = this.t * renderVector3f.n - this.n * renderVector3f.t;
        return new RenderVector3f(f, f2, f3);
    }

    public RenderVector3f T(RenderVector3f renderVector3f) {
        return new RenderVector3f(this.t - renderVector3f.t, this.n - renderVector3f.n, this.x - renderVector3f.x);
    }

    public RenderVector3f H() {
        this.t = -this.t;
        this.n = -this.n;
        this.x = -this.x;
        return this;
    }

    public float B(RenderVector3f renderVector3f) {
        return this.t * renderVector3f.t + this.n * renderVector3f.n + this.x * renderVector3f.x;
    }

    public RenderVector3f(float f, float f2, float f3) {
        this.t = f;
        this.n = f2;
        this.x = f3;
    }

    public String toString() {
        return "Vec3{x=" + this.t + ", y=" + this.n + ", z=" + this.x + '}';
    }

    public RenderVector3f W() {
        float f = (float)Math.sqrt(this.t * this.t + this.n * this.n + this.x * this.x);
        return new RenderVector3f(this.t / f, this.n / f, this.x / f);
    }
}

