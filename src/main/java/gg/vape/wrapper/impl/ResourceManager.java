package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import java.util.Collection;

public class ResourceManager
extends Wrapper {
    public Collection<String> U() {
        return (Collection)ResourceManager.vapeInstance.getMappingsMapperCompat().C2.e(this.I);
    }

    public ResourceManager(Object object) {
        super(object);
    }
}

