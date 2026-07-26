package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class PlayerDiggingAction
extends Wrapper {
    public static PlayerDiggingAction s() {
        return new PlayerDiggingAction(PlayerDiggingAction.c.getMappingsMapperCompat().Ci.l());
    }

    public PlayerDiggingAction(Object object) {
        super(object);
    }
}

