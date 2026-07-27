package gg.vape.ui.click.text;

import gg.vape.Vape;
import gg.vape.ui.click.text.SuffixTextTruncationIndexLruCache;
import gg.vape.ui.click.text.TruncatedTextSpec;
import gg.vape.ui.font.SmoothFontRenderer;
import java.util.LinkedHashMap;

public class SuffixTextTruncationIndexCache {
    public static SuffixTextTruncationIndexCache H;
    private static final int h;
    private final LinkedHashMap<Integer, Integer> K = new SuffixTextTruncationIndexLruCache(this, 16, 0.75f, true);


    public int n(TruncatedTextSpec truncatedTextSpec) {
        Integer n = this.K.get(truncatedTextSpec.hashCode());
        if (n != null) {
            return n;
        }
        SmoothFontRenderer smoothFontRenderer = truncatedTextSpec.q() ? Vape.INSTANCE.getFontManager().W(truncatedTextSpec.N(), false) : Vape.INSTANCE.getFontManager().Y(truncatedTextSpec.N());
        int n2 = -1;
        if (smoothFontRenderer.N(truncatedTextSpec.g()) <= truncatedTextSpec.y()) {
            n2 = truncatedTextSpec.g().length() - 1;
        } else {
            int n3 = (int)Math.ceil(truncatedTextSpec.g().length() / 2) - 1;
            boolean bl = false;
            while (n3 >= 0 && n3 < truncatedTextSpec.g().length()) {
                double d = smoothFontRenderer.N(truncatedTextSpec.g().substring(0, n3) + truncatedTextSpec.L());
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
        if (n2 == -1 && smoothFontRenderer.N(truncatedTextSpec.L()) > truncatedTextSpec.y()) {
            --n2;
        }
        this.K.put(truncatedTextSpec.hashCode(), n2);
        return n2;
    }

    public int k() {
        return this.K.size();
    }

    static {
        long l2 = -5362564765556145664L;
        h = (int)l2;
        H = new SuffixTextTruncationIndexCache();
    }
}

