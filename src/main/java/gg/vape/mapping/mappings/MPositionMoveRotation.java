package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class MPositionMoveRotation
extends Mapping {
    private static String[] V;
    private MappingField c;
    private MappingField e;
    private MappingField N;
    private MappingField T;

    public float J(Object object) {
        return this.e.getFloat(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void u(Object object, float f) {
        this.c.setFloat(object, f);
    }

    public float n(Object object) {
        return this.c.getFloat(object);
    }

    public MPositionMoveRotation() {
        this(MPositionMoveRotation.d());
    }

    private MPositionMoveRotation(String[] stringArray) {
        super(MappedClasses.Dd);
        String[] stringArray2 = stringArray;
        Class clazz = MappedClasses.qP;
        boolean bl = true;
        String string = "position";
        MPositionMoveRotation mPositionMoveRotation = this;
        this.N = this.J(string, bl, clazz);
        Class clazz2 = MappedClasses.qP;
        boolean bl2 = true;
        String string2 = "deltaMovement";
        MPositionMoveRotation mPositionMoveRotation2 = this;
        this.T = this.J(string2, bl2, clazz2);
        Class<Float> clazz3 = Float.TYPE;
        boolean bl3 = true;
        String string3 = "yRot";
        MPositionMoveRotation mPositionMoveRotation3 = this;
        this.c = this.J(string3, bl3, clazz3);
        Class<Float> clazz4 = Float.TYPE;
        boolean bl4 = true;
        String string4 = "xRot";
        MPositionMoveRotation mPositionMoveRotation4 = this;
        this.e = this.J(string4, bl4, clazz4);
    }

    public Object r(Object object) {
        return this.T.getObject(object);
    }

    public static void R(String[] stringArray) {
        V = stringArray;
    }

    public static String[] d() {
        return V;
    }

    public Object j(Object object) {
        return this.N.getObject(object);
    }

    static {
        MPositionMoveRotation.R(null);
    }

    public void d(Object object, float f) {
        this.e.setFloat(object, f);
    }
}

