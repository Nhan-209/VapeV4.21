package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MGuiContainer;
import gg.vape.wrapper.impl.Container;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.ResourceLocation;
import gg.vape.wrapper.impl.Slot;

public class GuiContainer
extends GuiScreen {
    public static ResourceLocation m$src$Lgg_vape_wrapper_impl_ResourceLocation_$1fc62cj() {
        return new ResourceLocation(GuiContainer.c.getMappings().hK.v());
    }

    public Slot P() {
        return new Slot(MGuiContainer.y(GuiContainer.c.getMappings().hK, this.I));
    }

    public int v() {
        return MGuiContainer.G(GuiContainer.c.getMappings().hK, this.I);
    }

    public int p() {
        return MGuiContainer.B(GuiContainer.c.getMappings().hK, this.I);
    }

    public int x() {
        return MGuiContainer.i(GuiContainer.c.getMappings().hK, this.I);
    }

    public Slot getSlotAtPosition(int n, int n2) {
        return new Slot(MGuiContainer.a(GuiContainer.c.getMappings().hK, this.I, n, n2));
    }

    public int b() {
        return MGuiContainer.e(GuiContainer.c.getMappings().hK, this.I);
    }

    public GuiContainer(Object object) {
        super(object);
    }

    public Container getInventorySlots() {
        return new Container(GuiContainer.c.getMappings().hK.y(this.I));
    }
}

