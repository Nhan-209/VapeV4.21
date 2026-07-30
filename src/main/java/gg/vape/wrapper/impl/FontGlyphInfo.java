package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class FontGlyphInfo
extends Wrapper {
    public float z() {
        return FontGlyphInfo.vapeInstance.getMappingsMapperCompat().hZ.k(this.I);
    }

    public FontGlyphInfo(Object object) {
        super(object);
    }

    public float L() {
        return FontGlyphInfo.vapeInstance.getMappingsMapperCompat().hZ.v(this.I);
    }

    public float f(boolean bl) {
        return FontGlyphInfo.vapeInstance.getMappingsMapperCompat().hZ.e(this.I, bl);
    }

    public float C() {
        return FontGlyphInfo.vapeInstance.getMappingsMapperCompat().hZ.B(this.I);
    }
}

