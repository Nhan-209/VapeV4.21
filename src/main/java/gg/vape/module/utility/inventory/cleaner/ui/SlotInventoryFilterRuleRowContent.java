package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterRuleRowBase;
import gg.vape.module.utility.inventory.cleaner.ui.ItemFilterSelectionComponent;
import gg.vape.module.utility.inventory.cleaner.ui.SlotInventoryFilterRuleRow;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

class SlotInventoryFilterRuleRowContent
extends InventoryFilterRuleRowBase {
    private final SlotInventoryFilterRule R;
    private final InventoryCleanerProfile o;
    private final ColorAnimation O;
    private final ColorAnimation G;
    final SlotInventoryFilterRuleRow Q;
    private final ItemFilterSelectionComponent a;

    SlotInventoryFilterRuleRowContent(SlotInventoryFilterRuleRow slotInventoryFilterRuleRow, InventoryCleanerProfile inventoryCleanerProfile, SlotInventoryFilterRule slotInventoryFilterRule) {
        this.Q = slotInventoryFilterRuleRow;
        this.G = new ColorAnimation(0.15, new Color(0, 0, 0, 0), SlotInventoryFilterRuleRowContent.J.l);
        this.O = new ColorAnimation(0.15, new Color(0, 0, 0, 0), SlotInventoryFilterRuleRowContent.J.K);
        this.o = inventoryCleanerProfile;
        this.R = slotInventoryFilterRule;
        this.a = new ItemFilterSelectionComponent(slotInventoryFilterRule);
        this.a.K(true);
        this.p();
        this.o(true);
        this.H(this.a);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void p() {
        this.G.setStartColor(this.R.q().j() ? SlotInventoryFilterRuleRowContent.J.t : SlotInventoryFilterRuleRowContent.J.l);
        this.G.setEndColor(this.R.q().j() ? SlotInventoryFilterRuleRowContent.J.l : SlotInventoryFilterRuleRowContent.J.y);
    }

    @Override
    public void H() {
        boolean bl = this.R.q().j();
        if (bl) {
            GuiRenderPrimitives.B(this.G$src$D$1b2f02a() + 1.0, this.n() + 1.0, this.A() - 2.0, this.L() - 2.0, new Color(0, 0, 0, 127), 2.0f);
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.G.getInterpolatedColor(), 4.0f, 1.0f, 1.0f);
            this.a.K(this.G$src$D$1b2f02a());
            this.a.S(this.n());
            this.a.o(this.A());
            this.a.Y(this.L());
            return;
        }
        GuiRenderPrimitives.B(this.G$src$D$1b2f02a() + 1.0, this.n() + 1.0, this.A() - 2.0, this.L() - 2.0, SlotInventoryFilterRuleRowContent.J.m, 2.0f);
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.G.getInterpolatedColor(), 4.0f, 1.0f, 1.0f);
        this.a.K(this.G$src$D$1b2f02a());
        this.a.S(this.n());
        this.a.o(this.A());
        this.a.Y(this.L());
        double d = 1.5;
        GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + this.A() / 2.0 - 0.75, this.n() + this.L() - 4.0, 1.5, 1.0, this.O.getInterpolatedColor());
        GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + this.A() / 2.0 - 0.75 - 3.0, this.n() + this.L() - 4.0, 1.5, 1.0, this.O.getInterpolatedColor());
        GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + this.A() / 2.0 - 0.75 + 3.0, this.n() + this.L() - 4.0, 1.5, 1.0, this.O.getInterpolatedColor());
    }

    @Override
    public void onEnable() {
        this.G.J();
        this.O.J();
    }

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            this.G.J();
            this.O.J();
        }
    }

    @Override
    public double x() {
        return 32.0;
    }

    @Override
    public double C() {
        return 32.0;
    }
}

