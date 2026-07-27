package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Vector3f;

public class Quaternion
extends Wrapper {
    public static Quaternion A(Vector3f j__02, float f, boolean bl) {
        if (bl) {
            f *= (float)Math.PI / 180;
        }
        float f2 = (float)Math.sin(f / 2.0f);
        float f3 = j__02.V() * f2;
        float f4 = j__02.D() * f2;
        float f5 = j__02.R() * f2;
        float f6 = (float)Math.cos(f / 2.0f);
        return new Quaternion(Quaternion.c.getMappingsMapperCompat().qf.N(f3, f4, f5, f6));
    }

    public float P() {
        return Quaternion.c.getMappingsMapperCompat().qf.O(this.I);
    }


    public static Quaternion K(float f, float f2, float f3, boolean bl) {
        if (ForgeVersion.MC_1_20_6.d()) {
            if (bl) {
                f *= (float)Math.PI / 180;
                f2 *= (float)Math.PI / 180;
                f3 *= (float)Math.PI / 180;
            }
            float f4 = (float)Math.sin(0.5f * f);
            float f5 = (float)Math.cos(0.5f * f);
            float f6 = (float)Math.sin(0.5f * f2);
            float f7 = (float)Math.cos(0.5f * f2);
            float f8 = (float)Math.sin(0.5f * f3);
            float f9 = (float)Math.cos(0.5f * f3);
            float f10 = f4 * f7 * f9 + f5 * f6 * f8;
            float f11 = f5 * f6 * f9 - f4 * f7 * f8;
            float f12 = f4 * f6 * f9 + f5 * f7 * f8;
            float f13 = f5 * f7 * f9 - f4 * f6 * f8;
            return new Quaternion(Quaternion.c.getMappingsMapperCompat().qf.N(f10, f11, f12, f13));
        }
        return new Quaternion(Quaternion.c.getMappingsMapperCompat().qf.v(f, f2, f3, bl));
    }

    public float Q() {
        return Quaternion.c.getMappingsMapperCompat().qf.V(this.I);
    }

    public float q() {
        return Quaternion.c.getMappingsMapperCompat().qf.h(this.I);
    }

    public Quaternion(Object object) {
        super(object);
    }

    public float N() {
        return Quaternion.c.getMappingsMapperCompat().qf.v(this.I);
    }
}

