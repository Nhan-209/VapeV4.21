package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class RayTraceContextFactory
extends Wrapper {
    public static RayTraceContextFactory v(Vec3 vec3, Vec3 vec32, ChestType jg_22, ITooltipFlag bA, Entity entity) {
        return new RayTraceContextFactory(RayTraceContextFactory.vapeInstance.getMappingsMapperCompat().DV.n(vec3.getObject(), vec32.getObject(), jg_22.getObject(), bA.getObject(), entity.getObject()));
    }

    public RayTraceContextFactory(Object object) {
        super(object);
    }
}

