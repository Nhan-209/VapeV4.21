package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRayTraceResult_Type;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;

public class RayTraceResult_type
extends Wrapper {
    private static RayTraceResult_type f;
    private static RayTraceResult_type r;
    private static RayTraceResult_type g;

    public static RayTraceResult_type entity() {
        if (f == null) {
            f = new RayTraceResult_type(MRayTraceResult_Type.G(RayTraceResult_type.c.getMappingsMapperCompat().q1));
        }
        return f;
    }

    public static RayTraceResult_type block() {
        if (r == null) {
            r = new RayTraceResult_type(MRayTraceResult_Type.b(RayTraceResult_type.c.getMappingsMapperCompat().q1));
        }
        return r;
    }

    public RayTraceResult_type(Object object) {
        super(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static RayTraceResult_type miss() {
        if (g == null) {
            g = new RayTraceResult_type(RayTraceResult_type.c.getMappingsMapperCompat().q1.c());
        }
        return g;
    }
}

