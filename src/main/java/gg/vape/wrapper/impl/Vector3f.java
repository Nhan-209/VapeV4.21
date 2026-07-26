package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MVector3f;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Quaternion;

public class Vector3f
extends Wrapper {
    public float R() {
        return Vector3f.c.getMappingsMapperCompat().qM.e(this.I);
    }

    public static Vector3f Z(float f, float f2, float f3) {
        return new Vector3f(MVector3f.u(Vector3f.c.getMappingsMapperCompat().qM, f, f2, f3));
    }

    public Quaternion I(float f) {
        if (ForgeVersion.MC_1_20_6.d()) {
            return Quaternion.A(this, f, true);
        }
        return new Quaternion(Vector3f.c.getMappingsMapperCompat().qM.O(this.I, f));
    }

    public float V() {
        return Vector3f.c.getMappingsMapperCompat().qM.X(this.I);
    }

    public Vector3f(Object object) {
        super(object);
    }

    public float D() {
        return Vector3f.c.getMappingsMapperCompat().qM.L(this.I);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }
}

