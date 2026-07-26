package gg.vape.wrapper.impl;

import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.TextComponentTranslation;

public class StringTextComponent
extends Wrapper {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public StringTextComponent(Object object) {
        super(object);
    }

    public TextComponentTranslation H() {
        return new TextComponentTranslation(StringTextComponent.c.getMappingsMapperCompat().DK.W(this.I));
    }

    public String x() {
        if (MappedClasses.DE != null && ForgeVersion.MC_26_1.d()) {
            return "";
        }
        return StringTextComponent.c.getMappingsMapperCompat().DK.q(this.I);
    }
}

