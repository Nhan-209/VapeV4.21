package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class PlayerEventNameFormat
extends Wrapper {
    public String h() {
        return PlayerEventNameFormat.c.getMappingsMapperCompat().R1.u(this.I);
    }

    public void J(String string) {
        PlayerEventNameFormat.c.getMappingsMapperCompat().R1.c(this.I, string);
    }

    public PlayerEventNameFormat(Object object) {
        super(object);
    }

    public String G() {
        return PlayerEventNameFormat.c.getMappingsMapperCompat().R1.I(this.I);
    }
}

