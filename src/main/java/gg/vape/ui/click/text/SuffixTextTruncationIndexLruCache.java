package gg.vape.ui.click.text;

import gg.vape.ui.click.text.SuffixTextTruncationIndexCache;
import java.util.LinkedHashMap;
import java.util.Map;

public class SuffixTextTruncationIndexLruCache
extends LinkedHashMap<Integer, Integer> {
    final SuffixTextTruncationIndexCache g;

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
        return this.size() > 512;
    }

    public SuffixTextTruncationIndexLruCache(SuffixTextTruncationIndexCache suffixTextTruncationIndexCache, int n, float f, boolean bl) {
        super(n, f, bl);
        this.g = suffixTextTruncationIndexCache;
    }

}
