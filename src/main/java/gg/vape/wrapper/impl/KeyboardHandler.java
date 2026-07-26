package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MKeyboardHandler;
import gg.vape.wrapper.Wrapper;

public class KeyboardHandler
extends Wrapper {
    public KeyboardHandler(Object object) {
        super(object);
    }

    public String F() {
        return MKeyboardHandler.X(KeyboardHandler.c.getMappingsMapperCompat().CR, this.I);
    }

    public void y(String string) {
        MKeyboardHandler.f(KeyboardHandler.c.getMappingsMapperCompat().CR, this.I, string);
    }
}

