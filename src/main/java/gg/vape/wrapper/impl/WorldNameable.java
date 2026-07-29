package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ITextComponent;

public class WorldNameable
extends Wrapper {
    public ITextComponent getDisplayName() {
        return new ITextComponent(WorldNameable.c.getMappingsMapperCompat().Dm.A(this.I));
    }

    public boolean hasCustomName() {
        return WorldNameable.c.getMappingsMapperCompat().Dm.V(this.I);
    }

    public String getName() {
        if (ForgeVersion.MC_1_16_5.d()) {
            ITextComponent iTextComponent = new ITextComponent(WorldNameable.c.getMappingsMapperCompat().Dm.s(this.I));
            return iTextComponent.getFormattedText();
        }
        return WorldNameable.c.getMappingsMapperCompat().Dm.L(this.I);
    }

    public WorldNameable(Object object) {
        super(object);
    }
}
