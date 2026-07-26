package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.FontGlyph;
import gg.vape.wrapper.impl.GlyphInfo;
import gg.vape.wrapper.impl.GlyphProvider;

public class FontSet
extends Wrapper {
    public FontGlyph W(int n, boolean bl) {
        GlyphProvider pf_22 = this.v(bl);
        if (pf_22.isNull()) {
            return null;
        }
        FontGlyph p4_03 = pf_22.o(n);
        FontGlyph fontGlyph = p4_03 != null ? p4_03 : this.P();
        return fontGlyph;
    }

    public FontGlyph P() {
        Object object = FontSet.c.getMappingsMapperCompat().Z.v(this.I);
        FontGlyph fontGlyph = object != null ? new FontGlyph(object) : null;
        return fontGlyph;
    }

    public FontGlyph G(char c2) {
        return this.W(c2, false);
    }

    public GlyphProvider v(boolean bl) {
        return new GlyphProvider(FontSet.c.getMappingsMapperCompat().Z.t(this.I, bl));
    }

    public GlyphInfo T(char c2) {
        FontGlyph p4_02 = this.G(c2);
        GlyphInfo glyphInfo = p4_02 != null ? p4_02.x() : null;
        return glyphInfo;
    }

    public FontSet(Object object) {
        super(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }
}

