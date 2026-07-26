package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MArmorMaterial;
import gg.vape.wrapper.Wrapper;

public class ArmorMaterial
extends Wrapper {
    public static ArmorMaterial v() {
        return new ArmorMaterial(MArmorMaterial.e(ArmorMaterial.c.getMappingsMapperCompat().e));
    }

    public static ArmorMaterial E() {
        return new ArmorMaterial(MArmorMaterial.L(ArmorMaterial.c.getMappingsMapperCompat().e));
    }

    public static ArmorMaterial c() {
        return new ArmorMaterial(MArmorMaterial.u(ArmorMaterial.c.getMappingsMapperCompat().e));
    }

    public static ArmorMaterial i() {
        return new ArmorMaterial(MArmorMaterial.v(ArmorMaterial.c.getMappingsMapperCompat().e));
    }

    public ArmorMaterial(Object object) {
        super(object);
    }

    public static ArmorMaterial r() {
        return new ArmorMaterial(MArmorMaterial.O(ArmorMaterial.c.getMappingsMapperCompat().e));
    }
}

