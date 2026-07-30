package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MGuiChest;

public class GuiChest
extends GuiContainer {
    public String z() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.F().getFormattedText();
        }
        return this.getLowerChestInventory().getName();
    }

    public GuiChest(Object object) {
        super(object);
    }

    public Inventory getLowerChestInventory() {
        if (ForgeVersion.MC_1_16_5.d()) {
            Object object = GuiChest.vapeInstance.getMappingsMapperCompat().hK.t(this.I);
            return new Inventory(MGuiChest.F(GuiChest.vapeInstance.getMappingsMapperCompat().Ch, object));
        }
        return new Inventory(MGuiChest.F(GuiChest.vapeInstance.getMappingsMapperCompat().Ch, this.I));
    }

}

