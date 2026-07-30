package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MSlot;
import gg.vape.wrapper.Wrapper;

public class Slot
extends Wrapper {
    public Slot(Object object) {
        super(object);
    }


    public int o() {
        return MSlot.Y(Slot.vapeInstance.getMappingsMapperCompat().qY, this.getObject());
    }

    public Inventory Z() {
        return new Inventory(MSlot.n(Slot.vapeInstance.getMappingsMapperCompat().qY, this.getObject()));
    }

    public int g() {
        return MSlot.d(Slot.vapeInstance.getMappingsMapperCompat().qY, this.getObject());
    }

    public ItemStack I() {
        return new ItemStack(Slot.vapeInstance.getMappingsMapperCompat().qY.I(this.getObject()));
    }

    public boolean v() {
        if (ForgeVersion.MC_26_1.d()) {
            boolean bl2 = !this.I().isNull();
            return bl2;
        }
        boolean bl = Slot.vapeInstance.getMappingsMapperCompat().qY.I(this.getObject()) != null;
        return bl;
    }
}

