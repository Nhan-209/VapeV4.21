package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MRayTraceContext
extends Mapping {
    private static final String b = "<init>";
    private MappingMethod L;

    public static Object P(MRayTraceContext mRayTraceContext, Object object, Object object2, Object object3, Object object4, Object object5) {
        return mRayTraceContext.r(object, object2, object3, object4, object5);
    }

    public MRayTraceContext() {
        super(MappedClasses.Fc);
        Class[] classArray = new Class[]{MappedClasses.qP, MappedClasses.qP, MappedClasses.DS, MappedClasses.Dm, MappedClasses.zc};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = b;
        MRayTraceContext mRayTraceContext = this;
        this.L = this.Y(string, bl, clazz, classArray);
    }

    private Object r(Object object, Object object2, Object object3, Object object4, Object object5) {
        return this.L.newInstance(object, object2, object3, object4, object5);
    }
}

