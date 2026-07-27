package gg.vape.utils.render;

import gg.vape.utils.render.RenderMatrix4f;
import java.util.ArrayDeque;
import java.util.Deque;

public class RenderMatrixStack {
    private final Deque<RenderMatrix4f> O = new ArrayDeque<RenderMatrix4f>();

    public void W() {
        this.c().b();
    }

    public RenderMatrixStack() {
        this.O.push(new RenderMatrix4f().b());
    }

    public void u(float f, float f2, float f3, float f4, float f5, float f6) {
        this.c().e(f, f2, f3, f4, f5, f6);
    }

    public void K(float f, float f2, float f3) {
        this.c().I(f, f2, f3);
    }

    public RenderMatrix4f c() {
        return this.O.peek();
    }

    public void g(float f, float f2, float f3) {
        this.c().O(f, f2, f3);
    }

    public void z() {
        if (this.O.size() > 1) {
            this.O.pop();
        }
    }

    public void a(float f) {
        this.c().v(f);
    }

    public void D() {
        this.O.push(new RenderMatrix4f().b());
    }


    public void e(float f, float f2, float f3, float f4) {
        this.c().d(f, f2, f3, f4);
    }

    public void u(RenderMatrix4f renderMatrix4f) {
        this.c().u(renderMatrix4f);
    }
}

