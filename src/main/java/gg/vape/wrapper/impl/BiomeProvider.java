package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MBiomeProviderBridge;
import gg.vape.wrapper.Wrapper;

public class BiomeProvider
extends Wrapper {
    public Iterable f() {
        return MBiomeProviderBridge.D(BiomeProvider.vapeInstance.getMappingsMapperCompat().K, this.I);
    }

    public BiomeProvider(Object object) {
        super(object);
    }
}

