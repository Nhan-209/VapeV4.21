package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MAbstractClientPlayer;
import gg.vape.wrapper.Wrapper;

public class InputMappingsInputFactory
extends Wrapper {
    public static InputMappingsInputFactory F(int n, int n2, int n3) {
        return new InputMappingsInputFactory(MAbstractClientPlayer.W(InputMappingsInputFactory.vapeInstance.getMappingsMapperCompat().Du, n, n2, n3));
    }

    public InputMappingsInputFactory(Object object) {
        super(object);
    }
}

