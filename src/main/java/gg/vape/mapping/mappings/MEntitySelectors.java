package gg.vape.mapping.mappings;

import com.google.common.base.Predicate;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.wrapper.impl.ForgeVersion;

public class MEntitySelectors
extends Mapping {
    private final MappingMethod u;

    private Object N(Object object) {
        return this.u.invokeObject(null, object);
    }

    public MEntitySelectors() {
        super(MappedClasses.qW);
        Class[] classArray = new Class[]{MappedClasses.zc};
        Class<Predicate> clazz = Predicate.class;
        String string = "getTeamCollisionPredicate";
        MEntitySelectors mEntitySelectors = this;
        this.u = ((MappingMethodBuilder)((MappingMethodBuilder)((MappingMethodBuilder)this.methodBuilder(string, clazz, classArray).setTypeForVersion(ForgeVersion.MC_1_16_5.n(), java.util.function.Predicate.class)).setNameForVersion(ForgeVersion.MC_1_16_5.n(), "pushableBy")).setStaticMember(true)).buildMethod();
    }

    public static Object f(MEntitySelectors mEntitySelectors, Object object) {
        return mEntitySelectors.N(object);
    }
}

