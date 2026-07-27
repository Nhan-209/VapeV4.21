package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.FontSet;

public class FontManager
extends Wrapper {

    public FontSet K() {
        Object object = FontManager.c.getMappingsMapperCompat().qb.x(this.I);
        FontSet fontSet = object != null ? new FontSet(object) : null;
        return fontSet;
    }

    public FontSet h() {
        Object object = FontManager.c.getMappingsMapperCompat().qb.u(this.I);
        FontSet fontSet = object != null ? new FontSet(object) : null;
        return fontSet;
    }

    public FontManager(Object object) {
        super(object);
    }

    public FontSet w(String string) {
        Object object = FontManager.c.getMappingsMapperCompat().qb.x(this.I, string);
        FontSet fontSet = object != null ? new FontSet(object) : null;
        return fontSet;
    }
}

