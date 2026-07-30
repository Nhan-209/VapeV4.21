package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MFluidState
extends Mapping {
    private static final String GET_HAS_NO_SKY_METHOD_NAME = "getHasNoSky";
    private final MappingMethod getHasNoSkyMethod;

    public MFluidState() {
        super(MappedClasses.DJ);
        this.getHasNoSkyMethod = this.Y(GET_HAS_NO_SKY_METHOD_NAME, true, Boolean.TYPE, new Class[]{});
    }

    public boolean hasNoSky(Object fluidState) {
        return this.getHasNoSkyMethod.invokeBoolean(fluidState, new Object[0]);
    }
}

