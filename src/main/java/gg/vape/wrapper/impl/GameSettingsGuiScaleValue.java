package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class GameSettingsGuiScaleValue
extends Wrapper {
    public static int s() {
        if (GameSettingsGuiScaleValue.c.getMappingsMapperCompat().Dv == null) {
            return 0;
        }
        return GameSettingsGuiScaleValue.c.getMappingsMapperCompat().Dv.V();
    }


    public GameSettingsGuiScaleValue(Object object) {
        super(object);
    }
}

