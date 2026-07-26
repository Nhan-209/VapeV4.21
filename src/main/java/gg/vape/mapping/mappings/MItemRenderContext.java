package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;

public class MItemRenderContext
extends Mapping {
    private final MappingField r;
    private final MappingField X;
    private final MappingMethod Y;

    public Object G(boolean bl, Object object) {
        return this.Y.L(null, bl, object);
    }

    public Object q() {
        return this.X.getObject(null);
    }

    public MItemRenderContext() {
        super(MappedClasses.lj);
        Class[] classArray = new Class[]{Boolean.TYPE, MappedClasses.VK};
        MItemRenderContext mItemRenderContext = this;
        this.Y = this.g(classArray);
        Class clazz = MappedClasses.lj;
        boolean bl = true;
        String string = "NONE";
        MItemRenderContext mItemRenderContext2 = this;
        this.X = this.u(string, bl, clazz);
        Class clazz2 = MappedClasses.lj;
        boolean bl2 = true;
        String string2 = "DEFAULT";
        MItemRenderContext mItemRenderContext3 = this;
        this.r = this.u(string2, bl2, clazz2);
    }

    public Object N() {
        return this.r.getObject(null);
    }
}

