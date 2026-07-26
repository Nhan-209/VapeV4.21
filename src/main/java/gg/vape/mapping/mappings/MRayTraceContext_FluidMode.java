package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MRayTraceContext_FluidMode
extends Mapping {
    private MappingField v;
    private MappingField S;
    private MappingField E;

    private Object C() {
        return this.E.getObject(null);
    }

    public static Object D(MRayTraceContext_FluidMode mRayTraceContext_FluidMode) {
        return mRayTraceContext_FluidMode.R();
    }

    public static Object z(MRayTraceContext_FluidMode mRayTraceContext_FluidMode) {
        return mRayTraceContext_FluidMode.C();
    }

    private Object L() {
        return this.S.getObject(null);
    }

    private Object R() {
        return this.v.getObject(null);
    }

    public static Object W(MRayTraceContext_FluidMode mRayTraceContext_FluidMode) {
        return mRayTraceContext_FluidMode.L();
    }

    public MRayTraceContext_FluidMode() {
        super(MappedClasses.Dm);
        Class clazz = MappedClasses.Dm;
        boolean bl = true;
        String string = "NONE";
        MRayTraceContext_FluidMode mRayTraceContext_FluidMode = this;
        this.S = this.u(string, bl, clazz);
        Class clazz2 = MappedClasses.Dm;
        boolean bl2 = true;
        String string2 = "SOURCE_ONLY";
        MRayTraceContext_FluidMode mRayTraceContext_FluidMode2 = this;
        this.E = this.u(string2, bl2, clazz2);
        Class clazz3 = MappedClasses.Dm;
        boolean bl3 = true;
        String string3 = "ANY";
        MRayTraceContext_FluidMode mRayTraceContext_FluidMode3 = this;
        this.v = this.u(string3, bl3, clazz3);
    }
}

