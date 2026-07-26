package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ResourceLocationKey;

public class ResourceLocationName
extends Wrapper {
    public String b(ResourceLocationKey p6_02) {
        return ResourceLocationName.c.getMappingsMapperCompat().qt.v(this.I, p6_02.getObject());
    }

    public String n() {
        return ResourceLocationName.c.getMappingsMapperCompat().qt.L(this.I);
    }

    public ResourceLocationName(Object object) {
        super(object);
    }
}

