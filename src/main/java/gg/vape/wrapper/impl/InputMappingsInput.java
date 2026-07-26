package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MInputMappingsInput;
import gg.vape.wrapper.Wrapper;

public class InputMappingsInput
extends Wrapper {
    public InputMappingsInput(Object object) {
        super(object);
    }

    public int R() {
        return MInputMappingsInput.B(InputMappingsInput.c.getMappingsMapperCompat().hm, this.I);
    }
}

