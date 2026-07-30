package gg.vape.wrapper.impl;

import java.util.stream.Stream;

public class EnchantmentRegistryLookup
extends ResourceKeyRegistryLookup {
    public Stream<EnchantmentHolder> x() {
        return EnchantmentRegistryLookup.vapeInstance.getMappingsMapperCompat().R2.a(this.getObject()).map(EnchantmentHolder::new);
    }

    public EnchantmentRegistryLookup(Object object) {
        super(object);
    }
}

