package gg.vape.wrapper.impl;

import com.google.common.base.Predicate;
import gg.vape.mapping.mappings.MEntitySelectors;
import gg.vape.wrapper.Wrapper;

public class EntitySelectors
extends Wrapper {
    public static Predicate x(Entity entity) {
        return (Predicate)MEntitySelectors.f(EntitySelectors.vapeInstance.getMappings().CB, entity.getObject());
    }

    public EntitySelectors(Object object) {
        super(object);
    }

    public static java.util.function.Predicate Z(Entity entity) {
        return (java.util.function.Predicate)MEntitySelectors.f(EntitySelectors.vapeInstance.getMappings().CB, entity.getObject());
    }
}

