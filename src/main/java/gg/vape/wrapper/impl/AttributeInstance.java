package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MIAttributeInstance;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AttributeModifier;
import gg.vape.wrapper.impl.ForgeVersion;
import java.util.Collection;
import java.util.UUID;

public class AttributeInstance
extends Wrapper {
    public AttributeInstance(Object object) {
        super(object);
    }

    public AttributeModifier getModifier(UUID uUID) {
        return new AttributeModifier(MIAttributeInstance.x(AttributeInstance.c.getMappings().Rv, this.I, uUID));
    }

    public void applyModifier(AttributeModifier attributeModifier) {
        MIAttributeInstance.b(AttributeInstance.c.getMappings().Rv, this.I, attributeModifier.getObject());
    }


    public Collection I() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MIAttributeInstance.d(AttributeInstance.c.getMappings().Rv, this.I);
        }
        return MIAttributeInstance.h(AttributeInstance.c.getMappings().Rv, this.I);
    }

    public double W() {
        return MIAttributeInstance.I(AttributeInstance.c.getMappings().Rv, this.I);
    }

    public void I(double d) {
        MIAttributeInstance.W(AttributeInstance.c.getMappings().Rv, this.I, d);
    }

    public void J() {
        MIAttributeInstance.V(AttributeInstance.c.getMappings().Rv, this.I);
    }
}

