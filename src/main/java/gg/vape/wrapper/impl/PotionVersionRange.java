package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MPotionVersionRange;
import gg.vape.wrapper.Wrapper;
import java.util.Set;

public class PotionVersionRange
extends Wrapper {
    public Set i() {
        return (Set)MPotionVersionRange.F(PotionVersionRange.vapeInstance.getMappingsMapperCompat().Cu, this.I);
    }

    public static PotionVersionRange W() {
        return new PotionVersionRange(MPotionVersionRange.h(PotionVersionRange.vapeInstance.getMappingsMapperCompat().Cu));
    }

    public PotionVersionRange(Object object) {
        super(object);
    }
}

