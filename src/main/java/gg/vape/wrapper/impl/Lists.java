package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import java.util.ArrayList;
import java.util.List;

public class Lists
extends Wrapper {
    public Lists(Object object) {
        super(object);
    }

    public static ArrayList u(Iterable iterable) {
        return Lists.c.getMappingsMapperCompat().hY.V(iterable);
    }

    public static List k(List list) {
        return Lists.c.getMappingsMapperCompat().hY.c(list);
    }
}

