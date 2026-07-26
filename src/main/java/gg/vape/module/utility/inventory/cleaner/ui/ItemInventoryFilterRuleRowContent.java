package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.ItemInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterRuleRowBase;
import gg.vape.module.utility.inventory.cleaner.ui.ItemFilterSelectionComponent;
import gg.vape.module.utility.inventory.cleaner.ui.ItemInventoryFilterRuleRow;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

class ItemInventoryFilterRuleRowContent
extends InventoryFilterRuleRowBase {
    private final ItemFilterSelectionComponent b;
    final ItemInventoryFilterRuleRow a;
    private final ColorAnimation R;
    private final ColorAnimation i;

    @Override
    public double x() {
        return 32.0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public double C() {
        return 32.0;
    }

    @Override
    public void onEnable() {
        this.R.J();
        this.i.J();
    }

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            this.R.J();
            this.i.J();
        }
    }

    @Override
    public void H() {
        GuiRenderPrimitives.B(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.R.getInterpolatedColor(), (float)(this.A() / 2.0));
        GuiRenderPrimitives.B(this.G$src$D$1b2f02a() + 1.0, this.n() + 1.0, this.A() - 2.0, this.L() - 2.0, this.i.getInterpolatedColor(), (float)(this.A() / 2.0) - 2.0f);
        this.b.K(this.G$src$D$1b2f02a());
        this.b.S(this.n());
        this.b.o(this.A());
        this.b.Y(this.L());
    }

    @Override
    public void p() {
        this.R.setStartColor(ItemInventoryFilterRuleRowContent.J.l);
        this.R.setEndColor(ItemInventoryFilterRuleRowContent.J.l.brighter());
        this.i.setStartColor(ItemInventoryFilterRuleRowContent.J.r);
        this.i.setEndColor(ItemInventoryFilterRuleRowContent.J.R);
    }

    public ItemInventoryFilterRuleRowContent(ItemInventoryFilterRuleRow itemInventoryFilterRuleRow, InventoryCleanerProfile inventoryCleanerProfile, ItemInventoryFilterRule itemInventoryFilterRule) {
        this.a = itemInventoryFilterRuleRow;
        this.R = new ColorAnimation(0.15, new Color(0, 0, 0, 0), ItemInventoryFilterRuleRowContent.J.l);
        this.i = new ColorAnimation(0.15, new Color(0, 0, 0, 0), ItemInventoryFilterRuleRowContent.J.l);
        this.b = new ItemFilterSelectionComponent(itemInventoryFilterRule);
        this.p();
        this.o(true);
        this.H(this.b);
    }
}

