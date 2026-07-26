package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;

public class GameSettingsGuiScaleValue
extends Wrapper {
    public static int s() {
        if (GameSettingsGuiScaleValue.c.getMappingsMapperCompat().Dv == null) {
            return 0;
        }
        return GameSettingsGuiScaleValue.c.getMappingsMapperCompat().Dv.V();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public GameSettingsGuiScaleValue(Object object) {
        super(object);
    }
}

