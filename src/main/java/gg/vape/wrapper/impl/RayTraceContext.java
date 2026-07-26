package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRayTraceContext;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.RayTraceContext$BlockMode;
import gg.vape.wrapper.impl.RayTraceContext$FluidMode;
import gg.vape.wrapper.impl.Vec3;

public class RayTraceContext
extends Wrapper {
    public static RayTraceContext b(Vec3 vec3, Vec3 vec32, RayTraceContext$FluidMode rayTraceContext$FluidMode, RayTraceContext$BlockMode rayTraceContext$BlockMode, Entity entity) {
        Object object = MRayTraceContext.P(RayTraceContext.c.getMappingsMapperCompat().Rs, vec3.getObject(), vec32.getObject(), rayTraceContext$FluidMode.getObject(), rayTraceContext$BlockMode.getObject(), entity.getObject());
        return new RayTraceContext(object);
    }

    public RayTraceContext(Object object) {
        super(object);
    }
}

