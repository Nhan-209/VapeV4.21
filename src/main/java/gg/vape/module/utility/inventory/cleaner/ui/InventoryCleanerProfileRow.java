package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfileValue;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerProfileRowClickHandler;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerSlotRulePreview;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.utils.render.GuiRenderPrimitives;

public class InventoryCleanerProfileRow
extends GuiComponent {
    private final InventoryCleanerProfileValue i;
    private final PanelComponent b = new PanelComponent(0.0, 0.0);
    private final InventoryCleanerProfile R;
    private static final String o = "widthwrap";

    @Override
    public void u() {
        this.b.T$src$V$1wse0de();
    }

    public InventoryCleanerProfileRow(InventoryCleanerProfileValue inventoryCleanerProfileValue, InventoryCleanerProfile inventoryCleanerProfile, Runnable runnable) {
        this.i = inventoryCleanerProfileValue;
        this.C(inventoryCleanerProfileValue);
        this.R = inventoryCleanerProfile;
        this.b.k(true);
        this.b.d(false);
        this.j(new InventoryCleanerProfileRowClickHandler(this, inventoryCleanerProfile, inventoryCleanerProfileValue, runnable));
        this.M();
    }

    @Override
    public void H() {
        this.w(this.R.Y());
        double d = this.G$src$D$1b2f02a();
        this.getClass();
        double d2 = d + 5.0;
        double d3 = this.n();
        double d4 = this.A();
        this.getClass();
        GuiRenderPrimitives.d(d2, d3, d4 - (double)(5.0f * 2.0f), this.L(), this.R.equals(this.i.K()) ? InventoryCleanerProfileRow.J.y : (this.w$src$Z$e457mb() ? InventoryCleanerProfileRow.J.R : InventoryCleanerProfileRow.J.m));
        double d5 = this.G$src$D$1b2f02a();
        this.getClass();
        this.b.K(d5 + 5.0);
        this.b.S(this.n());
        double d6 = this.A();
        this.getClass();
        this.b.o(d6 - (double)(5.0f * 2.0f));
        this.b.Y(this.L());
        this.b.d(false);
        this.b.c();
    }

    private void M() {
        this.b.S();
        this.b.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(o);
        double d = 2.75;
        this.b.h(new SpacerComponent(2.75, 0.0), new Object[0]);
        for (int i = 0; i < 9; ++i) {
            SlotInventoryFilterRule slotInventoryFilterRule = this.R.I(i);
            InventoryCleanerSlotRulePreview inventoryCleanerSlotRulePreview = new InventoryCleanerSlotRulePreview(this.i, this.R, slotInventoryFilterRule);
            this.b.h(new PaddedComponent(1.0, 1.5, 0.0, 2.75, inventoryCleanerSlotRulePreview), new Object[0]);
        }
        this.b.H(true);
    }

    @Override
    public double C() {
        return 11.0;
    }


    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (this.b.w$src$Z$e457mb()) {
            this.b.D(guiMouseEvent);
        }
    }

    @Override
    public void F() {
        this.b.J();
    }
}

