package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MInputMappingsBridge;
import gg.vape.wrapper.Wrapper;

public class InputMappingsBridge
extends Wrapper {
    public InputMappingsBridge(Object object) {
        super(object);
    }

    public static InputMappingsInput x(int n, int n2) {
        if (ForgeVersion.MC_1_21_10.d()) {
            InputMappingsInputFactory wg_22 = InputMappingsInputFactory.F(n, n2, 0);
            return new InputMappingsInput(MInputMappingsBridge.a(InputMappingsBridge.vapeInstance.getMappingsMapperCompat().CE, wg_22.getObject()));
        }
        return new InputMappingsInput(MInputMappingsBridge.e(InputMappingsBridge.vapeInstance.getMappingsMapperCompat().CE, n, n2));
    }
}

