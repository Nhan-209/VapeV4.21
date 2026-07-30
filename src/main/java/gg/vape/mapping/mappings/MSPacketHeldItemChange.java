package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MSPacketHeldItemChange
extends Mapping {
    private static final String CONSTRUCTOR_METHOD_NAME = "<init>";
    private final MappingMethod constructorMethod;

    public MSPacketHeldItemChange() {
        super(MappedClasses.l7);
        this.constructorMethod = this.Y(CONSTRUCTOR_METHOD_NAME, false, Void.TYPE, new Class[]{Integer.TYPE});
    }

    public Object newInstance(int slot) {
        return this.constructorMethod.newInstance(slot);
    }
}

