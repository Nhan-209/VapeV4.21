package gg.vape.mapping;

import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMemberBuilder;

public class MappingFieldBuilder
extends MappingMemberBuilder<MappingFieldBuilder, MappingField> {
    private int r = 0;

    public MappingField z() {
        return MappingField.E(this);
    }

    @Override
    public MappingField F() {
        return this.z();
    }

    public int i() {
        return this.r;
    }

    public MappingFieldBuilder J(int n) {
        this.r = n;
        return this;
    }
}
