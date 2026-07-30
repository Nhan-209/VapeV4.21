package gg.vape.wrapper.impl;

public class GuiContainerCreativeSlot
extends Slot {
    public Slot S() {
        return new Slot(GuiContainerCreativeSlot.vapeInstance.getMappingsMapperCompat().Db.q(this.I));
    }

    public GuiContainerCreativeSlot(Object object) {
        super(object);
    }
}

