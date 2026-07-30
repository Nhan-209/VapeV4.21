package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MFoodStats
extends Mapping {
    private static final String b = "getFoodLevel";
    private final MappingMethod n;

    private int K(Object object) {
        return this.n.invokeInt(object, new Object[0]);
    }

    public MFoodStats() {
        super(MappedClasses.Zd);
        Class[] classArray = new Class[]{};
        Class<Integer> clazz = Integer.TYPE;
        boolean bl = true;
        String string = b;
        MFoodStats mFoodStats = this;
        this.n = this.Y(string, bl, clazz, classArray);
    }

    public static int g(MFoodStats mFoodStats, Object object) {
        return mFoodStats.K(object);
    }
}

