package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MGlyphProvider;
import gg.vape.wrapper.Wrapper;

public class GlyphProvider
extends Wrapper {
    public GlyphProvider(Object object) {
        super(object);
    }

    public FontGlyph o(int n) {
        return new FontGlyph(MGlyphProvider.Q(GlyphProvider.vapeInstance.getMappingsMapperCompat().E, this.I, n));
    }
}

