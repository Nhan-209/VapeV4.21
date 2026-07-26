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
        return this.u.L(null, object);
    }

    public MEntitySelectors() {
        super(MappedClasses.qW);
        Class[] classArray = new Class[]{MappedClasses.zc};
        Class<Predicate> clazz = Predicate.class;
        String string = "getTeamCollisionPredicate";
        MEntitySelectors mEntitySelectors = this;
        this.u = ((MappingMethodBuilder)((MappingMethodBuilder)((MappingMethodBuilder)this.u(string, clazz, classArray).X(ForgeVersion.MC_1_16_5.n(), java.util.function.Predicate.class)).A(ForgeVersion.MC_1_16_5.n(), "pushableBy")).H(true)).s();
    }

    public static Object f(MEntitySelectors mEntitySelectors, Object object) {
        return mEntitySelectors.N(object);
    }
}

