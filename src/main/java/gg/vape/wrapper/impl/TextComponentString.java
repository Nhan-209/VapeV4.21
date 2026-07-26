package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTextComponentString;
import gg.vape.wrapper.impl.ITextComponent;

public class TextComponentString
extends ITextComponent {
    public TextComponentString(Object object) {
        super(object);
    }

    public String getText() {
        return MTextComponentString.E(TextComponentString.c.getMappingsMapperCompat().Dd, this.getObject());
    }

    public static TextComponentString create(String string) {
        return new TextComponentString(MTextComponentString.q(TextComponentString.c.getMappingsMapperCompat().Dd, string));
    }

    public void setText(String string) {
        MTextComponentString.T(TextComponentString.c.getMappingsMapperCompat().Dd, this.getObject(), string);
    }
}

