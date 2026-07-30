package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MMinecraftBridge
extends Mapping {
    private static final String BLOCK_ENTITY_FIELD_NAME = "blockEntity";
    private final MappingField blockEntityField;

    public MMinecraftBridge() {
        super(MappedClasses.up);
        this.blockEntityField = this.J(BLOCK_ENTITY_FIELD_NAME, true, MappedClasses.ZI);
    }

    public Object getBlockEntity(Object carrier) {
        return this.blockEntityField.getObject(carrier);
    }
}

