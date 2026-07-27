package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class Material
extends Wrapper {
    private static Material a;
    private static Material Y;
    private static Material h;

    public boolean u() {
        return Material.c.getMappingsMapperCompat().Cn.M(this.I);
    }


    public static Material w() {
        if (h == null) {
            h = new Material(Material.c.getMappingsMapperCompat().Cn.A());
        }
        return h;
    }

    public boolean s() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return false;
        }
        return Material.c.getMappingsMapperCompat().Cn.b(this.I);
    }

    public static Material k() {
        if (a == null) {
            a = new Material(Material.c.getMappingsMapperCompat().Cn.q());
        }
        return a;
    }

    public static Material f() {
        if (Y == null) {
            Y = new Material(Material.c.getMappingsMapperCompat().Cn.m());
        }
        return Y;
    }

    public static Material a() {
        return new Material(Material.c.getMappingsMapperCompat().Cn.H());
    }

    public boolean e() {
        return Material.c.getMappingsMapperCompat().Cn.z(this.I);
    }

    public Material(Object object) {
        super(object);
    }

    public boolean H() {
        return Material.c.getMappingsMapperCompat().Cn.O(this.I);
    }

    public boolean S() {
        return Material.c.getMappingsMapperCompat().Cn.A(this.I);
    }
}

