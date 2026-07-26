package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.RegistrySimple;

public class RegistryNamespaced
extends RegistrySimple {
    public Object L(int n) {
        return RegistryNamespaced.c.getMappingsMapperCompat().qc.e(this.I, n);
    }

    public RegistryNamespaced(Object object) {
        super(object);
    }
}

