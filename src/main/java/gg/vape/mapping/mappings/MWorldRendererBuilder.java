package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MWorldRendererBuilder
extends Mapping {
    private MappingField v;
    private MappingField n;
    private MappingField S;

    public static Object W(MWorldRendererBuilder mWorldRendererBuilder) {
        return mWorldRendererBuilder.p();
    }

    public static Object E(MWorldRendererBuilder mWorldRendererBuilder) {
        return mWorldRendererBuilder.t();
    }

    public static Object X(MWorldRendererBuilder mWorldRendererBuilder) {
        return mWorldRendererBuilder.f();
    }

    public MWorldRendererBuilder() {
        super(MappedClasses.Z2);
        Class clazz = MappedClasses.Z2;
        boolean bl = true;
        String string = "LEGACY";
        MWorldRendererBuilder mWorldRendererBuilder = this;
        this.n = this.registerStaticField(string, bl, clazz);
        Class clazz2 = MappedClasses.Z2;
        boolean bl2 = true;
        String string2 = "MOJANG";
        MWorldRendererBuilder mWorldRendererBuilder2 = this;
        this.v = this.registerStaticField(string2, bl2, clazz2);
        Class clazz3 = MappedClasses.Z2;
        boolean bl3 = true;
        String string3 = "MSA";
        MWorldRendererBuilder mWorldRendererBuilder3 = this;
        this.S = this.registerStaticField(string3, bl3, clazz3);
    }

    private Object t() {
        return this.v.getObject(null);
    }

    private Object f() {
        return this.n.getObject(null);
    }

    private Object p() {
        return this.S.getObject(null);
    }
}

