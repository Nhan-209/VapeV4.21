package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MAttributeModifier;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ResourceLocation;
import java.util.UUID;

public class AttributeModifier
extends Wrapper {
    public ResourceLocation a() {
        return new ResourceLocation(MAttributeModifier.T(AttributeModifier.c.getMappings().hv, this.I));
    }

    public AttributeModifier(Object object) {
        super(object);
    }

    public double getAmount() {
        return AttributeModifier.c.getMappings().hv.getAmount(this.I);
    }

    public UUID getId() {
        return MAttributeModifier.getID(AttributeModifier.c.getMappings().hv, this.I);
    }
}

