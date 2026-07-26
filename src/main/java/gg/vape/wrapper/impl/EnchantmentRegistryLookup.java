package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.EnchantmentHolder;
import gg.vape.wrapper.impl.ResourceKeyRegistryLookup;
import java.util.stream.Stream;

public class EnchantmentRegistryLookup
extends ResourceKeyRegistryLookup {
    public Stream<EnchantmentHolder> x() {
        return EnchantmentRegistryLookup.c.getMappingsMapperCompat().R2.a(this.getObject()).map(EnchantmentHolder::new);
    }

    public EnchantmentRegistryLookup(Object object) {
        super(object);
    }
}

