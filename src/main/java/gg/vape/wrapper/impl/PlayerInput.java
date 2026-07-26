package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MPlayerInput;
import gg.vape.wrapper.Wrapper;

public class PlayerInput
extends Wrapper {
    public PlayerInput(Object object) {
        super(object);
    }

    public void A(float f) {
        MPlayerInput.K(PlayerInput.c.getMappingsMapperCompat().RI, this.I, f);
    }

    public float Z() {
        return MPlayerInput.e(PlayerInput.c.getMappingsMapperCompat().RI, this.I);
    }

    public float P() {
        return MPlayerInput.B(PlayerInput.c.getMappingsMapperCompat().RI, this.I);
    }

    public void N(float f) {
        MPlayerInput.s(PlayerInput.c.getMappingsMapperCompat().RI, this.I, f);
    }
}

