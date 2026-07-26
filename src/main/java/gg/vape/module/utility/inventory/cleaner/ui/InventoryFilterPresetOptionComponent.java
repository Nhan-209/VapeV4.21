package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterPresetData;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.utils.render.GuiRenderPrimitives;

public class InventoryFilterPresetOptionComponent
extends InteractiveComponent {
    private final ColorAnimation Q;
    private GlyphIconComponent b;
    private final TruncatedTextComponent I;
    private final InventoryFilterPresetData K;
    private boolean v;

    public boolean isBlatantMod() {
        return this.v;
    }

    public void g(boolean bl) {
        this.v = bl;
    }

    public GlyphIconComponent m$src$Lgg_vape_ui_click_component_GlyphIconComponent_$1ecfqwu() {
        return this.b;
    }

    InventoryFilterPresetOptionComponent(InventoryFilterPresetData inventoryFilterPresetData, boolean bl) {
        this.Q = new ColorAnimation(0.15, InventoryFilterPresetOptionComponent.J.B, InventoryFilterPresetOptionComponent.J.O);
        this.K = inventoryFilterPresetData;
        this.v = bl;
        this.o(true);
        this.I = new TruncatedTextComponent(inventoryFilterPresetData == null ? "No rule" : inventoryFilterPresetData.getName(), "...", 30.0, 0.8, InventoryFilterPresetOptionComponent.J.A, true);
        if (inventoryFilterPresetData != null) {
            this.b = new GlyphIconComponent("newedit", 5.0, 5.0, 8.0, 8.0, InventoryFilterPresetOptionComponent.J.A, InventoryFilterPresetOptionComponent.J.f, null);
        }
        this.H(this.I);
        if (inventoryFilterPresetData != null) {
            this.H(this.b);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void c() {
        this.I.K(this.G$src$D$1b2f02a() + 8.0);
        this.I.S(this.n());
        this.I.o(this.A() - 15.0);
        this.I.Y(this.L());
        this.I.D(this.I.A());
        if (this.b != null) {
            this.b.K(this.G$src$D$1b2f02a() + this.A() - this.b.A() - 5.0);
            this.b.S(this.n() + 3.5);
            this.b.Y(this.L());
            this.b.Z(!this.b.l$src$Ljava_util_List_$7yhdmw().isEmpty() && this.w$src$Z$e457mb());
        }
        this.Q.u(this.w$src$Z$e457mb());
        super.c();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (this.b != null && this.b.w$src$Z$e457mb()) {
            return;
        }
        super.g(guiMouseEvent);
    }

    @Override
    public void H() {
        if (this.v) {
            GuiRenderPrimitives.B(this.G$src$D$1b2f02a() + 4.0, this.n() + 0.5, this.A() - 8.0, this.L() - 1.0, InventoryFilterPresetOptionComponent.J.B, 2.0f);
        }
        if (this.w$src$Z$e457mb()) {
            GuiRenderPrimitives.B(this.G$src$D$1b2f02a() + 4.0, this.n() + 0.5, this.A() - 8.0, this.L() - 1.0, InventoryFilterPresetOptionComponent.J.M, 2.0f);
        }
    }
}

