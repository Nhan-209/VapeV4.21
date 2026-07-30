package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class PlayerEventNameFormat
extends Wrapper {
    public String h() {
        return PlayerEventNameFormat.vapeInstance.getMappingsMapperCompat().R1.u(this.I);
    }

    public void J(String string) {
        PlayerEventNameFormat.vapeInstance.getMappingsMapperCompat().R1.c(this.I, string);
    }

    public PlayerEventNameFormat(Object object) {
        super(object);
    }

    public String G() {
        return PlayerEventNameFormat.vapeInstance.getMappingsMapperCompat().R1.I(this.I);
    }
}

