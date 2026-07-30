package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class TextFormatting
extends Wrapper {
    public static TextFormatting q(String string) {
        Object object = TextFormatting.vapeInstance.getMappingsMapperCompat().Cq.h(string);
        TextFormatting textFormatting = object != null ? new TextFormatting(object) : null;
        return textFormatting;
    }

    public TextFormatting(Object object) {
        super(object);
    }

    public Integer K() {
        return TextFormatting.vapeInstance.getMappingsMapperCompat().Cq.e(this.getObject());
    }


    public String A() {
        return TextFormatting.vapeInstance.getMappingsMapperCompat().Cq.m(this.getObject());
    }
}

