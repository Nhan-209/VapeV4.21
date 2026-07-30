package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MScoreboard;
import gg.vape.wrapper.impl.ForgeVersion;

public class MScoreObjective
extends Mapping {
    private MappingMethod v;
    private MappingMethod b;
    private MappingMethod K;


    public static Object B(MScoreObjective mScoreObjective, Object object) {
        return mScoreObjective.c(object);
    }

    public MScoreObjective() {
        this(MScoreboard.X());
    }

    private MScoreObjective(int[] nArray) {
        super(MappedClasses.Y);
        if (nArray != null) {
            Class[] classArray = new Class[]{};
            Class clazz = MappedClasses.F6;
            boolean bl = true;
            String string = "getScoreboard";
            MScoreObjective mScoreObjective = this;
            this.b = mScoreObjective.Y(string, bl, clazz, classArray);
            if (ForgeVersion.MC_1_16_5.d()) {
                Class[] classArray2 = new Class[]{};
                Class clazz2 = MappedClasses.Yr;
                boolean bl2 = true;
                String string2 = "getDisplayName";
                MScoreObjective mScoreObjective2 = this;
                this.v = this.Y(string2, bl2, clazz2, classArray2);
                Class[] classArray3 = new Class[]{};
                Class clazz3 = MappedClasses.Yr;
                boolean bl3 = true;
                String string3 = "func_237498_g_";
                MScoreObjective mScoreObjective3 = this;
                this.K = this.Y(string3, bl3, clazz3, classArray3);
            } else {
                Class[] classArray4 = new Class[]{};
                Class<String> clazz4 = String.class;
                boolean bl4 = true;
                String string4 = "getDisplayName";
                MScoreObjective mScoreObjective4 = this;
                this.v = this.Y(string4, bl4, clazz4, classArray4);
            }
            return;
        }
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.F6;
        boolean bl = true;
        String string = "getScoreboard";
        MScoreObjective mScoreObjective = this;
        this.v = mScoreObjective.Y(string, bl, clazz, classArray);
    }

    private Object p(Object object) {
        return this.v.invokeObject(object, new Object[0]);
    }

    private Object c(Object object) {
        return this.K.invokeObject(object, new Object[0]);
    }

    public static String O(MScoreObjective mScoreObjective, Object object) {
        return mScoreObjective.V(object);
    }

    private String V(Object object) {
        return (String)this.v.invokeObject(object, new Object[0]);
    }

    public static Object g(MScoreObjective mScoreObjective, Object object) {
        return mScoreObjective.p(object);
    }

    private Object E(Object object) {
        return this.b.invokeObject(object, new Object[0]);
    }

    public static Object r(MScoreObjective mScoreObjective, Object object) {
        return mScoreObjective.E(object);
    }
}

