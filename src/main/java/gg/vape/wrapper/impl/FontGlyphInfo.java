package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class FontGlyphInfo
extends Wrapper {
    public float z() {
        return FontGlyphInfo.c.getMappingsMapperCompat().hZ.k(this.I);
    }

    public FontGlyphInfo(Object object) {
        super(object);
    }

    public float L() {
        return FontGlyphInfo.c.getMappingsMapperCompat().hZ.v(this.I);
    }

    public float f(boolean bl) {
        return FontGlyphInfo.c.getMappingsMapperCompat().hZ.e(this.I, bl);
    }

    public float C() {
        return FontGlyphInfo.c.getMappingsMapperCompat().hZ.B(this.I);
    }
}

