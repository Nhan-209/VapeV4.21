package gg.vape.utils.render;

import gg.vape.utils.datas.FloatPair;
import gg.vape.utils.render.RenderMatrix4f;
import gg.vape.utils.render.RenderVector3f;
import gg.vape.utils.render.RenderVector4f;

public class WorldToScreenProjector {

    public static FloatPair d(RenderVector3f renderVector3f, RenderMatrix4f renderMatrix4f, RenderMatrix4f renderMatrix4f2, RenderMatrix4f renderMatrix4f3, int n, int n2) {
        RenderMatrix4f renderMatrix4f4 = renderMatrix4f2.u(renderMatrix4f);
        RenderMatrix4f renderMatrix4f5 = renderMatrix4f3.u(renderMatrix4f4);
        RenderVector4f renderVector4f = new RenderVector4f(renderVector3f.t, renderVector3f.n, renderVector3f.x, 1.0f);
        renderMatrix4f5.Z(renderVector4f, renderVector4f);
        if (renderVector4f.J <= 0.01f) {
            return null;
        }
        float f = renderVector4f.N / renderVector4f.J;
        float f2 = renderVector4f.w / renderVector4f.J;
        float f3 = (f * 0.5f + 0.5f) * (float)n;
        float f4 = (1.0f - (f2 * 0.5f + 0.5f)) * (float)n2;
        return new FloatPair(f3, f4);
    }
}

