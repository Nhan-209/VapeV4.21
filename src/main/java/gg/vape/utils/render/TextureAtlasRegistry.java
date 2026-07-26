package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.TextureAtlas;
import java.util.HashMap;
import java.util.Map;

public class TextureAtlasRegistry {
    private static final String b = "Unable to retrieve Texture Atlas id: ";
    private final Map<String, TextureAtlas> M = new HashMap<String, TextureAtlas>();
    private TextureAtlas f;
    private static TextureAtlasRegistry A;

    private TextureAtlasRegistry() {
    }

    public TextureAtlas r() {
        return this.f;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void s(String string) {
        if (!this.M.containsKey(string)) {
            return;
        }
        this.M.get(string).d().O();
        this.M.remove(string);
    }

    public static TextureAtlasRegistry w() {
        if (A == null) {
            A = new TextureAtlasRegistry();
        }
        return A;
    }

    public TextureAtlas U(String string) {
        if (!this.M.containsKey(string)) {
            this.M.put(string, new TextureAtlas());
        }
        return this.M.get(string);
    }

    public void U(TextureAtlas textureAtlas) {
        this.f = textureAtlas;
    }

    public TextureAtlas m(String string) {
        if (!this.M.containsKey(string)) {
            Vape.debugLog(b + string);
            return null;
        }
        return this.M.get(string);
    }
}

