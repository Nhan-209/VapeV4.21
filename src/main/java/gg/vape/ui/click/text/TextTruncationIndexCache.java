package gg.vape.ui.click.text;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.ui.click.text.TextTruncationIndexLruCache;
import gg.vape.ui.click.text.TruncatedTextSpec;
import gg.vape.ui.font.SmoothFontRenderer;
import java.lang.invoke.MethodHandles;
import java.util.LinkedHashMap;

public class TextTruncationIndexCache {
    private final LinkedHashMap<Integer, Integer> U = new TextTruncationIndexLruCache(this, 16, 0.75f, true);
    private static final int F;
    public static TextTruncationIndexCache J;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int b() {
        return this.U.size();
    }

    static {
        long l = ZkmLongKeyState.a(-3459048365626913539L, -7150995780154406557L, MethodHandles.lookup().lookupClass()).a(255620945587324L) ^ 0x6FAAD355DE3CL;
        long l2 = -8931204442707983872L;
        F = (int)l2;
        J = new TextTruncationIndexCache();
    }

    public int d(TruncatedTextSpec truncatedTextSpec) {
        Integer n = this.U.get(truncatedTextSpec.hashCode());
        if (n != null) {
            return n;
        }
        SmoothFontRenderer smoothFontRenderer = truncatedTextSpec.q() ? Vape.INSTANCE.getFontManager().W(truncatedTextSpec.N(), false) : Vape.INSTANCE.getFontManager().Y(truncatedTextSpec.N());
        int n2 = -2;
        if (smoothFontRenderer.N(truncatedTextSpec.g()) <= truncatedTextSpec.y()) {
            n2 = truncatedTextSpec.g().length() - 1;
        } else {
            int n3 = (int)Math.ceil(truncatedTextSpec.g().length() / 2) - 1;
            boolean bl = false;
            while (n3 >= 0 && n3 < truncatedTextSpec.g().length()) {
                double d = smoothFontRenderer.N(truncatedTextSpec.g().substring(0, n3));
                if (d > truncatedTextSpec.y()) {
                    bl = true;
                    --n3;
                    continue;
                }
                if (bl || n3 == truncatedTextSpec.g().length() - 1) break;
                ++n3;
            }
            n2 = n3;
        }
        if (n2 == -1) {
            --n2;
        }
        this.U.put(truncatedTextSpec.hashCode(), n2);
        return n2;
    }
}

