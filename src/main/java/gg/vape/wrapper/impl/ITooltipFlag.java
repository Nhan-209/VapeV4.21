package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class ITooltipFlag
extends Wrapper {
    public static ITooltipFlag n() {
        return new ITooltipFlag(ITooltipFlag.vapeInstance.getMappingsMapperCompat().qh.s());
    }

    public ITooltipFlag(Object object) {
        super(object);
    }
}

