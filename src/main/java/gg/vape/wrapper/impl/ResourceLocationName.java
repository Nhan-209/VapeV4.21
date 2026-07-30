package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class ResourceLocationName
extends Wrapper {
    public String b(ResourceLocationKey p6_02) {
        return ResourceLocationName.vapeInstance.getMappingsMapperCompat().qt.v(this.I, p6_02.getObject());
    }

    public String n() {
        return ResourceLocationName.vapeInstance.getMappingsMapperCompat().qt.L(this.I);
    }

    public ResourceLocationName(Object object) {
        super(object);
    }
}

