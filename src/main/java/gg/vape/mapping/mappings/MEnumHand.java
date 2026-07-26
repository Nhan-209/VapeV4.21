package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.wrapper.Wrapper;

public class MEnumHand
extends Mapping {
    private final MappingField w;
    private final MappingField g;

    public MEnumHand() {
        super(MappedClasses.Yf);
        Class clazz = MappedClasses.Yf;
        boolean bl = Wrapper.G;
        String string = "MAIN_HAND";
        MEnumHand mEnumHand = this;
        this.g = this.u(string, bl, clazz);
        Class clazz2 = MappedClasses.Yf;
        boolean bl2 = Wrapper.G;
        String string2 = "OFF_HAND";
        MEnumHand mEnumHand2 = this;
        this.w = this.u(string2, bl2, clazz2);
    }

    public Object c() {
        return this.w.getObject(null);
    }

    public Object L() {
        return this.g.getObject(null);
    }
}

