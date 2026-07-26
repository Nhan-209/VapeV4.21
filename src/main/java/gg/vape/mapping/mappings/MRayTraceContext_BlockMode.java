package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MRayTraceContext_BlockMode
extends Mapping {
    private MappingField w;
    private MappingField A;
    private MappingField Y;

    public static Object P(MRayTraceContext_BlockMode mRayTraceContext_BlockMode) {
        return mRayTraceContext_BlockMode.t();
    }

    private Object s() {
        return this.Y.getObject(null);
    }

    public static Object T(MRayTraceContext_BlockMode mRayTraceContext_BlockMode) {
        return mRayTraceContext_BlockMode.s();
    }

    public MRayTraceContext_BlockMode() {
        super(MappedClasses.DS);
        Class clazz = MappedClasses.DS;
        boolean bl = true;
        String string = "COLLIDER";
        MRayTraceContext_BlockMode mRayTraceContext_BlockMode = this;
        this.Y = this.u(string, bl, clazz);
        Class clazz2 = MappedClasses.DS;
        boolean bl2 = true;
        String string2 = "OUTLINE";
        MRayTraceContext_BlockMode mRayTraceContext_BlockMode2 = this;
        this.A = this.u(string2, bl2, clazz2);
        Class clazz3 = MappedClasses.DS;
        boolean bl3 = true;
        String string3 = "VISUAL";
        MRayTraceContext_BlockMode mRayTraceContext_BlockMode3 = this;
        this.w = this.u(string3, bl3, clazz3);
    }

    private Object j() {
        return this.A.getObject(null);
    }

    private Object t() {
        return this.w.getObject(null);
    }

    public static Object J(MRayTraceContext_BlockMode mRayTraceContext_BlockMode) {
        return mRayTraceContext_BlockMode.j();
    }
}

