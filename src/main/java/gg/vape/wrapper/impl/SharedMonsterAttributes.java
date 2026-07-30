package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class SharedMonsterAttributes
extends Wrapper {
    public SharedMonsterAttributes(Object object) {
        super(object);
    }

    public static SharedMonsterAttributes V() {
        return new SharedMonsterAttributes(SharedMonsterAttributes.vapeInstance.getMappingsMapperCompat().h3.K());
    }

    public static SharedMonsterAttributes c() {
        return new SharedMonsterAttributes(SharedMonsterAttributes.vapeInstance.getMappingsMapperCompat().h3.m());
    }
}

