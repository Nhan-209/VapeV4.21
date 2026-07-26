package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.module.utility.inventory.cleaner.ui.ItemFilterSelectionComponent;
import gg.vape.module.utility.inventory.cleaner.ui.MaterialFilterSelectionRow;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GuiRenderPrimitives;

class MaterialFilterSelectionRowContent
extends GuiComponent {
    private final ColorAnimation G;
    private final ItemFilterSelectionComponent b;
    final MaterialFilterSelectionRow o;

    @Override
    public void c() {
        this.b.K(this.G$src$D$1b2f02a());
        this.b.S(this.n());
        this.b.W(0.5f);
        this.b.D(8.0f);
        this.b.s(8.0f);
        this.b.o(this.A());
        this.b.Y(this.L());
        super.c();
    }

    @Override
    public void H() {
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.G.getInterpolatedColor());
    }

    @Override
    public void F() {
        if (this.w$src$Z$e457mb()) {
            this.G.J();
        }
    }

    @Override
    public double x() {
        return 14.0;
    }

    MaterialFilterSelectionRowContent(MaterialFilterSelectionRow materialFilterSelectionRow, ItemFilterSelection itemFilterSelection) {
        this.o = materialFilterSelectionRow;
        this.G = new ColorAnimation(0.15, MaterialFilterSelectionRowContent.J.z, MaterialFilterSelectionRowContent.J.M);
        this.b = new ItemFilterSelectionComponent(itemFilterSelection);
        this.o(true);
        this.H(this.b);
    }

    @Override
    public double C() {
        return 14.0;
    }

    @Override
    public void onEnable() {
        this.G.J();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

