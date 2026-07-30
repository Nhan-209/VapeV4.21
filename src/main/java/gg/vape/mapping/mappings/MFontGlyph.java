package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MFontGlyph
extends Mapping {
    private static final String b = "info";
    private MappingMethod E;

    public MFontGlyph() {
        super(MappedClasses.qd);
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.g;
        boolean bl = true;
        String string = b;
        MFontGlyph mFontGlyph = this;
        this.E = this.Y(string, bl, clazz, classArray);
    }

    public Object M(Object object) {
        return this.E.invokeObject(object, new Object[0]);
    }
}

