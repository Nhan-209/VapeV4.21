package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ITextComponent;
import gg.vape.wrapper.impl.Screen;

public class TitledScreen
extends Screen {
    public String E() {
        if (ForgeVersion.MC_1_7_10.L()) {
            return "";
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            ITextComponent t3_02 = new ITextComponent(TitledScreen.c.getMappingsMapperCompat().ql.o(this.I));
            return t3_02.C();
        }
        return (String)TitledScreen.c.getMappingsMapperCompat().ql.o(this.I);
    }

    public TitledScreen(Object object) {
        super(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }
}

