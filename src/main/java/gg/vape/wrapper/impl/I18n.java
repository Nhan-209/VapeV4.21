package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class I18n
extends Wrapper {
    public I18n(Object object) {
        super(object);
    }

    public static String f(String string, Object ... objectArray) {
        return I18n.vapeInstance.getMappingsMapperCompat().DB.R(string, objectArray);
    }

    public static Language w() {
        return new Language(I18n.vapeInstance.getMappingsMapperCompat().DB.S());
    }
}

