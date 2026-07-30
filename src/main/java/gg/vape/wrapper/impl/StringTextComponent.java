package gg.vape.wrapper.impl;

import gg.vape.mapping.MappedClasses;
import gg.vape.wrapper.Wrapper;

public class StringTextComponent
extends Wrapper {

    public StringTextComponent(Object object) {
        super(object);
    }

    public TextComponentTranslation H() {
        return new TextComponentTranslation(StringTextComponent.vapeInstance.getMappingsMapperCompat().DK.W(this.I));
    }

    public String x() {
        if (MappedClasses.DE != null && ForgeVersion.MC_26_1.d()) {
            return "";
        }
        return StringTextComponent.vapeInstance.getMappingsMapperCompat().DK.q(this.I);
    }
}

