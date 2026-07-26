package gg.vape.wrapper.impl;

import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.FontGlyphInfo;
import gg.vape.wrapper.impl.GlyphInfo;

public class FontGlyph
extends Wrapper {
    public boolean l() {
        boolean bl = MappedClasses.v != null && MappedClasses.v.isInstance(this.I);
        return bl;
    }

    public GlyphInfo x() {
        if (this.l()) {
            return new GlyphInfo(this.I);
        }
        return null;
    }

    public FontGlyphInfo D() {
        Object object = FontGlyph.c.getMappingsMapperCompat().De.M(this.I);
        FontGlyphInfo fontGlyphInfo = object != null ? new FontGlyphInfo(object) : null;
        return fontGlyphInfo;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public FontGlyph(Object object) {
        super(object);
    }
}

