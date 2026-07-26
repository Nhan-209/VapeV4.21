package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterLogicalOperator;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

class InventoryFilterLogicalOperatorDividerComponent
extends GuiComponent {
    private final InventoryFilterLogicalOperator v;

    public InventoryFilterLogicalOperatorDividerComponent(InventoryFilterLogicalOperator inventoryFilterLogicalOperator) {
        this.v = inventoryFilterLogicalOperator;
    }

    @Override
    public double x() {
        return 50.0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public double C() {
        return 20.0;
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.65);
        String string = this.v.getName().toUpperCase();
        double d = 20.0;
        double d2 = this.G$src$D$1b2f02a();
        this.getClass();
        double d3 = d2 - 5.0 / 2.0;
        if (this.v == InventoryFilterLogicalOperator.AND) {
            GuiRenderPrimitives.C(d3 + d / 2.0, this.n() - 1.0, 1.0, 5.0, InventoryFilterLogicalOperatorDividerComponent.J.o);
            GuiRenderPrimitives.C(d3 + d / 2.0, this.n() + this.L() / 2.0 + smoothFontRenderer.d(string) + 2.0, 1.0, 5.0, InventoryFilterLogicalOperatorDividerComponent.J.o);
        }
        double d4 = this.n() + this.L() / 2.0 - smoothFontRenderer.d(string) / 2.0;
        this.getClass();
        double d5 = d4 - 5.0 / 2.0;
        double d6 = smoothFontRenderer.d(string);
        this.getClass();
        GuiRenderPrimitives.B(d3, d5, d, d6 + 5.0, InventoryFilterLogicalOperatorDividerComponent.J.R, 1.0f);
        smoothFontRenderer.W(string, d3 + d / 2.0, this.n() + this.L() / 2.0 - smoothFontRenderer.d(string) / 2.0, Color.WHITE);
    }
}

