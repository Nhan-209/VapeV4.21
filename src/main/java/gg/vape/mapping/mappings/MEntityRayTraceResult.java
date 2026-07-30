package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MEntityRayTraceResult
extends Mapping {
    MappingMethod f;
    MappingMethod l;

    public Object z(Object object, Object object2) {
        return this.l.newInstance(object, object2);
    }

    public MEntityRayTraceResult() {
        super(MappedClasses.zl);
        Class[] classArray = new Class[]{MappedClasses.zc, MappedClasses.qP};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = "<init>";
        MEntityRayTraceResult mEntityRayTraceResult = this;
        this.l = this.Y(string, bl, clazz, classArray);
        Class[] classArray2 = new Class[]{};
        Class clazz2 = MappedClasses.zc;
        boolean bl2 = true;
        String string2 = "getEntity";
        MEntityRayTraceResult mEntityRayTraceResult2 = this;
        this.f = this.Y(string2, bl2, clazz2, classArray2);
    }

    public Object w(Object object) {
        return this.f.invokeObject(object, new Object[0]);
    }
}

