package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEntityRayTraceBridge;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.RayTraceResult;
import java.util.function.Predicate;

public class EntityRayTraceBridge
extends Wrapper {
    public EntityRayTraceBridge(Object object) {
        super(object);
    }

    public RayTraceResult V(Entity entity, float f, Predicate<Object> predicate) {
        return new RayTraceResult(MEntityRayTraceBridge.A(EntityRayTraceBridge.c.getMappingsMapperCompat().u, this.I, entity.getObject(), f, predicate));
    }
}

