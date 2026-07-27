package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class TooltipFlagBridge
extends Wrapper {

    public TooltipFlagBridge(Object object) {
        super(object);
    }

    public static TooltipFlagBridge J() {
        if (ForgeVersion.MC_1_20_6.d()) {
            Vape.notifyNativeStackTrace();
        }
        return new TooltipFlagBridge(TooltipFlagBridge.c.getMappings().hO.h());
    }
}

