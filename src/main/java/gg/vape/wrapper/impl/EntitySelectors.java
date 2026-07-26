package gg.vape.wrapper.impl;

import com.google.common.base.Predicate;
import gg.vape.mapping.mappings.MEntitySelectors;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Entity;

public class EntitySelectors
extends Wrapper {
    public static Predicate x(Entity entity) {
        return (Predicate)MEntitySelectors.f(EntitySelectors.c.getMappings().CB, entity.getObject());
    }

    public EntitySelectors(Object object) {
        super(object);
    }

    public static java.util.function.Predicate Z(Entity entity) {
        return (java.util.function.Predicate)MEntitySelectors.f(EntitySelectors.c.getMappings().CB, entity.getObject());
    }
}

