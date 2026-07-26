package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.Slot;

public class GuiContainerCreativeSlot
extends Slot {
    public Slot S() {
        return new Slot(GuiContainerCreativeSlot.c.getMappingsMapperCompat().Db.q(this.I));
    }

    public GuiContainerCreativeSlot(Object object) {
        super(object);
    }
}

