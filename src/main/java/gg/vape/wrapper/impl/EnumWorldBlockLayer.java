package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEnumWorldBlockLayer;
import gg.vape.wrapper.Wrapper;

public class EnumWorldBlockLayer
extends Wrapper {
    private static EnumWorldBlockLayer s;
    private static EnumWorldBlockLayer z;

    public static EnumWorldBlockLayer v() {
        if (s == null) {
            s = new EnumWorldBlockLayer(MEnumWorldBlockLayer.U(EnumWorldBlockLayer.vapeInstance.getMappingsMapperCompat().hA));
        }
        return s;
    }


    public EnumWorldBlockLayer(Object object) {
        super(object);
    }

    public static EnumWorldBlockLayer W() {
        if (z == null) {
            z = new EnumWorldBlockLayer(MEnumWorldBlockLayer.g(EnumWorldBlockLayer.vapeInstance.getMappingsMapperCompat().hA));
        }
        return z;
    }
}

