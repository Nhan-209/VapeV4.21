package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;

public class TextFormatting
extends Wrapper {
    public static TextFormatting q(String string) {
        Object object = TextFormatting.c.getMappingsMapperCompat().Cq.h(string);
        TextFormatting textFormatting = object != null ? new TextFormatting(object) : null;
        return textFormatting;
    }

    public TextFormatting(Object object) {
        super(object);
    }

    public Integer K() {
        return TextFormatting.c.getMappingsMapperCompat().Cq.e(this.getObject());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public String A() {
        return TextFormatting.c.getMappingsMapperCompat().Cq.m(this.getObject());
    }
}

