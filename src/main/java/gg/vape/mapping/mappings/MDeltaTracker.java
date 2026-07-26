package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MDeltaTracker
extends Mapping {
    private MappingMethod Q;
    private MappingMethod e;

    public MDeltaTracker() {
        super(MappedClasses.uy);
        Class[] classArray = new Class[]{};
        Class<Float> clazz = Float.TYPE;
        boolean bl = true;
        String string = "getGameTimeDeltaTicks";
        MDeltaTracker mDeltaTracker = this;
        this.e = this.Y(string, bl, clazz, classArray);
        Class[] classArray2 = new Class[]{Boolean.TYPE};
        Class<Float> clazz2 = Float.TYPE;
        boolean bl2 = true;
        String string2 = "getGameTimeDeltaPartialTick";
        MDeltaTracker mDeltaTracker2 = this;
        this.Q = this.Y(string2, bl2, clazz2, classArray2);
    }

    public static float K(MDeltaTracker mDeltaTracker, Object object) {
        return mDeltaTracker.p(object);
    }

    private float U(Object object, boolean bl) {
        return this.Q.s(object, bl);
    }

    public static float d(MDeltaTracker mDeltaTracker, Object object, boolean bl) {
        return mDeltaTracker.U(object, bl);
    }

    private float p(Object object) {
        return this.e.s(object, new Object[0]);
    }
}

