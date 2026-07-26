package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MEnumActionResult
extends Mapping {
    public final MappingField B;
    public final MappingField i;
    public final MappingField m;

    private Object getPass() {
        return this.B.getObject(null);
    }

    public static Object N(MEnumActionResult mEnumActionResult) {
        return mEnumActionResult.getPass();
    }

    public MEnumActionResult() {
        super(MappedClasses.zr);
        Class clazz = MappedClasses.zr;
        boolean bl = true;
        String string = "SUCCESS";
        MEnumActionResult mEnumActionResult = this;
        this.i = this.u(string, bl, clazz);
        Class clazz2 = MappedClasses.zr;
        boolean bl2 = true;
        String string2 = "PASS";
        MEnumActionResult mEnumActionResult2 = this;
        this.B = this.u(string2, bl2, clazz2);
        Class clazz3 = MappedClasses.zr;
        boolean bl3 = true;
        String string3 = "FAIL";
        MEnumActionResult mEnumActionResult3 = this;
        this.m = this.u(string3, bl3, clazz3);
    }

    private Object getFail() {
        return this.m.getObject(null);
    }

    public static Object H(MEnumActionResult mEnumActionResult) {
        return mEnumActionResult.getFail();
    }

    public static Object d(MEnumActionResult mEnumActionResult) {
        return mEnumActionResult.getSuccess();
    }

    private Object getSuccess() {
        return this.i.getObject(null);
    }
}

