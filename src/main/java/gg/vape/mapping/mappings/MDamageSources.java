package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MDamageSources
extends Mapping {
    private final MappingMethod z;
    private static int o;
    private final MappingMethod S;
    private MappingMethod L;

    public MDamageSources() {
        super(MappedClasses.ZZ);
        Class[] classArray = new Class[]{MappedClasses.Yl};
        Class clazz = MappedClasses.uB;
        boolean bl = true;
        String string = "playerAttack";
        MDamageSources mDamageSources = this;
        this.L = this.Y(string, bl, clazz, classArray);
        Class[] classArray2 = new Class[]{};
        Class clazz2 = MappedClasses.uB;
        boolean bl2 = true;
        String string2 = "fall";
        MDamageSources mDamageSources2 = this;
        this.S = this.Y(string2, bl2, clazz2, classArray2);
        int n = MDamageSources.e();
        Class[] classArray3 = new Class[]{MappedClasses.zc, MappedClasses.zc};
        Class clazz3 = MappedClasses.uB;
        boolean bl3 = true;
        String string3 = "explosion";
        MDamageSources mDamageSources3 = this;
        this.z = this.Y(string3, bl3, clazz3, classArray3);
    }

    public Object L(Object object) {
        return this.S.invokeObject(object, new Object[0]);
    }

    public Object J(Object object, Object object2) {
        return this.L.invokeObject(object, object2);
    }

    public static int e() {
        return o;
    }


    public static void c(int n) {
        o = n;
    }

    static {
        MDamageSources.c(0);
    }

    public static int n() {
        int n = MDamageSources.e();
        return 112;
    }

    public Object B(Object object) {
        return this.z.invokeObject(object, null, null);
    }
}

