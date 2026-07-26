package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.PotionEffectIcon;
import gg.vape.utils.render.PotionEffectIconKey;
import gg.vape.utils.render.PotionEffectIconRenderBackend;
import gg.vape.utils.render.PotionEffectIconTexture;
import gg.vape.wrapper.impl.PotionEffect;
import java.util.HashMap;

public class PotionEffectIconRenderer {
    private static String f;
    static HashMap<PotionEffectIconKey, PotionEffectIconRenderBackend> k;

    public static void f(PotionEffect ti_22, float f, float f2, int n, int n2, float f3, boolean bl) {
        try {
            PotionEffectIconKey is_22 = new PotionEffectIconKey(ti_22.C());
            PotionEffectIconRenderer.Y(ti_22.C());
            k.get(is_22).l(f, f2, n, n2, f3, bl);
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public static void Y(int n) {
        PotionEffectIconKey is_22 = new PotionEffectIconKey(n);
        if (!k.containsKey(is_22)) {
            PotionEffectIconRenderer.z(PotionEffect.o(n, 100, 0), is_22);
        }
    }

    public static void g(String string) {
        f = string;
    }

    public static String Q() {
        return f;
    }

    public static void g() {
        for (PotionEffectIconRenderBackend go_22 : k.values()) {
            go_22.B();
        }
        k.clear();
    }

    public static void V(PotionEffect ti_22, float f, float f2, int n, int n2, float f3) {
        PotionEffectIconRenderer.f(ti_22, f, f2, n, n2, f3, false);
    }

    private static void z(PotionEffect ti_22, PotionEffectIconKey is_22) {
        PotionEffectIconRenderBackend go_22 = GuiRenderPrimitives.d() ? new PotionEffectIconTexture() : new PotionEffectIcon();
        go_22.a(ti_22);
        k.put(is_22, go_22);
    }

    static {
        k = new HashMap();
        PotionEffectIconRenderer.g(null);
    }
}

