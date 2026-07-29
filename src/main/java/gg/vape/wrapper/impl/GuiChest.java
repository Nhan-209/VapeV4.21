package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MGuiChest;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiContainer;
import gg.vape.wrapper.impl.Inventory;

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
            Object object = GuiChest.c.getMappingsMapperCompat().hK.t(this.I);
            return new Inventory(MGuiChest.F(GuiChest.c.getMappingsMapperCompat().Ch, object));
        }
        return new Inventory(MGuiChest.F(GuiChest.c.getMappingsMapperCompat().Ch, this.I));
    }

}

