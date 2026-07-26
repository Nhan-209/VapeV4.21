package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MClickType;
import gg.vape.wrapper.Wrapper;

public class ClickType
extends Wrapper {
    public static ClickType[] b = new ClickType[]{ClickType.O(), ClickType.v(), ClickType.P(), ClickType.G(), ClickType.n(), ClickType.I(), ClickType.f()};

    public static ClickType I() {
        return new ClickType(MClickType.T(ClickType.c.getMappingsMapperCompat().qN));
    }

    public ClickType(Object object) {
        super(object);
    }

    public static ClickType O() {
        return new ClickType(MClickType.x(ClickType.c.getMappingsMapperCompat().qN));
    }

    public static ClickType G() {
        return new ClickType(MClickType.y(ClickType.c.getMappingsMapperCompat().qN));
    }

    public static ClickType v() {
        return new ClickType(MClickType.f(ClickType.c.getMappingsMapperCompat().qN));
    }

    public static ClickType f() {
        return new ClickType(MClickType.K(ClickType.c.getMappingsMapperCompat().qN));
    }

    public static ClickType n() {
        return new ClickType(MClickType.J(ClickType.c.getMappingsMapperCompat().qN));
    }

    public static ClickType P() {
        return new ClickType(MClickType.a(ClickType.c.getMappingsMapperCompat().qN));
    }
}

