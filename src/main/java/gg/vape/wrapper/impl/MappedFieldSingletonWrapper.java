package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.mapping.mappings.MMappedFieldSingletonWrapper;
import gg.vape.wrapper.Wrapper;

public class MappedFieldSingletonWrapper
extends Wrapper {
    private static MappedFieldSingletonWrapper A;

    public MappedFieldSingletonWrapper(Object object) {
        super(object);
    }


    public static MappedFieldSingletonWrapper T() {
        if (A == null) {
            A = new MappedFieldSingletonWrapper(MMappedFieldSingletonWrapper.W(Vape.INSTANCE.getMappings().CL).getObject(null));
        }
        return A;
    }
}

