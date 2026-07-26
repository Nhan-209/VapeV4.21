package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.Vec3;

public class EntityRayTraceResult
extends RayTraceResult {
    public Entity L() {
        return new Entity(EntityRayTraceResult.c.getMappingsMapperCompat().qS.w(this.I));
    }

    public EntityRayTraceResult(Object object) {
        super(object);
    }

    public static EntityRayTraceResult x(Entity entity, Vec3 vec3) {
        return new EntityRayTraceResult(EntityRayTraceResult.c.getMappingsMapperCompat().qS.z(entity.getObject(), vec3.getObject()));
    }
}

