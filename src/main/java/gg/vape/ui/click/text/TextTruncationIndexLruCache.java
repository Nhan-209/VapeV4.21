package gg.vape.ui.click.text;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.text.TextTruncationIndexCache;
import java.util.LinkedHashMap;
import java.util.Map;

public class TextTruncationIndexLruCache
extends LinkedHashMap<Integer, Integer> {
    final TextTruncationIndexCache x;

    public TextTruncationIndexLruCache(TextTruncationIndexCache textTruncationIndexCache, int n, float f, boolean bl) {
        super(n, f, bl);
        this.x = textTruncationIndexCache;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
        return this.size() > 512;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
