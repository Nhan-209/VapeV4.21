package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.utils.render.CachedTextTexture;
import gg.vape.utils.render.CachedTextTextureKey;
import gg.vape.wrapper.impl.Minecraft;
import java.util.HashMap;

public class CachedTextTextureRegistry {
    private static String H;
    static HashMap<CachedTextTextureKey, CachedTextTexture> e;

    public static String N() {
        return H;
    }

    private static void P(CachedTextTextureKey cachedTextTextureKey) {
        CachedTextTexture cachedTextTexture = new CachedTextTexture();
        cachedTextTexture.D(cachedTextTextureKey.O, cachedTextTextureKey.H());
        e.put(cachedTextTextureKey, cachedTextTexture);
    }


    public static void f(String string) {
        H = string;
    }

    public static void I(String string, float f, float f2, int n) {
        try {
            CachedTextTextureKey cachedTextTextureKey = new CachedTextTextureKey(string, n);
            CachedTextTextureRegistry.m(string, n);
            int n2 = Minecraft.getFontRenderer().getStringWidth(string);
            int n3 = Minecraft.getFontRenderer().FONT_HEIGHT(string);
            e.get(cachedTextTextureKey).O(f, f2, n2, n3);
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    public static void m(String string, int n) {
        CachedTextTextureKey cachedTextTextureKey = new CachedTextTextureKey(string, n);
        if (!e.containsKey(cachedTextTextureKey)) {
            CachedTextTextureRegistry.P(cachedTextTextureKey);
        }
    }

    static {
        e = new HashMap();
        CachedTextTextureRegistry.f(null);
    }
}

