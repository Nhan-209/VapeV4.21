package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEnumActionResult;
import gg.vape.wrapper.Wrapper;

public class EnumActionResult
extends Wrapper {
    public EnumActionResult(Object object) {
        super(object);
    }

    public static EnumActionResult C() {
        return new EnumActionResult(MEnumActionResult.N(EnumActionResult.c.getMappingsMapperCompat().RD));
    }

    public static EnumActionResult B() {
        return new EnumActionResult(MEnumActionResult.H(EnumActionResult.c.getMappingsMapperCompat().RD));
    }

    public static EnumActionResult A() {
        return new EnumActionResult(MEnumActionResult.d(EnumActionResult.c.getMappingsMapperCompat().RD));
    }
}

