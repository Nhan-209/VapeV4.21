package gg.vape.wrapper.impl;

public class EntityRayTraceResult
extends RayTraceResult {
    public Entity L() {
        return new Entity(EntityRayTraceResult.vapeInstance.getMappingsMapperCompat().qS.w(this.I));
    }

    public EntityRayTraceResult(Object object) {
        super(object);
    }

    public static EntityRayTraceResult x(Entity entity, Vec3 vec3) {
        return new EntityRayTraceResult(EntityRayTraceResult.vapeInstance.getMappingsMapperCompat().qS.z(entity.getObject(), vec3.getObject()));
    }
}

