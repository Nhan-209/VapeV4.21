package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.wrapper.Wrapper;

public class MRayTraceResult_Type
extends Mapping {
    public final MappingField j;
    public final MappingField e;
    public final MappingField a;

    public MRayTraceResult_Type() {
        super(MappedClasses.lk);
        Class clazz = MappedClasses.lk;
        boolean bl = Wrapper.isNativeAvailable;
        String string = "MISS";
        MRayTraceResult_Type mRayTraceResult_Type = this;
        this.a = this.registerStaticField(string, bl, clazz);
        Class clazz2 = MappedClasses.lk;
        boolean bl2 = Wrapper.isNativeAvailable;
        String string2 = "BLOCK";
        MRayTraceResult_Type mRayTraceResult_Type2 = this;
        this.j = this.registerStaticField(string2, bl2, clazz2);
        Class clazz3 = MappedClasses.lk;
        boolean bl3 = Wrapper.isNativeAvailable;
        String string3 = "ENTITY";
        MRayTraceResult_Type mRayTraceResult_Type3 = this;
        this.e = this.registerStaticField(string3, bl3, clazz3);
    }

    public static Object b(MRayTraceResult_Type mRayTraceResult_Type) {
        return mRayTraceResult_Type.getBlock();
    }

    private Object getEntity() {
        return this.e.getObject(null);
    }

    public Object c() {
        return this.a.getObject(null);
    }

    private Object getBlock() {
        return this.j.getObject(null);
    }

    public static Object G(MRayTraceResult_Type mRayTraceResult_Type) {
        return mRayTraceResult_Type.getEntity();
    }
}

