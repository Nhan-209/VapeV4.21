package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MPlayerInput
extends Mapping {
    private MappingField p;
    private MappingField g;

    public static float e(MPlayerInput mPlayerInput, Object object) {
        return mPlayerInput.a(object);
    }

    private float a(Object object) {
        return this.p.getFloat(object);
    }

    public MPlayerInput() {
        super(MappedClasses.YG);
        Class<Float> clazz = Float.TYPE;
        boolean bl = true;
        String string = "x";
        MPlayerInput mPlayerInput = this;
        this.g = this.J(string, bl, clazz);
        Class<Float> clazz2 = Float.TYPE;
        boolean bl2 = true;
        String string2 = "y";
        MPlayerInput mPlayerInput2 = this;
        this.p = this.J(string2, bl2, clazz2);
    }

    private void y(Object object, float f) {
        this.g.setFloat(object, f);
    }

    private void f(Object object, float f) {
        this.p.setFloat(object, f);
    }

    public static float B(MPlayerInput mPlayerInput, Object object) {
        return mPlayerInput.s(object);
    }

    private float s(Object object) {
        return this.g.getFloat(object);
    }

    public static void s(MPlayerInput mPlayerInput, Object object, float f) {
        mPlayerInput.y(object, f);
    }

    public static void K(MPlayerInput mPlayerInput, Object object, float f) {
        mPlayerInput.f(object, f);
    }
}

