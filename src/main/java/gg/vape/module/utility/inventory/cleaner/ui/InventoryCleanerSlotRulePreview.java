package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfileValue;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.ItemFilterSelectionComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GuiRenderPrimitives;

class InventoryCleanerSlotRulePreview
extends GuiComponent {
    private final InventoryCleanerProfile v;
    private final InventoryCleanerProfileValue i;
    private final SlotInventoryFilterRule b;
    private final ItemFilterSelectionComponent o;

    @Override
    public double C() {
        return 8.0;
    }

    public InventoryCleanerSlotRulePreview(InventoryCleanerProfileValue inventoryCleanerProfileValue, InventoryCleanerProfile inventoryCleanerProfile, SlotInventoryFilterRule slotInventoryFilterRule) {
        this.i = inventoryCleanerProfileValue;
        this.C(inventoryCleanerProfileValue);
        this.v = inventoryCleanerProfile;
        this.b = slotInventoryFilterRule;
        this.o = new ItemFilterSelectionComponent(slotInventoryFilterRule.q());
        this.o(true);
        this.H(this.o);
    }

    @Override
    public double x() {
        return 8.0;
    }

    @Override
    public void H() {
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A() + 1.0, this.L() + 1.0, InventoryCleanerSlotRulePreview.J.r);
        this.o.K(this.G$src$D$1b2f02a() + 0.5);
        this.o.S(this.n() + 0.5);
        this.o.o(this.A());
        this.o.Y(this.L());
        this.o.D(6.0f);
        this.o.s(6.0f);
        this.o.W(0.5f);
    }
}

