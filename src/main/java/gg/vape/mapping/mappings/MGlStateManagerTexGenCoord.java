package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MGlStateManagerTexGenCoord
extends Mapping {
    private final MappingField M;
    private final MappingField J;
    private final MappingField q;

    public Object u() {
        return this.M.getObject(null);
    }

    public MGlStateManagerTexGenCoord() {
        super(MappedClasses.Zi);
        Class clazz = MappedClasses.Zi;
        boolean bl = true;
        String string = "NONE";
        MGlStateManagerTexGenCoord mGlStateManagerTexGenCoord = this;
        this.J = this.u(string, bl, clazz);
        Class clazz2 = MappedClasses.Zi;
        boolean bl2 = true;
        String string2 = "CLIENT";
        MGlStateManagerTexGenCoord mGlStateManagerTexGenCoord2 = this;
        this.M = this.u(string2, bl2, clazz2);
        Class clazz3 = MappedClasses.Zi;
        boolean bl3 = true;
        String string3 = "SERVER";
        MGlStateManagerTexGenCoord mGlStateManagerTexGenCoord3 = this;
        this.q = this.u(string3, bl3, clazz3);
    }

    public Object m() {
        return this.q.getObject(null);
    }

    public Object z() {
        return this.J.getObject(null);
    }
}

