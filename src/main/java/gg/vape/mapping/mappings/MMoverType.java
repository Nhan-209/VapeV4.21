package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.wrapper.Wrapper;

public class MMoverType
extends Mapping {
    private final MappingField W;
    private final MappingField x;
    private final MappingField M;
    private final MappingField N;
    private final MappingField J;

    public Object k() {
        return this.M.getObject(null);
    }

    public MMoverType() {
        super(MappedClasses.k);
        Class clazz = MappedClasses.k;
        boolean bl = Wrapper.G;
        String string = "SELF";
        MMoverType mMoverType = this;
        this.x = this.u(string, bl, clazz);
        Class clazz2 = MappedClasses.k;
        boolean bl2 = Wrapper.G;
        String string2 = "PLAYER";
        MMoverType mMoverType2 = this;
        this.W = this.u(string2, bl2, clazz2);
        Class clazz3 = MappedClasses.k;
        boolean bl3 = Wrapper.G;
        String string3 = "PISTON";
        MMoverType mMoverType3 = this;
        this.N = this.u(string3, bl3, clazz3);
        Class clazz4 = MappedClasses.k;
        boolean bl4 = Wrapper.G;
        String string4 = "SHULKER_BOX";
        MMoverType mMoverType4 = this;
        this.J = this.u(string4, bl4, clazz4);
        Class clazz5 = MappedClasses.k;
        boolean bl5 = Wrapper.G;
        String string5 = "SHULKER";
        MMoverType mMoverType5 = this;
        this.M = this.u(string5, bl5, clazz5);
    }

    public Object x() {
        return this.N.getObject(null);
    }

    public Object W() {
        return this.x.getObject(null);
    }

    public Object h() {
        return this.W.getObject(null);
    }

    public Object q() {
        return this.J.getObject(null);
    }
}

