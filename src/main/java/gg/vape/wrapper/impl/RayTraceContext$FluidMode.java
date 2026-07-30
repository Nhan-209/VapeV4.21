package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRayTraceContext_BlockMode;
import gg.vape.wrapper.Wrapper;

public class RayTraceContext$FluidMode
extends Wrapper {
    public static RayTraceContext$FluidMode n() {
        return new RayTraceContext$FluidMode(MRayTraceContext_BlockMode.P(RayTraceContext$FluidMode.vapeInstance.getMappingsMapperCompat().R3));
    }

    public RayTraceContext$FluidMode(Object object) {
        super(object);
    }

    public static RayTraceContext$FluidMode S() {
        return new RayTraceContext$FluidMode(MRayTraceContext_BlockMode.J(RayTraceContext$FluidMode.vapeInstance.getMappingsMapperCompat().R3));
    }

    public static RayTraceContext$FluidMode I() {
        return new RayTraceContext$FluidMode(MRayTraceContext_BlockMode.T(RayTraceContext$FluidMode.vapeInstance.getMappingsMapperCompat().R3));
    }
}

