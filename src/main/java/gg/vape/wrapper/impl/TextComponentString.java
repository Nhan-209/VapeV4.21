package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTextComponentString;

public class TextComponentString
extends ITextComponent {
    public TextComponentString(Object object) {
        super(object);
    }

    public String getText() {
        return MTextComponentString.E(TextComponentString.vapeInstance.getMappingsMapperCompat().Dd, this.getObject());
    }

    public static TextComponentString create(String string) {
        return new TextComponentString(MTextComponentString.q(TextComponentString.vapeInstance.getMappingsMapperCompat().Dd, string));
    }

    public void setText(String string) {
        MTextComponentString.T(TextComponentString.vapeInstance.getMappingsMapperCompat().Dd, this.getObject(), string);
    }
}

