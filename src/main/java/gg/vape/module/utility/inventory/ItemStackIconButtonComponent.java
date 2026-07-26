package gg.vape.module.utility.inventory;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ItemIconRenderer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.awt.Color;

public class ItemStackIconButtonComponent
extends InteractiveComponent {
    private ItemStack Q;
    private boolean GX;
    private double b = 27.5;
    private Color K;
    private Color I;
    private int v;

    @Override
    public void H() {
        double d = this.L() / 2.0;
        if (this.GX) {
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a() + 1.0, this.n() + 1.0, this.b - 1.0, this.b - 1.0, ItemStackIconButtonComponent.J.y, 2.0f, 1.0f, 2.0f);
        }
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 1.0, this.n() + 1.0, this.b - 2.0, this.b - 2.0, this.w$src$Z$e457mb() ? this.I : this.K);
        if (this.Q != null && this.Q.isNotNull()) {
            int n = 16;
            double d2 = (double)n / 2.0;
            float f = (float)(this.G$src$D$1b2f02a() + d - d2);
            float f2 = (float)(this.n() + d - d2);
            ItemIconRenderer.R(this.Q, f, f2, n, n);
        }
    }

    @Override
    public void u() {
    }

    public ItemStackIconButtonComponent(Color color, Color color2, ItemStack itemStack) {
        this.K = color;
        this.I = color2;
        if (itemStack != null && itemStack.isNotNull()) {
            this.Q = itemStack;
            this.v = itemStack.getItem().P();
            this.w(itemStack.x());
        }
    }

    public void P(int n) {
        this.v = n;
        Item item = Item.T(n);
        if (item.isNotNull()) {
            this.Q = ItemStack.S(item);
            this.w(this.Q.x());
        }
    }

    @Override
    public double C() {
        return this.b;
    }

    public void c(boolean bl) {
        this.GX = bl;
    }

    public void h(ItemStack itemStack) {
        this.Q = itemStack;
        if (itemStack != null && itemStack.isNotNull()) {
            this.w(itemStack.x());
        }
    }

    public ItemStackIconButtonComponent(Color color, Color color2, int n) {
        this.K = color;
        this.I = color2;
        this.P(n);
    }

    @Override
    public double x() {
        return this.b;
    }

    @Override
    public void I() {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void F() {
    }
}

