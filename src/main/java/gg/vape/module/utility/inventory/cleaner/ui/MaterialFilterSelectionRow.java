package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.module.utility.inventory.cleaner.ui.MaterialFilterSelectionRowContent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class MaterialFilterSelectionRow
extends GuiComponent {
    private final ItemFilterSelection K;
    private final MaterialFilterSelectionRowContent b;
    private static final String o = "newclose";
    private final ColorAnimation I;
    private final ColorAnimation i;

    @Override
    public void c() {
        this.b.K(this.G$src$D$1b2f02a() + 3.0);
        this.b.S(this.n() + 3.0);
        super.c();
        GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + this.A() - 4.0, this.n() + 3.0, 6.0, 1.0, this.I.getInterpolatedColor());
        ImageRenderer.E(this.i.getInterpolatedColor(), (float)(this.G$src$D$1b2f02a() + this.A() - 4.5), (float)(this.n() + 2.0), o, 7.0f, 7.0f, false);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public MaterialFilterSelectionRow(ItemFilterSelection itemFilterSelection) {
        this.I = new ColorAnimation(0.15, new Color(0, 0, 0, 0), MaterialFilterSelectionRow.J.d);
        this.i = new ColorAnimation(0.15, new Color(0, 0, 0, 0), Color.WHITE);
        this.K = itemFilterSelection;
        this.b = new MaterialFilterSelectionRowContent(this, itemFilterSelection);
        this.o(true);
        this.H(this.b);
    }

    @Override
    public double C() {
        return 18.0;
    }

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            this.I.J();
            this.i.J();
        }
    }

    @Override
    public double x() {
        return 17.0;
    }

    @Override
    public void onEnable() {
        this.I.J();
        this.i.J();
    }

    public ItemFilterSelection y$src$Lgg_vape_module_utility_inventory_cleaner_ItemFi$17dg1qx() {
        return this.K;
    }
}

