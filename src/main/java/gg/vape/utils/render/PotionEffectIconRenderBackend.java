package gg.vape.utils.render;

import gg.vape.wrapper.impl.PotionEffect;

public interface PotionEffectIconRenderBackend {
    public void d(float var1, float var2, int var3, int var4, float var5);

    default public void l(float f, float f2, int n, int n2, float f3, boolean bl) {
        this.d(f, f2, n, n2, f3);
    }

    public void a(PotionEffect var1);

    public void B();
}

